package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.model.Response;

import java.util.List;

public interface ReportService {
    //获取被举报的问题
    public List<Question> getReportQuestion();

    //获取被举报的回复
    public List<Response> getReportResponse();
}
