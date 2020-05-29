package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.AttentionDao;
import com.fzu.teamwork.dao.QuestionDao;
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

import javax.annotation.Resource;
import javax.swing.*;

import java.util.Date;
import java.util.List;

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
    @Resource
    private QuestionDao questionDao;

    private User user;
    private UserVO userVO;

    private Question question;
    private QuestionVO questionVO;

    private Attention attention;

    @Resource
    private AttentionDao attentionDao;

    @BeforeEach
    public void createTestData(){
        //新增用于测试的创建问题并关注的用户
        user = new User();
        user.setAccount("221701118");
        user.setIdentity(UserIdentity.student);
        user.setName("testZJW");
        user.setPassword(Encryptor.encrypt("672"));
        user.setIdCard(Encryptor.encrypt("110101199603070672"));
        int code = userService.addUser(user);
        if(code == 0){
            System.out.println("添加成功,用户id为：" + user.getId());
            userVO = userService.convertToUserVo(user);
        }else{
            System.out.println("用户添加失败，状态码为:" + code);
        }
        //新增用于测试的待关注问题
        question = new Question();
        questionVO = new QuestionVO();
        questionVO.setTitle("testTitle");
        questionVO.setContent("testContent");
        question.setAuthorId(user.getId());
        question.setCreateTime(new Date());
        questionVO.setQuestion(question);
        questionService.addQuestion(questionVO);
        //新增关注记录
        attention = new Attention();
        attention.setUserId(user.getId());
        attention.setQuestionId(question.getId());
        attention.setCreateTime(new Date());
        attention.setFlag(1);
        attentionService.insertAttention(attention);
    }

    @AfterEach
    public void deleteTestData(){
        //删除所创建的用于测试的数据
        questionService.deleteQuestionById(question.getId());
        userService.deleteUsers(user.getId());
    }

    @Test
    void insertAttention() {
        int focusNum = userVO.getAccountData().getFocusNum();
        //检验是否关注成功
        Assert.assertEquals((long)attention.getFlag(),(long)attentionDao.selectByPrimaryKey(attention.getId()).getFlag());
        //检验用户关注数是否+1
        userVO = userService.convertToUserVo(user);
        Assert.assertEquals(focusNum+1,(long)userVO.getAccountData().getFocusNum());
        //检验是否取消关注成功
        attention.setFlag(0);
        attentionService.insertAttention(attention);
        Assert.assertEquals((long)attention.getFlag(),(long)attentionDao.selectByPrimaryKey(attention.getId()).getFlag());
        //检验用户关注数是否-1
        focusNum = userVO.getAccountData().getFocusNum();
        userVO = userService.convertToUserVo(user);
        Assert.assertEquals(focusNum - 1,(long)userVO.getAccountData().getFocusNum());
        //检验关注不存在的问题后用户关注数是否改变
        focusNum = userVO.getAccountData().getFocusNum();
        while(true){
            int id = (int)Math.random()*10000;
            if(questionDao.selectByPrimaryKey(id) == null){
                attention.setQuestionId(id);
                break;
            }
        }
        attentionService.insertAttention(attention);
        userVO = userService.convertToUserVo(user);//获取最新的用户数据
        Assert.assertEquals(focusNum,(long)userVO.getAccountData().getFocusNum());
    }

}