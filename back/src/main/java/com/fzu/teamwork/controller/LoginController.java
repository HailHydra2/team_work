package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.PassToken;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.model.UserExample;
import com.fzu.teamwork.service.LoginService;
import com.fzu.teamwork.service.LoginServiceImpl;
import com.fzu.teamwork.service.TokenService;
import com.fzu.teamwork.util.ErrorStatus;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

@Slf4j
@RestController
public class LoginController {

    @Resource(name = "loginServiceImpl")
    LoginService loginService;

    @Autowired
    TokenService tokenService;


    //login根据账号密码获取用户信息
    @PassToken//没有权限限制
    @PostMapping("/user")
    public @ResponseBody AjaxResponse getUser(@RequestBody User user){
        UserVO userVO=loginService.login(user);

        if(userVO.getUser().getMark() == ErrorStatus.ACCOUNT_NOT_EXIT) {//账号不存在
            return AjaxResponse.error(ErrorStatus.ACCOUNT_NOT_EXIT,"账号不存在");
        } else if(userVO.getUser().getMark() == ErrorStatus.PASSWORD_ERROR) {//密码错误
            return AjaxResponse.error(ErrorStatus.PASSWORD_ERROR,"密码错误");
        } else {//验证正确
            return AjaxResponse.success(userVO);
        }
    }
}
