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

    @Resource(name = "responseServiceImpl")
    private ResponseService responseService;

    //用于获取消息列表的策略类
    private MessageStrategy messageStrategy;
    //用于存储消息列表的某个分页
    private MessagePage messagePage;
    //用于根据消息对实体数据进行处理的策略类
    private MessageOperateStrategy operateStrategy;

    /*创建获取消息列表的策略器(uid是用户id)
    *type=1：获取某个用户的消息列表
    */
    public void createMessageStrategy(int type, int uid){
        if(type == 1){
            messageStrategy = new MessageByUid(uid, messagePage, messageDao);
        }
    }

    /* 创建根据消息对实体数据进行处理的策略对象
     * type=1：对回复问题消息处理的策略对象
     * type=2：对关注问题消息处理的策略对象
     * type=3：对投诉某条回复消息处理的策略对象
     * type=4：对点灭某条回复消息处理的策略对象
     * type=5：点赞某条回复消息处理的策略对象
     * type=6：投诉某个问题消息处理的策略对象
     * type:7：删除某条回复消息处理的策略对象
     * type=8：删除某个问题消息处理的策略对象
     * type=9：创建问题消息处理的策略对象
     * type=10：创建回复消息处理的策略对象
     */
    public void createMessageOperateStrategy(int type, InternalMessage message){
        if(type == 1){//对回复问题消息的处理策略对象
            operateStrategy = new MessageResQStrategy(message,userService,questionService);
        }else if(type == 2){//对关注问题消息处理的策略对象
            operateStrategy = new MessageAQStrategy(message,userService);
        }else if(type ==3){//投诉某条回复
            operateStrategy = new MessageRRStrategy(message, userService, responseService);
        }else if(type == 4) {//点灭某条回复
            operateStrategy = new MessageDLRStrategy(message,responseService);
        }else if(type ==5) {//点赞某条回复
            operateStrategy = new MessageLRStrategy(message,userService,responseService);
        }else if(type == 6) {//投诉某个问题
            operateStrategy = new MessageRepQStrategy(message, userService, questionService);
        }else if(type == 7) {//删除某条回复
            operateStrategy = new MessageDelRStrategy(message,userService,responseService,questionService);
        }else if(type == 8) {//删除某个问题
            operateStrategy = new MessageDelQStrategy(message,questionService,userService);
        }else if(type == 9) {//创建问题
            operateStrategy = new MessageCQStrategy(message, userService, questionService);
        }else if(type == 10) {//创建回复
            operateStrategy = new MessageCRStrategy(message, userService, responseService, questionService);
        }else{
            log.info("MessageService 策略对象type错误");
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
            //回复问题消息
            createMessageOperateStrategy(1, internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.attention)){
            //关注问题消息
            createMessageOperateStrategy(2, internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.reportResponse)){
            //投诉某个问题
            createMessageOperateStrategy(3,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.dislikeResponse)){
            //点灭某条回复
            createMessageOperateStrategy(4,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.likeResponse)){
            //点赞某条回复
            createMessageOperateStrategy(5,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.reportQuestion)){
            //投诉某个问题
            createMessageOperateStrategy(6,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.deleteResponse)){
            //删除某条回复
            createMessageOperateStrategy(7,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.deleteQuestion)){
            //删除某个问题
            createMessageOperateStrategy(8,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.createQuestion)){
            //创建问题
            createMessageOperateStrategy(9, internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.createResponse)){
            //创建回复
            createMessageOperateStrategy(10,internalMessage);
        }else{
            log.info("MessageService messageWay 错误");
        }

        //根据消息对实体对象的数据进行处理更新,并获取要保存的消息
        Message message = operateStrategy.operate();

        //将消息插入数据库保存
        if(message != null){
            //需要保存的消息
            messageDao.insert(message);
        }
    }
}
