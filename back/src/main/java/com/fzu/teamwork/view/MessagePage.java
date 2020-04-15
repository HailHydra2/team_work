package com.fzu.teamwork.view;

import com.fzu.teamwork.model.Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class MessagePage{
    private int pageIndex;//当前页号
    private int count;//本页要展示的最大条数
    private List<Integer> buttonList;//最近的5个页号的页码序列
    private boolean hasPrevious;//是否有上一页
    private boolean hasNext;//是否有下一页
    private int pageNum;//总页数
    private List<Message> messages;
}
