package com.fzu.teamwork.model;


import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.view.QuestionPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//获取用户回复的问题列表
public class QuestionBeResponse extends QuestionStrategy{
    private int userId;
    private QuestionPage questionPage;
    private QuestionDao questionDao;

    public QuestionBeResponse(int userId, QuestionPage questionPage, QuestionDao questionDao){
        this.userId = userId;
        this.questionPage = questionPage;
        this.questionDao = questionDao;
    }

    public List<Question> getQuestionList(){
        Map<String, Integer> map = new HashMap<>();
        map.put("uid",userId);
        //获取该页第一个回复的索引[（页码-1）*一页所包含的数量]
        int firstIndex = (questionPage.getPageIndex() - 1) * questionPage.getCount();
        map.put("start",firstIndex);
        map.put("count",questionPage.getCount());
        List<Question> questionList = questionDao.selectResponseQuestion(map);
        return questionList;
    }
}