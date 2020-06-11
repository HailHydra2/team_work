package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.ScoreNum;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.ResponsePage;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ResponseServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private LikeService likeService;

    @Resource
    private QuestionDao questionDao;

    private User user;
    private UserVO userVO;

    private Question question;
    private QuestionVO questionVO;

    private Response response;
    private ResponseVO responseVO;

    private int questionResponseNum;
    private int userResponnseNum;
    private int exprienceValue;
    private int score;
    private boolean result;

    @BeforeEach
    public void createTestData(){
        //新增用于测试的创建问题并回复点赞的用户
        user = new User();
        user.setAccount("221701518");
        user.setIdentity(UserIdentity.student);
        user.setName("testZJW");
        user.setPassword(Encryptor.encrypt("779"));
        user.setIdCard(Encryptor.encrypt("420102200403070779"));
        int code = userService.insertUser(user);
        if(code == 0){
            System.out.println("添加成功,用户id为：" + user.getId());
            userVO = userService.convertToUserVo(user);
        }else{
            System.out.println("用户添加失败，状态码为:" + code);
        }
        //新增用于测试的待回复问题
        question = new Question();
        questionVO = new QuestionVO();
        questionVO.setTitle("testTitle");
        questionVO.setContent("testContent");
        question.setAuthorId(user.getId());
        question.setCreateTime(new Date());
        questionVO.setQuestion(question);
        questionService.insertQuestion(questionVO);
        questionResponseNum = question.getResponseNum();
        //新增用于测试的待点赞回复
        response = new Response();
        responseVO = new ResponseVO();
        responseVO.setContent("testResponseContent");
        response.setQuestionId(question.getId());
        response.setAuthorId(user.getId());
        response.setCreateTime(new Date());
        responseVO.setResponse(response);
        userVO = userService.convertToUserVo(user);
        userResponnseNum = userVO.getAccountData().getResponseNum();
        score = userVO.getAccountData().getScore();
        exprienceValue = userVO.getAccountData().getExperienceValue();
        result = responseService.insertResponse(responseVO);
    }

    @AfterEach
    public void deleteTestData(){
        //删除所创建的用于测试的数据
        questionService.deleteQuestionById(question.getId());
        userService.deleteUsers(user.getId());
    }

    @Test
    void getResponseById(){
        //检验是否成功获取对应回复
        Assert.assertEquals(response.getId(),responseService.getResponseById(response.getId()).getId());
        //检验获取不存在的回复是否返回null
        Assert.assertEquals(null,responseService.getResponseById(response.getId() + 1));
    }

    @Test
    void getResponsePageByQuestionId() {
        Response response1 = new Response();
        ResponseVO responseVO1 = new ResponseVO();
        responseVO1.setContent("testResponseContent1");
        response1.setQuestionId(question.getId());
        response1.setAuthorId(user.getId());
        response1.setCreateTime(new Date());
        responseVO1.setResponse(response1);
        responseService.insertResponse(responseVO1);

        ResponsePage responsePage = new ResponsePage();
        responsePage.setCount(2);
        responsePage.setPageIndex(1);
        List<ResponseVO> responseVOList = new ArrayList<>();
        responseVO = responseService.convertToVO(response);
        responseVO1 = responseService.convertToVO(response1);
        responseVOList.add(responseVO);
        responseVOList.add(responseVO1);

        //检验获取的回复列表是否和插入的回复一致
        for (int i = 0;i < responseVOList.size();i++){
            Assert.assertEquals(responseVOList.get(i).getResponse().getId(),responseService.getResponsePageByQuestionId(question.getId(),responsePage).getResponses().get(i).getResponse().getId());
        }
    }

    @Test
    void deleteResponseById() {
        responseVO = responseService.convertToVO(response);
        responseService.insertResponse(responseVO);
        question = questionDao.selectByPrimaryKey(question.getId());
        questionResponseNum = question.getResponseNum();
        userVO = userService.convertToUserVo(user);//获取最新的用户数据
        userResponnseNum = userVO.getAccountData().getResponseNum();

        //检验删除回复是否成功并且返回1
        Assert.assertEquals(1,responseService.deleteResponseById(response.getId()));
        //检验问题的回复数是否-1
        question = questionDao.selectByPrimaryKey(question.getId());//获取最新的问题数据
        Assert.assertEquals(questionResponseNum - 1,(long)question.getResponseNum());
        //检验回复者的回复数是否-1
        userVO = userService.convertToUserVo(user);
        Assert.assertEquals(userResponnseNum - 1,(long)userVO.getAccountData().getResponseNum());
        //检验删除不存在的回复是否返回0
        Assert.assertEquals(0,responseService.deleteResponseById(response.getId()+1));
    }

    @Test
    void deleteResponseList() {
        Response response1 = new Response();
        ResponseVO responseVO1 = new ResponseVO();
        responseVO1.setContent("testResponseContent1");
        response1.setQuestionId(question.getId());
        response1.setAuthorId(user.getId());
        response1.setCreateTime(new Date());
        responseVO1.setResponse(response1);
        responseService.insertResponse(responseVO1);

        Response response2 = new Response();
        ResponseVO responseVO2 = new ResponseVO();
        responseVO2.setContent("testResponseContent2");
        response2.setQuestionId(question.getId());
        response2.setAuthorId(user.getId());
        response2.setCreateTime(new Date());
        responseVO2.setResponse(response2);
        responseService.insertResponse(responseVO2);

        List<Integer> list = new ArrayList<>();
        list.add(response.getId());
        list.add(response1.getId());

        userVO = userService.convertToUserVo(user);
        userResponnseNum = userVO.getAccountData().getResponseNum();
        //检验是否成功批量删除所有回复（所有回复都存在）
        Assert.assertEquals(0,responseService.deleteResponseList(list).size());
        //检验回复者的回复数是否减少对应的删除数
        userVO = userService.convertToUserVo(user);
        Assert.assertEquals(userResponnseNum - list.size(),(long)userVO.getAccountData().getResponseNum());
        //检验是否成功批量删除所有回复（部分回复不存在）
        list.add(response2.getId());
        List<Integer> list1 = responseService.deleteResponseList(list);
        Assert.assertEquals(2,list1.size());
        //检验回复者的回复数是否减少对应的删除数
        userVO = userService.convertToUserVo(user);
        Assert.assertEquals(userResponnseNum - list.size(),(long)userVO.getAccountData().getResponseNum());
        //检验返回的不存在的回复ID是否一致
        for (int i = 0;i < list1.size();i++){
            Assert.assertEquals(list.get(i),list1.get(i));
        }
    }

    @Test
    void insertResponse() {
        //检验回复是否成功创建
        Assert.assertEquals(response.getId(),responseService.getResponseById(response.getId()).getId());
        //检验被回复的问题的回复数是否+1
        question = questionDao.selectByPrimaryKey(question.getId());//获取该问题最新的数据
        Assert.assertEquals(questionResponseNum + 1,(long)question.getResponseNum());
        //检验回复者的回复数是否+1
        userVO = userService.convertToUserVo(user);
        Assert.assertEquals(userResponnseNum + 1,(long)userVO.getAccountData().getResponseNum());
        //检验经验是否正确添加
        Assert.assertEquals(exprienceValue + ScoreNum.RESPONSE_QUESTION_EXPERIENCE,(long)userVO.getAccountData().getExperienceValue());
        //检验积分是否正确添加
        Assert.assertEquals(score + ScoreNum.RESPONSE_QUESTION_SCORE,(long)userVO.getAccountData().getScore());
        //检验是否返回true
        Assert.assertEquals(true,result);
        //检验回复不存在的问题时是否返回true
        response.setQuestionId(question.getId() + 1);
        responseVO = responseService.convertToVO(response);
        result = responseService.insertResponse(responseVO);
        Assert.assertEquals(false,result);
    }

    @Test
    void getResponsePageBeReported(){
        //第一条被举报的回复，被举报一次
        ReportResponse reportResponse1 = new ReportResponse();
        reportResponse1.setResponseId(response.getId());
        reportResponse1.setReportorId(user.getId());
        reportResponse1.setFlag(1);
        reportService.insertResponseReport(reportResponse1);
        //第二条被举报的回复，被举报两次
        Response response1 = new Response();
        ResponseVO responseVO1 = new ResponseVO();
        responseVO1.setContent("testResponseContent1");
        response1.setQuestionId(question.getId());
        response1.setAuthorId(user.getId());
        response1.setCreateTime(new Date());
        responseVO1.setResponse(response1);
        responseService.insertResponse(responseVO1);
        ReportResponse reportResponse2 = new ReportResponse();
        reportResponse2.setResponseId(response1.getId());
        reportResponse2.setReportorId(user.getId());
        reportResponse2.setFlag(1);
        reportService.insertResponseReport(reportResponse2);
        reportService.insertResponseReport(reportResponse2);
        //检验被举报两次的回复是否排在被举报一次的回复的前面,预期第一个回复的举报数是2第二个回复的举报数是1
        List<Response> responseList = reportService.getReportResponse();
        for(int i = 1; i < responseList.size(); i++){
            Assert.assertTrue(responseList.get(i-1).getReportNum()
                    > responseList.get(i).getReportNum());
        }
    }

    @Test
    void convertToVO(){
        //检验回复是否匿名成功
        response.setAnonymous(1);
        responseVO = responseService.convertToVO(response);
        responseService.insertResponse(responseVO);
        Assert.assertEquals("匿名用户",responseService.convertToVO(responseService.getResponseById(response.getId())).getAuthorName());
    }

    @Test
    void addListRelationToUid(){
        List<ResponseVO> responseVOList = new ArrayList<>();
        responseVO = responseService.convertToVO(response);

        Response response1 = new Response();
        ResponseVO responseVO1 = new ResponseVO();
        responseVO1.setContent("testResponseContent1");
        response1.setQuestionId(question.getId());
        response1.setAuthorId(user.getId());
        response1.setCreateTime(new Date());
        responseVO1.setResponse(response1);
        responseService.insertResponse(responseVO1);

        Likes likes = new Likes();
        likes.setResponseId(response.getId());
        likes.setFlag(1);
        likes.setUserId(user.getId());
        likeService.insertLikeInfo(likes);

        ReportResponse reportResponse1 = new ReportResponse();
        reportResponse1.setResponseId(response1.getId());
        reportResponse1.setReportorId(user.getId());
        reportResponse1.setFlag(1);
        reportService.insertResponseReport(reportResponse1);

        responseVO = responseService.convertToVO(response);
        responseVO1 = responseService.convertToVO(response1);
        responseVOList.add(responseVO);
        responseVOList.add(responseVO1);
        responseService.addListRelationToUid(responseVOList,user.getId());

        //检验点赞关系是否添加成功
        Assert.assertEquals(1,responseVOList.get(0).getLike());
        //检验举报关系是否添加成功
        Assert.assertEquals(true,responseVOList.get(1).isDoesReported());
    }
}