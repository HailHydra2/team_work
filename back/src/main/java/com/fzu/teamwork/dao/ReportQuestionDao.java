package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.ReportQuestion;
import com.fzu.teamwork.model.ReportQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportQuestionDao {
    long countByExample(ReportQuestionExample example);

    int deleteByExample(ReportQuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportQuestion record);

    int insertSelective(ReportQuestion record);

    List<ReportQuestion> selectByExample(ReportQuestionExample example);

    ReportQuestion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportQuestion record, @Param("example") ReportQuestionExample example);

    int updateByExample(@Param("record") ReportQuestion record, @Param("example") ReportQuestionExample example);

    int updateByPrimaryKeySelective(ReportQuestion record);

    int updateByPrimaryKey(ReportQuestion record);
}