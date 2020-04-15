package com.fzu.teamwork.controller;


import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
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

    @Resource
    private UserDao userDao;

    UserServiceImpl userServiceImpl=new UserServiceImpl();

    @GetMapping("/users")
    public ArrayList<User> getUser(){
        return userServiceImpl.getUsers();
    }
/*
    @GetMapping("/users")
    public ArrayList<User> getUser(){

        ArrayList<User> userArrayList=new ArrayList<>();

        for(int i=1;i<=2;i++)
        {
            System.out.println("iiiiiii="+i);
            userArrayList.add(userDao.selectByPrimaryKey(i));
        }
        return userArrayList;
    }
*/

    @DeleteMapping("/users/{id}")
    public @ResponseBody AjaxResponse deleteUser(@PathVariable int id){
        //userServiceImpl.deleteUsers(id);
        return AjaxResponse.success();
    }

    @PostMapping("/users")
    public @ResponseBody AjaxResponse addUser(@RequestBody User user){
        //userServiceImpl.addUsers(user);
        return AjaxResponse.success();
    }


    //修改密码
    @PutMapping("/user")
    public @ResponseBody AjaxResponse updatePassword(@RequestBody User user){

        return AjaxResponse.success();
    }
}
