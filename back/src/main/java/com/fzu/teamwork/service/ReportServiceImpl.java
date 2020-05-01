package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.ReportQuestionDao;
import com.fzu.teamwork.dao.ReportResponseDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.*;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class ReportServiceImpl implements ReportService{

    @Resource
    private QuestionDao questionDao;

    @Resource
    private ResponseDao responseDao;

    @Resource
    private ReportQuestionDao reportQuestionDao;

    @Resource
    private ReportResponseDao reportResponseDao;


    @Override
    public List<Question> getReportQuestion(){
        //创建查询条件
        QuestionExample example = new QuestionExample();
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andReportNumGreaterThanOrEqualTo(0);
        example.setOrderByClause("report_num desc");
        //返回的问题列表
        List<Question> questionList;
        questionList =questionDao.selectByExample(example);
        return questionList;
    }

    @Override
    public List<Response> getReportResponse(){
        //创建查询条件
        ResponseExample example = new ResponseExample();
        ResponseExample.Criteria criteria = example.createCriteria();
        criteria.andReportNumGreaterThanOrEqualTo(0);
        example.setOrderByClause("report_num desc");
        List<Response> responseList = responseDao.selectByExample(example);
        return responseList;
    }

    @Override
    public void addQuestionReport(ReportQuestion reportQuestion){
        //创建查询条件（投诉人id，被投诉问题id相同的记录）
        ReportQuestionExample example = new ReportQuestionExample();
        ReportQuestionExample.Criteria criteria = example.createCriteria();
        criteria.andReportorIdEqualTo(reportQuestion.getReportorId());
        criteria.andQuestionIdEqualTo(reportQuestion.getQuestionId());

        List<ReportQuestion> list = reportQuestionDao.selectByExample(example);
        if(list.size() == 1){
            //有投诉记录
            reportQuestion.setId(list.get(0).getId());
            reportQuestionDao.updateByPrimaryKey(reportQuestion);
        }else if(list.size() == 0){
            //没有投诉记录
            reportQuestionDao.insert(reportQuestion);
        }else{
            //有超过一条一样的记录
            log.info("错误，数据库中包含多于一条同一个用户对同一个问题的投诉");
        }
    }

    @Override
    public void addResponseReport(ReportResponse reportResponse){
        //创建查询条件
        ReportResponseExample example = new ReportResponseExample();
        ReportResponseExample.Criteria criteria = example.createCriteria();
        criteria.andReportorIdEqualTo(reportResponse.getReportorId());
        criteria.andResponseIdEqualTo(reportResponse.getResponseId());

        List<ReportResponse> list = reportResponseDao.selectByExample(example);

        if(list.size() == 1){
            //存在投诉记录
            reportResponse.setId(list.get(0).getId());
            reportResponseDao.updateByPrimaryKey(reportResponse);
        }else if(list.size() == 0){
            //不存在投诉记录
            reportResponseDao.insert(reportResponse);
        }else{
            //存在超过一条同一用户对同一回复的投诉
            log.info("错误，存在超过一条同一用户对同一回复的投诉");
        }
    }
}
