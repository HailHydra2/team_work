package com.fzu.teamwork.model;

import com.fzu.teamwork.model.Message;
import com.fzu.teamwork.model.MessageOperateStrategy;
import com.fzu.teamwork.service.QuestionService;
import com.fzu.teamwork.service.ResponseService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.MessageWay;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.UserVO;

//MessageService对回复被删除消息进行处理的策略类(DelR: Delete Response)
public class MessageDelRStrategy extends MessageOperateStrategy {

    //被删除回复的作者
    private UserVO author;
    //被删除的回复
    private ResponseVO responseVO;
    //被删除回复所属的问题
    private QuestionVO questionVO;

    private UserService userService;
    private ResponseService responseService;
    private QuestionService questionService;

    //构造函数
    public MessageDelRStrategy(InternalMessage message, UserService userService,
                               ResponseService responseService,QuestionService questionService){
        this.responseService = responseService;
        this.userService = userService;
        this.questionService = questionService;
        //获取被删除问题的实体
        Response response = responseService.getResponseById(message.getObject_id());
        responseVO = responseService.convertToVO(response);
        //获取被删除问题的作者
        User user = userService.getUserById(response.getAuthorId());
        author = userService.convertToUserVo(user);
        //获取被删除问题所属的问题
        Question question = questionService.getQuestionById(response.getQuestionId());
        questionVO = questionService.convertToVO(question);
    }

    //根据消息进行处理,返回要保存的Message信息
    public Message operate(){
        //更新被删除消息作者的数据信息
        updateAuthor();
        //返回要保存到数据的消息
        return createMessage();
    }

    //更新被删除用户的数据（回复数-1）
    public void updateAuthor(){
        AccountData accountData = author.getAccountData();
        //被删除回复作者回复数-1
        accountData.setResponseNum(accountData.getResponseNum()-1);
        //更新用户数据
        author.setAccountData(accountData);
        //更新数据库
        userService.updateUser(author);
    }

    //创建保存到数据库的消息
    public Message createMessage(){
        Message message = new Message();
        //被删除回复作者是消息对象
        message.setObjectId(author.getUser().getId());
        //消息产生方式
        message.setWay(MessageWay.deleteResponse);
        //消息描述信息
        String description = "您关于\"" + questionVO.getTitle() + "\"的回复被管理员删除";
        message.setDescription(description);
        return message;
    }
}
