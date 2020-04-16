package com.fzu.teamwork.controller;


import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Reward;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.RewardService;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.RewardVO;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class RewardController {

    @Resource(name = "rewardServiceImpl")
    RewardService rewardService;

    @GetMapping("/rewards")
    public @ResponseBody List<RewardVO> getRewards(){
        List<RewardVO> rewardVOS = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            RewardVO rewardVO = new RewardVO();
            rewardVO.setReward(new Reward());
            rewardVO.getReward().setUserId(i);
            rewardVO.getReward().setApplyTime(new Date());
            rewardVO.getReward().setId(1);
            rewardVO.setAccount("22170" + i);
            rewardVO.getReward().setRewardNum(10.0);
            rewardVO.getReward().setType("SyntheticTest");
            rewardVO.setName("user" + i);
            rewardVOS.add(rewardVO);
        }
        return  rewardVOS;
    }

    @GetMapping("/testRewards")
    public @ResponseBody List<Reward> testGetRewards(){
        return rewardService.getRewardList();
    }

    @PostMapping("/reward")
    public @ResponseBody AjaxResponse addReward(@RequestBody Reward reward){
        UserVO userVO = new UserVO();
        userVO.setUser(new User());
        userVO.getUser().setAccount("221701421");
        userVO.getUser().setIdCard("123");
        userVO.getUser().setId(reward.getUserId());
        userVO.getUser().setIdentity(UserIdentity.student);
        userVO.setAccountData(new AccountData());
        userVO.getAccountData().setId(10);
        userVO.getAccountData().setUserId(reward.getUserId());
        userVO.getAccountData().setExperienceValue(100);
        userVO.getAccountData().setFocusNum(1);
        userVO.getAccountData().setLevel(10);
        userVO.getAccountData().setQuestionNum(10);
        userVO.getAccountData().setScore(100);
        userVO.getAccountData().setResponseNum(12);
        rewardService.insertReward(reward);
        return AjaxResponse.success(userVO);
    }

    @PostMapping("/testReward")
    public @ResponseBody AjaxResponse testAddReward(@RequestBody Reward reward){
        UserVO userVO = rewardService.insertReward(reward);
        return AjaxResponse.success(userVO);
    }
}
