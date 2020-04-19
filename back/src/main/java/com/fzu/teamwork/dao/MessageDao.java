package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.Message;
import com.fzu.teamwork.model.MessageExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MessageDao {
    long countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    //获取object_id(被操作者)=uid子消息列表（map: {"uid":用户id, ""start":子列表开始位置索引, "count":子列表长度}）
    public List<Message> selectMessageByUid(Map<String, Integer> map);
}