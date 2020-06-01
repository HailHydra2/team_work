package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.AdminLimit;
import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Reward;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.RewardService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.ErrorStatus;
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

    @Resource(name = "userServiceImpl")
    UserService userService;

    //管理员界面获取奖励申请记录列表(具体实现)
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @GetMapping("/rewards")
    public @ResponseBody List<RewardVO> getRewards(){
        return rewardService.getRewardList();
    }

    //奖励兑换页面申请兑换奖励（具体实现）
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/reward")
    public @ResponseBody AjaxResponse insertReward(@RequestBody Reward reward){
        Boolean result = rewardService.insertReward(reward);
        User user = userService.getUserById(reward.getUserId());
        UserVO userVO = userService.convertToUserVo(user);
        if(result){//积分充足
            return AjaxResponse.success(userVO);
        }else{//积分不足
            return AjaxResponse.error(ErrorStatus.SCORE_INSUFFICIENT, "积分不足", userVO);
        }
    }
}
