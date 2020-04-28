package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.LikesDao;
import com.fzu.teamwork.model.Likes;
import com.fzu.teamwork.model.LikesExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    private LikesDao likesDao;

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

        if(likeList.size() > 0){
            //已经存在记录
            like.setId(likeList.get(0).getId());
            //更新数据库
            likesDao.updateByPrimaryKey(like);
        }else{
            //不存在记录
            //插入数据库
            likesDao.insert(like);
        }
    }
}
