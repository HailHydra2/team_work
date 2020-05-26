package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Attention;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.AttentionService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.ErrorStatus;
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
    public @ResponseBody AjaxResponse addAttention(@RequestBody Attention attention){
        User user = userService.getUserById(attention.getUserId());
        UserVO userVO = userService.convertToUserVo(user);
        int focusNum = userVO.getAccountData().getFocusNum();
        userVO = attentionService.insertAttention(attention);
        if(focusNum == userVO.getAccountData().getFocusNum()){//关注数没发生改变，关注问题不存在
            return AjaxResponse.error(ErrorStatus.QUESTION_NOT_EXIT,"关注问题已被删除", userVO);
        }else{//关注成功
            return AjaxResponse.success(userVO);
        }
    }
}
