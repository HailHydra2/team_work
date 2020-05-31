package com.fzu.teamwork.service;

import com.fzu.teamwork.model.InternalMessage;
import com.fzu.teamwork.view.MessagePage;

public interface MessageService {

    //获取用户的消息的某个分页
    public MessagePage getMessagePageByUid(int uid, MessagePage page);

    //删除某个用户的所有消息(返回删除消息条数)
//    public int deleteUserMessage(int uid);

    //根据消息对实体对象的数据进行更新
    public void updateInfoByMessage(InternalMessage message);
}
