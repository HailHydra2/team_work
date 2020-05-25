package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.ScoreNum;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class QuestionServiceImplTest{

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    private User user;
    private UserVO userVO;

    @BeforeEach
    public void createTestUser(){
        user = new User();
        user.setAccount("221701422");
        user.setIdentity(UserIdentity.student);
        user.setName("testWSH");
        user.setPassword(Encryptor.encrypt("11X"));
        user.setIdCard(Encryptor.encrypt("13072619990713611X"));
        int code = userService.addUser(user);
        if(code == 0){
            System.out.println("添加成功,用户id为：" + user.getId());
            userVO = userService.convertToUserVo(user);
        }else{
            System.out.println("用户添加失败，状态码为:" + code);
        }
    }

    @AfterEach
    //删除用户
    public void destroyUser(){
        userService.deleteUsers(user.getId());
    }

    @Test
    void getQuestionById() {
        System.out.println(user.getId());
    }

    @Test
    void convertToVO() {
    }

    @Test
    void updateQuestion() {
    }


    //添加问题测试
    @Test
    void addQuestion() {
        QuestionVO questionVO = new QuestionVO();
        Question question = new Question();
        questionVO.setQuestion(question);
        questionVO.setTitle("title1");
        questionVO.setContent("content1");
        question.setAuthorId(user.getId());
        question.setCreateTime(new Date());
        questionService.addQuestion(questionVO);

        int questionNum = userVO.getAccountData().getQuestionNum();
        int score = userVO.getAccountData().getScore();

        //查看是否成功插入数据库
        Assert.assertEquals(questionService.getQuestionById(question.getId()).getId(), question.getId());
        userVO = userService.convertToUserVo(user);
        //用户提问数是否+1
        Assert.assertEquals(questionNum+1, (long)userVO.getAccountData().getQuestionNum());
        //用户积分是否有增加
        Assert.assertEquals(score + ScoreNum.CREATE_QUESTION_SCORE, (long)userVO.getAccountData().getScore());
    }

    @Test
    void getQuestionPage() {
    }

    @Test
    void getQuestionPage1() {
    }

    @Test
    void getAttentionQuestionPage() {
    }

    @Test
    void deleteQuestionById() {
    }

    @Test
    void deleteQuestionsById() {
    }

    @Test
    void convertToVOList() {
    }

    @Test
    void addRelationToUId() {
    }
}