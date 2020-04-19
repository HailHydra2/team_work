package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.MessageDao;
import com.fzu.teamwork.model.Message;
import com.fzu.teamwork.model.MessageByUid;
import com.fzu.teamwork.model.MessageStrategy;
import com.fzu.teamwork.view.MessagePage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService{

    @Resource
    private MessageDao messageDao;

    //用于获取消息列表的策略类
    private MessageStrategy messageStrategy;
    //用于存储消息列表的某个分页
    private MessagePage messagePage;
    //用户id
    private int uid;

    /*创建获取消息列表的策略器
    *type=1：获取某个用户的问题列表
    */
    public void createMessageStrategy(int type){
        if(type == 1){
            messageStrategy = new MessageByUid(uid, messagePage, messageDao);
        }
    }

    //获取用户的消息的某个分页
    @Override
    public MessagePage getMessagePageByUid(int uid, MessagePage page){
        this.messagePage = page;
        this.uid = uid;
        //创建对应的列表获取策略对象
        createMessageStrategy(1);
        List<Message> messageList = messageStrategy.getMessageList();
        page.setMessages(messageList);
        return page;
    }
}
