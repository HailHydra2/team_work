package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.Title;
import com.fzu.teamwork.model.TitleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TitleDao {
    long countByExample(TitleExample example);

    int deleteByExample(TitleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Title record);

    int insertSelective(Title record);

    List<Title> selectByExample(TitleExample example);

    Title selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Title record, @Param("example") TitleExample example);

    int updateByExample(@Param("record") Title record, @Param("example") TitleExample example);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);

    //根据问题ID查找question_title表中对应的titleID
    int selectTitleByQuestionID(Integer questionID);

    //插入question_title表
    int insertQuestionTitle(Integer questionID, Integer titleID);

    //根据问题ID删除question_title表中对应的数据
    void deleteQuestionTitle(Integer questionID);
}