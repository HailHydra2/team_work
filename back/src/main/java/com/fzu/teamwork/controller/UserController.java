package com.fzu.teamwork.controller;


import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.LoginService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.service.UserServiceImpl;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@RestController
public class UserController {


    @Resource(name = "userServiceImpl")
    UserServiceImpl userService;

    //get users
    @GetMapping("/users")
    public ArrayList<User> getUser(){
        return userService.getUsers();
    }

    //delete user
    @DeleteMapping("/users/{id}")
    public @ResponseBody AjaxResponse deleteUser(@PathVariable int id){
        //userServiceImpl.deleteUsers(id);
        return AjaxResponse.success();
    }

    //add user
    @PostMapping("/users")
    public @ResponseBody AjaxResponse addUser(@RequestBody User user){
        //userServiceImpl.addUsers(user);
        return AjaxResponse.success();
    }

    //update password
    @PutMapping("/user")
    public @ResponseBody AjaxResponse updatePassword(@RequestBody User user){

        return AjaxResponse.success();
    }

}
