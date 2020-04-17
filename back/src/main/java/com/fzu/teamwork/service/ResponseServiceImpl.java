package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.Response;
import com.fzu.teamwork.model.ResponseInAQuestion;
import com.fzu.teamwork.model.ResponseStrategy;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.ResponsePage;
import com.fzu.teamwork.view.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ResponseServiceImpl implements ResponseService{
    @Resource
    ResponseDao responseDao;

    @Resource(name = "contentServiceImpl")
    ContentService contentService;

    //回复分页
    private ResponsePage page;
    //所属问题id
    private int questionId;
    //获取问题列表的策略类
    private ResponseStrategy responseStrategy;



    //创建所需的列表获取策略类(type=1:获取某个问题回复列表)
    private void createResponseStrategy(int type){
        if(type == 1){//获取某个问题回复列表的策略对象
            //实例化对应策略对象
            responseStrategy = new ResponseInAQuestion(questionId, page, responseDao);
        }
    }

    //分页获取对应问题的回复列表
    @Override
    public ResponsePage getResponsePageByQuestionId(int questionId, ResponsePage page){
        this.questionId = questionId;
        this.page = page;
        //创建获取回复列表的策略
        createResponseStrategy(1);
        //获取对应的问题列表
        List<Response> responses = responseStrategy.getResponseList();
        //将查询的List<Response>转化为List<ResponseVO>，并保存到page中
        page.setResponses(convertToVOList(responses));
        //返回所需的回复page
        return page;
    }

    //获取回复列表（投诉列表）
    public ResponsePage getResponsePageBeReported(ResponsePage page){
        return null;
    }


    //将Response对象转换为ResponseVO对象
    public ResponseVO convertToVo(Response response){
        ResponseVO responseVO = new ResponseVO();
        //设置responseVO对象的response属性
        responseVO.setResponse(response);
        //根据外键contentId获取回复对象所对应的内容
        String content = contentService.getContentById(1);
        //将内容赋到responseVO的content属性
        responseVO.setContent(content);
        return  responseVO;
    }

    //将List<Response>转化为List<ResponseVO>
    public List<ResponseVO> convertToVOList(List<Response> responses){
        //返回responseVO列表
        List<ResponseVO> responseVOList = new ArrayList<>();
        for(Response response : responses){
            //将response转换为responseVO
            ResponseVO responseVO = convertToVo(response);
            //将转换后的responseVO添加到返回列表
            responseVOList.add(responseVO);
        }
        return responseVOList;
    }

    //删除编号为id的回复
    @Override
    public int deleteResponseById(int id){
        try{
            responseDao.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            log.info("回复记录删除失败");
            return -1;
        }
        return 1;
    }
}
