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

    //static
    @GetMapping("/users")
    public ArrayList<User> getUser(){
        ArrayList<User> userArrayList=new ArrayList<>();
        User u1=new User();
        u1.setAccount("221701316");
        u1.setName("lch");
        u1.setPassword("123456");
        u1.setIdCard("123");
        u1.setIdentity("student");
        u1.setPhoneNum("123456");
        User u2=new User();
        u2.setAccount("221701319");
        u2.setName("lll");
        u2.setPassword("123456");
        u2.setIdCard("123");
        u2.setIdentity("student");
        u2.setPhoneNum("123456");
        userArrayList.add(u1);
        userArrayList.add(u2);
        return userArrayList;
    }

    //获取所有用户
    @GetMapping("/tusers")
    public ArrayList<User> getUser_test()
    {
        return userService.getUsers();
    }

    //按id删除用户
    @DeleteMapping("/tusers/{id}")
    public @ResponseBody AjaxResponse deleteUser(@PathVariable int id){
        userService.deleteUsers(id);
        return AjaxResponse.success();
    }

    //添加用户
    @PostMapping("/tusers")
    public @ResponseBody AjaxResponse addUser(@RequestBody User user){
        userService.addUsers(user);
        return AjaxResponse.success();
    }

    //更新密码
    @PutMapping("/tuser")
    public @ResponseBody AjaxResponse updatePassword(@RequestBody User user){
        UserVO userVO=new UserVO();
        userVO.setUser(user);
        userService.updateUser(userVO);
        return AjaxResponse.success();
    }

}
