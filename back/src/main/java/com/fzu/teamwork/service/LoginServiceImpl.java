package com.fzu.teamwork.service;


import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.model.UserExample;
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

public class LoginServiceImpl implements LoginService{

    @Resource
    public UserDao userDao;
/*
    //获取用户
    @Override
    public  User getUser(User user)
    {
        //从数据库中找到对应的user
        UserExample userExample=new UserExample();
        UserExample.Criteria criteria=userExample.createCriteria();
        criteria.andAccountEqualTo(user.getAccount());
        List<User> users=userDao.selectByExample(userExample);
        //因为列表唯一，就一个user
        User userFromDb=users.get(0);
        //UserVO userVO=new UserVO();
        //userVO.setUser(userFromDb);
        //userVO.setAccountData();
        return userFromDb;
    }
*/
    @Override
    public  User getUser(User user)
    {
        return userDao.selectByPrimaryKey(2);
    }


}
