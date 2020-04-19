package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Response;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.ResponsePage;
import com.fzu.teamwork.view.ResponseVO;

import java.util.List;

public interface ResponseService {

    //获取某个回复分页(对应问题id所拥有的回复的某个页面)
    public ResponsePage getResponsePageByQuestionId(int questionId, ResponsePage page);
    //获取回复列表（投诉列表）
    public List<Response> getResponsePageBeReported();

    //根据id删除回复记录，删除成功返回1,删除失败返回0
    public int deleteResponseById(int id);

    //获取编号为id的回复
    public ResponseVO getResponseById(int id);
}
