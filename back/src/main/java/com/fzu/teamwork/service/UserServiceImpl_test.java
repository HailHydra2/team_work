package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.model.UserExample;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl_test implements UserService{
    @Resource
    private UserDao userDao;

    private static Integer maxGetNum=100;//最大查找人数

    public ArrayList<User> getUsers(){
        ArrayList<User> users=new ArrayList<>();
        for(int i=1;i<maxGetNum;i++)//假设同时查找maxGetNum个user
        {
            if(userDao.selectByPrimaryKey(i)!=null)
            {
                users.add(userDao.selectByPrimaryKey(i));
            }
        }
        return users;
    }

    //delete user
    @Override
    public void deleteUsers(int id) { userDao.deleteByPrimaryKey(id); }

    //add user
    @Override
    public void addUsers(User user) { userDao.insert(user); }

    @Override
    public User getUserById(int id){
        return null;
    }

    @Override
    public User getUserByAccount(String account){
        return null;
    }

    //updata password
    @Override
    public User updateUser(UserVO userVO){
        String newPassWord=userVO.getUser().getPassword();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAccountEqualTo(userVO.getUser().getAccount());
        userDao.updateByExampleSelective(userVO.getUser(),userExample);
        return null;
    }
}