package com.fzu.teamwork.controller;

import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Message;
import com.fzu.teamwork.model.Response;
import com.fzu.teamwork.view.ResponsePage;
import com.fzu.teamwork.view.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class ResponseController {

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

    @DeleteMapping("/responses/{id}")
    public @ResponseBody AjaxResponse deleteResponse(@PathVariable int id){
        System.out.println("删除" + id + "号response");
        return AjaxResponse.success();
    }


}
