package com.fzu.teamwork.model;

import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.view.ResponsePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//ResponseService用于获取举报回复列表的策略类
public class ResponseBeReported extends ResponseStrategy{

    //列表中被举报数的最小值（查找举报数超过几条的回复）
    private int minReportedNum;

    private ResponseDao  responseDao;

    //构造函数
    public ResponseBeReported(ResponseDao responseDao){
        this.responseDao = responseDao;
        minReportedNum = 10;//列表中被举报数的最小值（查找举报数超过几条的回复）
    }

    //获取回复列表
    public List<Response> getResponseList(){
        Map <String, Integer> map = new HashMap<>();
        map.put("minReportedNum",minReportedNum);
        List<Response> responses = responseDao.selectSublistBeReported(map);
        return responses;
    }
}
