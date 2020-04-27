package com.fzu.teamwork.service;


import com.fzu.teamwork.dao.AccountDataDao;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.*;
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

public class LoginServiceImpl implements LoginService {

    @Resource(name = "userServiceImpl")
    UserServiceImpl userService;

    //获取用户
    @Override
    public UserVO getUser(User user) {
        User u = userService.getUserByAccount(user.getAccount());
        UserVO userVO = new UserVO();
        if (u == null){
            //账户不存在
            user.setMark(0);//设置mark
            userVO.setUser(user);
        } else{//账户存在
            if(user.getPassword().equals(u.getPassword())){
                //密码正确
                u.setMark(2);//标记位
                userVO = userService.convertToUserVo(u);
            }else{//密码错误
                user.setMark(1);//设置mark
                userVO.setUser(user);
            }
        }
        return userVO;
    }
}
