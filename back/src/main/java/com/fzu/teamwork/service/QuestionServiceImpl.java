package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.*;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.QuestionSortApproach;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;
import io.swagger.models.auth.In;
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
    private ResponseDao responseDao;

    @Resource
    private UserService userService;

    @Resource
    private AttentionService attentionService;

    @Resource
    private TitleDao titleDao;

    @Resource
    private ContentDao contentDao;

    @Resource
    private QuestionTitleDao questionTitleDao;

    //获取问题列表的策略类
    private QuestionStrategy questionStrategy;
    //要获取的问题分页
    private QuestionPage questionPage;

    //根据question的id主键获取问题
    @Override
    public Question getQuestionById(int id){
        return questionDao.selectByPrimaryKey(id);
    }

    //创建获取问题列表的策略对象
    /**
     * type=1：按热度排序（评论数递减排序）
     * type=2：按时间由近到远排序
     * type:3：按关键字排序
     */
    private void createQuestionStrategy(int type){
        if(type == 1){//按热度排序（评论数递减排序）
            questionStrategy = new QuestionByHeatStrategy(questionPage, questionDao);
        }else if(type == 2){//按时间由近到远排序
            questionStrategy = new QuestionByTimeStrategy(questionPage, questionDao);
        }else if(type == 3){//按关键字排序
            questionStrategy = new QuestionByKeywordStrategy(questionPage,questionDao,
                    titleDao,questionTitleDao);
        }
    }


    //将question对象转换为QuestionVO对象
    @Override
    public QuestionVO convertToVO(Question question){
        QuestionVO questionVO = new QuestionVO();
        questionVO.setQuestion(question);
        int questionID = question.getId();
        int titleID = titleDao.selectTitleByQuestionID(questionID);
        int contentID = question.getContentId();
        Title title = titleDao.selectByPrimaryKey(titleID);
        Content content = contentDao.selectByPrimaryKey(contentID);
        questionVO.setTitle(title.getTitle());
        questionVO.setContent(content.getContent());
        return questionVO;
    }

    //更新问题数据
    @Override
    public Question updateQuestion(QuestionVO question){
        return null;
    }

    @Override
    public UserVO addQuestion(QuestionVO questionVO){
        Content content = new Content();
        content.setContent(questionVO.getContent());
        contentDao.insert(content);
        int contentId = content.getId();

        Question question = questionVO.getQuestion();
        //System.out.println(question);
        question.setResponseNum(0);
        question.setReportNum(0);
        question.setContentId(contentId);
        questionDao.insert(question);

        Title title = new Title();
        title.setTitle(questionVO.getTitle());
        titleDao.insert(title);
        System.out.println("queId" + question.getId() + "  titleId: " + title.getId());
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
        if(questionPage.getSortApproach().equals(QuestionSortApproach.SORT_BY_HEAT)){
            //通过热度排序
            createQuestionStrategy(1);//创建对应策略对象
        }else if(questionPage.getSortApproach().equals(QuestionSortApproach.SORT_BY_KEY_WORD)){
            //通过关键字查询
            createQuestionStrategy(3);//创建对应策略对象
        }else if(questionPage.getSortApproach().equals(QuestionSortApproach.SORT_BY_DATE)){
            //通过时间由近到远排序
            createQuestionStrategy(2);//创建对应策略对象
        }
        List<Question> questionList = questionStrategy.getQuestionList();
        List<QuestionVO> questionVOList = convertToVOList(questionList);
        questionPage.setQuestions(questionVOList);
        return questionPage;
    }

    //获取用户提问问题列表
    @Override
    public QuestionPage getQuestionPage(Integer userId, QuestionPage questionPage){
        this.questionPage = questionPage;
        questionStrategy = new QuestionByUidStrategy(userId, questionPage,questionDao);
        List<Question> questionList = questionStrategy.getQuestionList();
        List<QuestionVO> questionVOList = convertToVOList(questionList);
        questionPage.setQuestions(questionVOList);
        return questionPage;
    }

    @Override
    public QuestionPage getAttentionQuestionPage(Integer userId, QuestionPage questionPage){
        List<Question> questionList = new ArrayList<>();
        List<Integer> idList =  attentionService.getAttentionQuestionList(userId);
        for (int id:idList){
            questionList.add(getQuestionById(id));
        }
        List<QuestionVO> questionVOList = new ArrayList<>();
        for(Question question : questionList) {
            questionVOList.add(convertToVO(question));
        }
        questionPage.setQuestions(questionVOList);
        return questionPage;
    }

    @Override
    public QuestionPage getResponseQuestion(String userId, QuestionPage questionPage){
        QuestionStrategy questionStrategy;
        questionStrategy = new getResponseQuestion(Integer.parseInt(userId),questionDao, responseDao);
        List<Question> questionList = questionStrategy.getQuestionList();
        List<QuestionVO> questionVOList = convertToVOList(questionList);
        questionPage.setQuestions(questionVOList);
        return questionPage;
    }

    @Override
    public void deleteQuestionById(String id){
        int questionId = Integer.parseInt(id);
        int titleId = titleDao.selectTitleByQuestionID(questionId);
        int contentId = questionDao.selectByPrimaryKey(questionId).getContentId();
        titleDao.deleteQuestionTitle(questionId);
        titleDao.deleteByPrimaryKey(titleId);
        questionDao.deleteByPrimaryKey(questionId);
        contentDao.deleteByPrimaryKey(contentId);
    }

    @Override
    public void deleteQuestionsById(int[] questionIdList){
        for (int questionId:questionIdList){
            int titleId = titleDao.selectTitleByQuestionID(questionId);
            int contentId = questionDao.selectByPrimaryKey(questionId).getContentId();
            titleDao.deleteQuestionTitle(questionId);
            titleDao.deleteByPrimaryKey(titleId);
            questionDao.deleteByPrimaryKey(questionId);
            contentDao.deleteByPrimaryKey(contentId);
        }
    }

    //将List<Question>转化为List<QuestionVO>
    public List<QuestionVO> convertToVOList(List<Question> questions){
        List<QuestionVO> questionVOList = new ArrayList<>();
        for(Question question : questions) {
            questionVOList.add(convertToVO(question));
        }
        return questionVOList;
    }
}
