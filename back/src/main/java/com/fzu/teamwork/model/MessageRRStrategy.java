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
        //获取被投诉作者的实体对象
        user = userService.getUserById(message.getObject_id());
        author = userService.convertToUserVo(user);
        //获取被投诉回复的实体对象
    }

    //根据消息进行处理,返回要保存的Message信息
    public Message operate(){
        return null;
    }
}
