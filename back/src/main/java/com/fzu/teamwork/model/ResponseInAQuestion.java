package com.fzu.teamwork.model;

import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.service.ContentService;
import com.fzu.teamwork.view.ResponsePage;
import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//ResponseService用于获取某个问题回复列表的策略子类
@Log4j
public class ResponseInAQuestion extends ResponseStrategy{

    //所属问题id
    private int questionId;
    //查询问题列表的分页信息
    private ResponsePage page;

    private ResponseDao responseDao;

    public ResponseInAQuestion(int questionId, ResponsePage page, ResponseDao responseDao){
        this.questionId = questionId;
        this.page = page;
        this.responseDao = responseDao;
    }

    //获取回复列表
    public List<Response> getResponseList(){
        //log.info("page:{}",page);
        Map<String, Integer> map = new HashMap<>();
        map.put("questionId",questionId);
        //获取该页第一个回复的索引[（页码-1）*一页所包含的数量]
        int firstIndex = (page.getPageIndex() - 1) * page.getCount();
        map.put("start",firstIndex);
        map.put("count",page.getCount());
        //查找id=questionId列表中start到start+count的子列表
        List<Response> responses = responseDao.selectSublistByQuestionId(map);
        //List<Response> responses = responseDao.test();
        //responses = responseDao.selectByExample(null);
        return responses;
    }
}
