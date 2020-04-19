package com.fzu.teamwork.controller;


import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.view.MessagePage;
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
    private QuestionDao questionDao;

    @GetMapping("/questions")
    public @ResponseBody AjaxResponse getQuestionPage(@RequestBody QuestionPage questionPage){
        questionPage.setPageNum(10);
        List<Integer> buttonList = new ArrayList<>();
        buttonList.add(1);
        buttonList.add(2);
        questionPage.setButtonList(buttonList);
        questionPage.setHasPrevious(false);
        questionPage.setHasNext(true);
        List<QuestionVO> questionList = new ArrayList<>();
        QuestionVO question1 = new QuestionVO();
        question1.setTitle("hello");
        question1.setContent("hello world");
        questionList.add(question1);
        QuestionVO question2 = new QuestionVO();
        question2.setTitle("goodbye");
        question2.setContent("goodbye world");
        questionList.add(question2);
        questionPage.setQuestions(questionList);
        return AjaxResponse.success(questionPage);
    }

    @PostMapping("/question")
    public  @ResponseBody AjaxResponse addQuestion(@RequestBody QuestionVO questionVO){
        Title title = new Title();
        title.setTitle(questionVO.getTitle());
        title.setId(questionVO.getQuestion().getContentId());

        Content content = new Content();
        content.setContent(questionVO.getContent());
        content.setId(questionVO.getQuestion().getContentId());

        AccountData accountData = new AccountData();
        accountData.setId(1);
        accountData.setLevel(10);
        accountData.setScore(10);
        accountData.setExperienceValue(99/100);
        accountData.setFocusNum(5);
        accountData.setQuestionNum(5);
        accountData.setResponseNum(5);

        User user = new User();
        user.setId(1);
        user.setAccount("221701421");
        user.setPassword("123456");
        user.setName("wsh");
        user.setIdCard("123456789");
        user.setIdentity("student");
        user.setPhoneNum("110");
        user.setAccountDataId(1);

        UserVO userVO = new UserVO();
        userVO.setUser(user);
        userVO.setAccountData(accountData);
        return AjaxResponse.success(userVO);
    }

    @GetMapping("/userQuestions/{uid}")
    public @ResponseBody AjaxResponse getUserQuestionPage(@PathVariable String uid,  @RequestBody QuestionPage questionPage){
        questionPage.setPageNum(10);
        List<Integer> buttonList = new ArrayList<>();
        buttonList.add(1);
        buttonList.add(2);
        questionPage.setButtonList(buttonList);
        questionPage.setHasPrevious(false);
        questionPage.setHasNext(true);
        List<QuestionVO> questionList = new ArrayList<>();
        QuestionVO question1 = new QuestionVO();
        question1.setTitle("hello");
        question1.setContent("hello world");
        questionList.add(question1);
        questionPage.setQuestions(questionList);
        return AjaxResponse.success(questionPage);
    }

    @GetMapping("/userAttentions/{uid}")
    public @ResponseBody AjaxResponse getAttentionQuestionPage(@PathVariable String uid, @RequestBody QuestionPage questionPage){
        questionPage.setPageNum(10);
        List<Integer> buttonList = new ArrayList<>();
        buttonList.add(1);
        buttonList.add(2);
        questionPage.setButtonList(buttonList);
        questionPage.setHasPrevious(false);
        questionPage.setHasNext(true);
        List<QuestionVO> questionList = new ArrayList<>();
        QuestionVO question1 = new QuestionVO();
        question1.setTitle("goodbye");
        question1.setContent("goodbye world");
        questionList.add(question1);
        questionPage.setQuestions(questionList);
        return AjaxResponse.success(questionPage);
    }

    @GetMapping("/userResponseQuestions/{uid}")
    public @ResponseBody AjaxResponse getUserResponseQuestion(@PathVariable String uid, @RequestBody QuestionPage questionPage){
        questionPage.setPageNum(10);
        List<Integer> buttonList = new ArrayList<>();
        buttonList.add(1);
        buttonList.add(2);
        questionPage.setButtonList(buttonList);
        questionPage.setHasPrevious(false);
        questionPage.setHasNext(true);
        List<QuestionVO> questionList = new ArrayList<>();
        QuestionVO question1 = new QuestionVO();
        question1.setTitle("goodbye");
        question1.setContent("goodbye");
        questionList.add(question1);
        questionPage.setQuestions(questionList);
        return AjaxResponse.success(questionPage);
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

    @DeleteMapping("/question/{id}")
    public @ResponseBody AjaxResponse deleteQuestion(@PathVariable String id){
        return AjaxResponse.success();
    }
}
