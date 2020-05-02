package com.fzu.teamwork.service;

import com.fzu.teamwork.model.Question;
import com.fzu.teamwork.util.QuestionSortApproach;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.QuestionVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;

    @Test
    public void getQuestionByIdTest(){
        questionService.getQuestionById(1);
    }

    @Test
    public void convertToVOTest() {
        Question question = new Question();
        question.setId(26);
        question.setReportNum(0);
        question.setResponseNum(0);
        question.setContentId(55);
        question.setAuthorId(1);
        question.setCreateTime(new Date());
        questionService.convertToVO(question);
    }

    @Test
    public void updateQuestionTest(){
        QuestionVO questionVO = new QuestionVO();
        Question question = new Question();
        question.setId(26);
        question.setReportNum(0);
        question.setResponseNum(0);
        question.setContentId(55);
        question.setAuthorId(1);
        question.setCreateTime(new Date());
        questionVO.setQuestion(question);
        questionService.updateQuestion(questionVO);
    }

    @Test
    public void addQuestionTest(){
        QuestionVO questionVO = new QuestionVO();
        questionVO.setTitle("title");
        questionVO.setContent("content");
        Question question = new Question();
        question.setId(26);
        question.setReportNum(0);
        question.setResponseNum(0);
        question.setContentId(55);
        question.setAuthorId(1);
        question.setCreateTime(new Date());
        questionVO.setQuestion(question);
        questionService.updateQuestion(questionVO);
    }

    @Test
    public void getQuestionPageTest(){
        QuestionPage questionPage = new QuestionPage();
        questionPage.setPageIndex(5);
        questionPage.setCount(5);
        questionPage.setSortApproach(QuestionSortApproach.SORT_BY_HEAT);
        questionService.getQuestionPage(questionPage);
        questionService.getQuestionPage(1,questionPage);
        questionService.getAttentionQuestionPage(1,questionPage);
        questionService.getResponseQuestion("1",questionPage);
    }

    @Test
    public void deleteQuestionByIdTest(){
        questionService.deleteQuestionById("26");
    }

    @Test
    public void deleteQuestionsByIdTest(){
        int[] list = {27,28};
        questionService.deleteQuestionsById(list);
    }

    @Test
    public void addRelationToUIdTest(){
        QuestionVO questionVO = new QuestionVO();
        Question question = new Question();
        question.setId(26);
        question.setReportNum(0);
        question.setResponseNum(0);
        question.setContentId(55);
        question.setAuthorId(1);
        question.setCreateTime(new Date());
        questionVO.setQuestion(question);
        questionService.addRelationToUId(questionVO,29);
    }
}
