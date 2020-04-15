package com.fzu.teamwork.controller;

import com.fzu.teamwork.dao.AccountDataDao;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class HelloController {

    @Resource
    private UserDao userDao;

    @Resource
    private AccountDataDao accountDataDao;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/addUser")
    public @ResponseBody AjaxResponse addUser(@RequestBody User user){
        userDao.insert(user);
        return AjaxResponse.success(user);
    }

    @PostMapping("/addaccount")
    public @ResponseBody AjaxResponse addUser(@RequestBody AccountData accountData){
        accountDataDao.insert(accountData);
        return AjaxResponse.success(accountData);
    }

    @GetMapping("/getUser/{id}")
    public @ResponseBody AjaxResponse getUser(@PathVariable int id){
        return AjaxResponse.success(userDao.selectByPrimaryKey(id));
    }
}
