package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.RewardType;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.RewardVO;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

class RewardServiceImplTest extends LCH_testFather{
    @Autowired
    private RewardServiceImpl rewardService;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountDataServiceImpl accountDataService;


    private User user;
    private AccountData accountData;
    private UserVO userVO;
    private Reward reward;


    @BeforeEach
    public void createTestData(){  // 复制绍鸿的代码
        //新增用于测试的创建问题并回复点赞的用户
        user = new User();
        user.setAccount("221701118");
        user.setIdentity(UserIdentity.student);
        user.setName("testZJW");
        user.setPassword(Encryptor.encrypt("779"));
        user.setIdCard(Encryptor.encrypt("420102200403070779"));

        int code = userService.addUser(user);
        if(code == 0){
            System.out.println("添加成功,用户id为：" + user.getId());
            userVO = userService.convertToUserVo(user);
        }else{
            System.out.println("用户添加失败，状态码为:" + code);
        }

        accountData=accountDataService.getById(user.getAccountDataId());
        userVO.getAccountData().setScore(999);
        userService.updateUser(userVO);


    }

    @AfterEach
    public void deleteTestData(){
        //删除所创建的用于测试的数据
        userService.deleteUsers(user.getId());
        
    }





    @Test
    void insertReward() {

        //申请类别为党员服务时长
        reward=new Reward();
        reward.setUserId(user.getId());
        reward.setType(RewardType.ServiceTime); //党员服务时长
        reward.setRewardNum(5.0);
        Date date=new Date();
        reward.setApplyTime(date);
        assertEquals("测试错误",true,rewardService.insertReward(reward));

        //申请类别为综测
        reward=new Reward();
        reward.setUserId(user.getId());
        reward.setType(RewardType.SyntheticTest); //综测
        reward.setRewardNum(5.0);
        reward.setApplyTime(date);
        assertEquals("测试错误",true,rewardService.insertReward(reward));

    }

    @Test
    void calculateScore()
    {
        //测试积分不够的例子
        reward=new Reward();
        reward.setId(2);
        reward.setUserId(user.getId());
        reward.setType(RewardType.ServiceTime);
        reward.setRewardNum(0.0);
        Date date=new Date();
        reward.setApplyTime(date);
        userVO.getAccountData().setScore(0);//设置足够的积分
        userService.updateUser(userVO);
        assertEquals("测试错误",false,rewardService.insertReward(reward));

        //测试积分充足的例子
        reward.setRewardNum(5.0);
        userVO.getAccountData().setScore(9000);//设置足够的积分
        userService.updateUser(userVO);
        assertEquals("测试错误",true,rewardService.insertReward(reward));

    }


    @Test
    void calculate()
    {
        Reward reward=new Reward();

        //申请类型错误
        reward.setId(2);
        reward.setUserId(user.getId());
        reward.setType("error");
        reward.setRewardNum(0.0);
        Date date=new Date();
        reward.setApplyTime(date);

        assertEquals("测试错误",-1,rewardService.calculate(reward.getType(),reward.getRewardNum()));


    }




    @Test
    void getRewardList() throws ParseException {

        //设置reward
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