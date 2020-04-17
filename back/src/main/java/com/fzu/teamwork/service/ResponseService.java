package com.fzu.teamwork.service;

import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.ResponsePage;

public interface ResponseService {


    //获取问题的回复列表
    public ResponsePage getResponsePage(int questionId, ResponsePage page);
}
