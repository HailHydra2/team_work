package com.fzu.teamwork.model;

import com.fzu.teamwork.service.ResponseService;
import com.fzu.teamwork.view.ResponseVO;

//MessageService点灭某个回复消息进行处理的策略类（DLR:Dislike Response）
public class MessageDLRStrategy extends MessageOperateStrategy{

    private ResponseService responseService;

    //被点灭的回复实体
    private ResponseVO responseVO;

    //构造函数
    public MessageDLRStrategy(InternalMessage message, ResponseService responseService){
        //获取被点灭的回复实体
        Response r = responseService.getResponseById(message.getObject_id());
        responseVO = responseService.convertToVO(r);
    }

    //根据消息进行处理,返回要保存的Message信息
    public Message operate(){
        //更新被点灭回复的数据（点灭数）
        updateResponse();
        //不保存点灭消息
        return null;
    }

    public void updateResponse(){
        Response r = responseVO.getResponse();
        //被点灭数+1
        r.setDislikeNum(r.getDislikeNum() + 1);
        responseVO.setResponse(r);
        //更新数据库信息
        responseService.updateResponse(responseVO);
    }
}
