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
import java.util.Random;

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

    //正确身份证列表
    private String[] idCards = {
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

        List<User> userList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            user = new User();
            user.setAccount("22170152" + i);
            user.setName("rightUsers" + i);
            String idCard = idCards[i];
            user.setIdCard(Encryptor.encrypt(idCard));
            user.setPassword(Encryptor.encrypt(idCard.substring(idCard.length()-3)));
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

    //添加全错用户列表
    @Test
    public void addUsersAllError(){
        User user;
        User user1 = new User();
        UserExample example = new UserExample();
        List<String> failedMessageList;//错误消息列表
        List<User> userList = new ArrayList<>();
        //添加一个正确的用户到数据库
        user1.setAccount("221701521");
        user1.setIdCard("220102199003076079");
        user1.setIdentity(UserIdentity.student);
        user1.setName("rightUser1");
        addUser(user1);
        for(int i = 0; i < 10; i++){
            user = new User();
            user.setAccount("22170152" + i);
            user.setName("errorUsers" + i);
            String idCard =  idCards[i];
            user.setIdCard(Encryptor.encrypt(idCard));
            user.setPassword(Encryptor.encrypt(idCard.substring(idCard.length()-3)));
            user.setIdentity(UserIdentity.student);
            userList.add(user);//添加到用列表
        }
        userList.get(0).setAccount("123");//账号非法
        userList.get(1).setAccount(null);//账号不存在
        userList.get(2).setName(null);//姓名为空
        userList.get(3).setIdCard(null);//身份证为空
        userList.get(4).setIdCard(Encryptor.encrypt("123"));//身份证非法
        userList.get(5).setAccount(user1.getAccount());//账号已经被注册
        userList.get(6).setIdCard(user1.getIdCard());//身份证已经被注册
        userList.get(7).setIdentity(null);//身份为空
        userList.get(8).setIdentity("stu");//身份错误
        userList.get(9).setAccount("12345678&");//非法账号

        failedMessageList = userService.addUsers(userList);//批量添加用户
        Assert.assertEquals(userList.size(), failedMessageList.size());//列表中每个用户都有对应的错误描述
        for(User u : userList){
            if(u.getAccount() != null) {
                example.createCriteria().andAccountEqualTo(u.getAccount());
                //没有将错误用户信息插入数据库
                Assert.assertEquals(0, userDao.selectByExample(example).size());
            }
        }

        //删除添加的测试用户
        userService.deleteUsers(user1.getId());
    }

    //添加部分正确部分错误用户列表
    @Test
    public void addUsersHalfRight(){
        User user;
        UserExample example = new UserExample();
        List<String> failedMessageList;//创建失败描述信息列表
        List<String> rightUserList = new ArrayList<>();//正确用户账号列表
        List<String> errorUserList = new ArrayList<>();//错误用户账号列表
        List<User> userList = new ArrayList<>();
        //添加10个正确用户数据
        for(int i = 0; i < 3; i++){
            user = new User();
            user.setAccount("22170152" + i);
            user.setName("rightUsers" + i);
            String idCard = idCards[i];
            user.setIdCard(Encryptor.encrypt(idCard));
            user.setPassword(Encryptor.encrypt(idCard.substring(idCard.length()-3)));
            user.setIdentity(UserIdentity.student);
            userList.add(user);//添加到用户列表
            rightUserList.add(user.getAccount());//将用户id添加到正确的用户id列表
        }
        //添加10个错误用户数据
        for(int i = 0; i < 3; i++){
            user = new User();
            user.setAccount("22170162" + i);
            user.setName("errorUsers" + i);
            String idCard = "12345";//错误身份证号
            user.setIdCard(Encryptor.encrypt(idCard));
            user.setPassword(Encryptor.encrypt(idCard.substring(idCard.length()- 3)));
            user.setIdentity(UserIdentity.student);
            userList.add(user);//添加到用户列表
            errorUserList.add(user.getAccount());//将错误用户的id添加到错误用户id列表
        }
        failedMessageList = userService.addUsers(userList);//批量添加用户
        //判断返回错误信息条数是否正确
        Assert.assertEquals(errorUserList.size(), failedMessageList.size());
        //判断所有正确用户是否都被添加到了数据库
        for(String account : rightUserList){
            //判断是否成功被记录到数据库
            example = new UserExample();
            example.createCriteria().andAccountEqualTo(account);
            Assert.assertEquals(1, userDao.selectByExample(example).size());
            //删除被保存的数据
        }

        //判断所有错误用户信息是否都没有被保存到数据库
        for(String account : errorUserList){
            //判断是否没有保存到数据库
            example = new UserExample();
            example.createCriteria().andAccountEqualTo(account);
            Assert.assertEquals(0, userDao.selectByExample(example).size());
        }
        //删除添加的数据
        for (String account : rightUserList){
            example = new UserExample();
            example.createCriteria().andAccountEqualTo(account);
            userDao.deleteByExample(example);
        }

    }

    //测试获取所有用户函数
    @Test
    public void getUsers(){
        List<User> users = userDao.selectByExample(null);
        List<User> list = userService.getUsers();
        //测试获取的用户人数是否相同
        Assert.assertEquals(users.size(), list.size());
    }

    //测试删除用户
    @Test
    public void deleteUsers(){
        User user = new User();
        //添加用户
        user.setAccount("221701521");
        user.setIdCard("220102199003076079");
        user.setIdentity(UserIdentity.student);
        user.setName("rightUser1");
        addUser(user);
        //判断是否成功删除新添加的用户
        Assert.assertTrue(userService.deleteUsers(user.getId()));
        //重复删除刚删除的用户，判断返回是否为false
        Assert.assertFalse(userService.deleteUsers(user.getId()));
    }

    //测试批量删除用户（列表用户都存在）
    @Test
    public void deleteUsersListAllExit(){
        User user;
        UserExample example = new UserExample();
        List<User> userList = new ArrayList<>();
        List<Integer> userIdList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            user = new User();
            user.setAccount("22170152" + i);
            user.setName("rightUsers" + i);
            String idCard = idCards[i];
            user.setIdCard(Encryptor.encrypt(idCard));
            user.setPassword(Encryptor.encrypt(idCard.substring(idCard.length()-3)));
            user.setIdentity(UserIdentity.student);
            userList.add(user);//添加到用列表
        }
        userService.addUsers(userList);
        //用户添加到数据库之后记录用户id
        for(User u : userList){
            userIdList.add(u.getId());
        }
        //删除用户
        userService.deleteUsersAll(userIdList);
        //判断新添加用户是否被删除
        for(int id : userIdList){
            Assert.assertNull(userDao.selectByPrimaryKey(id));
        }
    }

    //测试批量删除用户（列表用户都不存在）
    @Test
    public void deleteUsersListNotExit(){
        User user;
        UserExample example = new UserExample();
        List<Integer> userIdList = new ArrayList<>();
        List<Integer> failedList = new ArrayList<>();
        Random r = new Random();//用于生成随机的id
        //用户添加到数据库之后记录用户id
        for(int i = 0; i < 10; i++){
            int id = r.nextInt(10000);
            //确保是不存在的用户id
            if(userDao.selectByPrimaryKey(id) != null){
                i--;
            }else{
                userIdList.add(id);
            }
        }
        //删除用户
        failedList = userService.deleteUsersAll(userIdList);
        //判断是否添加失败所有id全部返回
        Assert.assertEquals(userIdList.size(), failedList.size());
    }

    //修改密码测试
    @Test
    public void changePassword(){
        User user = new User();
        //添加学生
        user.setAccount("221701521");
        user.setIdCard("220102199003076079");
        user.setIdentity(UserIdentity.student);
        user.setName("changePwdUser1");
        addUser(user);

        //通过密保的请求
        String newPwd = "123";
        String oldPwd = Encryptor.decrypt(user.getPassword());
        user.setPassword(Encryptor.encrypt(newPwd));//修改密码
        userService.changePassword(user);
        user = userDao.selectByPrimaryKey(user.getId());//获取最新的用户数据
        //验证密码是否是新密码
        Assert.assertEquals(newPwd, Encryptor.decrypt(user.getPassword()));
        //与旧密码不同
        Assert.assertNotEquals(oldPwd, Encryptor.decrypt(user.getPassword()));

        //没有通过密保的请求
        newPwd = "234";
        oldPwd = Encryptor.decrypt(user.getPassword());
        user.setIdCard(Encryptor.encrypt("123"));//不匹配的身份证号
        user.setPassword(Encryptor.encrypt(newPwd));//修改密码
        userService.changePassword(user);
        user = userDao.selectByPrimaryKey(user.getId());//获取最新的用户数据
        //验证密码是否是新密码
        Assert.assertNotEquals(newPwd, Encryptor.decrypt(user.getPassword()));
        //验证密码是否仍是旧密码
        Assert.assertEquals(oldPwd, Encryptor.decrypt(user.getPassword()));

        //删除新添加的测试用户
        userService.deleteUsers(user.getId());
    }

    //测试重置密码
    @Test
    public void resetPassword(){
        User user = new User();
        UserVO userVO;
        //添加学生
        user.setAccount("221701521");
        user.setIdCard("220102199003076079");
        user.setIdentity(UserIdentity.student);
        user.setName("resetPwdUser1");
        addUser(user);
        user.setPassword(Encryptor.encrypt("123"));//将密码修改成123
        userVO = userService.convertToUserVo(user);
        userService.updateUser(userVO);//更新数据库
        String oldPwd = Encryptor.encrypt("123");//旧密码
        String expectPwd = Encryptor.encrypt("079");//重置后的正确密码

        //账号正确，身份证不匹配
        user.setIdCard(Encryptor.encrypt("1233"));
        user.setPassword(expectPwd);
        //重置密码，函数返回值为-1
        Assert.assertEquals(-1, userService.resetPassword(user));
        user = userDao.selectByPrimaryKey(user.getId());//获取数据库最新的数据
        //密码与旧密码一致
        Assert.assertEquals(oldPwd, user.getPassword());

        //正确的重置密码
        //判断函数返回值是否为1
        user.setPassword(expectPwd);
        Assert.assertEquals(1, userService.resetPassword(user));
        user = userDao.selectByPrimaryKey(user.getId());//获取最新的用户信息
        Assert.assertEquals(expectPwd, user.getPassword());//验证密码是否被成功重置

        //重置不存在的账号
        user.setAccount("1223");
        user.setPassword(expectPwd);
        //判断函数返回值是否为-2
        Assert.assertEquals(-2,userService.resetPassword(user));

        //删除新添加的测试用户
        userService.deleteUsers(user.getId());
    }

    //获取单个用户单元测试
    @Test
    public void getUserById(){
        Random random = new Random(1);
        User user = new User();
        User user1;
        user.setAccount("221701521");
        user.setIdCard("220102199003076079");
        user.setIdentity(UserIdentity.student);
        user.setName("getUser1");
        addUser(user);

        //获取存在于数据库的数据
        user1 = userService.getUserById(user.getId());
        Assert.assertNotNull(user1);
        //判断获取的用户是否是想要的用户数据
        Assert.assertEquals(user.getAccount(), user1.getAccount());

        int id;//要查找用户的id
        while(true){
            id = random.nextInt(10000);
            //确保id是不存在的用户id
            if(userDao.selectByPrimaryKey(id) == null){
                break;
            }
        }
        Assert.assertNull(userService.getUserById(id));
        //删除添加的测试用户
        userService.deleteUsers(user.getId());
    }

    //测试通过账号获取用户
    @Test
    public void getUserByAccount(){
        User user = new User();
        User user1;
        user.setAccount("221701521");
        user.setIdCard("220102199003076079");
        user.setIdentity(UserIdentity.student);
        user.setName("getUser2");
        addUser(user);

        //获取存在于数据库的数据
        user1 = userService.getUserByAccount(user.getAccount());
        Assert.assertNotNull(user1);
        //判断获取的用户是否是想要的用户数据
        Assert.assertEquals(user.getId(), user1.getId());

        //查找一个账号不存在的用户
        Assert.assertNull(userService.getUserByAccount("123"));

        //删除添加的测试用户
        userService.deleteUsers(user.getId());

    }

}