package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.Attention;
import com.fzu.teamwork.model.AttentionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttentionDao {
    long countByExample(AttentionExample example);

    int deleteByExample(AttentionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Attention record);

    int insertSelective(Attention record);

    List<Attention> selectByExample(AttentionExample example);

    Attention selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Attention record, @Param("example") AttentionExample example);

    int updateByExample(@Param("record") Attention record, @Param("example") AttentionExample example);

    int updateByPrimaryKeySelective(Attention record);

    int updateByPrimaryKey(Attention record);

    //获取编号为uid用户的关注问题id列表
    List<Integer> getAttentionQuestionList(Integer uid);
}