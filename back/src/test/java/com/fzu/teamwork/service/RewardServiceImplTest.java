package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.RewardDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.RewardType;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.RewardVO;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class RewardServiceImplTest {
    @Autowired
    private RewardServiceImpl rewardService;

    @Autowired
    private UserService userService;



    @Resource
    private RewardDao rewardDao;


    private User user;
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

        int code = userService.insertUser(user);
        if(code == 0){
            System.out.println("添加成功,用户id为：" + user.getId());
            userVO = userService.convertToUserVo(user);
        }else{
            System.out.println("用户添加失败，状态码为:" + code);
        }
    }

    @AfterEach
    public void deleteTestData(){
        //删除所创建的用于测试的数据
        userService.deleteUsers(user.getId());
    }


    //测试申请党员服务时长奖励函数
    @Test
    public void insertRewardOfService() {
        //申请类别为党员服务时长
        reward=new Reward();
        reward.setUserId(user.getId());
        reward.setType(RewardType.SERVICE); //党员服务时长
        reward.setApplyTime(new Date());
        int score;

        //设置充足积分（10个党员时长）
        userVO.getAccountData().setScore(10* RewardType.SERVICE_SCORE);
        userService.updateUser(userVO);
        reward.setRewardNum(5.0);//申请5个时长
        //获取账号当前积分
        score = userVO.getAccountData().getScore();
        //判断申请结果
        Assert.assertTrue(rewardService.insertReward(reward));
        //判断用户剩余积分数
        userVO = userService.convertToUserVo(user);//获取最新的账号数据
        score = (int)(score - reward.getRewardNum()*RewardType.SERVICE_SCORE);
        Assert.assertEquals(score, (long)userVO.getAccountData().getScore());
        //判断申请记录是否成功保存到数据库
        Assert.assertNotNull(rewardDao.selectByPrimaryKey(reward.getId()));

        //申请超过积分总数的时长
        reward.setRewardNum(10.0);
        //获取当前最新账户数据
        userVO = userService.convertToUserVo(user);
        score = userVO.getAccountData().getScore();
        //检测判定结果是否为false
        Assert.assertFalse(rewardService.insertReward(reward));
        //判定用户积分有没有减少
        userVO = userService.convertToUserVo(user);//获取当前最新数据
        Assert.assertEquals(score, (long)userVO.getAccountData().getScore());
    }

    //测试申请党员服务综测奖励函数
    @Test
    public void insertRewardOfSynthetic() {
        //申请类别为党员服务时长
        reward=new Reward();
        reward.setUserId(user.getId());
        reward.setType(RewardType.SYNTHETIC); //综测
        reward.setApplyTime(new Date());
        int score;

        //设置充足积分（10分综测）
        userVO.getAccountData().setScore(10* RewardType.SYNTHETIC_SCORE);
        userService.updateUser(userVO);
        reward.setRewardNum(5.0);//申请5分综测
        //获取账号当前积分
        score = userVO.getAccountData().getScore();
        //判断申请结果
        Assert.assertTrue(rewardService.insertReward(reward));
        //判断用户剩余积分数
        userVO = userService.convertToUserVo(user);//获取最新的账号数据
        score = (int)(score - reward.getRewardNum()*RewardType.SYNTHETIC_SCORE);
        Assert.assertEquals(score, (long)userVO.getAccountData().getScore());
        //判断申请记录是否成功保存到数据库
        Assert.assertNotNull(rewardDao.selectByPrimaryKey(reward.getId()));

        //申请超过积分总数的时长
        reward.setRewardNum(10.0);
        //获取当前最新账户数据
        userVO = userService.convertToUserVo(user);
        score = userVO.getAccountData().getScore();
        //检测判定结果是否为false
        Assert.assertFalse(rewardService.insertReward(reward));
        //判定用户积分有没有减少
        userVO = userService.convertToUserVo(user);//获取当前最新数据
        Assert.assertEquals(score, (long)userVO.getAccountData().getScore());
    }

    //申请错误类型奖励
    @Test
    public void insertRewardOfError(){
        //申请类别为党员服务时长
        reward=new Reward();
        reward.setUserId(user.getId());
        reward.setType("abc"); //错误奖励类型
        reward.setApplyTime(new Date());
        reward.setRewardNum(10.0);

        //判断申请结果是否错误
        Assert.assertFalse(rewardService.insertReward(reward));
    }

//    @Test
//    void calculateScore()
//    {
//        //测试积分不够的例子
//        reward=new Reward();
//        reward.setId(2);
//        reward.setUserId(user.getId());
//        reward.setType(RewardType.ServiceTime);
//        reward.setRewardNum(0.0);
//        Date date=new Date();
//        reward.setApplyTime(date);
//        userVO.getAccountData().setScore(0);//设置足够的积分
//        userService.updateUser(userVO);
//        assertEquals("测试错误",false,rewardService.insertReward(reward));
//
//        //测试积分充足的例子
//        reward.setRewardNum(5.0);
//        userVO.getAccountData().setScore(9000);//设置足够的积分
//        userService.updateUser(userVO);
//        assertEquals("测试错误",true,rewardService.insertReward(reward));
//
//    }
//
//
//    @Test
//    void calculate()
//    {
//        Reward reward=new Reward();
//
//        //申请类型错误
//        reward.setId(2);
//        reward.setUserId(user.getId());
//        reward.setType("error");
//        reward.setRewardNum(0.0);
//        Date date=new Date();
//        reward.setApplyTime(date);
//
//        assertEquals("测试错误",-1,rewardService.calculate(reward.getType(),reward.getRewardNum()));
//
//
//    }
//
//

    //测试获取奖励申请记录列表
    @Test
    void getRewardList(){
        //设置充足积分（100分综测的积分）
        userVO.getAccountData().setScore(100*RewardType.SYNTHETIC_SCORE);
        //更新数据库账号数据
        userService.updateUser(userVO);
        Reward reward;
        Date date = new Date();
        List<RewardVO> rewardList;
        for(int i = 0; i < 10; i++ ){
            reward = new Reward();
            reward.setUserId(user.getId());
            if(i%2 ==0){
                reward.setType(RewardType.SYNTHETIC); //综测
            }else{
                reward.setType(RewardType.SERVICE);//时长
            }
            date = new Date(date.getTime() + i*24*60*3600);
            reward.setApplyTime(date);
            reward.setRewardNum((double)i + 1);
            Assert.assertTrue(rewardService.insertReward(reward));
        }
        //获取回复列表
        rewardList = rewardService.getRewardList();
        //判断是否是按时间由近到远
        for(int i = 1; i < rewardList.size(); i++){
            Assert.assertTrue(rewardList.get(i-1).getReward().getApplyTime().getTime()
                    >= rewardList.get(i).getReward().getApplyTime().getTime());
        }
    }
}