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
    private String token;

}
