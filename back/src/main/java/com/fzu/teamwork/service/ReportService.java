package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.model.ReportQuestion;
import com.fzu.teamwork.model.ReportResponse;
import com.fzu.teamwork.model.Response;

import java.util.List;

public interface ReportService {
    //获取被举报的问题
    public List<Question> getReportQuestion();

    //获取被举报的回复
    public List<Response> getReportResponse();

    //举报问题
    public void addQuestionReport(ReportQuestion reportQuestion);

    //举报回复
    public void addResponseReport(ReportResponse reportResponse);
}
