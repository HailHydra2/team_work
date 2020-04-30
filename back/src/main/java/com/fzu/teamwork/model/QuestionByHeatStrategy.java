package com.fzu.teamwork.model;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.view.QuestionPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//QuestionService通过热度排序获取问题列表的策略类
public class QuestionByHeatStrategy extends QuestionStrategy{

    //获取列表所属的分页信息
    private QuestionPage questionPage;
    private QuestionDao questionDao;

    //构造函数
    public QuestionByHeatStrategy(QuestionPage page, QuestionDao dao){
        this.questionPage = page;
        this.questionDao = dao;
    }

    public List<Question> getQuestionList(){
        Map<String, Integer> map = new HashMap<>();
        //获取该页第一个回复的索引[（页码-1）*一页所包含的数量]
        int firstIndex = (questionPage.getPageIndex() - 1) * questionPage.getCount();
        //分页第一个索引
        map.put("start",firstIndex);
        //分页包含数量
        map.put("count",questionPage.getCount());
        List<Question> questionList = questionDao.selectQuestionByHeat(map);
        return questionList;
    }
}
