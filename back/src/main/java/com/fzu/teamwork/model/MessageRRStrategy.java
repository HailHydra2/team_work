package com.fzu.teamwork.model;


import com.fzu.teamwork.service.ResponseService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.UserVO;

//MessageService对投诉某个回复消息进行处理的策略类（RR:Report Response）
public class MessageRRStrategy extends MessageOperateStrategy{

    //投诉者
    private UserVO complainant;
    //被投诉回复的作者
    private UserVO author;
    //被投诉的回复
    private ResponseVO response;
    //操作的消息
    private InternalMessage message;

    private UserService userService;

    private ResponseService responseService;

    //构造函数
    public MessageRRStrategy(InternalMessage message, UserService userService, ResponseService responseService){
        //获取投诉人实体对象
        User user = userService.getUserById(message.getOperator_id());
        complainant = userService.convertToUserVo(user);
        //获取被投诉回复对象实体
        Response r = responseService.getResponseById(message.getObject_id());
        response = responseService.convertToVO(r);
        //获取被投诉作者的实体对象
        user = userService.getUserById(r.getAuthorId());
        author = userService.convertToUserVo(user);
    }

    //根据消息进行处理,返回要保存的Message信息
    public Message operate(){
        //更新被举报问题作者的数据（扣除积分）
        updateAuthor();
        //更新被举报问题数据（被举报数+1）
        updateResponse();
        //举报消息不进行保存
        return null;
    }

    //更新被投诉回复作者的账户数据（扣除相应积分）
    public void updateAuthor(){
        AccountData accountData = author.getAccountData();
        //扣除用户积分
        int score = accountData.getScore();
        score--;
        accountData.setScore(score);
        //更新用户信息
        author.setAccountData(accountData);
        userService.updateUser(author);
    }

    //更新被举报问题的数据
    public void updateResponse(){
        //被举报数+1
        Response r = response.getResponse();
        r.setReportNum(r.getReportNum()+1);
        response.setResponse(r);
        //更新数据库信息
        responseService.updateResponse(response);
    }


}
