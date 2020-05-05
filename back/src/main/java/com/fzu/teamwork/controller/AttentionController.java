package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Attention;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.AttentionService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class AttentionController {

    @Resource(name = "attentionServiceImpl")
    AttentionService attentionService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    //关注/取消关注（实现类）
    @LoginToken//需要登录权限
    @UserLimit//老师和学生才有权限
    @PostMapping("/attention")
    public @ResponseBody AjaxResponse testAddAttention(@RequestBody Attention attention){
        UserVO userVO = attentionService.insertAttention(attention);
        return AjaxResponse.success(userVO);
    }
}
