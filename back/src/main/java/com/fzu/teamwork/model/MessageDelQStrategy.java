package com.fzu.teamwork.model;


import com.fzu.teamwork.service.QuestionService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.MessageWay;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;

//MessageService对回复被删除问题进行处理的策略类(DelQ: Delete Question)
public class MessageDelQStrategy extends MessageOperateStrategy{

    //被删除的问题
    private QuestionVO questionVO;
    //被删除问题的作者
    private UserVO author;

    private QuestionService questionService;
    private UserService userService;

    //构造函数
    public MessageDelQStrategy(InternalMessage message, QuestionService questionService, UserService userService){
        this.questionService = questionService;
        this.userService = userService;
        //获取被删除消息
        Question question = questionService.getQuestionById(message.getObject_id());
        questionVO = questionService.convertToVO(question);
        //获取被删除问题作者
        User user = userService.getUserById(question.getAuthorId());
        author = userService.convertToUserVo(user);
    }

    //根据消息进行处理,返回要保存的Message信息
    public Message operate(){
        //更新被删除问题作者数据（提问数-1）
        updateAuthor();
        //返回保存数据库的消息
        return createMessage();
    }

    //更新被删除问题作者数据（提问数）
    public void updateAuthor(){
        //获取作者账户信息
        AccountData accountData = author.getAccountData();
        //提问数-1
        accountData.setQuestionNum(accountData.getQuestionNum()-1);
        //更新用户数据
        author.setAccountData(accountData);
        //更新数据库信息
        userService.updateUser(author);
    }

    //创建要保存到数据库的消息
    public Message createMessage(){
        Message message = new Message();
        //被删除问题作者是消息对象
        message.setObjectId(author.getUser().getId());
        //设置消息产生方式
        message.setWay(MessageWay.DELETE_QUESTION);
        //消息描述信息
        String description = "您的问题\"" + questionVO.getTitle() + "\"已被管理员删除";
        message.setDescription(description);
        return message;
    }
}
