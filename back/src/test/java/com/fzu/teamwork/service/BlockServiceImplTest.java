package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Block;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BlockServiceImplTest extends LCH_testFather{

    @Autowired
    private BlockService blockService;

    @Test
    void addBlock() {

        boolean res=true;
        if(blockService.getBlock()!=null)
            res=false;
        Assert.assertEquals("测试错误",false,res);

        Block block=new Block();
        block.setId(6);
        block.setBlockName("1");
        block.setKeyWord("1");

        //我期望的block
        Block block_except=block;

        //比较与期望的结果
        Assert.assertEquals("测试错误",block_except,blockService.getBlock());

    }

    @Test
    void deleteBlock() {
        boolean res=true;
        if(blockService.getBlock()==null)
            res=false;
        Assert.assertEquals("测试错误",false,res);
    }

    @Test
    void getBlock() {

        Block block=new Block();
        block.setId(6);
        block.setBlockName("1");
        block.setKeyWord("1");

        //blockService.addBlock(block);

        //我期望的block
        Block block_except=block;

        //比较与期望的结果
        Assert.assertEquals("测试错误",block_except,blockService.getBlock());
         
    }
}