package com.fzu.teamwork.model;

import com.fzu.teamwork.dao.MessageDao;
import com.fzu.teamwork.view.MessagePage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*MessageService用于获取消息列表的策略类
 *用于获取object_id为uid的消息（操作对象的编号为uid的）记录子列表
 */
public class MessageByUid extends MessageStrategy{

    //消息所属user的id
    private int uid;

    //想要获取消息的某个分页
    private MessagePage page;

    private MessageDao messageDao;

    public MessageByUid(int uid, MessagePage page, MessageDao messageDao){
        this.uid = uid;
        this.page = page;
        this.messageDao = messageDao;
    }

    //根据Uid获取消息列表
    @Override
    public List<Message> getMessageList(){
        Map<String, Integer> map = new HashMap<>();
        map.put("uid",uid);
        //获取该页第一个回复的索引[（页码-1）*一页所包含的数量]
        int firstIndex = (page.getPageIndex() - 1) * page.getCount();
        map.put("start",firstIndex);
        map.put("count",page.getCount());
        //查找object_id=uid列表中start到start+count的子列表
        List<Message> messages = messageDao.selectMessageByUid(map);
        return messages;
    }
}
