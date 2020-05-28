package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.AccountDataDao;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AccountDataExample;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.model.UserExample;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.ErrorStatus;
import com.fzu.teamwork.util.IdCard;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.UserVO;
import io.swagger.annotations.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

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
    @Resource
    private TokenService tokenService;


    //user变成userVO
    public UserVO convertToUserVo(User user) {
        UserVO userVO=new UserVO();
        AccountData accountData;
        //获取对应的AccountData
        //log.info("account_id{}",user.getAccountDataId());
        accountData=accountDataDao.selectByPrimaryKey(user.getAccountDataId());
        String token = tokenService.getToken(user);//获取用户token
        //封装userVO
        userVO.setToken(token);
        userVO.setUser(user);
        userVO.setAccountData(accountData);
        return userVO;
    }

    //获取所有用户
    @Override
    public List<User> getUsers(){
        List<User> users;
        users = userDao.selectByExample(null);
        return users;
    }

    //删除用户
    @Override
    public boolean deleteUsers(int id) {
        return userDao.deleteByPrimaryKey(id) == 1;
    }

    //批量删除用户
    @Override
    public List<Integer> deleteUsersAll(List<Integer> idList) {
        List<Integer> failedList = new ArrayList<>();
        for(int id : idList){
            if(!deleteUsers(id)){//删除失败
                failedList.add(id);
            }
        }
        return failedList;
    }


    //添加一个用户(返回添加结果 0：成功添加 其他：错误对应的状态码)
    @Override
    public int addUser(User user) {
        int flag = doesLegal(user);
        if(flag != 0){//添加用户非法
            return flag;//返回错误状态码
        }
        if(user.getIdentity().equals(UserIdentity.admin)){
            //用户注册的是管理员账号(不需要账号信息)
            user.setAccountDataId(-1);
        }else{//老师或学生
            //新建账户数据记录
            AccountData accountData = new AccountData();
            accountDataDao.insert(accountData);
            user.setAccountDataId(accountData.getId());
        }
        userDao.insert(user);
        return 0;
    }

    //判断要添加的用户是否合法（返回判断结果 0-合法 其他-对应错误状态码）
    public int doesLegal(User user){
        String account = user.getAccount();
        String idCard = user.getIdCard();
        int code;
        //判姓名
        code = doesLegalName(user.getName());
        if(code != 0){
            return code;
        }
        //判学号
        code = doesLegalAccount(user.getAccount());
        if(code != 0){
            return code;
        }
        //判身份证
        code = doesLegalIdCard(user.getIdCard());
        if(code != 0){
            return code;
        }
        //判身份
        code = doesLegalIdentity(user.getIdentity());
        if(code != 0){
            return code;
        }
        return 0;
    }

    //判断账号是否合法(合法返回0，不合法返回错误状态码)
    public int doesLegalAccount(String account){
        if(account == null){//账号为空
            return ErrorStatus.ACCOUNT_NULL;
        }
        if(account.length() != 9){
            return ErrorStatus.ACCOUNT_ILLEGAL;//账号（学号）非法
        }
        for(int i = 0; i < account.length(); i++){
            if(!Character.isDigit(account.charAt(i)) && !Character.isLetter(account.charAt(i))){
                return ErrorStatus.ACCOUNT_ILLEGAL;//账号非法
            }
        }
        //判断重复性
        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo(account);
        if(userDao.selectByExample(example).size() > 0){
            return ErrorStatus.ACCOUNT_HAS_EXIT;//账户已被注册
        }
        return 0;
    }

    //判断身份证是否合法(合法返回0，不合法返回错误状态码)
    public int doesLegalIdCard(String idCard){
        if(idCard == null){//身份证为空
            return ErrorStatus.ID_CARD_NULL;
        }
        String decodeIdCard = "";//解密后的身份证
        try {
            decodeIdCard = Encryptor.decrypt(idCard);//解密
            if(!IdCard.IDCardValidate(decodeIdCard)){
                return ErrorStatus.ID_CARD_ILLEGAL;//身份证非法
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //判断重复性
        UserExample idExample = new UserExample();
        idExample.createCriteria().andIdCardEqualTo(idCard);
        System.out.println(idCard);
        if(userDao.selectByExample(idExample).size() > 0){
            return ErrorStatus.ID_CARD_HAS_EXIT;//身份证号已经被注册
        }
        return 0;
    }

    //姓名是否合法（合法返回0，非法返回错误状态码）
    public int doesLegalName(String name){
        if(name == null){//姓名为空
            return ErrorStatus.NAME_NULL;
        }
        return 0;
    }

    //身份是否合法（合法返回0，非法返回错误状态码）
    public int doesLegalIdentity(String identity){
        if(identity == null){//身份为空
            return ErrorStatus.IDENTITY_NULL;
        }
        if(UserIdentity.doesLegal(identity)){//合法
            return 0;
        }else{//身份不存在
            return ErrorStatus.IDENTITY_ERROR;
        }
    }

    @Override
    //批量增加用户(返回添加失败用户的描述信息)
    public List<String> addUsers(List<User> users){
        List<String> failedList = new ArrayList<>();
        BASE64Decoder decoder = new BASE64Decoder();
        int code;
        String message = "";
        String decodeIdCard = "";
        for(User user : users){
            code = addUser(user);
            if(code != 0){
                if(code == ErrorStatus.ACCOUNT_ILLEGAL){//账号非法
                    message = "姓名为：" + user.getName() + "的用户账号[学号]非法（应为9位字母数字串组成）";
                }else if(code == ErrorStatus.ACCOUNT_NULL){//账号为空
                    message = "姓名为：" + user.getName() + "的账号为空";
                }else if(code == ErrorStatus.NAME_NULL){//姓名为空
                    message = "账号为：" + user.getAccount() + "的用户姓名为空";
                }else if(code == ErrorStatus.ID_CARD_NULL){//身份证为空
                    message = "账号为：" + user.getAccount() + "的用户身份证为空";
                }else if(code == ErrorStatus.ID_CARD_ILLEGAL){//身份证非法
                    message = "账号为：" + user.getAccount() + "的用户身份证非法";
                }else if(code == ErrorStatus.ACCOUNT_HAS_EXIT){//账号已经被注册
                    message = "姓名为：" + user.getName() + "的用户添加失败，"
                            + user.getAccount() + "账户（学号）已被注册";
                }else if(code == ErrorStatus.ID_CARD_HAS_EXIT){//身份证已经被注册
                    decodeIdCard = Encryptor.decrypt(user.getIdCard());
                    message = user.getName() + "添加失败，身份证" + decodeIdCard + "已经被注册";
                }else if(code == ErrorStatus.IDENTITY_NULL){//身份为空
                    message = "账号为：" + user.getAccount() + "的用户身份为空";
                }else if(code == ErrorStatus.IDENTITY_ERROR){//身份非法
                    message = "账号为：" + user.getAccount() + "的用户身份非法";
                }
                failedList.add(message);
            }
        }
        return failedList;
    }

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

    //更改密码(成功返回更改后的UserVO，失败返回null)
    public UserVO changePassword(User user){
        User u = userDao.selectByPrimaryKey(user.getId());
        if(u.getIdCard().equals(user.getIdCard())){
            //密保正确（身份证）
            userDao.updateByPrimaryKey(user);
            return convertToUserVo(user);
        }else{
            return null;
        }
    }

    //重置密码返回值是重置结果 -1：身份证错误，-2：账号不存在，1：成功）
    @Override
    public int resetPassword(User user){
        //创建查询条件
        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo(user.getAccount());//账号相同
        List<User> list = userDao.selectByExample(example);
        if(list.size() == 0){
            //账号不存在
            return -2;
        }
        User u = list.get(0);
        if(u.getIdCard().equals(user.getIdCard())){
            //验证正确
            u.setPassword(user.getPassword());
            userDao.updateByPrimaryKey(u);
            return 1;
        }else{
            //身份证号验证错误
            return -1;
        }
    }
}