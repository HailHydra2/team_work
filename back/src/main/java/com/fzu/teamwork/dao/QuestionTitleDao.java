package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.QuestionTitle;
import com.fzu.teamwork.model.QuestionTitleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionTitleDao {
    long countByExample(QuestionTitleExample example);

    int deleteByExample(QuestionTitleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuestionTitle record);

    int insertSelective(QuestionTitle record);

    List<QuestionTitle> selectByExample(QuestionTitleExample example);

    QuestionTitle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuestionTitle record, @Param("example") QuestionTitleExample example);

    int updateByExample(@Param("record") QuestionTitle record, @Param("example") QuestionTitleExample example);

    int updateByPrimaryKeySelective(QuestionTitle record);

    int updateByPrimaryKey(QuestionTitle record);
}