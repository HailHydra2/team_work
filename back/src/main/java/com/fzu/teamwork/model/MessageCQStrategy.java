package com.fzu.teamwork.model;

import com.fzu.teamwork.service.QuestionService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.MessageWay;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;

//MessageService对回复被创建问题进行处理的策略类(CQ: Create Question)
public class MessageCQStrategy extends MessageOperateStrategy{

    //新创建问题的作者
    private UserVO author;
    //新创建的问题
    private QuestionVO questionVO;

    private UserService userService;
    private QuestionService questionService;

    //构造函数
    public MessageCQStrategy(InternalMessage message, UserService userService, QuestionService questionService){
        this.userService = userService;
        this.questionService = questionService;

        //获取作者信息
        User user = userService.getUserById(message.getOperator_id());
        author = userService.convertToUserVo(user);
        //获取新创建的问题
        Question question = questionService.getQuestionById(message.getObject_id());
        questionVO = questionService.convertToVO(question);
    }

    //根据消息进行处理,返回要保存的Message信息
    public Message operate(){
        //更新问题作者数据
        updateAuthor();
        //将要保存的消息返回
        return createMessage();
    }

    //更新作者数据（提问数，经验值，积分）
    public void updateAuthor(){
        AccountData accountData = author.getAccountData();
        //积分数
        int score = accountData.getScore();
        score += 20;
        //经验值
        int experience = accountData.getExperienceValue();
        experience += 20;
        accountData.setScore(score);
        accountData.setExperienceValue(experience);
        //提问数+1
        accountData.setQuestionNum(accountData.getQuestionNum()+1);
        //更新作者账户数据
        author.setAccountData(accountData);
        //更新数据库
        userService.updateUser(author);
    }

    //创建保存到数据库的消息
    public Message createMessage(){
        Message message = new Message();
        //消息对象为问题作者
        message.setObjectId(author.getUser().getId());
        //消息产生方式(创建问题)
        message.setWay(MessageWay.createQuestion);
        //消息描述信息
        String description = "您在" + questionVO.getQuestion().getCreateTime() + "创建了\""
                + questionVO.getTitle() +"\"问题";
        message.setDescription(description);
        //返回要保存到数据库的消息
        return message;
    }
}
