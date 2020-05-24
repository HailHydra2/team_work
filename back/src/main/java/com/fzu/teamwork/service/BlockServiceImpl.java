package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.BlockDao;
import com.fzu.teamwork.model.Block;
import com.fzu.teamwork.model.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
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

    //添加临时板块（返回添加结果）
    @Override
    public Boolean addBlock(Block block) {
        if(blockDao.selectByExample(null).size() > 0){//已经存在临时板块
            return false;//添加失败
        }else{
            blockDao.insert(block);
            return true;//添加成功
        }
    }

    //删除临时板块
    @Override
    public boolean deleteBlock(){
        if(blockDao.selectByExample(null).size() == 0){//临时板块不存在
            return false;
        }else{
            blockDao.deleteByExample(null);
            return true;
        }
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
