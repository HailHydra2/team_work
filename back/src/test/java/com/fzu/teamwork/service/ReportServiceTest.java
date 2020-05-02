package com.fzu.teamwork.service;

import com.fzu.teamwork.model.ReportQuestion;
import com.fzu.teamwork.model.ReportResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportServiceTest {
    @Autowired
    private ReportService reportService;

    @Test
    public void getReportQuestionTest(){
        reportService.getReportQuestion();
    }

    @Test
    public void getReportResponseTest(){
        reportService.getReportResponse();
    }

    @Test
    public void addQuestionReportTest(){
        ReportQuestion reportQuestion = new ReportQuestion();
        reportQuestion.setId(7);
        reportQuestion.setReportorId(2);
        reportQuestion.setQuestionId(30);
        reportQuestion.setFlag(0);
        reportService.addQuestionReport(reportQuestion);
    }

    @Test
    public void addResponseReportTest(){
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setId(7);
        reportResponse.setReportorId(2);
        reportResponse.setResponseId(50);
        reportResponse.setFlag(0);
        reportService.addResponseReport(reportResponse);
    }
}
