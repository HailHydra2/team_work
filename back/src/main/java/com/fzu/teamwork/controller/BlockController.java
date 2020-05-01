package com.fzu.teamwork.controller;

import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Block;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.BlockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class BlockController {

    @Resource(name = "blockServiceImpl")
    BlockService blockService;

    //新增临时板块
    @PostMapping("/block")
    public @ResponseBody AjaxResponse addBlock_test(@RequestBody Block block){
        blockService.addBlock(block);
        return AjaxResponse.success();
    }

    //删除临时板块
    @DeleteMapping("/block")
    public @ResponseBody AjaxResponse deleteBlock_test(){
        blockService.deleteBlock();
        return AjaxResponse.success();
    }

    //获取临时板块
    @GetMapping("/block")
    public @ResponseBody AjaxResponse getBlock(){
        Block block = blockService.getBlock();
        if(block == null){
            //没有临时板块
            return AjaxResponse.success(201,"没有临时板块");
        }else{
            //有临时板块
            return AjaxResponse.success(block);
        }
    }
}
