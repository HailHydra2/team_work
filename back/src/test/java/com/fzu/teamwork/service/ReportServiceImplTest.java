package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.model.Report;
import com.fzu.teamwork.model.ReportQuestion;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.QuestionVO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceImplTest extends LCH_testFather{

    @Autowired
    private UserService userService;

    @Autowired
    private  QuestionService questionService;

    @Autowired
    private ReportService reportService;

    @Test
    void getReportQuestion() {
    }

    @Test
    void getReportResponse() {
    }

    @Test
    void addQuestionReport() {
        //我设置的reportquestion
        ReportQuestion reportQuestion=new ReportQuestion();

        //更新对应问题的投诉
        reportQuestion.setId(2);
        reportQuestion.setFlag(1);
        reportQuestion.setQuestionId(61);
        reportQuestion.setReportorId(2);
        //Assert.assertEquals("测试错误",true,reportService.addQuestionReport(reportQuestion));

        //问题id不存在的reportQuestion
        reportQuestion.setId(7);
        reportQuestion.setFlag(2);
        reportQuestion.setQuestionId(55555);
        reportQuestion.setReportorId(1);

        //不存在对应的问题
        Assert.assertEquals("测试错误",false,reportService.addQuestionReport(reportQuestion));


        //存在对应的问题
        reportQuestion.setQuestionId(57);
        Assert.assertEquals("测试错误",true,reportService.addQuestionReport(reportQuestion));

    }

    @Test
    void addResponseReport() {
    }
}