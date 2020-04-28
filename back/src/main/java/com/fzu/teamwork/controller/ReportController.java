package com.fzu.teamwork.controller;


import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.ReportQuestionDao;
import com.fzu.teamwork.dao.ReportResponseDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class ReportController {

    @Resource
    private ReportService reportService;

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
        return reportService.getReportQuestion();
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
        return reportService.getReportResponse();
    }

    //实现举报问题接口
    @PostMapping("questionReport")
    public @ResponseBody AjaxResponse addQuestionReport(@RequestBody ReportQuestion reportQuestion){
        reportService.addQuestionReport(reportQuestion);
        return AjaxResponse.success();
    }

    //实现举报回复接口
    @PostMapping("/responseReport")
    public @ResponseBody AjaxResponse addResponseReport(@RequestBody ReportResponse reportResponse){
        reportService.addResponseReport(reportResponse);
        return AjaxResponse.success();
    }
}
