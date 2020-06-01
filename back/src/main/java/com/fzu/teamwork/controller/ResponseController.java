package com.fzu.teamwork.controller;

import com.fzu.teamwork.annoation.AdminLimit;
import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Response;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.ResponseService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.ErrorStatus;
import com.fzu.teamwork.view.ResponsePage;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.UserVO;
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

    @Resource(name = "userServiceImpl")
    UserService userService;


    //获取问题的回复列表，id是所属问题id,uid是当前用户id（具体实现接口）
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/responses/{id}/{uid}")
    public @ResponseBody  AjaxResponse getResponsePage
    (@PathVariable int id, @PathVariable int uid, @RequestBody ResponsePage page){
        //获取需要的分页
        responseService.getResponsePageByQuestionId(id,page);
        //将page中的responseVO列表与用户uid关联（是否点过赞/点灭，投诉过）
        responseService.addListRelationToUid(page.getResponses(),uid);
        return AjaxResponse.success(page);
    }


    //根据回复id删除回复记录（实现接口）
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @DeleteMapping("/response/{id}")
    public @ResponseBody AjaxResponse deleteResponse(@PathVariable int id){
        if(responseService.deleteResponseById(id) > 0){//删除成功
            return AjaxResponse.success("删除成功");
        }else{//删除问题不存在
            return AjaxResponse.error(ErrorStatus.RESPONSE_NOT_EXIT,"删除失败，该回复不存在");
        }
    }

    //获取编号为id的回复（实现接口）
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @GetMapping("/response/{id}")
    public @ResponseBody AjaxResponse getResponse(@PathVariable int id){
        Response response = responseService.getResponseById(id);
        if(response != null){
            ResponseVO responseVO = responseService.convertToVO(response);
            return AjaxResponse.success(responseVO);
        }else {
            return AjaxResponse.error(ErrorStatus.RESPONSE_NOT_EXIT,"编号为id的回复不存在");
        }
    }

    //批量删除回复
    @LoginToken//需要登录
    @AdminLimit//需要管理员权限
    @DeleteMapping("responses")
    public @ResponseBody AjaxResponse deleteResponseList(@RequestBody List<Integer> responseIdList){
        List<Integer> failedList = new ArrayList<>();//删除失败（不存在）回复id列表
        failedList = responseService.deleteResponseList(responseIdList);
        String message = "成功删除" + (responseIdList.size() - failedList.size()) + "条回复";
        if(failedList.size() == 0){//全部删除成功
            return AjaxResponse.success(message);
        }else{//部分问题删除失败
            message += ",id为 [";
            for(int id : failedList){
                message += (id + ",");
            }
            message += "]的回复不存在";
            return AjaxResponse.error(ErrorStatus.RESPONSE_NOT_EXIT, message);
        }
    }

    //回复问题
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/response")
    public @ResponseBody AjaxResponse insertResponse(@RequestBody ResponseVO responseVO) {
        if(responseService.insertResponse(responseVO) == true) {//回复成功
            User user = userService.getUserById(responseVO.getResponse().getAuthorId());
            UserVO userVO = userService.convertToUserVo(user);
            return AjaxResponse.success(userVO);
        }else{//回复失败，回复问题不存在
            return AjaxResponse.error(ErrorStatus.QUESTION_NOT_EXIT, "您回复的问题已被删除");
        }
    }

    //前端有个组件会多调用一次接口，暂时找到好的解决方法，写了下面这个不做任何处理的接口给第二次调用
    @PostMapping("/test")
    public void test(){

    }
}
