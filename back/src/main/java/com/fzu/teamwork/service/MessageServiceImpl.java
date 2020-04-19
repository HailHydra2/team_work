package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.MessageDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.MessageWay;
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

    @Resource(name = "userServiceImpl")
    private UserService userService;

    @Resource(name = "questionServiceImpl")
    private QuestionService questionService;

    //用于获取消息列表的策略类
    private MessageStrategy messageStrategy;
    //用于存储消息列表的某个分页
    private MessagePage messagePage;
    //用于根据消息对实体数据进行处理的策略类
    private MessageOperateStrategy operateStrategy;

    /*创建获取消息列表的策略器(uid是用户id)
    *type=1：获取某个用户的问题列表
    */
    public void createMessageStrategy(int type, int uid){
        if(type == 1){
            messageStrategy = new MessageByUid(uid, messagePage, messageDao);
        }
    }

    /* 创建根据消息对实体数据进行处理的策略对象
     * type=1：对回复问题消息的处理策略对象
     */
    public void createMessageOperateStrategy(int type, InternalMessage message){
        if(type == 1){//对回复问题消息的处理策略对象
            operateStrategy = new MessageRQStrategy(message,userService,questionService);
        }
    }

    //获取用户的消息的某个分页
    @Override
    public MessagePage getMessagePageByUid(int uid, MessagePage page){
        this.messagePage = page;
        //创建对应的列表获取策略对象
        createMessageStrategy(1, uid);
        List<Message> messageList = messageStrategy.getMessageList();
        page.setMessages(messageList);
        return page;
    }

    //删除某个用户的所有消息(返回删除消息条数)
    @Override
    public int deleteUserMessage(int uid){
        MessageExample example = new MessageExample();
        example.createCriteria().andObjectIdEqualTo(uid);
        //删除object_id为uid(被操作者为udi)的消息，并返回删除条数
        int num = messageDao.deleteByExample(example);
        return num;
    }

    //根据消息对实体对象的数据进行更新
    @Override
    public void updateInfoByMessage(InternalMessage internalMessage){
        //判断消息类型，创建需要的策略对象
        if(internalMessage.getWay().equals(MessageWay.response)){
            //是回复问题消息
            createMessageOperateStrategy(1, internalMessage);
        }

        //根据消息对实体对象的数据进行处理更新,并获取要保存的消息
        Message message = operateStrategy.operate();
        //将消息插入数据库保存
        messageDao.insert(message);
    }
}
