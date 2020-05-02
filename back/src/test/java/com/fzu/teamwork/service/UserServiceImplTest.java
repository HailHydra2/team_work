package com.fzu.teamwork.service;

import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest extends LCH_testFather{

    @Autowired
    private UserService userService;

    @Test
    void convertToUserVo() {
        User user=new User();
        user.setId(3);
        user.setAccount("s2");
        user.setPassword("2");
        user.setName("学生2");
        user.setIdCard("123");
        user.setIdentity("student");
        user.setAccountDataId(2);

        AccountData accountData=new AccountData();
        accountData.setId(2);
        accountData.setLevel(0);
        accountData.setExperienceValue(0);
        accountData.setScore(1);
        accountData.setFocusNum(0);
        accountData.setQuestionNum(0);
        accountData.setResponseNum(0);

        //我期待的uesrVo
        UserVO userVO_except=new UserVO();
        userVO_except.setUser(user);
        userVO_except.setAccountData(accountData);

        //比较期望与实际
        Assert.assertEquals("测试错误",userVO_except,userService.convertToUserVo(user));
    }

    @Test
    void getUsers() {
    }

    @Test
    void deleteUsers() {
    }

    @Test
    void deleteUsersAll() {
    }

    @Test
    void addUser() {
        User user=new User();
        user.setId(10);
        user.setAccount("t3");
        user.setPassword("3");
        user.setName("教师3");
        user.setIdCard("123");
        user.setIdentity("teacher");
        user.setAccountDataId(-1);

        //我期待的uesr
        User user_except=user;

        //比较期望与实际
        userService.addUser(user);
        Assert.assertEquals("测试错误",user_except,userService.getUserById(user.getId()));
    }

    @Test
    void getUserById() {

        User user=new User();
        user.setId(3);
        user.setAccount("s2");
        user.setPassword("2");
        user.setName("学生2");
        user.setIdCard("123");
        user.setIdentity("student");
        user.setAccountDataId(2);

        //我期待的uesr
        User user_except=user;

        //比较期望与实际
        Assert.assertEquals("测试错误",user_except,userService.getUserById(3));


    }

    @Test
    void getUserByAccount() {

        User user=new User();
        user.setId(3);
        user.setAccount("s2");
        user.setPassword("2");
        user.setName("学生2");
        user.setIdCard("123");
        user.setIdentity("student");
        user.setAccountDataId(2);

        //我期望的user
        User user_except=user;

        //比较期望与实际
        Assert.assertEquals("测试错误",user,userService.getUserByAccount(user.getAccount()));

    }

    @Test
    void updateUser() {
        User user=new User();
        user.setId(2);
        user.setAccount("s1");
        user.setPassword("555");//修改密码
        user.setName("学生1");
        user.setIdCard("112");
        user.setIdentity("student");
        user.setAccountDataId(1);

        //我期望的userVo
        User user_except=user;
        UserVO userVO_except=userService.convertToUserVo(user);

        userService.updateUser(userVO_except);

        //比较期望与实际
        Assert.assertEquals("测试错误",user.getPassword(),userService.getUserById(user.getId()).getPassword());

    }
}