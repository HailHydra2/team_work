package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Attention;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttentionServiceTest {
    @Autowired AttentionService attentionService;

    @Test
    public void insertAttentionTest(){
        Attention attention = new Attention();
        attention.setId(15);
        attention.setFlag(1);
        attention.setQuestionId(30);
        attention.setUserId(1);
        attention.setCreateTime(new Date());
        attentionService.insertAttention(attention);
    }

    @Test
    public void getAttentionQuestionListTest(){
        attentionService.getAttentionQuestionList(1);
    }
}
