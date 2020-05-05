package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Reward;
import com.fzu.teamwork.util.RewardType;
import com.fzu.teamwork.view.RewardVO;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

class RewardServiceImplTest extends LCH_testFather{
    @Autowired
    private RewardService rewardService;

    @Autowired
    private UserService userService;

    @Test
    void insertReward() {

        Reward reward=new Reward();
        reward.setId(2);
        reward.setUserId(1);
        reward.setType(RewardType.ServiceTime);
        reward.setRewardNum(6.0);
        Date date=new Date();
        reward.setApplyTime(date);

        //真实的UserVo
        UserVO userVO=rewardService.insertReward(reward);

        //我期望的UserVo
        UserVO userVO_except=userService.convertToUserVo(userService.getUserById(reward.getUserId()));

        //比较与期望的结果
        assertEquals("测试错误",userVO_except,userVO);

    }


    @Test
    void getRewardList() throws ParseException {
        Reward reward=new Reward();
        reward.setId(1);
        reward.setUserId(1);
        reward.setType("s");
        reward.setRewardNum(60.0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2020-05-17 20:39:39");
        reward.setApplyTime(date);

        //我期望的UserVo
        List<RewardVO> rewardVOS_except=new ArrayList<>();
        RewardVO rewardVO=new RewardVO();
        rewardVO.setReward(reward);
        rewardVO.setAccount(userService.getUserById(reward.getUserId()).getAccount());
        rewardVO.setName(userService.getUserById(reward.getUserId()).getName());
        rewardVOS_except.add(rewardVO);

        //比较与期望的结果
        assertEquals("测试错误",rewardVOS_except,rewardService.getRewardList());
    }
}