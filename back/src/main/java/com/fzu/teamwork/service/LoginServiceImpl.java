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

    @Resource
    public UserDao userDao;

    @Resource
    AccountDataDao accountDataDao;

    //获取用户
    @Override
    public UserVO getUser(User user) {
        //从数据库中找到对应的user
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAccountEqualTo(user.getAccount());
        List<User> users = userDao.selectByExample(userExample);

        UserVO userVO = new UserVO();

        //no account
        if (users.size() == 0)
        {
            System.out.println("no account!");
            user.setMark(0);//设置mark
            userVO.setUser(user);
            return userVO;
        }

        // error password
        else if (!user.getPassword().equals(users.get(0).getPassword()))
        {
            System.out.println("error password!");
            user.setMark(1);//设置mark
            userVO.setUser(user);
            return userVO;
        }

        //succese
        else
        {
            //获取对应的AccountData
            AccountData accountData=accountDataDao.selectByPrimaryKey(users.get(0).getAccountDataId());
            //封装uesrVO
            users.get(0).setMark(3);//设置mark
            userVO.setUser(users.get(0));
            userVO.setAccountData(accountData);
            return userVO;
        }
    }
}
