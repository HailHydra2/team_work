package com.fzu.teamwork.model;

import java.util.List;

//ResponseService用于获取回复列表的抽象策略类
public abstract class ResponseStrategy {
    //获取回复列表
    public abstract List<Response> getResponseList();
}
