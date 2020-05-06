package com.fzu.teamwork.model;


import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.view.QuestionPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//获取用户回复的问题列表
public class QuestionBeResponse extends QuestionStrategy{
    private int userId;
    private QuestionPage questionPage;
    private QuestionDao questionDao;
    private Map<String, Integer> map;

    public QuestionBeResponse(int userId, QuestionPage questionPage, QuestionDao questionDao){
        this.userId = userId;
        this.questionPage = questionPage;
        this.questionDao = questionDao;
    }

    public List<Question> getQuestionList(){
        map = new HashMap<>();
        map.put("uid",userId);
        //获取该页第一个回复的索引[（页码-1）*一页所包含的数量]
        int firstIndex = (questionPage.getPageIndex() - 1) * questionPage.getCount();
        map.put("start",firstIndex);
        map.put("count",questionPage.getCount());
        List<Question> questionList = questionDao.selectResponseQuestion(map);
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
        int total = questionDao.selectResponseQuestionNum(map);
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
