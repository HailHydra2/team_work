package com.fzu.teamwork.controller;


import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@Slf4j
@RestController
public class LoginController {
    @GetMapping("/user")
    public @ResponseBody
    AjaxResponse getUser(@RequestBody User user){
        UserVO userVO=new UserVO();

        AccountData accountData=new AccountData();
        accountData.setLevel(10);
        accountData.setScore(11);
        accountData.setExperienceValue(99);
        accountData.setFocusNum(15);
        accountData.setQuestionNum(18);
        accountData.setResponseNum(20);

        userVO.setUser(user);
        userVO.setAccountData(accountData);
        
        return AjaxResponse.success(userVO);
    }

}
