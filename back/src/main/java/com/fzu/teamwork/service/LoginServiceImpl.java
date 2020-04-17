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

    @Resource(name = "userServiceImpl")
    UserServiceImpl userService;

    @Resource
    private UserDao userDao;

    @Resource
    AccountDataDao accountDataDao;

    //获取用户
    @Override
    public UserVO getUser(User user) {


        //no account
        if (userService.getUserByAccount(user.getAccount()).size() == 0)
        {
            UserVO userVO = new UserVO();
            System.out.println("no account!");
            user.setMark(0);//设置mark
            userVO.setUser(user);
            return userVO;
        }

        // error password
        else if (!user.getPassword().equals(userService.getUserByAccount(user.getAccount()).get(0).getPassword()))
        {
            UserVO userVO = new UserVO();
            System.out.println("error password!");
            user.setMark(1);//设置mark
            userVO.setUser(user);
            return userVO;
        }

        //succese
        else
        {
            UserVO userVO=userService.convertToUserVo(userService.getUserByAccount(user.getAccount()).get(0));
            userVO.getUser().setMark(3);
            return userVO;
        }
    }
}
