package com.fzu.teamwork.controller;


import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Message;
import com.fzu.teamwork.view.MessagePage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class MessageController {

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
}
