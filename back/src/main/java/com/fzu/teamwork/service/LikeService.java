package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Likes;

public interface LikeService {

    //添加（更新）点赞记录
    public void insertLikeInfo(Likes like);
}
