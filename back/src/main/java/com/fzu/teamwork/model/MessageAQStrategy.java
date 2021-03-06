package com.fzu.teamwork.model;

import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.view.UserVO;

//MessageService对关注问题消息进行处理的策略类
public class MessageAQStrategy extends MessageOperateStrategy{

    private UserService userService;

    //关注的用户
    private UserVO user;

    //关注数变化值（1：关注+1， -1：关注-1）
    private int changeNum;

    //消息
    private InternalMessage message;

    public MessageAQStrategy(InternalMessage message, UserService userService){
        this.userService = userService;
        this.message = message;
    }

    //根据消息进行处理,返回要保存的Message信息
    @Override
    public Message operate(){
        //根据消息中关注者的id获得用户对象
        User u = userService.getUserById(message.getOperator_id());
        //用消息的flag代表关注或取消关注(1或-1)
        changeNum = message.getFlag();
        user = userService.convertToUserVo(u);
        //更新关注的的数据信息（关注数）
        updateUserInfo();
        //关注不需要保存信息
        return null;
    }

    //更新用户的数据信息（关注数）
    private void updateUserInfo(){
        //获取用户原来的关注数
        int focusNum = user.getAccountData().getFocusNum();
        //更新关注数
        focusNum += changeNum;
        user.getAccountData().setFocusNum(focusNum);
        //更新用户在数据库中的信息
        userService.updateUser(user);
    }
}
