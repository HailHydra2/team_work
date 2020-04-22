package com.fzu.teamwork.model;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.view.QuestionPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class getQuestion extends QuestionStrategy{
    private QuestionPage questionPage;

    private QuestionDao questionDao;

    public getQuestion(QuestionPage questionPage, QuestionDao questionDao){
        this.questionPage = questionPage;
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestionList() {
        Map<String, Integer> map = new HashMap<>();
        //获取该页第一个回复的索引[（页码-1）*一页所包含的数量]
        int firstIndex = (questionPage.getPageIndex() - 1) * questionPage.getCount();
        map.put("start",firstIndex);
        map.put("count",questionPage.getCount());
        List<Question> questionList = questionDao.selectQuestion(map);
        return questionList;
    }
}
