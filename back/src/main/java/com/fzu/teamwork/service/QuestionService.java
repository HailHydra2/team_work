package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;

public interface QuestionService {

    //根据question的id主键获取问题
    public Question getQuestionById(int id);

    //将question对象转换为QuestionVO对象
    public QuestionVO convertToVO(Question question);

    //更新问题数据
    public Question updateQuestion(QuestionVO question);

    //增加问题
    public UserVO addQuestion(QuestionVO questionVO);

    //获取问题的某个分页
    public QuestionPage getQuestionPage(QuestionPage questionPage);

    //获取用户问题的某个分页
    public QuestionPage getQuestionPage(String userId, QuestionPage questionPage);

    //获取用户关注的问题分页
    public QuestionPage getQuestionPageByIdList(String userId, QuestionPage questionPage);

    //获取用户回答过的问题分页
    public QuestionPage getResponseQuestion(String userId, QuestionPage questionPage);

    //根据问题ID删除单个问题
    public void deleteQuestionById(String userId);

    //根据问题ID批量删除问题
    public void deleteQuestionsById(int[] questionIdList);
}
