package com.fzu.teamwork.controller;


import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.model.UserExample;
import com.fzu.teamwork.service.LoginService;
import com.fzu.teamwork.service.LoginServiceImpl;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

@Slf4j
@RestController
public class LoginController {

    @Resource(name = "loginServiceImpl")
    LoginService loginService;

    //static
//    @PostMapping("/user")
//    public @ResponseBody AjaxResponse getUser(@RequestBody User user) {
//        log.info("{}",user);
//        UserVO userVO = new UserVO();
//        userVO.setUser(user);
//        user.setName("翁绍鸿");
//        user.setId(1);
//        userVO.getUser().setIdentity(UserIdentity.student);
//        AccountData accountData=new AccountData();
//        accountData.setLevel(10);
//        accountData.setScore(10);
//        accountData.setExperienceValue(99);
//        accountData.setFocusNum(16);
//        accountData.setQuestionNum(18);
//        accountData.setResponseNum(25);
//
//        userVO.setUser(user);
//        userVO.setAccountData(accountData);
//
//        return AjaxResponse.success(userVO);
//    }

    //login根据账号密码获取用户信息
    @PostMapping("/user")
    public @ResponseBody AjaxResponse getUser(@RequestBody User user){
        UserVO userVO=loginService.getUser(user);
        log.info("userVO{}",userVO);
        if(userVO.getUser().getMark()==0) {
            //账号不存在
            return AjaxResponse.error(400,"账号不存在");
        } else if(userVO.getUser().getMark()==1) {
            //密码错误
            return AjaxResponse.error(401,"密码错误");
        } else {
            //验证正确
            return AjaxResponse.success(userVO);
        }
    }
}
