package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.BlockDao;
import com.fzu.teamwork.model.Block;
import com.fzu.teamwork.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public void deletebBlock(){
        blockDao.deleteByPrimaryKey(1);
    }



}
