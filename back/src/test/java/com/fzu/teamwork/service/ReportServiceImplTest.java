package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceImplTest extends LCH_testFather{

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;
    @Autowired
    private ResponseService responseService;
    @Resource
    private QuestionDao questionDao;
    @Resource
    private ResponseDao responseDao;

    @Autowired
    private ReportService reportService;

    private User user;//测试用户

    //测试获取投诉问题列表
    @Test
    void getReportQuestion() {
        List<Question> questionList;
        for(int i = 0; i < 10; i++){
            Question question = addQuestion(user.getId(), i).getQuestion();
            question.setReportNum(i*10);
            QuestionVO questionVO = questionService.convertToVO(question);
            questionService.updateQuestion(questionVO);
        }

        questionList = reportService.getReportQuestion();
        for(int i = 1; i < questionList.size(); i++){
            //检验是否按投诉数递减排序
            Assert.assertTrue(questionList.get(i-1).getReportNum() >= questionList.get(i).getReportNum());
        }
    }

    //测试获取被举报回复列表
    @Test
    void getReportResponse() {
        List<Response> responseList;
        for(int i = 0; i < 10; i++){
            Question question = addQuestion(user.getId(), i).getQuestion();
            Response response = addResponse(question.getId(), i).getResponse();
            response.setReportNum(i*10);
            ResponseVO responseVO = responseService.convertToVO(response);
            responseService.updateResponse(responseVO);
        }

        responseList = reportService.getReportResponse();
        for(int i = 1; i < responseList.size(); i++){
            //是否按投诉数递减排序
            Assert.assertTrue(responseList.get(i-1).getReportNum() >= responseList.get(i).getReportNum());
        }
    }

    //准备测试需要的数据（一个被举报问题和一条被举报的回复）
    @BeforeEach
    public void prepareData(){
        user = new User();
        user.setAccount("221701422");
        user.setIdentity(UserIdentity.student);
        user.setName("testWSH");
        user.setPassword(Encryptor.encrypt("873"));
        user.setIdCard(Encryptor.encrypt("360102199003077873"));
        int code = userService.addUser(user);
        if(code != 0){
            System.out.println("用户添加失败，状态码为:" + code);
        }
    }

    //删除测试添加的数据
    @AfterEach
    public void deleteData(){
        userService.deleteUsers(user.getId());//同时删除所属的问题和回复
    }

    //添加问题函数
    public QuestionVO addQuestion(int userId, int i){
        QuestionVO questionVO = new QuestionVO();
        Question question = new Question();
        questionVO.setQuestion(question);
        questionVO.setTitle("title" + i);
        questionVO.setContent("content" + i);
        question.setAuthorId(userId);
        question.setCreateTime(new Date());
        questionService.addQuestion(questionVO);
        return questionVO;
    }

    //添加回复
    public ResponseVO addResponse(int questionId, int i){
        ResponseVO responseVO = new ResponseVO();
        Response response = new Response();
        response.setQuestionId(questionId);
        response.setAuthorId(user.getId());
        response.setCreateTime(new Date());
        responseVO.setResponse(response);
        responseVO.setContent("content" +i);
        responseService.insertResponse(responseVO);
        return responseVO;
    }

    //测试对问题投诉的函数
    @Test
    void addQuestionReport() {
        //添加测试问题
        Question question = addQuestion(user.getId(), 1).getQuestion();
        int reportNum = question.getReportNum();
        Boolean result;
        ReportQuestion reportQuestion = new ReportQuestion();
        reportQuestion.setReportorId(user.getId());
        reportQuestion.setQuestionId(question.getId());
        //投诉问题
        reportQuestion.setFlag(1);
        result = reportService.addQuestionReport(reportQuestion);
        question = questionDao.selectByPrimaryKey(question.getId());//获取最新的问题数据
        //判断投诉是否成功
        Assert.assertTrue(result);
        //判断举报数是否+1
        Assert.assertEquals(reportNum + 1, (long)question.getReportNum());

        //取消投诉
        reportQuestion.setFlag(0);
        reportNum = question.getReportNum();//获取最新投诉数
        result = reportService.addQuestionReport(reportQuestion);//取消投诉
        question = questionDao.selectByPrimaryKey(question.getId());//获取最新的问题数据
        //判断投诉数是否-1
        Assert.assertEquals(reportNum - 1, (long)question.getReportNum());
        //判断取消是否成功
        Assert.assertTrue(result);

        int id;
        //投诉不存在问题
        while(true){
            id = (int) Math.random()*10000;
            if(questionDao.selectByPrimaryKey(id) == null){
                reportQuestion.setQuestionId(id);
                break;
            }
        }
        result = reportService.addQuestionReport(reportQuestion);
        reportNum = question.getReportNum();
        question = questionDao.selectByPrimaryKey(question.getId());//获取问题最新的数据
        //判断投诉是否失败
        Assert.assertFalse(result);
        //判断问题投诉数是否发生变化
        Assert.assertEquals(reportNum, (long)question.getReportNum());

        //删除测试问题
        questionService.deleteQuestionById(question.getId());
    }

    //测试对回复进行测试
    @Test
    void addResponseReport() {
        //添加被投诉回复所属的问题
        Question question = addQuestion(user.getId(),1).getQuestion();
        //添加对回复的投诉
        Response response = addResponse(question.getId(), 1).getResponse();
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setFlag(1);
        reportResponse.setReportorId(user.getId());
        reportResponse.setResponseId(response.getId());

        boolean result;
        int reportNum;

        //投诉回复
        reportNum = response.getReportNum();
        result = reportService.addResponseReport(reportResponse);
        //判断投诉是否成功
        Assert.assertTrue(result);
        //判断投诉数是否+1
        response = responseDao.selectByPrimaryKey(response.getId());//获取回复的最新数据
        Assert.assertEquals(reportNum + 1, (long)response.getReportNum());

        //取消投诉
        reportResponse.setFlag(0);
        reportNum = response.getReportNum();
        result = reportService.addResponseReport(reportResponse);
        //判断是否取消成功
        Assert.assertTrue(result);
        //判断投诉数是否-1
        response = responseDao.selectByPrimaryKey(response.getId());//获取最新的回复数据
        Assert.assertEquals(reportNum - 1, (long)response.getReportNum());

        //投诉不存在的回复
        responseService.deleteResponseById(response.getId());//删除该条回复
        //投诉
        result = reportService.addResponseReport(reportResponse);
        //判断投诉结果
        Assert.assertFalse(result);


        //删除新添加的测试回复
        responseService.deleteResponseById(response.getId());
        //删除新添加的测试问题
        questionService.deleteQuestionById(question.getId());
    }

}