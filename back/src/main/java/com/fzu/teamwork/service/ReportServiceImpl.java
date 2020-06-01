package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.ReportQuestionDao;
import com.fzu.teamwork.dao.ReportResponseDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.MessageWay;
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

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;


    //获取被举报问题列表
    @Override
    public List<Question> getReportQuestion(){
        //创建查询条件
        QuestionExample example = new QuestionExample();
        QuestionExample.Criteria criteria = example.createCriteria();
        criteria.andReportNumGreaterThanOrEqualTo(1);
        example.setOrderByClause("report_num desc");
        //返回的问题列表
        List<Question> questionList;
        questionList =questionDao.selectByExample(example);
        return questionList;
    }

    //获取被举报回复列表
    @Override
    public List<Response> getReportResponse(){
        //创建查询条件
        ResponseExample example = new ResponseExample();
        ResponseExample.Criteria criteria = example.createCriteria();
        criteria.andReportNumGreaterThanOrEqualTo(1);
        example.setOrderByClause("report_num desc");
        List<Response> responseList = responseDao.selectByExample(example);
        return responseList;
    }

    //添加对问题的投诉记录（返回值为处理结果）
    @Override
    public boolean insertQuestionReport(ReportQuestion reportQuestion){
        Question question = questionDao.selectByPrimaryKey(reportQuestion.getQuestionId());
        if(question == null){//投诉问题不存在
            return false;
        }
        //创建查询条件（投诉人id，被投诉问题id相同的记录）
        ReportQuestionExample example = new ReportQuestionExample();
        ReportQuestionExample.Criteria criteria = example.createCriteria();
        criteria.andReportorIdEqualTo(reportQuestion.getReportorId());
        criteria.andQuestionIdEqualTo(reportQuestion.getQuestionId());

        List<ReportQuestion> list = reportQuestionDao.selectByExample(example);
        if(list.size() == 1){
            //有投诉记录
            reportQuestion.setId(list.get(0).getId());
            //更新举报信息更新举报记录
            reportQuestionDao.updateByPrimaryKey(reportQuestion);
        }else if(list.size() == 0){
            //没有投诉记录，插入举报记录
            reportQuestionDao.insert(reportQuestion);
        }

        //创建内部发送消息
        InternalMessage message = new InternalMessage();
        //操作者为举报人id
        message.setOperator_id(reportQuestion.getReportorId());
        //操作对象id是问题id
        message.setObject_id(reportQuestion.getQuestionId());
        //
        if(reportQuestion.getFlag() == 1){
            //举报,消息标志位设置为1
            message.setFlag(1);
        }else if(reportQuestion.getFlag() == 0){
            //取消举报，标志位设置为-1
            message.setFlag(-1);
        }
        //消息产生方式设为投诉问题
        message.setWay(MessageWay.REPORT_QUESTION);
        //发送消息
        messageService.updateInfoByMessage(message);
        return true;
    }

    //添加对回复的投诉(返回值为处理结果)
    @Override
    public boolean insertResponseReport(ReportResponse reportResponse){
        Response response = responseDao.selectByPrimaryKey(reportResponse.getResponseId());
        if(response == null){//被举报回复不存在
            return false;
        }
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
        }

        //创建内部传递消息
        InternalMessage message = new InternalMessage();
        //操作者为投诉人id
        message.setOperator_id(reportResponse.getReportorId());
        //操作对象是被投诉回复
        message.setObject_id(reportResponse.getResponseId());
        //操作方式:投诉问题
        message.setWay(MessageWay.REPORT_RESPONSE);
        if(reportResponse.getFlag() == 1){
            //创建投诉
            message.setFlag(1);
        }else if(reportResponse.getFlag() == 0){
            //取消投诉
            message.setFlag(-1);
        }
        //发送消息
        messageService.updateInfoByMessage(message);
        return true;
    }
}
