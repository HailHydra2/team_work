package com.fzu.teamwork.model;

import java.util.List;

//MessageService用于获取消息列表的抽象策略基类
public abstract class MessageStrategy {

    //根据Uid获取消息列表
    public abstract List<Message> getMessageList();
}
