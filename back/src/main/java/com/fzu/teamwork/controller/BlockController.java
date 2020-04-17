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

    //static
    @PostMapping("/block")
    public @ResponseBody
    AjaxResponse addBlock(@RequestBody Block block){
        return AjaxResponse.success();
    }

    //static
    @DeleteMapping("/block")
    public @ResponseBody
    AjaxResponse deleteBlock(){
        return AjaxResponse.success();
    }

    //add block
    @PostMapping("/tblock")
    public @ResponseBody AjaxResponse addBlock_test(@RequestBody Block block){
        blockService.addBlock(block);
        return AjaxResponse.success();
    }

    //delete block
    @DeleteMapping("/tblock")
    public @ResponseBody AjaxResponse deleteBlock_test(){
        blockService.deletebBlock();
        return AjaxResponse.success();
    }
}
