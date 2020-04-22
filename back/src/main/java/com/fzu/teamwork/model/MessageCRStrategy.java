package com.fzu.teamwork.model;

import com.fzu.teamwork.service.QuestionService;
import com.fzu.teamwork.service.ResponseService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.MessageWay;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.UserVO;

//MessageService对创建回复进行处理的策略类(CR: Create Response)
public class MessageCRStrategy extends MessageOperateStrategy{

    //创建回复的作者
    private UserVO author;
    //创建的回复
    private ResponseVO responseVO;
    //回复所属的问题
    private QuestionVO questionVO;

    private UserService userService;
    private ResponseService responseService;
    private QuestionService questionService;

    //构造函数
    public MessageCRStrategy(InternalMessage message, UserService userService,
                             ResponseService responseService, QuestionService questionService){
        this.userService = userService;
        this.responseService = responseService;
        this.questionService = questionService;

        //获取创建回复的作者
        User user = userService.getUserById(message.getOperator_id());
        author = userService.convertToUserVo(user);
        //获取创建的回复
        Response response = responseService.getResponseById(message.getObject_id());
        responseVO = responseService.convertToVO(response);
        //获取回复所属的问题
        Question question = questionService.getQuestionById(response.getQuestionId());
        questionVO = questionService.convertToVO(question);
    }

    //根据消息进行处理,返回要保存的Message信息
    public Message operate(){
        //更新作者账户信息（回复数，积分，经验值）
        updateAuthor();
        //更新问题信息（回复数）
        updateQuestion();
        //返回保存到数据库的消息
        return createMessage();
    }

    //更新回复作者数据（回复数，积分，经验值）
    public void updateAuthor(){
        AccountData accountData = author.getAccountData();
        int responseNum = accountData.getResponseNum();
        int score = accountData.getScore();
        int experience = accountData.getExperienceValue();

        responseNum += 1;
        score += 5;
        experience += 10;

        accountData.setScore(score);
        accountData.setExperienceValue(experience);
        accountData.setResponseNum(responseNum);
        //更新作者账户信息
        author.setAccountData(accountData);
        //更新数据库
        userService.updateUser(author);
    }

    //更新回复所属问题的信息（回复数）
    public void updateQuestion(){
        Question question = questionVO.getQuestion();
        //回复数+1
        question.setResponseNum(question.getResponseNum() + 1);
        //更新所属问题信息
        questionVO.setQuestion(question);
        //更新数据库
        questionService.updateQuestion(questionVO);
    }

    //创建保存到数据库的消息
    public Message createMessage(){
        Message message = new Message();
        //消息对象是回复作者
        message.setObjectId(author.getUser().getId());
        //消息产生方式
        message.setWay(MessageWay.createResponse);
        //消息描述信息
        String description = "您在" + responseVO.getResponse().getCreateTime()
                + "回复了问题\"" + questionVO.getTitle() + "\"";
        message.setDescription(description);
        //返回保存到数据库的消息
        return message;
    }
}
