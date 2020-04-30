package com.fzu.teamwork.model;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.view.QuestionPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//QuestionService通过时间获取问题列表的策略类
public class QuestionByTimeStrategy extends QuestionStrategy{

    //用户需要查询列表所属的分页信息
    private QuestionPage questionPage;
    private QuestionDao questionDao;

    public QuestionByTimeStrategy(QuestionPage page, QuestionDao dao){
        questionPage = page;
        questionDao = dao;
    }

    public List<Question> getQuestionList(){
        Map<String, Integer> map = new HashMap<>();
        //获取该页第一个回复的索引[（页码-1）*一页所包含的数量]
        int firstIndex = (questionPage.getPageIndex() - 1) * questionPage.getCount();
        //分页第一个索引
        map.put("start",firstIndex);
        //分页包含数量
        map.put("count",questionPage.getCount());
        List<Question> questionList = questionDao.selectQuestionByTime(map);
        return questionList;
    }
}
