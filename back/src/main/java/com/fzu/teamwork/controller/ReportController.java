package com.fzu.teamwork.controller;


import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.ReportQuestionDao;
import com.fzu.teamwork.dao.ReportResponseDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ReportController {
    private QuestionDao questionDao;
    private ResponseDao responseDao;
    private ReportQuestionDao reportQuestionDao;
    private ReportResponseDao reportResponseDao;

    @GetMapping("/questionReports")
    public List<Question> getReportQuestion(){
        List<Question> questionList= new ArrayList<>();
        Question question1 = new Question();
        question1.setId(1);
        question1.setAuthorId(1);
        question1.setReportNum(1);
        questionList.add(question1);
        Question question2 = new Question();
        question2.setId(2);
        question2.setAuthorId(1);
        question2.setReportNum(1);
        questionList.add(question2);
        return questionList;
    }

    //实现获取所有被举报的问题接口
    @GetMapping("/testQuestionReports")
    public List<Question> testGetReportQuestion(){
        List<Question> questionList = new ArrayList<>();
        List<Integer> questionIdList = reportQuestionDao.selectAllQuestion();
        for (int id:questionIdList){
            questionList.add(questionDao.selectByPrimaryKey(id));
        }
        return questionList;
    }

    @GetMapping("/responseReports")
    public List<Response> getReportResponse(){
        List<Response> responseList = new ArrayList<>();
        Response response1 = new Response();
        response1.setId(1);
        response1.setAuthorId(1);
        response1.setReportNum(1);
        responseList.add(response1);
        Response response2 = new Response();
        response2.setId(2);
        response2.setAuthorId(1);
        response2.setReportNum(1);
        responseList.add(response2);
        return responseList;
    }

    //实现获取所有被举报的回复接口
    @GetMapping("/testResponseReports")
    public List<Response> testGetReportResponse(){
        List<Response> responseList = new ArrayList<>();
        List<Integer> responseIdList = reportResponseDao.selectAllResponse();
        for(int id:responseIdList){
            responseList.add(responseDao.selectByPrimaryKey(id));
        }
        return responseList;
    }
}
