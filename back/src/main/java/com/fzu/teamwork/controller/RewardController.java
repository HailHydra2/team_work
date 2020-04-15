package com.fzu.teamwork.controller;


import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Reward;
import com.fzu.teamwork.view.RewardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class RewardController {

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
}
