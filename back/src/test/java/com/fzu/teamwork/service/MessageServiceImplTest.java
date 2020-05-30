package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Message;
import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.MessagePage;
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
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class MessageServiceImplTest {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private MessageService messageService;

    private User user;
    private UserVO userVO;

    @BeforeEach
    public void createTestUser(){
        user = new User();
        user.setAccount("221701422");
        user.setIdentity(UserIdentity.student);
        user.setName("testWSH");
        user.setPassword(Encryptor.encrypt("873"));
        user.setIdCard(Encryptor.encrypt("360102199003077873"));
        int code = userService.addUser(user);
        if(code == 0){
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

    public QuestionVO addQuestion(int i){
        QuestionVO questionVO = new QuestionVO();
        Question question = new Question();
        questionVO.setQuestion(question);
        questionVO.setTitle("title" + i);
        questionVO.setContent("content" + i);
        question.setAuthorId(user.getId());
        question.setCreateTime(new Date());
        questionService.addQuestion(questionVO);
        return questionVO;
    }

    //测试获取消息页面函数
    @Test
    void getMessagePageByUid() {
        MessagePage page = new MessagePage();
        page.setPageIndex(1);
        page.setCount(12);

        //循环添加10个问题（创建10条消息记录）
        for(int i = 0; i < 12; i++){
            addQuestion(i);
        }

        messageService.getMessagePageByUid(user.getId(),page);
        //验证所有消息是否都获取到了
        Assert.assertEquals(12, page.getMessages().size());

        for(Message message : page.getMessages()){
            //验证所有消息是否都是查询用户的
            Assert.assertEquals(user.getId(), message.getObjectId());
        }

        //有分页的情况
        page.setCount(2);
        messageService.getMessagePageByUid(user.getId(), page);
        Assert.assertEquals(2, page.getMessages().size());
        Assert.assertEquals(1, page.getPageIndex());
    }
}