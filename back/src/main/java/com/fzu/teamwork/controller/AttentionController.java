package com.fzu.teamwork.controller;


import com.fzu.teamwork.model.AjaxResponse;
import com.fzu.teamwork.model.Attention;
import com.fzu.teamwork.service.AttentionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class AttentionController {

    @Resource(name = "attentionServiceImpl")
    AttentionService attentionService;

    //关注/取消关注（实现类）
    @PostMapping("/attention")
    public @ResponseBody AjaxResponse testAddAttention(@RequestBody Attention attention){
        attentionService.insertAttention(attention);
        return AjaxResponse.success();
    }

    //获取用户关注问题list列表（测试，不是作为前后端接口）
    @GetMapping("/testAttentionList/{uid}")
    public @ResponseBody AjaxResponse getAttentionQuestionList(@PathVariable int uid){
        List<Integer> list = attentionService.getAttentionQuestionList(uid);
        return AjaxResponse.success(list);
    }
}
