package com.fzu.teamwork.model;

//MessageService根据消息进行数据处理的策略基类
public abstract class MessageOperateStrategy {

    //根据消息进行处理,返回要保存的Message信息
    public abstract Message operate();
}
