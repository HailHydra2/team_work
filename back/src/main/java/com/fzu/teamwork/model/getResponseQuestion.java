package com.fzu.teamwork.model;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.QuestionVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class getResponseQuestion extends QuestionStrategy{
    private int userId;
    private QuestionDao questionDao;
    private ResponseDao responseDao;

    public getResponseQuestion(int userId, QuestionDao questionDao){
        this.userId = userId;
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestionList() {
        List<Question> questionList = new ArrayList<>();
        List<Integer> questionIdList = new ArrayList<>();
        questionIdList = responseDao.selectQuestionIdByAuthorId(userId);
        for (int id:questionIdList){
            questionList.add(questionDao.selectByPrimaryKey(id));
        }
        return questionList;
    }
}
