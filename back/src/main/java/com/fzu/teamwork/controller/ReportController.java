package com.fzu.teamwork.controller;


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
}
