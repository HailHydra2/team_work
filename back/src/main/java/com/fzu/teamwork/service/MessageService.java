package com.fzu.teamwork.service;

import com.fzu.teamwork.view.MessagePage;

public interface MessageService {

    //获取用户的消息的某个分页
    public MessagePage getMessagePageByUid(int uid, MessagePage page);
}
