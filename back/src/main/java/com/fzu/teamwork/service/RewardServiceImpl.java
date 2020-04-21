package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.RewardDao;
import com.fzu.teamwork.model.Reward;
import com.fzu.teamwork.model.RewardExample;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.util.RewardType;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class RewardServiceImpl implements RewardService{

    @Resource(name = "userServiceImpl")
    private UserService userServiceImpl;

    @Resource
    RewardDao rewardDao;

    //用户账号信息
    private UserVO userVO;

    @Override
    public UserVO insertReward(Reward reward){
        //查找申请用户
        User user = userServiceImpl.getUserById(reward.getUserId());
        //获取用户的账户信息（将user对象转换为userVO对象）
        userVO = userServiceImpl.convertToUserVo(user);
        //计算用户所需积分（若积分充足返回所需积分，不足返回-1）
        int needScore = doesLeagal(reward);
        //判断用户积分是否充足
        if(needScore > 0) {
            //积分充足
            //用户当前积分
            int score = userVO.getAccountData().getScore();
            //扣除兑换积分
            userVO.getAccountData().setScore(score - needScore);
            //更新用户数据
            userServiceImpl.updateUser(userVO);
        }
        rewardDao.insert(reward);
        //返回用户信息
        return userVO;
    }


    //判断用户的积分是否足够申请, 返回值为所需积分（积分不足返回-1）
    public int doesLeagal(Reward reward){
        //计算所需积分数
        int needScore = calculateScore(reward.getType(), reward.getRewardNum());
        if(userVO.getAccountData().getScore() >= needScore){
            //积分充足
            return needScore;
        }else{
            //积分不足
            return -1;
        }
    }

    //计算申请的积分数（类别兑换规则*数量），若类别非法返回-1
    public int calculateScore(String rewardType, double num){
        if(rewardType.equals(RewardType.ServiceTime)){
            //申请类别为党员服务时长
            return (int)(num*20);//一个时长对应20积分
        }else if(rewardType.equals(RewardType.SyntheticTest)){
            //兑换类别为综测
            return (int)(num*30);//一分综测对应30积分
        }else{
            log.info("申请类别输入错误：{}",rewardType);
            return -1;
        }
    }

    //获取奖励申请列表
    @Override
    public List<Reward> getRewardList(){
        RewardExample example = new RewardExample();
        example.setOrderByClause("`APPLY_TIME` DESC");
        List<Reward> rewardsList = rewardDao.selectByExample(example);
        return rewardsList;
    }

}
