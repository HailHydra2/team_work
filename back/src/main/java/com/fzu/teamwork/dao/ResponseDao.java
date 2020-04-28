package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.Response;
import com.fzu.teamwork.model.ResponseExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ResponseDao {
    long countByExample(ResponseExample example);

    int deleteByExample(ResponseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Response record);

    int insertSelective(Response record);

    List<Response> selectByExample(ResponseExample example);

    Response selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Response record, @Param("example") ResponseExample example);

    int updateByExample(@Param("record") Response record, @Param("example") ResponseExample example);

    int updateByPrimaryKeySelective(Response record);

    int updateByPrimaryKey(Response record);

    //查询某个问题的子回复列表（map: {"questionId":问题id, ""start":子列表开始位置索引, "count":子列表长度}）
    List<Response> selectSublistByQuestionId(Map<String, Integer> map);

    //查询被举报回复的子列表(map: {"minReportedNum":最小被举报数})
    List<Response> selectSublistBeReported(Map<String, Integer> map);

    List<Integer> selectQuestionIdByAuthorId(int authorId);

    //根据int[]参数批量删除回复记录
    int deleteResponseInList(int[] arrays);
}