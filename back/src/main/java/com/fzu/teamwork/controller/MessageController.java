package com.fzu.teamwork.controller;


import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.view.MessagePage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MessageController {

    @GetMapping("/userMessages/{uid}")
    public @ResponseBody
    AjaxResponse getMessagePage(@PathVariable int uid, @RequestBody MessagePage page){

        return AjaxResponse.success();
    }
}
