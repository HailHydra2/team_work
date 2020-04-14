package com.fzu.teamwork.controller;


import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class LoginController {
    @GetMapping("/user")
    public @ResponseBody
    AjaxResponse getUser(@RequestBody User user){
        UserVO userVO=new UserVO(user);
        return AjaxResponse.success(userVO);
    }

}
