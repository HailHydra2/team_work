package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Block;

public interface BlockService {

    //add block
    public void addBlock(Block block);

    //delete block
    public void deleteBlock();

    //获取临时板块
    public Block getBlock();
}
