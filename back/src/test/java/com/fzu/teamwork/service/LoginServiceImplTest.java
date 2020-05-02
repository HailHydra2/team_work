package com.fzu.teamwork.service;

import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.view.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceImplTest extends LCH_testFather {
    @Autowired
    private LoginService loginService;

    @Test
    void getUser() {

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


    }
}