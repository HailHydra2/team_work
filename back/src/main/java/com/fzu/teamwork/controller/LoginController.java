package com.fzu.teamwork.controller;


import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.model.UserExample;
import com.fzu.teamwork.service.LoginService;
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

    @Resource(name = "loginServiceImpl")
    LoginService loginService;

    @GetMapping("/user")
    public @ResponseBody AjaxResponse getUser(@RequestBody User user){
        UserVO userVO=loginService.getUser(user);

        //no account
        if(userVO.getUser().getMark()==0)
        {
            return AjaxResponse.noaccount();
        }
        //error password
        else if(userVO.getUser().getMark()==1)
        {
            return AjaxResponse.errorpassword();
        }
        //success
        else
        {
            return AjaxResponse.success(userVO);
        }


    }
}
