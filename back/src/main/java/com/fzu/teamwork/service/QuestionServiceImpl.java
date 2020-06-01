package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.*;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.MessageWay;
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
    private ResponseService responseService;

    @Resource
    private TitleDao titleDao;

    @Resource
    private ContentDao contentDao;
    @Resource
    private QuestionTitleDao questionTitleDao;
    @Resource
    private AttentionDao attentionDao;
    @Resource
    private UserDao userDao;
    @Resource
    private ReportQuestionDao reportQuestionDao;
    @Resource
    private KindDao kindDao;

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

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
        }else if(type == 4){//通过类别筛选并按热度排序
            questionStrategy = new QuestionByKindAndHeat(questionPage,questionDao,kindDao);
        }else if(type == 5){//通过类别筛选并按时间排序
            questionStrategy = new QuestionByKindAndDate(questionPage,questionDao,kindDao);
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
        int authorID = question.getAuthorId();
        int kindID = question.getKindId();

        Title title = titleDao.selectByPrimaryKey(titleID);
        Content content = contentDao.selectByPrimaryKey(contentID);
        User author = userDao.selectByPrimaryKey(authorID);
        Kind kind = kindDao.selectByPrimaryKey(kindID);

        questionVO.setTitle(title.getTitle());
        questionVO.setContent(content.getContent());
        questionVO.setKind(kind.getName());
        if(question.getAnonymous() == 0){//非匿名
            questionVO.setAuthorName(author.getName());
        }else{//匿名
            questionVO.setAuthorName("匿名用户");
        }
        return questionVO;
    }

    //更新问题数据
    @Override
    public Question updateQuestion(QuestionVO questionVO){
        Question question = questionVO.getQuestion();
        //更新问题数据（回复数等），因为没有更改标题内容功能因此暂时不做对这两部分的修改
        questionDao.updateByPrimaryKey(question);
        return question;
    }

    //创建问题
    @Override
    public UserVO insertQuestion(QuestionVO questionVO){
        Content content = new Content();
        content.setContent(questionVO.getContent());
        contentDao.insert(content);
        int contentId = content.getId();

        Question question = questionVO.getQuestion();
        question.setContentId(contentId);
        questionDao.insert(question);

        Title title = new Title();
        title.setTitle(questionVO.getTitle());
        titleDao.insert(title);
        titleDao.insertQuestionTitle(question.getId(),title.getId());

        int userID = question.getAuthorId();
        User user = userService.getUserById(userID);

        //创建内部传输消息
        InternalMessage message = new InternalMessage();
        //操作对象是作者
        message.setOperator_id(userID);
        //操作对象是创建的问题
        message.setObject_id(question.getId());
        //操作方式
        message.setWay(MessageWay.CREATE_QUESTION);
        //发送消息
        messageService.updateInfoByMessage(message);
        //重新获取用户信息（因为消息处理模块有更新用户数据）
        user = userService.getUserById(userID);
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
        }else if(questionPage.getSortApproach().equals(QuestionSortApproach.SORT_BY_KIND_AND_HEAT)){
            //通过类别筛选并按热度排序
            createQuestionStrategy(4);//创建对应策略对象
        }else if(questionPage.getSortApproach().equals(QuestionSortApproach.SORT_BY_KIND_AND_DATE)){
            //通过类别筛选并按时间排序
            createQuestionStrategy(5);//创建对应策略对象
        }
        List<Question> questionList = questionStrategy.getQuestionList();
        questionStrategy.getButtonList();//获取分页按钮
        List<QuestionVO> questionVOList = convertToVOList(questionList);
        questionPage.setQuestions(questionVOList);
        return questionPage;
    }

    //获取用户回复、提问问题列表
    //type：0回复，1:提问, 2:关注
    @Override
    public QuestionPage getQuestionPage(Integer userId, QuestionPage questionPage, int type){
        this.questionPage = questionPage;
        if(type == RESPONSE_TYPE){//回复
            questionStrategy = new QuestionBeResponse(userId,questionPage,questionDao);
        }else if(type == QUESTION_TYPE){//提问
            questionStrategy = new QuestionByUidStrategy(userId, questionPage,questionDao);
        }else if(type == ATTENTION_TYPE){//关注
            questionStrategy = new QuestionBeAttentionStrategy(userId,questionPage,questionDao,attentionDao);
        }
        List<Question> questionList = questionStrategy.getQuestionList();
        questionStrategy.getButtonList();//获取分页按钮
        List<QuestionVO> questionVOList = convertToVOList(questionList);
        questionPage.setQuestions(questionVOList);
        return questionPage;
    }


    @Override
    public boolean deleteQuestionById(int questionId){
        if(questionDao.selectByPrimaryKey(questionId) == null){//数据库中不存在该问题
            return false;
        }

        InternalMessage message = new InternalMessage();
        message.setWay(MessageWay.DELETE_QUESTION);//删除问题
        message.setObject_id(questionId);//被操作对象是问题
        messageService.updateInfoByMessage(message);

        int titleId;
        int contentId;
        //删除回复
        ResponseExample example = new ResponseExample();//创建删除回复条件
        example.createCriteria().andQuestionIdEqualTo(questionId);
        List<Response> responseList = responseDao.selectByExample(example);//问题包含的回复列表
        for(Response response :responseList){//删除回复在内容表的记录
            responseService.deleteResponseById(response.getId());
        }

        //删除问题
        titleId = titleDao.selectTitleByQuestionID(questionId);
        contentId = questionDao.selectByPrimaryKey(questionId).getContentId();
        titleDao.deleteByPrimaryKey(titleId);//删除标题
        questionDao.deleteByPrimaryKey(questionId);//删除问题
        contentDao.deleteByPrimaryKey(contentId);//删除问题描述内容
        return true;//删除成功
    }

    //批量删除问题（返回删除失败问题id列表）
    @Override
    public List<Integer> deleteQuestionsById(List<Integer> questionIdList) {
        List<Integer> failedList = new ArrayList<>();
        for (int questionId : questionIdList) {
            if (deleteQuestionById(questionId) == false) {//删除失败（不存在该问题）
                failedList.add(questionId);
            }
        }
        return failedList;
    }
    //将List<Question>转化为List<QuestionVO>
    public List<QuestionVO> convertToVOList(List<Question> questions){
        List<QuestionVO> questionVOList = new ArrayList<>();
        for(Question question : questions) {
            questionVOList.add(convertToVO(question));
        }
        return questionVOList;
    }

    //为QuestionVO添加与用户uid之间的关系（是否已经关注、投诉）
    @Override
    public void addRelationToUId(QuestionVO questionVO, Integer uid){
        //创建关注表查询条件
        AttentionExample example = new AttentionExample();
        AttentionExample.Criteria criteria = example.createCriteria();
        //关注记录用户id、问题id与查询情况一致，flag=1
        criteria.andUserIdEqualTo(uid);
        criteria.andQuestionIdEqualTo(questionVO.getQuestion().getId());
        criteria.andFlagEqualTo(1);
        //查询结果
        List<Attention> attentionList = attentionDao.selectByExample(example);
        if(attentionList.size() > 0){//已关注
            questionVO.setDoesAttention(true);
        }else{//未关注
            questionVO.setDoesAttention(false);
        }
        //创建投诉查询条件
        ReportQuestionExample example1 = new ReportQuestionExample();
        ReportQuestionExample.Criteria criteria1 = example1.createCriteria();
        //投诉记录用户id、问题id与查询情况一致，flag=1
        criteria1.andQuestionIdEqualTo(questionVO.getQuestion().getId());
        criteria1.andReportorIdEqualTo(uid);
        criteria1.andFlagEqualTo(1);
        //查询结果
        List<ReportQuestion> reportQuestionList = reportQuestionDao.selectByExample(example1);
        if(reportQuestionList.size() > 0){//已投诉
            questionVO.setDoesReported(true);
        }else{//未投诉
            questionVO.setDoesReported(false);
        }
    }
}
