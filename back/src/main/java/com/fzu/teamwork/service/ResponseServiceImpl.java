package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.Response;
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
    private ResponsePage page;

    @Resource
    ResponseDao responseDao;

    @Resource(name = "contentServiceImpl")
    ContentService contentService;

    //分页获取对应问题的回复列表
    @Override
    public ResponsePage getResponsePage(int questionId, ResponsePage p){
        page = p;
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
        for(Response response : responses){
            log.info("response:{}",response);
        }
        //将查询的List<Response>转化为List<ResponseVO>，并保存到page中
        page.setResponses(convertToVOList(responses));
        return page;
    }

    public List<Response> getResponses(){
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
