package com.fzu.teamwork.controller;


import com.fzu.teamwork.annoation.AdminLimit;
import com.fzu.teamwork.annoation.LoginToken;
import com.fzu.teamwork.annoation.UserLimit;
import com.fzu.teamwork.dao.*;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.service.*;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    @Resource
    private ContentDao contentDao;

    @Resource
    private QuestionDao questionDao;

    @Resource
    private TitleDao titleDao;

    //获取问题列表接口
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/questions")
    public @ResponseBody AjaxResponse getQuestionPage(@RequestBody QuestionPage questionPage){
        log.info("enter{}",questionPage);
        QuestionPage page = questionService.getQuestionPage(questionPage);
        return AjaxResponse.success(page);
    }

    //新增问题接口
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/question")
    public  @ResponseBody AjaxResponse addQuestion(@RequestBody QuestionVO questionVO){
        return AjaxResponse.success(questionService.addQuestion(questionVO));
    }

    //获取用户问题列表接口
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/userQuestions/{uid}")
    public @ResponseBody AjaxResponse getUserQuestionPage(@PathVariable Integer uid,  @RequestBody QuestionPage questionPage){
        QuestionPage page = questionService.getQuestionPage(uid,questionPage,1);
        return AjaxResponse.success(page);
    }

    //关注问题列表接口
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/userAttentions/{uid}")
    public @ResponseBody AjaxResponse testGetAttentionQuestionPage(@PathVariable Integer uid, @RequestBody QuestionPage questionPage){
        QuestionPage page = questionService.getQuestionPage(uid, questionPage,2);
        return AjaxResponse.success(page);
    }

    //用户回答过的问题列表接口
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @PostMapping("/userResponseQuestions/{uid}")
    public @ResponseBody AjaxResponse TestGetUserResponseQuestion(@PathVariable Integer uid, @RequestBody QuestionPage questionPage){
        QuestionPage page = questionService.getQuestionPage(uid,questionPage,0);
        return AjaxResponse.success(page);
    }

    //问题详细信息接口（除了基本信息还包含与用户有关的信息——是否已关注等）
    @SneakyThrows
    @LoginToken//需要登录
    @UserLimit//普通用户权限
    @GetMapping("/question/{id}/{uid}")
    public @ResponseBody AjaxResponse testGetQuestion(@PathVariable String id, @PathVariable(required = false) Integer uid){
        Question question = questionDao.selectByPrimaryKey(Integer.parseInt(id));
        QuestionVO questionVO = questionService.convertToVO(question);
        //添加与用户uid之间的关系
        questionService.addRelationToUId(questionVO, uid);
        return AjaxResponse.success(questionVO);
    }

    //问题详细信息接口（只包含问题的基本信息——标题和内容）
    @SneakyThrows
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @GetMapping("/question/{id}")
    public @ResponseBody AjaxResponse testGetQuestion(@PathVariable Integer id){
        System.out.println("id" + id);
        Question question = questionDao.selectByPrimaryKey(id);
        QuestionVO questionVO = questionService.convertToVO(question);
        return AjaxResponse.success(questionVO);
    }

    //删除问题接口
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @DeleteMapping("/question/{id}")
    public @ResponseBody AjaxResponse testDeleteQuestion(@PathVariable String id){
        questionService.deleteQuestionById(id);
        return AjaxResponse.success();
    }

    //批量删除问题
    @LoginToken//需要登录
    @AdminLimit//管理员权限
    @DeleteMapping("/questions")
    public @ResponseBody AjaxResponse deleteQuestions(@RequestBody int[] questionIdList){
        questionService.deleteQuestionsById(questionIdList);
        return AjaxResponse.success();
    }
}
