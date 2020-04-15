package com.fzu.teamwork.controller;

import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Block;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.service.BlockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class BlockController {

    @PostMapping("/block")
    public @ResponseBody
    AjaxResponse addBlock(@RequestBody Block block){
        return AjaxResponse.success();
    }

    @DeleteMapping("/block")
    public @ResponseBody
    AjaxResponse deleteBlock(){
        return AjaxResponse.success();
    }
}
