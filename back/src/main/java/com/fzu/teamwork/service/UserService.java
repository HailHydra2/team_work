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
    public int addUser(User user);

    //批量添加用户
    public List<User> addUsers(List<User> users);

    //更新用户信息(包括基本信息和账户信息)
    public User updateUser(UserVO userVO);

    //更改密码(成功返回更改后的UserVO，失败返回null)
    public UserVO changePassword(User user);

    //重置密码（返回值是重置结果 -1：身份证错误，-2：账号不存在，1：成功）
    public int resetPassword(User user);

}
