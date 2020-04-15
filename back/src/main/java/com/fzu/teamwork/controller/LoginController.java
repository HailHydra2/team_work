package com.fzu.teamwork.controller;


import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.model.UserExample;
import com.fzu.teamwork.service.LoginServiceImpl;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.*;

@Slf4j
@RestController
public class LoginController {


    @GetMapping("/user")
    public @ResponseBody AjaxResponse getUser(@RequestBody User user){
        LoginServiceImpl loginServiceImpl=new LoginServiceImpl();
        //System.out.println(loginServiceImpl.getUser(user));
        return AjaxResponse.success(loginServiceImpl.getUser(user));
    }



/*
    @GetMapping("/user")
    public @ResponseBody AjaxResponse getUser(@RequestBody User user){
        //从数据库中找到对应的user
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andAccountEqualTo(user.getAccount());
        List<User> users=userDao.selectByExample(userExample);

        System.out.println("hello");

        //因为列表唯一，就一个user
        User userFromDb=users.get(0);

        System.out.println("usser="+userFromDb.getAccount());
        //UserVO userVO=new UserVO();
        //userVO.setUser(userFromDb);
        //userVO.setAccountData();
        return AjaxResponse.success(userFromDb);
    }
*/


}
