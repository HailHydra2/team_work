package com.fzu.teamwork.view;

import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO{
    private User user;
    private AccountData accountData;

    //使用user构造userVO对象（实例化accountData）
    public UserVO(User user){

    }
}
