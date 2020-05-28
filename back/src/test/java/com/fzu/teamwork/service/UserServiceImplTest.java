package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.AccountDataDao;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.model.UserExample;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.ErrorStatus;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Resource
    private UserDao userDao;
    @Resource
    private AccountDataDao accountDataDao;

    //添加用户，返回添加函数返回的状态码
    public int addUser(User user){
        String idCard = user.getIdCard();
        UserVO userVO = null;
        if(idCard != null){
            user.setIdCard(Encryptor.encrypt(idCard));//加密
            user.setPassword(Encryptor.encrypt(idCard.substring(idCard.length()-3)));//取身份证后三位为密码
        }
        int code = userService.addUser(user);
        if (code == 0) {
            userVO = userService.convertToUserVo(user);
        }else{
            System.out.println("添加用户失败，状态码为：" + code);
        }
        return code;
    }

    //测试添加正确的学生、老师、管理员用户
    @Test
    public void addUserRight(){
        User user = new User();
        //添加学生
        user.setAccount("221701521");
        user.setIdCard("220102199003076079");
        user.setIdentity(UserIdentity.student);
        user.setName("rightUser1");
        addUser(user);
        //确认是否成功插入数据库
        Assert.assertNotNull(userDao.selectByPrimaryKey(user.getId()));
        //是否创建对应账户数据及记录
        Assert.assertNotNull(accountDataDao.selectByPrimaryKey(user.getAccountDataId()));
        //删除数据
        userService.deleteUsers(user.getId());

        //添加老师
        user.setIdentity(UserIdentity.teacher);
        user.setIdCard("220102199003076079");
        addUser(user);
        //确认是否成功插入数据库
        Assert.assertNotNull(userDao.selectByPrimaryKey(user.getId()));
        //是否创建对应账户数据及记录
        Assert.assertNotNull(accountDataDao.selectByPrimaryKey(user.getAccountDataId()));
        //删除数据
        userService.deleteUsers(user.getId());

        //添加管理员
        user.setIdentity(UserIdentity.admin);
        user.setIdCard("220102199003076079");
        addUser(user);
        //确认是否成功插入数据库
        Assert.assertNotNull(userDao.selectByPrimaryKey(user.getId()));
        //账户信息外键为-1
        Assert.assertEquals(-1,(long)user.getAccountDataId());
        //删除数据
        userService.deleteUsers(user.getId());
    }

    //重复添加已经存在的用户（学号、身份证）
    @Test
    public void addExitUser(){
        User user = new User();
        UserExample example = new UserExample();
        //添加用户
        user.setAccount("221701521");
        user.setIdCard("220102199003076079");
        user.setIdentity(UserIdentity.student);
        user.setName("exitUser1");
        addUser(user);

        User user1 = new User();
        //添加重复账号用户
        user1.setAccount(user.getAccount());
        user1.setIdCard("22010219900307847X");
        user1.setIdentity(UserIdentity.student);
        user1.setName("exitUser2");
        //函数返回状态码为重复账号对应状态码
        Assert.assertEquals(ErrorStatus.ACCOUNT_HAS_EXIT, addUser(user1));
        example.createCriteria().andIdCardEqualTo(user1.getIdCard());
        Assert.assertEquals(0,userDao.selectByExample(example).size());

        //添加重复身份证
        user1.setAccount("221701522");
        user1.setIdCard(Encryptor.decrypt(user.getIdCard()));
        user1.setIdentity(UserIdentity.student);
        user1.setName("exitUser3");
        //返回值为重复身份证错误状态码
        Assert.assertEquals(ErrorStatus.ID_CARD_HAS_EXIT, addUser(user1));
        //没有添加对应用户记录
        example.createCriteria().andAccountEqualTo(user1.getAccount());
        Assert.assertEquals(0,userDao.selectByExample(example).size());

        //删除添加的用户
        userService.deleteUsers(user.getId());
    }

    //添加错误的用户信息
    @Test
    public void addErrorUser(){
        User user = new User();
        UserExample example = new UserExample();
        //添加账号错误用户
        user.setAccount("123");//错误账号
        user.setName("errorUser1");
        user.setIdCard("220102199003076079");
        user.setIdentity(UserIdentity.student);
        //函数返回状态码为账号错误状态码
        Assert.assertEquals(ErrorStatus.ACCOUNT_ILLEGAL, addUser(user));
        //错误用户信息未插入数据库
        example.createCriteria().andAccountEqualTo(user.getAccount());
        Assert.assertEquals(0,userDao.selectByExample(example).size());

        //添加身份证错误用户
        user.setAccount("221701523");
        user.setIdCard("123");//错误身份证
        Assert.assertEquals(ErrorStatus.ID_CARD_ILLEGAL, addUser(user));
        //错误用户信息没有插入数据库
        example.createCriteria().andAccountEqualTo(user.getAccount());
        Assert.assertEquals(0,userDao.selectByExample(example).size());

        //添加身份错误用户
        user.setIdCard("220102199003076079");
        user.setIdentity("stu");//错误身份
        //返回身份错误对应状态码
        Assert.assertEquals(ErrorStatus.IDENTITY_ERROR, addUser(user));
        //错误用户信息没有被插入数据库
        example.createCriteria().andAccountEqualTo(user.getAccount());
        Assert.assertEquals(0, userDao.selectByExample(example).size());
    }

    //添加部分信息为空的用户
    @Test
    public void addEmptyUser(){
        User user = new User();
        UserExample example = new UserExample();

        //添加账号为空用户信息
        user.setIdCard("220102199003076079");
        user.setIdentity(UserIdentity.student);
        user.setName("emptyUser1");
        //返回状态码为账号为空状态码
        Assert.assertEquals(ErrorStatus.ACCOUNT_NULL, addUser(user));
        //错误用户信息没有插入数据库
        example.createCriteria().andIdCardEqualTo(user.getIdCard());
        Assert.assertEquals(0,userDao.selectByExample(example).size());

        //添加身份证为空的用户信息
        user.setAccount("221701522");
        user.setIdCard(null);//身份证置位空
        //返回状态码为身份证空状态码
        Assert.assertEquals(ErrorStatus.ID_CARD_NULL, addUser(user));
        //错误用户信息没有插入数据库
        example.createCriteria().andAccountEqualTo(user.getAccount());
        Assert.assertEquals(0, userDao.selectByExample(example).size());

        //添加身份为空用户信息
        user.setIdCard("220102199003076079");
        user.setIdentity(null);//身份置位空
        //返回身份为空状态码
        Assert.assertEquals(ErrorStatus.IDENTITY_NULL, addUser(user));
        //错误信息没有插入数据库
        example.createCriteria().andAccountEqualTo(user.getAccount());
        Assert.assertEquals(0, userDao.selectByExample(example).size());

        //添加姓名为空的用户信息
        user.setIdentity(UserIdentity.student);
        user.setName(null);//用户姓名置位空
        //返回状态码为用户姓名为空状态码
        Assert.assertEquals(ErrorStatus.NAME_NULL, addUser(user));
        //错误信息没有插入数据库
        example.createCriteria().andAccountEqualTo(user.getAccount());
        Assert.assertEquals(0, userDao.selectByExample(example).size());
    }

    //批量增加全合法的用户列表信息
    @Test
    public void addUsersAllRight(){
        User user;
        UserExample example = new UserExample();
        List<String> failedMessageList;//创建失败描述信息列表
        //正确身份证列表
        String[] idCards = {
                "360102199003074111",
                "360102199003077873",
                "360102199003074998",
                "36010219900307607X",
                "360102199003079377",
                "210102199003076798",
                "210102199003075517",
                "210102199003070935",
                "210102199003074290",
                "210102199003076173"
        };
        List<Integer> rightIdList = new ArrayList<>();//正确问题id序列
        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            user = new User();
            user.setAccount("22170152" + i);
            user.setName("rightUsers" + i);
            String idCard = idCards[i];
            user.setIdCard(Encryptor.encrypt(idCard));
            user.setPassword(Encryptor.encrypt(idCards[i].substring(idCard.length()-3)));
            user.setIdentity(UserIdentity.student);
            userList.add(user);//添加到用列表
        }
        failedMessageList = userService.addUsers(userList);
        //创建失败描述信息列表长度为0
        Assert.assertEquals(0, failedMessageList.size());
        //列表中所有用户均被存储到数据库
        for(User user1 : userList){
            Assert.assertNotNull(userDao.selectByPrimaryKey(user1.getId()));
        }
        //删除添加的数据信息
        for(User u : userList){
            userService.deleteUsers(u.getId());
        }
    }
}