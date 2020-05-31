package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Response;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.ResponsePage;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.UserVO;
import org.omg.CORBA.INTERNAL;

import java.util.List;

public interface ResponseService {

    //获取某个回复分页(对应问题id所拥有的回复的某个页面)
    public ResponsePage getResponsePageByQuestionId(int questionId, ResponsePage page);
    //获取回复列表（投诉列表）
//    public List<Response> getResponsePageBeReported();

    //根据id删除回复记录，删除成功返回1,删除失败返回0
    public int deleteResponseById(int id);

    //获取编号为id的回复
    public Response getResponseById(int id);

    //将Response对象实体转换为ResponseVO对象实体
    public ResponseVO convertToVO(Response response);

    //根据ResponseVO更新数据(因为前端没有更改内容功能，暂时只更新response的数据，不更新内容)
    public void updateResponse(ResponseVO responseVO);

    //根据回复id数组批量删除回复(返回删除失败的id列表)
    public List<Integer> deleteResponseList(List<Integer> idList);

    //向数据库中插入一条回复记录(返回值为操作结果)
    public boolean insertResponse(ResponseVO responseVO);

    //为responseVO列表添加用户uid的关联信息（是否点过赞/点灭，投诉）
    public void addListRelationToUid(List<ResponseVO> list, int uid);
}
