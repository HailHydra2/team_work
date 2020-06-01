package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.AdminLimit;
import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.LoginService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.service.UserServiceImpl;
import com.fzu.teamwork.util.ErrorStatus;
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

    //获取所有用户
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @GetMapping("/users")
    public List<User> getUser() {
        return userService.getUsers();
    }

    //按id删除用户
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @DeleteMapping("/user/{id}")
    public @ResponseBody AjaxResponse deleteUser(@PathVariable int id){
        if(userService.deleteUsers(id)){//删除成功
            return AjaxResponse.success("成功删除id为" + id + "的用户");
        }else {//删除失败
            return AjaxResponse.error(ErrorStatus.ACCOUNT_NOT_EXIT,
                    "id为" + id + "的用户已被其他管理员删除");
        }
    }

    //按id数组批量删除用户
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @DeleteMapping("/users")
    public @ResponseBody AjaxResponse deleteUserAll(@RequestBody List<Integer> userIdList){
        List<Integer> failedList;//删除失败的id列表
        failedList = userService.deleteUsersAll(userIdList);
        String message = "成功删除" + (userIdList.size() - failedList.size()) + "人";
        if(failedList.size() == 0){
            return AjaxResponse.success(message);
        }else{
            message += ", 失败" + failedList.size() + "人";
            return AjaxResponse.error(ErrorStatus.ACCOUNT_NOT_EXIT, message);
        }
    }

    //添加用户
    @PostMapping("/users")
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    public @ResponseBody AjaxResponse insertUser(@RequestBody User user){
        int code = userService.insertUser(user);//添加是否成功标志位（0：成功 其他：对应错误状态码）
        String message = "";//错误描述信息
        if(code == 0){//添加成功
            return AjaxResponse.success();
        }else if(code == ErrorStatus.ACCOUNT_ILLEGAL){//账号非法
            message = "添加用户账号非法（应为9位字母数字串组成）";
        }else if(code == ErrorStatus.ID_CARD_ILLEGAL){
            message = "添加用户身份证非法";
        }else if(code == ErrorStatus.ACCOUNT_HAS_EXIT){
            message = "添加失败，账户（学号）已被注册";
        }else if(code == ErrorStatus.ID_CARD_HAS_EXIT){
            message = "添加失败，身份证已经被注册";
        }
        return AjaxResponse.error(code,message);
    }

    //批量添加用户
    @PostMapping("/userList")
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    public @ResponseBody AjaxResponse addUsers(@RequestBody List<User> users){
        List<String> failedList = userService.addUsers(users);
        int total = users.size();
        int failedNum = failedList.size();
        if(failedList.size() == 0){//全部添加成功
            return AjaxResponse.success("成功添加" +total + "人");
        }else{//部分账户数据不合法
            String message = "批量添加" + total + "人，成功" + (total-failedNum) + "人，失败"
                    + failedNum + "人";
            return AjaxResponse.error(ErrorStatus.SOME_USER_ILLEGAL,message,failedList);
        }
    }

    //更新密码
    @LoginToken//需要登录
    @PutMapping("/user")
    public @ResponseBody AjaxResponse updatePassword(@RequestBody User user){
        UserVO userVO = userService.changePassword(user);
        if(userVO == null){//密保验证没通过（身份证错误）
            return AjaxResponse.error(ErrorStatus.CHANGE_PWD_FAILED, "身份证验证错误");
        }else{
            return AjaxResponse.success(userVO);
        }
    }

    //重置密码
    @PutMapping("/resetPwd")
    public @ResponseBody AjaxResponse resetPassword(@RequestBody User user){
        int result = userService.resetPassword(user);
        if(result == ErrorStatus.ACCOUNT_NOT_EXIT){//账号不存在
            return AjaxResponse.error(ErrorStatus.ACCOUNT_NOT_EXIT, "账号不存在");
        }else if(result == ErrorStatus.ID_CARD_NOT_MATCH){//验证信息错误（身份证不匹配）
            return AjaxResponse.error(ErrorStatus.CHANGE_PWD_FAILED, "身份证错误");
        }else{//重置成功
            return AjaxResponse.success();
        }
    }

}
