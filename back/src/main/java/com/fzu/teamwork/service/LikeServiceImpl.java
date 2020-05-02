package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.LikesDao;
import com.fzu.teamwork.model.InternalMessage;
import com.fzu.teamwork.model.Likes;
import com.fzu.teamwork.model.LikesExample;
import com.fzu.teamwork.util.MessageWay;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    private LikesDao likesDao;

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

    //添加（更新）点赞记录
    public void insertLikeInfo(Likes like){
        //查看数据库中是否已经存在该用户对该回复的点赞记录
        LikesExample example = new LikesExample();
        LikesExample.Criteria criteria = example.createCriteria();
        System.out.println("userId:" + like.getUserId() + "  responseId:" + like.getResponseId());
        //用户id一样的点赞记录
        criteria.andUserIdEqualTo(like.getUserId());
        //被点赞回复id一样的记录
        criteria.andResponseIdEqualTo(like.getResponseId());
        //查找记录
        List<Likes> likeList = likesDao.selectByExample(example);
        //原来的点赞记录标志位
        int oldFlag = 0;

        if(likeList.size() > 0){
            //已经存在记录
            like.setId(likeList.get(0).getId());
            //旧记录的标志位
            oldFlag = likeList.get(0).getFlag();
            //更新数据库
            likesDao.updateByPrimaryKey(like);
        }else{
            //不存在记录
            //插入数据库
            likesDao.insert(like);
        }

        //创建内部发送消息
        InternalMessage message = new InternalMessage();
        //操作者为点赞者id
        message.setOperator_id(like.getUserId());
        //被操作对象是被点赞回复id
        message.setObject_id(like.getResponseId());

        //操作方式
        if(like.getFlag() == 1){
            //点赞
            message.setWay(MessageWay.LIKE_RESPONSE);
            //设置表示位为点赞
            message.setFlag(1);
            //设置标志位为原来的点赞记录情况  0没有，-1点灭
            message.setFlag2(oldFlag);
        }else if(like.getFlag() == -1){
            //点灭
            //设置标志位为点灭
            message.setFlag(-1);
            message.setWay(MessageWay.DISLIKE_RESPONSE);
            //设置标志位为原来的点赞记录情况   0没有  1点赞
            message.setFlag2(oldFlag);
        }else if(like.getFlag() == 0){
            //取消点赞或者点灭
            if(oldFlag == 1){
                //取消点赞
                message.setWay(MessageWay.LIKE_RESPONSE);
                //设置标志位
                message.setFlag(0);
            }else{
                //取消点灭
                message.setWay(MessageWay.DISLIKE_RESPONSE);
                //设置标志位
                message.setFlag(0);
            }

        }
        messageService.updateInfoByMessage(message);
    }
}
