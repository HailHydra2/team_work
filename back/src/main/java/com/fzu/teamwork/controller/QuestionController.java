package com.fzu.teamwork.controller;


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

    public QuestionController() {
    }

//    @PostMapping("/questions")
//    public @ResponseBody AjaxResponse getQuestionPage(@RequestBody QuestionPage questionPage){
//        questionPage.setPageNum(10);
//        List<Integer> buttonList = new ArrayList<>();
//        buttonList.add(1);
//        buttonList.add(2);
//        questionPage.setButtonList(buttonList);
//        questionPage.setHasPrevious(false);
//        questionPage.setHasNext(true);
//        List<QuestionVO> questionList = new ArrayList<>();
//        QuestionVO question1 = new QuestionVO();
//        question1.setTitle("hello");
//        question1.setContent("hello world");
//        questionList.add(question1);
//        QuestionVO question2 = new QuestionVO();
//        question2.setTitle("goodbye");
//        question2.setContent("goodbye world");
//        questionList.add(question2);
//        questionPage.setQuestions(questionList);
//        return AjaxResponse.success(questionPage);
//    }

    //新增获取问题列表接口
    @PostMapping("/questions")
    public @ResponseBody AjaxResponse testGetQuestionPage(@RequestBody QuestionPage questionPage){
        log.info("enter{}",questionPage);
        QuestionPage page = questionService.getQuestionPage(questionPage);
        return AjaxResponse.success(page);
    }

//    @PostMapping("/question")
//    public  @ResponseBody AjaxResponse addQuestion(@RequestBody QuestionVO questionVO){
//        Title title = new Title();
//        title.setTitle(questionVO.getTitle());
//        title.setId(questionVO.getQuestion().getContentId());
//
//        Content content = new Content();
//        content.setContent(questionVO.getContent());
//        content.setId(questionVO.getQuestion().getContentId());
//
//        AccountData accountData = new AccountData();
//        accountData.setId(1);
//        accountData.setLevel(10);
//        accountData.setScore(10);
//        accountData.setExperienceValue(99/100);
//        accountData.setFocusNum(5);
//        accountData.setQuestionNum(5);
//        accountData.setResponseNum(5);
//
//        User user = new User();
//        user.setId(1);
//        user.setAccount("221701421");
//        user.setPassword("123456");
//        user.setName("wsh");
//        user.setIdCard("123456789");
//        user.setIdentity("student");
//        user.setPhoneNum("110");
//        user.setAccountDataId(1);
//
//        UserVO userVO = new UserVO();
//        userVO.setUser(user);
//        userVO.setAccountData(accountData);
//        return AjaxResponse.success(userVO);
//    }

    //实现新增问题接口
    @PostMapping("/question")
    public  @ResponseBody AjaxResponse testAddQuestion(@RequestBody QuestionVO questionVO){
        return AjaxResponse.success(questionService.addQuestion(questionVO));
    }

//    @PostMapping("/userQuestions/{uid}")
//    public @ResponseBody AjaxResponse getUserQuestionPage(@PathVariable String uid,  @RequestBody QuestionPage questionPage){
//        questionPage.setPageNum(10);
//        List<Integer> buttonList = new ArrayList<>();
//        buttonList.add(1);
//        buttonList.add(2);
//        questionPage.setButtonList(buttonList);
//        questionPage.setHasPrevious(false);
//        questionPage.setHasNext(true);
//        List<QuestionVO> questionList = new ArrayList<>();
//        QuestionVO question1 = new QuestionVO();
//        question1.setTitle("hello");
//        question1.setContent("hello world");
//        questionList.add(question1);
//        questionPage.setQuestions(questionList);
//        return AjaxResponse.success(questionPage);
//    }

    //实现获取用户问题列表接口
    @PostMapping("/userQuestions/{uid}")
    public @ResponseBody AjaxResponse testGetUserQuestionPage(@PathVariable String uid,  @RequestBody QuestionPage questionPage){
        QuestionPage page = questionService.getQuestionPage(uid,questionPage);
        return AjaxResponse.success(page);
    }

//    @PostMapping("/userAttentions/{uid}")
//    public @ResponseBody AjaxResponse getAttentionQuestionPage(@PathVariable String uid, @RequestBody QuestionPage questionPage){
//        questionPage.setPageNum(10);
//        List<Integer> buttonList = new ArrayList<>();
//        buttonList.add(1);
//        buttonList.add(2);
//        questionPage.setButtonList(buttonList);
//        questionPage.setHasPrevious(false);
//        questionPage.setHasNext(true);
//        List<QuestionVO> questionList = new ArrayList<>();
//        QuestionVO question1 = new QuestionVO();
//        question1.setTitle("goodbye");
//        question1.setContent("goodbye world");
//        questionList.add(question1);
//        questionPage.setQuestions(questionList);
//        return AjaxResponse.success(questionPage);
//    }

    //实现获取关注问题列表接口
    @PostMapping("/userAttentions/{uid}")
    public @ResponseBody AjaxResponse testGetAttentionQuestionPage(@PathVariable String uid, @RequestBody QuestionPage questionPage){
        QuestionPage page = questionService.getQuestionPageByIdList(uid, questionPage);
        return AjaxResponse.success(page);
    }

//    @PostMapping("/userResponseQuestions/{uid}")
//    public @ResponseBody AjaxResponse getUserResponseQuestion(@PathVariable String uid, @RequestBody QuestionPage questionPage){
//        questionPage.setPageNum(10);
//        List<Integer> buttonList = new ArrayList<>();
//        buttonList.add(1);
//        buttonList.add(2);
//        questionPage.setButtonList(buttonList);
//        questionPage.setHasPrevious(false);
//        questionPage.setHasNext(true);
//        List<QuestionVO> questionList = new ArrayList<>();
//        QuestionVO question1 = new QuestionVO();
//        question1.setTitle("goodbye");
//        question1.setContent("goodbye");
//        questionList.add(question1);
//        questionPage.setQuestions(questionList);
//        return AjaxResponse.success(questionPage);
//    }

    //实现获取用户回答过的问题列表接口
    @PostMapping("/userResponseQuestions/{uid}")
    public @ResponseBody AjaxResponse TestGetUserResponseQuestion(@PathVariable String uid, @RequestBody QuestionPage questionPage){
        QuestionPage page = questionService.getResponseQuestion(uid,questionPage);
        return AjaxResponse.success(page);
    }

    @SneakyThrows
    @GetMapping("/question/{id}")
    public @ResponseBody AjaxResponse getQuestion(@PathVariable String id){
        QuestionVO questionVO = new QuestionVO();
        Question question = new Question();
        question.setId(Integer.getInteger(id));
        question.setAuthorId(1);
        question.setResponseNum(5);
        question.setReportNum(3);

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-04-15 15:30:00");
        question.setCreateTime(date);
        questionVO.setQuestion(question);
        questionVO.setTitle("hello");
        questionVO.setContent("hello world");
        return  AjaxResponse.success(questionVO);
    }

    //实现获取问题详细信息接口
    @SneakyThrows
    @GetMapping("/testQuestion/{id}")
    public @ResponseBody AjaxResponse testGetQuestion(@PathVariable String id){
        Question question = questionDao.selectByPrimaryKey(Integer.parseInt(id));
        QuestionVO questionVO = questionService.convertToVO(question);
        return AjaxResponse.success(questionVO);
    }

    @DeleteMapping("/question/{id}")
    public @ResponseBody AjaxResponse deleteQuestion(@PathVariable String id){
        return AjaxResponse.success();
    }

    //实现删除问题接口
    @DeleteMapping("/testQuestion/{id}")
    public @ResponseBody AjaxResponse testDeleteQuestion(@PathVariable String id){
        questionService.deleteQuestionById(id);
        return AjaxResponse.success();
    }

    //批量删除问题
    @DeleteMapping("/questions")
    public @ResponseBody AjaxResponse deleteQuestions(@RequestBody int[] questionIdList){
        questionService.deleteQuestionsById(questionIdList);
        return AjaxResponse.success();
    }
}
