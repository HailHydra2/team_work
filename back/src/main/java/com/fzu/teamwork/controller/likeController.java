package com.fzu.teamwork.controller;


import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Likes;
import com.fzu.teamwork.service.LikeService;
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
    @PostMapping("likeResponse")
    public @ResponseBody AjaxResponse likeResponse(@RequestBody Likes like){
        likeService.insertLikeInfo(like);
        return AjaxResponse.success();
    }
}
