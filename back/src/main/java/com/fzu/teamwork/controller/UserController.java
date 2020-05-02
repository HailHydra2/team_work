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

    //static delete
    @DeleteMapping("/users/{id}")
    public @ResponseBody AjaxResponse deleteUser(@PathVariable int id){
        return AjaxResponse.success();
    }

    //static update
    @PutMapping("/user")
    public @ResponseBody AjaxResponse updatePassword(@RequestBody User user){
        return AjaxResponse.success();
    }

    //获取所有用户
    @GetMapping("/users")
    public ArrayList<User> getUser_test()
    {
        return userService.getUsers();
    }

    //按id删除用户
    @DeleteMapping("/tusers/{id}")
    public @ResponseBody AjaxResponse deleteUser_test(@PathVariable int id){
        userService.deleteUsers(id);
        return AjaxResponse.success();
    }

    //按id数组批量删除用户
    @DeleteMapping("/tusers")
    public @ResponseBody AjaxResponse deleteUserAll_test(@RequestBody int[] userIdList){
        userService.deleteUsersAll(userIdList);
        return AjaxResponse.success();
    }

    //添加用户
    @PostMapping("/users")
    public @ResponseBody AjaxResponse addUser_test(@RequestBody User user){
        userService.addUser(user);
        return AjaxResponse.success();
    }

    //更新密码
    @PutMapping("/tuser")
    public @ResponseBody AjaxResponse updatePassword_test(@RequestBody User user){
        UserVO userVO=new UserVO();
        userVO.setUser(user);
        userService.updateUser(userVO);
        return AjaxResponse.success();
    }

}
