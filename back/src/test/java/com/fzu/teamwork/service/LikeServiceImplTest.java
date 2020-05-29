package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.LikesDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.ScoreNum;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class LikeServiceImplTest {
    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private LikeService likeService;

    @Resource
    private ResponseDao responseDao;

    @Resource
    private UserDao userDao;

    private User user;
    private UserVO userVO;

    private Question question;
    private QuestionVO questionVO;

    private Response response;
    private ResponseVO responseVO;

    private Likes likes;

    @Resource
    private LikesDao likesDao;

    private int userScore;
    private int likeNum;
    private int dislikeNum;
    private boolean result;

    @BeforeEach
    public void createTestData(){
        //新增用于测试的创建问题并回复点赞的用户
        user = new User();
        user.setAccount("221701118");
        user.setIdentity(UserIdentity.student);
        user.setName("testZJW");
        user.setPassword(Encryptor.encrypt("314"));
        user.setIdCard(Encryptor.encrypt("110101199603070314"));
        int code = userService.addUser(user);
        if(code == 0){
            System.out.println("添加成功,用户id为：" + user.getId());
            userVO = userService.convertToUserVo(user);
        }else{
            System.out.println("用户添加失败，状态码为:" + code);
        }
        //新增用于测试的待回复问题
        question = new Question();
        questionVO = new QuestionVO();
        questionVO.setTitle("testTitle");
        questionVO.setContent("testContent");
        question.setAuthorId(user.getId());
        question.setCreateTime(new Date());
        questionVO.setQuestion(question);
        questionService.addQuestion(questionVO);
        //新增用于测试的待点赞回复
        response = new Response();
        responseVO = new ResponseVO();
        responseVO.setContent("testResponseContent");
        response.setQuestionId(question.getId());
        response.setAuthorId(user.getId());
        response.setCreateTime(new Date());
        responseVO.setResponse(response);
        responseService.insertResponse(responseVO);
        //新增用于测试的点赞记录
        likes = new Likes();
        likes.setUserId(user.getId());
        likes.setResponseId(response.getId());
        likes.setFlag(1);
    }

    @AfterEach
    public void deleteTestData(){
        //删除所创建的用于测试的数据
        responseService.deleteResponseById(response.getId());
        questionService.deleteQuestionById(question.getId());
        userService.deleteUsers(user.getId());
    }

    @Test
    void insertLikeInfo() {
        likeNum = response.getLikeNum();//获取点赞前的点赞数
        user = userDao.selectByPrimaryKey(response.getAuthorId());
        userVO = userService.convertToUserVo(user);
        userScore = userVO.getAccountData().getScore();
        result = likeService.insertLikeInfo(likes);//点赞
        response = responseDao.selectByPrimaryKey(likes.getResponseId());//获取最新的回复数据
        responseVO = responseService.convertToVO(response);
        //检验是否点赞成功
        Assert.assertEquals(likes.getFlag(),likesDao.selectByPrimaryKey(likes.getId()).getFlag());
        //检验回复的点赞数是否+1
        Assert.assertEquals(likeNum + 1,(long)response.getLikeNum());
        //检验用户积分是否正确添加
        userVO = userService.convertToUserVo(user);
        Assert.assertEquals(userScore + ScoreNum.LIKE_SCORE,(long)userVO.getAccountData().getScore());
        //检验返回值是否为true
        Assert.assertEquals(true,result);

        //检验取消点赞是否成功
        likes.setFlag(0);
        likeNum = response.getLikeNum();//获取取消前点赞数
        result = likeService.insertLikeInfo(likes);
        Assert.assertEquals(likes.getFlag(),likesDao.selectByPrimaryKey(likes.getId()).getFlag());
        //检验回复的点赞数是否-1
        response = responseDao.selectByPrimaryKey(likes.getResponseId());
        Assert.assertEquals(likeNum - 1,(long)response.getLikeNum());
        //检验用户积分是否正确扣除
        userVO = userService.convertToUserVo(user);
        Assert.assertEquals(userScore,(long)userVO.getAccountData().getScore());
        //检验返回值是否为true
        Assert.assertEquals(true,result);

        //检验点灭是否成功
        likes.setFlag(-1);
        dislikeNum = response.getDislikeNum();//获取点灭前的点灭数
        result = likeService.insertLikeInfo(likes);
        Assert.assertEquals(likes.getFlag(),likesDao.selectByPrimaryKey(likes.getId()).getFlag());
        //检验回复的点灭数是否+1
        response = responseDao.selectByPrimaryKey(likes.getResponseId());
        Assert.assertEquals(dislikeNum + 1,(long)response.getDislikeNum());
        //检验返回值是否为true
        Assert.assertEquals(true,result);

        //检验点灭是否成功
        likes.setFlag(0);
        dislikeNum = response.getDislikeNum();//获取取消点灭前的点灭数
        result = likeService.insertLikeInfo(likes);
        Assert.assertEquals(likes.getFlag(),likesDao.selectByPrimaryKey(likes.getId()).getFlag());
        //检验回复的点灭数是否-1
        response = responseDao.selectByPrimaryKey(likes.getResponseId());
        Assert.assertEquals(dislikeNum - 1,(long)response.getDislikeNum());
        //检验返回值是否为true
        Assert.assertEquals(true,result);

        //检验回复不存在时是否返回false
        likes.setResponseId(response.getId() + 1);
        result = likeService.insertLikeInfo(likes);
        Assert.assertEquals(false,result);
    }
}