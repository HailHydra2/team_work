package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Attention;
import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AttentionServiceImplTest {
    @Autowired
    private AttentionService attentionService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    private User user;
    private UserVO userVO;

    Question question;
    QuestionVO questionVO;

    @BeforeEach
    public void createTestData(){
        user = new User();
        user.setAccount("221701118");
        user.setIdentity(UserIdentity.student);
        user.setName("testZJW");
        user.setPassword(Encryptor.encrypt("11X"));
        user.setIdCard(Encryptor.encrypt("13072619990713611X"));
        int code = userService.addUser(user);
        if(code == 0){
            System.out.println("添加成功,用户id为：" + user.getId());
            userVO = userService.convertToUserVo(user);
        }else{
            System.out.println("用户添加失败，状态码为:" + code);
        }

        question = new Question();
        questionVO = new QuestionVO();
        questionVO.setTitle("testTitle");
        questionVO.setContent("testContent");
        question.setAuthorId(user.getId());
        question.setCreateTime(new Date());
        questionVO.setQuestion(question);
        questionService.addQuestion(questionVO);
    }

    @AfterEach
    public void deleteTestData(){
        //questionService.deleteQuestionById(question.getId());
        userService.deleteUsers(user.getId());
    }

    @Test
    void insertAttention() {
        Attention attention = new Attention();
        attention.setUserId(user.getId());
        attention.setQuestionId(question.getId());
        attention.setCreateTime(new Date());
        attention.setFlag(1);
        attentionService.insertAttention(attention);

        Assert.assertEquals(1,(long)attention.getFlag());
    }

    @Test
    void getAttentionQuestionList() {
    }
}