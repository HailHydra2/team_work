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

    //管理员界面获取奖励申请记录列表(具体实现)
    @GetMapping("/rewards")
    public @ResponseBody List<RewardVO> testGetRewards(){
        return rewardService.getRewardList();
    }

    //奖励兑换页面申请兑换奖励（具体实现）
    @PostMapping("/reward")
    public @ResponseBody AjaxResponse testAddReward(@RequestBody Reward reward){
        UserVO userVO = rewardService.insertReward(reward);
        return AjaxResponse.success(userVO);
    }
}
