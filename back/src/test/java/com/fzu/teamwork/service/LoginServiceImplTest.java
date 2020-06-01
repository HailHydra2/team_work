package com.fzu.teamwork.service;

import com.fzu.teamwork.model.User;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class LoginServiceImplTest {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    private User user;
    private UserVO userVO;

    @BeforeEach
    public void createTestUser(){
        user = new User();
        user.setAccount("221701422");
        user.setIdentity(UserIdentity.student);
        user.setName("testWSH");
        user.setPassword(Encryptor.encrypt("873"));
        user.setIdCard(Encryptor.encrypt("360102199003077873"));
        int code = userService.insertUser(user);
        if(code == 0){
            userVO = userService.convertToUserVo(user);
        }else{
            System.out.println("用户添加失败，状态码为:" + code);
        }
    }

    @AfterEach
    //删除用户
    public void destroyUser(){
        userService.deleteUsers(user.getId());
    }

    //测试登录函数
    @Test
    public void login(){
        //成功登录验证
        UserVO u = loginService.login(user);
        //验证登录结果
        Assert.assertNotNull(u);
        Assert.assertEquals(2, (long)u.getUser().getMark());

        //验证密码错误
        user.setPassword(Encryptor.decrypt("123"));
        u = loginService.login(user);
        Assert.assertNotNull(u);
        Assert.assertEquals(1, (long)u.getUser().getMark());

        //登录不存在账号
        user.setAccount("123");
        u = loginService.login(user);
        Assert.assertEquals(0, (long)u.getUser().getMark());
    }
}