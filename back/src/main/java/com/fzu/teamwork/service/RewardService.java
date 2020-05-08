package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Reward;
import com.fzu.teamwork.view.RewardVO;
import com.fzu.teamwork.view.UserVO;

import java.util.List;

public interface RewardService {

    //新建一个奖励申请记录，返回值申请结果(扣除相应积分)
    public Boolean insertReward(Reward reward);

    //获取奖励申请列表
    public List<RewardVO> getRewardList();


}
