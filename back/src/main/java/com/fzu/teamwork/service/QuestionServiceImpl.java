package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.ContentDao;
import com.fzu.teamwork.dao.QuestionDao;
import com.fzu.teamwork.dao.TitleDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class QuestionServiceImpl implements QuestionService{

    @Resource
    private QuestionDao questionDao;

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    @Resource
    private AttentionService attentionService;

    @Resource
    private QuestionPage questionPage;

    @Resource
    private TitleDao titleDao;

    @Resource
    private ContentDao contentDao;

    //根据question的id主键获取问题
    @Override
    public Question getQuestionById(int id){
        return questionDao.selectByPrimaryKey(id);
    }

    //将question对象转换为QuestionVO对象
    @Override
    public QuestionVO convertToVO(Question question){
        QuestionVO questionVO = null;
        questionVO.setQuestion(question);
        int questionID = question.getId();
        int titleID = titleDao.selectTitleByQuestionID(questionID);
        int contentID = question.getContentId();
        Title title = titleDao.selectByPrimaryKey(titleID);
        Content content = contentDao.selectByPrimaryKey(contentID);
        questionVO.setTitle(title.getTitle());
        questionVO.setTitle(content.getContent());
        return questionVO;
    }

    //更新问题数据
    @Override
    public Question updateQuestion(QuestionVO question){
        return null;
    }

    @Override
    public UserVO addQuestion(QuestionVO questionVO){
        Content content = null;
        content.setContent(questionVO.getContent());
        contentDao.insert(content);
        int contentId = content.getId();

        Question question = questionVO.getQuestion();
        question.setResponseNum(0);
        question.setReportNum(0);
        question.setContentId(contentId);
        questionDao.insert(question);

        Title title = null;
        title.setTitle(questionVO.getTitle());
        titleDao.insert(title);
        titleDao.insertQuestionTitle(question.getId(),title.getId());

        int userID = question.getAuthorId();
        User user = userService.getUserById(userID);
        UserVO userVO = userService.convertToUserVo(user);
        return userVO;
    }

    @Override
    //获取问题的某个分页
    public QuestionPage getQuestionPage(QuestionPage questionPage){
        this.questionPage = questionPage;
        QuestionStrategy questionStrategy;
        questionStrategy = new getQuestion(questionPage,questionDao);
        List<Question> questionList = questionStrategy.getQuestionList();
        List<QuestionVO> questionVOList = null;
        for(Question question : questionList) {
            questionVOList.add(convertToVO(question));
        }
        questionPage.setQuestions(questionVOList);
        return questionPage;
    }

    @Override
    public QuestionPage getQuestionPage(String userId, QuestionPage questionPage){
        this.questionPage = questionPage;
        QuestionStrategy questionStrategy;
        questionStrategy = new getUsersQuestion(Integer.parseInt(userId),questionPage,questionDao);
        List<Question> questionList = questionStrategy.getQuestionList();
        List<QuestionVO> questionVOList = null;
        for(Question question : questionList) {
            questionVOList.add(convertToVO(question));
        }
        questionPage.setQuestions(questionVOList);
        return questionPage;
    }

    @Override
    public QuestionPage getQuestionPageByIdList(String userId, QuestionPage questionPage){
        this.questionPage = questionPage;
        List<Question> questionList = null;
        List<Integer> idList =  attentionService.getAttentionQuestionList(Integer.parseInt(userId));
        for (int id:idList){
            questionList.add(getQuestionById(id));
        }
        List<QuestionVO> questionVOList = null;
        for(Question question : questionList) {
            questionVOList.add(convertToVO(question));
        }
        questionPage.setQuestions(questionVOList);
        return questionPage;
    }

    @Override
    public QuestionPage getResponseQuestion(String userId, QuestionPage questionPage){
        this.questionPage = questionPage;
        QuestionStrategy questionStrategy;
        questionStrategy = new getResponseQuestion(Integer.parseInt(userId),questionDao);
        List<Question> questionList = questionStrategy.getQuestionList();
        List<QuestionVO> questionVOList = null;
        for(Question question : questionList) {
            questionVOList.add(convertToVO(question));
        }
        questionPage.setQuestions(questionVOList);
        return questionPage;
    }

    @Override
    public void deleteQuestionById(String id){
        int questionId = Integer.parseInt(id);
        System.out.println(questionId);
        int titleId = titleDao.selectTitleByQuestionID(questionId);
        int contentId = questionDao.selectByPrimaryKey(questionId).getContentId();
        titleDao.deleteQuestionTitle(questionId);
        titleDao.deleteByPrimaryKey(titleId);
        questionDao.deleteByPrimaryKey(questionId);
        contentDao.deleteByPrimaryKey(contentId);
    }
}
