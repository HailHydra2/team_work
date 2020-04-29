package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.BlockDao;
import com.fzu.teamwork.model.Block;
import com.fzu.teamwork.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@RestController
public class BlockServiceImpl implements BlockService {

    @Resource
    private BlockDao blockDao;

    //add block
    @Override
    public void addBlock(Block block) {
        blockDao.insert(block);
    }

    @Override
    public void deleteBlock(){
        blockDao.deleteByPrimaryKey(1);
    }

    //获取临时板块
    @Override
    public Block getBlock(){
        //查询所有临时板块记录（只有一条）
        List<Block> blockList = blockDao.selectByExample(null);
        if(blockList.size() == 1){
            //有临时板块记录，返回第一条(因为业务需要，只有一条)
            return blockList.get(0);
        } else if (blockList.size() == 0) {
            //没有临时板块
            return null;
        }else{
            log.info("block表中有多于一条的临时板块记录，逻辑错误");
        }
        return null;
    }

}
