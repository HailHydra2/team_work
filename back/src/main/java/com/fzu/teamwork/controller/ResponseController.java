package com.fzu.teamwork.controller;

import com.fzu.teamwork.annoation.AdminLimit;
import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Response;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.ResponseService;
import com.fzu.teamwork.service.UserService;
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


    //*************这个接口是不是没用***************************
    //获取问题的回复列表，id是所属问题id（具体实现接口）
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/responses/{id}")
    public @ResponseBody  AjaxResponse testGetResponsePage(@PathVariable int id, @RequestBody ResponsePage page){
        responseService.getResponsePageByQuestionId(id,page);
        return AjaxResponse.success(page);
    }

    //获取问题的回复列表，id是所属问题id,uid是当前用户id（具体实现接口）
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/responses/{id}/{uid}")
    public @ResponseBody  AjaxResponse testGetResponsePage(@PathVariable int id, @PathVariable int uid, @RequestBody ResponsePage page){
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
    public @ResponseBody AjaxResponse testDeleteResponse(@PathVariable int id){
        if(responseService.deleteResponseById(id) > 0){
            //删除成功
            return AjaxResponse.success();
        }else{
            return AjaxResponse.error(400,"数据库删除失败");
        }
    }

    //获取编号为id的回复（实现接口）
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @GetMapping("/response/{id}")
    public @ResponseBody AjaxResponse testGetResponse(@PathVariable int id){
        Response response = responseService.getResponseById(id);
        ResponseVO responseVO = responseService.convertToVO(response);
        if(responseVO != null){
            return AjaxResponse.success(responseVO);
        }else {
            return AjaxResponse.error(501,"编号为id的回复不存在");
        }
    }

    //批量删除回复
    @LoginToken//需要登录
    @AdminLimit//需要管理员权限
    @DeleteMapping("responses")
    public @ResponseBody AjaxResponse deleteResponseList(@RequestBody int[] responseIdList){
        int delNum = responseService.deleteResponseList(responseIdList);
        return AjaxResponse.success("成功删除记录" + delNum + "条");
    }

    //回复问题
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/response")
    public @ResponseBody AjaxResponse addResponse(@RequestBody ResponseVO responseVO) {
        responseService.insertResponse(responseVO);
        User user = userService.getUserById(responseVO.getResponse().getAuthorId());
        UserVO userVO = userService.convertToUserVo(user);
        return AjaxResponse.success(userVO);
    }

    @PostMapping("/test")
    public void test(){
//        log.info("o:{}", 0);
    }
}
