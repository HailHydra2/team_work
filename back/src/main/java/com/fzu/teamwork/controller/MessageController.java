package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Message;
import com.fzu.teamwork.service.MessageService;
import com.fzu.teamwork.service.MessageServiceImpl;
import com.fzu.teamwork.view.MessagePage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class MessageController {

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;


    //获取某个用户的问题分页（实现接口）
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/userMessages/{uid}")
    public @ResponseBody AjaxResponse getMessagePage(@PathVariable int uid, @RequestBody MessagePage page){
        messageService.getMessagePageByUid(uid, page);
        return AjaxResponse.success(page);
    }

}
