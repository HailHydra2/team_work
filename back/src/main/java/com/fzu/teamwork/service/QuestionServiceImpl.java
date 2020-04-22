package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.view.QuestionVO;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

@Log4j
@Service
public class QuestionServiceImpl implements QuestionService{

    //根据question的id主键获取问题
    @Override
    public Question getQuestionById(int id){
        return null;
    }

    //将question对象转换为QuestionVO对象
    @Override
    public QuestionVO convertToVO(Question question){
        return null;
    }

    //更新问题数据
    @Override
    public Question updateQuestion(QuestionVO question){
        return null;
    }
}
