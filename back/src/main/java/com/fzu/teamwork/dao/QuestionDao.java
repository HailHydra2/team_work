package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.model.QuestionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface QuestionDao {
    long countByExample(QuestionExample example);

    int deleteByExample(QuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    List<Question> selectByExample(QuestionExample example);

    Question selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    //获取问题列表（map: {"start":子列表开始位置索引, "count":子列表长度}）
    public List<Question> selectQuestion(Map<String, Integer> map);

    //获取用户问题列表（map: {"user":用户ID，"start":子列表开始位置索引, "count":子列表长度}）
    public List<Question> selectUserQuestion(Map<String, Integer> map);

    //获取用户关注问题列表（map: {"user":用户ID，"start":子列表开始位置索引, "count":子列表长度}）
    public List<Question> selectAttentionQuestion(Map<String, Integer> map);

    /**根据热度获取用户问题列表（map: {"start":子列表开始位置索引, "count":子列表长度}）
     * 热度：按照回复数递减排序
     */
    public List<Question> selectQuestionByHeat(Map<String, Integer> map);

    /**根据时间获取用户问题列表（map: {"start":子列表开始位置索引, "count":子列表长度}）
     * 按照时间由近到远排序
     */
    public List<Question> selectQuestionByTime(Map<String, Integer> map);

    /**
     * 根据标题关键字查询
     */
    public List<Question> selectQuestionByKeyWord(Map<String, Object> map);

    /**
     * 根据标题关键字查询
     */
    public Integer selectQuestionNumByKeyWord(Map<String, Object> map);

    //查询回复过的问题列表
    public List<Question> selectResponseQuestion(Map<String,Integer> map);

    //查询回复过的问题列表
    public Integer selectResponseQuestionNum(Map<String,Integer> map);

    /**
     * 根据类型查询筛选并按时间排序并分页
     */
    public List<Question> selectQuestionByKindAndHeat(Map<String, Object> map);

    /**
     * 根据类型查询筛选并按时间排序并分页
     */
    public List<Question> selectQuestionByKindAndDate(Map<String, Object> map);
}