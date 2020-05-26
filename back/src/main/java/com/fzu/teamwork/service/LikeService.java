package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Likes;

public interface LikeService {

    //添加（更新）点赞记录（返回值为点赞结果）
    public boolean insertLikeInfo(Likes like);
}
