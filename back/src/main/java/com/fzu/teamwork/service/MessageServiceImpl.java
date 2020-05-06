package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.MessageDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.MessageWay;
import com.fzu.teamwork.view.MessagePage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        getButtonList(uid);
        page.setMessages(messageList);
        return page;
    }

    //获取分页下面的按钮列表
    public void getButtonList(int uid){
        //每页有几条数据
        int count = messagePage.getCount();
        //问题总条数
        MessageExample example = new MessageExample();
        example.createCriteria().andObjectIdEqualTo(uid);
        int total = (int)messageDao.countByExample(example);
        //当前页号
        int pageIndex = messagePage.getPageIndex();
        //总页数
        int maxIndex = total%count == 0 ? total/count : total/count +1;
        List<Integer> list = new ArrayList<>();
        //按钮的最后一个是当前页+5/最大页
        int lastIndex = (pageIndex + 5 > maxIndex) ? maxIndex : pageIndex + 5;
        //按钮列表的第一个
        int startIndex;
        if(lastIndex == pageIndex + 5){//最后一个没到最后一页，第一个就是当前页
            startIndex = pageIndex;
        }else{//最后一个是最后一页，第一个是
            int t = 5 - (maxIndex - pageIndex + 1);//前面要补几个按钮
            startIndex = pageIndex - t > 0 ? pageIndex - t : 1;
        }

        for(int i = startIndex; i <= lastIndex; i++){
            list.add(i);
        }
        messagePage.setButtonList(list);

        messagePage.setHasPrevious(true);//是否有上一页
        messagePage.setHasNext(true);//是否还有下一页
        messagePage.setPageNum(maxIndex);//设置总页数
        if(pageIndex == 1){
            messagePage.setHasPrevious(false);
        }
        if(pageIndex == maxIndex){
            messagePage.setHasNext(false);
        }
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
        if(internalMessage.getWay().equals(MessageWay.RESPONSE)){
            //回复问题消息
            createMessageOperateStrategy(1, internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.ATTENTION)){
            //关注问题消息
            createMessageOperateStrategy(2, internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.REPORT_RESPONSE)){
            //投诉某个问题
            createMessageOperateStrategy(3,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.DISLIKE_RESPONSE)){
            //点灭某条回复
            createMessageOperateStrategy(4,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.LIKE_RESPONSE)){
            //点赞某条回复
            createMessageOperateStrategy(5,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.REPORT_QUESTION)){
            //投诉某个问题
            createMessageOperateStrategy(6,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.DELETE_RESPONSE)){
            //删除某条回复
            createMessageOperateStrategy(7,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.DELETE_QUESTION)){
            //删除某个问题
            createMessageOperateStrategy(8,internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.CREATE_QUESTION)){
            //创建问题
            createMessageOperateStrategy(9, internalMessage);
        }else if(internalMessage.getWay().equals(MessageWay.CREATE_RESPONSE)){
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
