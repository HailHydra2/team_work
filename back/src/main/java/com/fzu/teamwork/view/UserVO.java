package com.fzu.teamwork.view;

import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.AccountDataService;
import com.fzu.teamwork.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;


@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO{

    @Resource(name = "accountDataServiceImpl")
    AccountDataService accountDataService;


    private User user;
    private AccountData accountData;

    //使用user构造userVO对象（实例化accountData）
    public UserVO(User user){
        this.setUser(user);
        this.setAccountData(accountDataService.getById(user.getAccountDataId()));
    }
}
