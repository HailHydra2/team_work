package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;

import java.util.List;

public interface QuestionService {
    public static int RESPONSE_TYPE = 0;//回复问题列表类型
    public static int QUESTION_TYPE = 1;//提问列表类型
    public static int ATTENTION_TYPE = 2;//关注问题列表类型

    //根据question的id主键获取问题
    public Question getQuestionById(int id);

    //将question对象转换为QuestionVO对象
    public QuestionVO convertToVO(Question question);

    //更新问题数据
    public Question updateQuestion(QuestionVO question);

    //增加问题
    public UserVO insertQuestion(QuestionVO questionVO);

    //获取问题的某个分页
    public QuestionPage getQuestionPage(QuestionPage questionPage);

    //获取用户问题的某个分页(0回复列表，1提问列表，2关注)
    public QuestionPage getQuestionPage(Integer userId, QuestionPage questionPage, int type);


    //根据问题ID删除单个问题(返回是否删除成功——数据库中是否存在该问题)
    public boolean deleteQuestionById(int questionId);


    //根据问题ID批量删除问题(返回删除失败的问题id列表)
    public List<Integer> deleteQuestionsById(List<Integer> questionIdList);

    //为QuestionVO添加与用户uid之间的关系（是否已经关注、投诉）
    public void addRelationToUId(QuestionVO questionVO, Integer uid);
}
