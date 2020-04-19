package com.fzu.teamwork.controller;


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

    @GetMapping("/userMessages/{uid}")
    public @ResponseBody
    AjaxResponse getMessagePage(@PathVariable int uid, @RequestBody MessagePage page){
        page.setPageNum(10);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        page.setButtonList(list);
        page.setHasPrevious(false);
        page.setHasNext(true);
        List<Message> messages = new ArrayList<>();
        for(int i = 0; i < page.getCount(); i++){
            Message message = new Message();
            message.setDescription("message" + i);
            messages.add(message);
        }
        page.setMessages(messages);
        return AjaxResponse.success(page);
    }

    //获取某个用户的问题分页（实现接口）
    @GetMapping("/testUserMessage/{uid}")
    public @ResponseBody AjaxResponse testGetMessagePage(@PathVariable int uid, @RequestBody MessagePage page){
        messageService.getMessagePageByUid(uid, page);
        return AjaxResponse.success(page);
    }


    //*******************文档没写的接口
    //删除某个用户所有的消息
    @DeleteMapping("/testUserMessage/{uid}")
    public @ResponseBody AjaxResponse deleteUserMessage(@PathVariable int uid){
        int num = messageService.deleteUserMessage(uid);
        return AjaxResponse.success("您共删除"+ num +"条消息记录");
    }
}
