package com.fzu.teamwork.service;

import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.ResponsePage;

public interface ResponseService {

    //获取回复列表(对应问题id所拥有的问题列表)
    public ResponsePage getResponsePageByQuestionId(int questionId, ResponsePage page);
    //获取回复列表（投诉列表）
    public ResponsePage getResponsePageBeReported(ResponsePage page);

    //根据id删除回复记录，删除成功返回1,删除失败返回0
    public int deleteResponseById(int id);
}
