package com.fzu.teamwork.controller;

import com.fzu.teamwork.annoation.AdminLimit;
import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Block;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.BlockService;
import com.fzu.teamwork.util.ErrorStatus;
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
    @LoginToken//需要登录
    @AdminLimit//需要管理员权限
    @PostMapping("/block")
    public @ResponseBody AjaxResponse insertBlock(@RequestBody Block block){
        if(blockService.insertBlock(block) == true){//添加成功
            return AjaxResponse.success("添加成功");
        }else{
            return AjaxResponse.error(ErrorStatus.BLOCK_HAS_EXIT, "临时板块已经存在，不能重复添加");
        }
    }

    //删除临时板块
    @LoginToken//需要登录
    @AdminLimit//需要管理员权限
    @DeleteMapping("/block")
    public @ResponseBody AjaxResponse deleteBlock(){
        if(blockService.deleteBlock() == true){//撤销成功
            return AjaxResponse.success("撤销临时板块成功");
        }else{
            return AjaxResponse.error(ErrorStatus.BLOCK_NOT_EXIT, "要删除的临时板块不存在");
        }
    }

    //获取临时板块
    @LoginToken//需要登录
    @GetMapping("/block")
    public @ResponseBody AjaxResponse getBlock(){
        Block block = blockService.getBlock();
        if(block == null){//没有临时板块
            return AjaxResponse.success(ErrorStatus.BLOCK_NOT_EXIT,"没有临时板块");
        }else{//有临时板块
            return AjaxResponse.success(block);
        }
    }
}
