package com.fzu.teamwork.model;

import com.fzu.teamwork.service.QuestionService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.MessageWay;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;
import io.swagger.models.auth.In;

//MessageService对回复问题消息进行处理的策略类
public class MessageRQStrategy extends MessageOperateStrategy{

    //操作消息
    private InternalMessage internalMessage;
    //用户服务层对象
    private UserService userService;
    //问题服务层对象
    private QuestionService questionService;
    //回复者
    private UserVO respondents;
    //问题作者
    private UserVO questionAuthor;
    //被回复的问题
    private QuestionVO question;

    //构造函数
    public MessageRQStrategy(InternalMessage message, UserService userService, QuestionService questionService){
        this.internalMessage = message;
        this.userService = userService;
        this.questionService = questionService;
    }

    //根据消息进行处理
    @Override
    public Message operate(){
        //回复者
        User user = userService.getUserById(internalMessage.getOperator_id());
        respondents = userService.convertToUserVo(user);
        //问题作者
        user = userService.getUserById(internalMessage.getObject_id());
        questionAuthor = userService.convertToUserVo(user);
        /****************
         * 注意question的对象的获取接口还未实现
         * 还未根据questionId获取question对象
         */

        //更新回复者账号数据（积分经验值等）
        updateRespondents();
        //更新问题作者数据
        updateAuthor();
        //更新问题数据（问题回复数）
        updateQuestion();
        //返回创建的消息
        return createMessage();
    }

    //对回复者数据进行更新
    private void updateRespondents(){
        AccountData accountData = respondents.getAccountData();
        //用户积分
        int score = accountData.getScore();
        //经验值
        int experienceValue = accountData.getExperienceValue();
        //回复数
        int responseNum = accountData.getResponseNum();

        score+=10;//回复问题积分+10
        experienceValue+=10;//回复问题经验值+10
        responseNum++;//回复数+1

        accountData.setScore(score);
        accountData.setExperienceValue(experienceValue);
        accountData.setResponseNum(responseNum);
        respondents.setAccountData(accountData);

        //更新用户数据
        userService.updateUser(respondents);
    }

    //对问题作者的数据进行更新
    private void updateAuthor(){
        //暂无什么可以更新的
    }

    //对问题数据进行更新
    private void updateQuestion(){
        int responseNum = question.getQuestion().getResponseNum();
        responseNum++;
        question.getQuestion().setResponseNum(responseNum);
        /*************************************
         * 注意更新问题数据的接口还未实现，没有调用
         */
    }

    //创建最终要返回的消息对象
    private Message createMessage(){
        Message message = new Message();
        String description = respondents.getUser().getName() + "回复了您\""+
                question.getTitle() + "\"的问题";

        //设置消息的对象为问题的作者
        message.setObjectId(internalMessage.getObject_id());
        //设置消息的描述信息
        message.setDescription(description);
        //设置消息的产生方式为回复
        message.setWay(MessageWay.response);

        return message;
    }
}
