package com.fzu.teamwork.model;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.QuestionTitleDao;
import com.fzu.teamwork.dao.TitleDao;
import com.fzu.teamwork.view.QuestionPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//QuestionService通过关键字查询问题列表
public class QuestionByKeywordStrategy extends QuestionStrategy{

    //存放要查询问题列表所属分页信息
    private QuestionPage questionPage;

    private QuestionDao questionDao;

    //查询条件
    private Map<String, Object> map;

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
        map = new HashMap<>();
        //获取该页第一个回复的索引[（页码-1）*一页所包含的数量]
        int firstIndex = (questionPage.getPageIndex() - 1) * questionPage.getCount();
        //分页第一个索引
        map.put("start", firstIndex);
        //分页包含数量
        map.put("count", questionPage.getCount());
        //查询关键字
        map.put("keyWord","%" + keyWord + "%");
        List<Question> questionList = questionDao.selectQuestionByKeyWord(map);
        getButtonList();
        return questionList;
    }

    //获取分页下面的按钮列表
    public void getButtonList(){
        //每页有几条数据
        int count = questionPage.getCount();
        //问题总条数
        if(questionDao == null){
            System.out.println("dao null");
        }
        System.out.println("map:" + map);
        int total = questionDao.selectQuestionNumByKeyWord(map);
        System.out.println("total:" + total);
        //当前页号
        int pageIndex = questionPage.getPageIndex();
        //总页数
        int maxIndex = total%count == 0 ? total/count : total/count +1;
        List<Integer> list = new ArrayList<>();
        //按钮的最后一个是当前页+5/最大页
        int lastIndex = (pageIndex + 5 > maxIndex) ? maxIndex : pageIndex + 5;
        //按钮列表的第一个
        int startIndex;
        if(lastIndex == pageIndex + 5){//最后一个没到最后一页，第一个就是当前页
            startIndex = pageIndex;
        }else{//最后一个是最后一页，第一个是
            int t = 5 - (maxIndex - pageIndex + 1);//前面要补几个按钮
            startIndex = pageIndex - t > 0 ? pageIndex - t : 1;
        }


        for(int i = startIndex; i <= lastIndex; i++){
            list.add(i);
        }
        questionPage.setButtonList(list);
        questionPage.setHasPrevious(true);//是否有上一页
        questionPage.setHasNext(true);//是否还有下一页
        questionPage.setPageNum(maxIndex);//设置总页数
        if(pageIndex == 1){
            questionPage.setHasPrevious(false);
        }
        if(pageIndex == maxIndex){
            questionPage.setHasNext(false);
        }
    }
}
