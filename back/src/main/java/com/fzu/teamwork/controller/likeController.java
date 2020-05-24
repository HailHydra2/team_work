package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Likes;
import com.fzu.teamwork.service.LikeService;
import com.fzu.teamwork.util.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class likeController {

    @Resource(name = "likeServiceImpl")
    private LikeService likeService;

    //点赞回复/取消点赞/点灭回复
    @LoginToken//需要登录
    @UserLimit//需要普通用户权限
    @PostMapping("/likeResponse")
    public @ResponseBody AjaxResponse likeResponse(@RequestBody Likes like){
        if(likeService.insertLikeInfo(like) == true){//点赞/点灭成功
            return AjaxResponse.success();
        }else {//点赞回复不存在
            return AjaxResponse.error(ErrorStatus.RESPONSE_NOT_EXIT, "操作回复已被删除");
        }
    }
}
