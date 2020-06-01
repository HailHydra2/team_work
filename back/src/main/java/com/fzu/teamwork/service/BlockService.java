package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Block;
import com.sun.org.apache.xpath.internal.operations.Bool;

public interface BlockService {

    //添加临时板块（返回添加结果）
    public Boolean insertBlock(Block block);

    //撤销临时板块（返回操作结果）
    public boolean deleteBlock();

    //获取临时板块
    public Block getBlock();
}
