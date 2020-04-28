package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.ReportQuestionDao;
import com.fzu.teamwork.dao.ReportResponseDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.model.ReportQuestion;
import com.fzu.teamwork.model.ReportResponse;
import com.fzu.teamwork.model.Response;
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
        List<Question> questionList = new ArrayList<>();
        List<Integer> questionIdList = reportQuestionDao.selectAllQuestion();
        for (int id:questionIdList){
            questionList.add(questionDao.selectByPrimaryKey(id));
        }
        return questionList;
    }

    @Override
    public List<Response> getReportResponse(){
        List<Response> responseList = new ArrayList<>();
        List<Integer> responseIdList = reportResponseDao.selectAllResponse();
        for(int id:responseIdList){
            responseList.add(responseDao.selectByPrimaryKey(id));
        }
        return responseList;
    }

    @Override
    public void addQuestionReport(ReportQuestion reportQuestion){
        reportQuestionDao.insert(reportQuestion);
    }

    @Override
    public void addResponseReport(ReportResponse reportResponse){
        reportResponseDao.insert(reportResponse);
    }
}
