package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Likes;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class LikeServiceImplTest extends LCH_testFather{

    @Autowired
    private LikeService likeService;

    @Test
    void insertLikeInfo() {
        Likes likes=new Likes();
        likes.setId(17);
        likes.setResponseId(38);
        likes.setUserId(5);
        likes.setFlag(1);

        likeService.insertLikeInfo(likes);

        Assert.assertEquals("测试错误",likes.getUserId(),likes.getUserId());
        Assert.assertEquals("测试错误",likes.getResponseId(),likes.getResponseId());

    }
}