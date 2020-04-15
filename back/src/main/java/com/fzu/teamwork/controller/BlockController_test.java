package com.fzu.teamwork.controller;

import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Block;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.BlockService;
import com.fzu.teamwork.service.BlockServiceImpl;
import com.fzu.teamwork.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class BlockController_test {

    @Resource(name = "blockServiceImpl")
    BlockService blockService;


    //add block
    @PostMapping("/tblock")
    public @ResponseBody AjaxResponse addBlock(@RequestBody Block block){
        blockService.addBlock(block);
        return AjaxResponse.success();
    }

    //delete block
    @DeleteMapping("/tblock")
    public @ResponseBody AjaxResponse deleteBlock(){
        blockService.deletebBlock();
        return AjaxResponse.success();
    }

}