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
public class UserVO extends User {

    private String Account;
    private String Name;
    private String Identity;

    private AccountData accountData;

    public UserVO (User user){
        user.setAccount("221701421");
        user.setName("wsh");
        user.setIdentity("student");

        AccountData accountData=new AccountData();
        accountData.setLevel(10);
        accountData.setScore(10);
        accountData.setExperienceValue(99);
        accountData.setFocusNum(12);
        accountData.setQuestionNum(13);
        accountData.setResponseNum(15);

        this.Account=user.getAccount();
        this.Name=user.getName();
        this.Identity=user.getIdentity();

        this.accountData=accountData;

    }

}
