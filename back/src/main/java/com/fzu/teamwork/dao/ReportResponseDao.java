package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.ReportResponse;
import com.fzu.teamwork.model.ReportResponseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportResponseDao {
    long countByExample(ReportResponseExample example);

    int deleteByExample(ReportResponseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReportResponse record);

    int insertSelective(ReportResponse record);

    List<ReportResponse> selectByExample(ReportResponseExample example);

    ReportResponse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReportResponse record, @Param("example") ReportResponseExample example);

    int updateByExample(@Param("record") ReportResponse record, @Param("example") ReportResponseExample example);

    int updateByPrimaryKeySelective(ReportResponse record);

    int updateByPrimaryKey(ReportResponse record);
}