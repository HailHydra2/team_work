package com.fzu.teamwork.service;

import com.fzu.teamwork.model.User;
import com.fzu.teamwork.view.UserVO;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    //user变成userVO
    public UserVO convertToUserVo(User user);

    //获取所有用户
    public ArrayList<User> getUsers();

    //通过id查找用户
    public User getUserById(int id);

    //通过account查找用户
    public User getUserByAccount(String account);

    //delete user
    public void deleteUsers(int id);

    //delete all user
    public void deleteUsersAll(int id[]);

    //添加一个用户
    public void addUser(User user);

    //更新用户信息(包括基本信息和账户信息)
    public User updateUser(UserVO userVO);




}
