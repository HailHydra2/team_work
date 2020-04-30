package com.fzu.teamwork.model;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.QuestionTitleDao;
import com.fzu.teamwork.dao.TitleDao;
import com.fzu.teamwork.view.QuestionPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//QuestionService通过关键字查询问题列表
public class QuestionByKeywordStrategy extends QuestionStrategy{

    //存放要查询问题列表所属分页信息
    private QuestionPage questionPage;

    private QuestionDao questionDao;

    private TitleDao titleDao;

    private QuestionTitleDao questionTitleDao;

    //构造函数
    public QuestionByKeywordStrategy(QuestionPage page, QuestionDao questionDao,
                                     TitleDao titleDao, QuestionTitleDao questionTitleDao){
        questionPage = page;
        this.questionDao = questionDao;
        this.titleDao = titleDao;
        this.questionTitleDao = questionTitleDao;
    }

    public List<Question> getQuestionList(){
        String keyWord = questionPage.getKeyWord();
        Map<String, Object> map = new HashMap<>();
        //获取该页第一个回复的索引[（页码-1）*一页所包含的数量]
        int firstIndex = (questionPage.getPageIndex() - 1) * questionPage.getCount();
        //分页第一个索引
        map.put("start", firstIndex);
        //分页包含数量
        map.put("count", questionPage.getCount());
        //查询关键字
        map.put("keyWord","%" + keyWord + "%");
        List<Question> questionList = questionDao.selectQuestionByKeyWord(map);
        return questionList;
    }
}
