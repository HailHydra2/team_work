package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.view.QuestionVO;

public interface QuestionService {

    //根据question的id主键获取问题
    public Question getQuestionById(int id);

    //将question对象转换为QuestionVO对象
    public QuestionVO convertToVO(Question question);

    //更新问题数据
    public Question updateQuestion(QuestionVO question);
}
