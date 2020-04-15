package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

    public ArrayList<User> getUsers(){

        ArrayList<User> userArrayList=new ArrayList<>();

        /*for(int i=1;i<=2;i++)
        {
            userArrayList.add(userDao.selectByPrimaryKey(i));
        }
        */

        User u1=new User();
        u1.setAccount("221701316");
        u1.setName("lch");
        u1.setPassword("123456");
        u1.setIdCard("123");
        u1.setIdentity("student");
        u1.setPhoneNum("123456");

        User u2=new User();
        u2.setAccount("221701319");
        u2.setName("lll");
        u2.setPassword("123456");
        u2.setIdCard("123");
        u2.setIdentity("student");
        u2.setPhoneNum("123456");

        userArrayList.add(u1);
        userArrayList.add(u2);


        return userArrayList;
    }

    //注意接口没写！！！！
    public void deleteUsers(int id)
    {
        userDao.deleteByPrimaryKey(id);
    }

    //注意接口没写！！！！
    public void addUsers(User user)
    {
        userDao.insert(user);
    }

    @Override
    public User getUserById(int id){
        return null;
    }

    @Override
    public User getUserByAccount(String account){
        return null;
    }

    @Override
    //更新用户信息(包括基本信息和账户信息)
    public User updateUser(UserVO userVO){
        return null;
    }
}