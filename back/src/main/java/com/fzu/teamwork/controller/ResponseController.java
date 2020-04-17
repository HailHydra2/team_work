package com.fzu.teamwork.controller;

import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Response;
import com.fzu.teamwork.service.ResponseService;
import com.fzu.teamwork.view.ResponsePage;
import com.fzu.teamwork.view.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class ResponseController {

    @Resource(name = "responseServiceImpl")
    ResponseService responseService;

    //获取问题的回复列表，id是所属问题id(静态数据)
    @GetMapping("/responses/{id}")
    public @ResponseBody  AjaxResponse getResponsePage(@PathVariable int id, @RequestBody ResponsePage page) {
        page.setPageNum(10);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        page.setButtonList(list);
        page.setHasPrevious(false);
        page.setHasNext(true);
        List<ResponseVO> responses = new ArrayList<>();
        for(int i = 0; i < page.getCount(); i++){
            ResponseVO responseVO = new ResponseVO();
            responseVO.setContent("content" + i);
            responseVO.setQuality(0);
            responses.add(responseVO);
        }
        page.setResponses(responses);
        return AjaxResponse.success(responses);
    }

    @GetMapping("/response/{id}")
    public @ResponseBody AjaxResponse getResponse(@PathVariable int id){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setResponse(new Response());
        responseVO.getResponse().setId(id);
        responseVO.setContent("response1");
        responseVO.getResponse().setQuestionId(1);
        return AjaxResponse.success(responseVO);
    }

    //获取问题的回复列表，id是所属问题id
    @GetMapping("/testResponses/{id}")
    public @ResponseBody  AjaxResponse testGetResponsePage(@PathVariable int id, @RequestBody ResponsePage page){
        responseService.getResponsePageByQuestionId(id,page);
        return AjaxResponse.success(page);
    }


    @DeleteMapping("/response/{id}")
    public @ResponseBody AjaxResponse deleteResponse(@PathVariable int id){
        System.out.println("删除" + id + "号response");
        return AjaxResponse.success();
    }

    //根据回复id删除回复记录
    @DeleteMapping("/testResponse/{id}")
    public @ResponseBody AjaxResponse testDeleteResponse(@PathVariable int id){
        if(responseService.deleteResponseById(id) > 0){
            //删除成功
            return AjaxResponse.success();
        }else{
            return AjaxResponse.error(400,"数据库删除失败");
        }
    }

    //测试举报回复列表获取
    @GetMapping("/testResponseReports")
    public List<Response> getResponseReported(){
        return responseService.getResponsePageBeReported();
    }

}
