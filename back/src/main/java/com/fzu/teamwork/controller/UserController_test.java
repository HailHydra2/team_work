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
public class UserController_test {

    @Resource(name = "userServiceImpl_test")
    UserService userService;

    //get users
    @GetMapping("/tusers")
    public ArrayList<User> getUser()
    {
        return userService.getUsers();
    }

    //delete user
    @DeleteMapping("/tusers/{id}")
    public @ResponseBody AjaxResponse deleteUser(@PathVariable int id){
        userService.deleteUsers(id);
        return AjaxResponse.success();
    }

    //add user
    @PostMapping("/tusers")
    public @ResponseBody AjaxResponse addUser(@RequestBody User user){
        userService.addUsers(user);
        return AjaxResponse.success();
    }

    //update password
    @PutMapping("/tuser")
    public @ResponseBody AjaxResponse updatePassword(@RequestBody User user){
        UserVO userVO=new UserVO();
        userVO.setUser(user);
        userService.updateUser(userVO);
        return AjaxResponse.success();
    }
}
