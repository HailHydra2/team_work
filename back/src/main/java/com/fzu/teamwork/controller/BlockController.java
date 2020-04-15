package com.fzu.teamwork.controller;

import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Block;
import com.fzu.teamwork.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BlockController {

    @PostMapping("/block")
    public @ResponseBody
    AjaxResponse addBlock(@RequestBody Block block){
        return AjaxResponse.success(block);
    }

    @DeleteMapping("/block")
    public @ResponseBody
    AjaxResponse deleteBlock(){
        return AjaxResponse.success();
    }
}
