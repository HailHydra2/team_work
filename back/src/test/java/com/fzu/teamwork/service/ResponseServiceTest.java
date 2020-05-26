package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Response;
import com.fzu.teamwork.view.ResponsePage;
import com.fzu.teamwork.view.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseServiceTest {
    @Autowired
    private ResponseService responseService;

    @Test
    public void getResponsePageByQuestionIdTest(){
        ResponsePage responsePage = new ResponsePage();
        responsePage.setPageIndex(5);
        responsePage.setCount(5);
        responseService.getResponsePageByQuestionId(30,responsePage);
    }

    @Test
    public void deleteResponseByIdTest(){
        responseService.deleteResponseById(38);
    }

    @Test
    public void getResponseByIdTest(){
        responseService.getResponseById(50);
    }

    @Test
    public void convertToVOTest(){
        Response response = new Response();
        response.setContentId(70);
        responseService.convertToVO(response);
    }

    @Test
    public void updateResponseTest(){
        ResponseVO responseVO = new ResponseVO();
        Response response = new Response();
        response.setId(50);
        response.setContentId(70);
        response.setDislikeNum(0);
        response.setLikeNum(0);
        response.setReportNum(0);
        response.setAuthorId(1);
        response.setCreateTime(new Date());
        response.setQuestionId(30);
        responseVO.setResponse(response);
        responseService.updateResponse(responseVO);
    }

    @Test
    public void deleteResponseListTest(){
//        int[] list = {39,40};
//        responseService.deleteResponseList(list);
    }

    @Test
    public void insertResponseTest(){
        ResponseVO responseVO = new ResponseVO();
        Response response = new Response();
        response.setId(50);
        response.setContentId(70);
        response.setDislikeNum(0);
        response.setLikeNum(0);
        response.setReportNum(0);
        response.setAuthorId(1);
        response.setCreateTime(new Date());
        response.setQuestionId(30);
        responseVO.setResponse(response);
        responseVO.setContent("content");
        responseService.insertResponse(responseVO);
    }

    @Test
    public void addListRelationToUidTest(){
        ResponseVO responseVO = new ResponseVO();
        Response response = new Response();
        response.setId(50);
        response.setContentId(70);
        response.setDislikeNum(0);
        response.setLikeNum(0);
        response.setReportNum(0);
        response.setAuthorId(1);
        response.setCreateTime(new Date());
        response.setQuestionId(30);
        responseVO.setResponse(response);
        List<ResponseVO> list = new ArrayList<>();
        list.add(responseVO);
        responseService.addListRelationToUid(list,1);
    }
}
