package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.AccountDataDao;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AccountDataExample;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.model.UserExample;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
@RestController
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;
    @Resource
    private AccountDataDao accountDataDao;

    private static Integer maxGetNum=100;//最大查找人数

    //user变成userVO
    public UserVO convertToUserVo(User user) {
        UserVO userVO=new UserVO();
        AccountData accountData=new AccountData();
        //获取对应的AccountData
        accountData=accountDataDao.selectByPrimaryKey(user.getAccountDataId());
        //封装userVO
        userVO.setUser(user);
        userVO.setAccountData(accountData);
        return userVO;
    }

    //获取所有用户
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
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByAccount(String account){

        //从数据库中找到对应的user
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAccountEqualTo(account);
        List<User> users = userDao.selectByExample(userExample);
        if(users.size() == 0){//账户不存在
            return null;
        }else{//账户存在
            return users.get(0);
        }
    }

    //update password
    @Override
    public User updateUser(UserVO userVO){
        //更新用户信息
        userDao.updateByPrimaryKey(userVO.getUser());
        //更新账户数据
        accountDataDao.updateByPrimaryKey(userVO.getAccountData());
        return null;
    }
}