package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.MessageDao;
import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.InternalMessage;
import com.fzu.teamwork.model.Message;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.util.MessageWay;
import com.fzu.teamwork.view.MessagePage;
import com.fzu.teamwork.view.UserVO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceImplTest extends LCH_testFather{

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Resource
    private MessageDao messageDao;

    @Test
    void getMessagePageByUid() {

        List<Message> messages=new ArrayList<>();
        Message message=new Message();
        message.setId(3);
        message.setObjectId(2);
        message.setWay(MessageWay.RESPONSE);
        message.setDescription("学生1回复了您\"四级英语考试如何准备呢？");
        messages.add(message);

        //我期望的messagePage
        MessagePage messagePage_except=new MessagePage();
        messagePage_except.setMessages(messages);
        messagePage_except.setPageNum(10);
        messagePage_except.setCount(6);
        messagePage_except.setHasNext(true);
        messagePage_except.setHasPrevious(true);
        messagePage_except.setPageIndex(1);

        //比较与期望的结果
        Assert.assertEquals("测试错误",messagePage_except.getMessages(),messageService.getMessagePageByUid(2,messagePage_except).getMessages());

    }

    @Test
    void deleteUserMessage() {
        //比较和期望的结果
        Assert.assertEquals("测试错误",4,messageService.deleteUserMessage(3));
    }

    @Test
    void updateInfoByMessage1() {

        InternalMessage internalMessage=new InternalMessage();
        //internalMessage.setWay(MessageWay.RESPONSE);
        internalMessage.setObject_id(26);
        internalMessage.setOperator_id(2);
        internalMessage.setFlag(1);
        internalMessage.setFlag2(2);

        messageService.updateInfoByMessage(internalMessage);
        //比较预期的结果
        Assert.assertEquals("测试错误",internalMessage.getWay(),messageDao.selectByPrimaryKey(17).getWay());

    }

    @Test
    void updateInfoByMessage2() {

        InternalMessage internalMessage=new InternalMessage();
        //internalMessage.setWay(MessageWay.RESPONSE);
        internalMessage.setObject_id(26);
        internalMessage.setOperator_id(2);
        internalMessage.setFlag(1);
        internalMessage.setFlag2(2);

        User user=new User();
        user.setId(2);
        user.setAccount("s1");
        user.setPassword("555");
        user.setName("学生1");
        user.setIdCard("112");
        user.setIdentity("student");
        user.setAccountDataId(1);
        AccountData accountData=new AccountData();
        accountData.setId(1);
        accountData.setLevel(0);
        accountData.setExperienceValue(220);
        accountData.setScore(212);
        accountData.setFocusNum(18);
        accountData.setQuestionNum(6);
        accountData.setResponseNum(10);
        //我期待的uesrVo
        UserVO userVO_except=new UserVO();
        userVO_except.setUser(user);
        userVO_except.setAccountData(accountData);

        internalMessage.setWay(MessageWay.ATTENTION);
        messageService.updateInfoByMessage(internalMessage);

        //比较预期的结果
        Assert.assertEquals("测试错误",userVO_except,userService.convertToUserVo(userService.getUserById(internalMessage.getOperator_id())));

    }
}