package com.fzu.teamwork.service;


import com.fzu.teamwork.dao.AccountDataDao;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@RestController

public class LoginServiceImpl implements LoginService {


    //获取用户
    @Override
    public UserVO getUser(User user) {

        UserVO userVO = new UserVO();
        AccountData accountData=new AccountData();
        accountData.setLevel(10);
        accountData.setScore(10);
        accountData.setExperienceValue(99);
        accountData.setFocusNum(16);
        accountData.setQuestionNum(18);
        accountData.setResponseNum(25);

        userVO.setUser(user);
        userVO.setAccountData(accountData);

        return userVO;
    }
}
