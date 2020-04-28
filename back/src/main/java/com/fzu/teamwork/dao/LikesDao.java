package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.Likes;
import com.fzu.teamwork.model.LikesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LikesDao {
    long countByExample(LikesExample example);

    int deleteByExample(LikesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Likes record);

    int insertSelective(Likes record);

    List<Likes> selectByExample(LikesExample example);

    Likes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Likes record, @Param("example") LikesExample example);

    int updateByExample(@Param("record") Likes record, @Param("example") LikesExample example);

    int updateByPrimaryKeySelective(Likes record);

    int updateByPrimaryKey(Likes record);
}