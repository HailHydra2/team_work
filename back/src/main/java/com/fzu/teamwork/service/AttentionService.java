package com.fzu.teamwork.service;


import com.fzu.teamwork.dao.AttentionDao;
import com.fzu.teamwork.model.Attention;
import com.fzu.teamwork.view.UserVO;

import javax.annotation.Resource;
import java.util.List;

//关注实体服务层接口
public interface AttentionService {

    //向数据库插入关注记录（返回改变后的用户数据—关注数发生改变）
    public UserVO insertAttention(Attention record);

    //根据uid查询用户关注问题的id列表
    public List<Integer> getAttentionQuestionList(int uid);
}
