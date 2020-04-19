package com.fzu.teamwork.model;

import java.util.List;

public abstract class MessageStrategy {

    //根据Uid获取消息列表
    public abstract List<Message> getMessageList();
}
