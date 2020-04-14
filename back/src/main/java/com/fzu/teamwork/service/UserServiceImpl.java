package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.User;

import javax.annotation.Resource;
import java.util.*;

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

    public void deleteUsers(int id)
    {
        userDao.deleteByPrimaryKey(id);
    }

    public void addUsers(User user)
    {
        userDao.insert(user);
    }

}
