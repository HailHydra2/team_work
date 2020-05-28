package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.AttentionDao;
import com.fzu.teamwork.dao.KindDao;
import com.fzu.teamwork.dao.MessageDao;
import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.model.*;
import com.fzu.teamwork.util.Encryptor;
import com.fzu.teamwork.util.QuestionSortApproach;
import com.fzu.teamwork.util.ScoreNum;
import com.fzu.teamwork.util.UserIdentity;
import com.fzu.teamwork.view.QuestionPage;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.UserVO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class QuestionServiceImplTest{

    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Autowired
    private AttentionService attentionService;
    @Autowired
    private ResponseService responseService;
    @Autowired
    private ReportService reportService;
    @Resource
    private MessageDao messageDao;
    @Resource
    private KindDao kindDao;
    @Resource
    private AttentionDao attentionDao;
    @Resource
    private ResponseDao responseDao;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private User user;
    private UserVO userVO;

    @BeforeEach
    public void createTestUser(){
        user = new User();
        user.setAccount("221701422");
        user.setIdentity(UserIdentity.student);
        user.setName("testWSH");
        user.setPassword(Encryptor.encrypt("873"));
        user.setIdCard(Encryptor.encrypt("360102199003077873"));
        int code = userService.addUser(user);
        if(code == 0){
            userVO = userService.convertToUserVo(user);
        }else{
            System.out.println("用户添加失败，状态码为:" + code);
        }
    }

    @AfterEach
    //删除用户
    public void destroyUser(){
        userService.deleteUsers(user.getId());
    }

    public QuestionVO addQuestion(int i){
        QuestionVO questionVO = new QuestionVO();
        Question question = new Question();
        questionVO.setQuestion(question);
        questionVO.setTitle("title" + i);
        questionVO.setContent("content" + i);
        question.setAuthorId(user.getId());
        question.setCreateTime(new Date());
        questionService.addQuestion(questionVO);
        return questionVO;
    }

    //根据id获取问题
    @Test
    void getQuestionById() {
        QuestionVO questionVO = addQuestion(1);
        Question question = questionService.getQuestionById(questionVO.getQuestion().getId());
        Assert.assertNotNull(question);//验证是否成功从数据库获取对应问题
        Assert.assertEquals(questionVO.getQuestion().getId(), question.getId());//验证获取的问题是否一样
        question = questionService.getQuestionById(2);//不存在的问题id
        Assert.assertNull(question);//判断函数是否返回null
    }

    //测试添加问题与用户关系函数
    @Test
    void addRelationToUid(){
        //创建问题
        QuestionVO questionVO = addQuestion(1);
        //取消关注
        Attention attention = new Attention();
        attention.setFlag(0);//取消关注标志位
        attention.setQuestionId(questionVO.getQuestion().getId());
        attention.setCreateTime(new Date());
        attention.setUserId(user.getId());
        attentionService.insertAttention(attention);//添加关注记录
        //添加投诉
        ReportQuestion reportQuestion = new ReportQuestion();
        reportQuestion.setFlag(1);//投诉标志位
        reportQuestion.setQuestionId(questionVO.getQuestion().getId());//投诉问题id
        reportQuestion.setReportorId(user.getId());//投诉人id
        reportService.addQuestionReport(reportQuestion);//添加投诉记录
        questionService.addRelationToUId(questionVO,user.getId());
        Assert.assertTrue(!questionVO.isDoesAttention());//判断是否还未关注（上面添加的标志位是0-取消关注）
        Assert.assertTrue(questionVO.isDoesReported());//判断是否有投诉标志

        questionVO = addQuestion(2);
        //关注
        attention.setFlag(1);//取消关注标志位
        attention.setQuestionId(questionVO.getQuestion().getId());
        attention.setCreateTime(new Date());
        attention.setUserId(user.getId());
        attentionService.insertAttention(attention);//添加关注记录
        //没有投诉记录
        questionService.addRelationToUId(questionVO,user.getId());
        Assert.assertTrue(questionVO.isDoesAttention());//有关注记录
        Assert.assertTrue(!questionVO.isDoesReported());//没有投诉记录
    }
    //测试问题转换视图对象函数
    @Test
    void convertToVO() {
        QuestionVO questionVO = addQuestion(1);//添加问题
        QuestionVO questionVO1;
        Question question = questionVO.getQuestion();
        question.setAnonymous(1);//设置匿名
        questionVO1 = questionService.convertToVO(question);
        Assert.assertEquals("匿名用户", questionVO1.getAuthorName());//判断匿名是否有设置
        question.setAnonymous(0);//非匿名
        questionVO1 = questionService.convertToVO(question);
        Assert.assertEquals(user.getName(), questionVO1.getAuthorName());//判断作者是否一致
        Assert.assertEquals(questionVO.getTitle(), questionVO1.getTitle());//判断标题是否一致
        Assert.assertEquals(questionVO.getContent(), questionVO1.getContent());//判断内容是否一致

    }


    //添加问题测试
    @Test
    void addQuestion() {
        QuestionVO questionVO = addQuestion(1);
        Question question = questionVO.getQuestion();

        int questionNum = userVO.getAccountData().getQuestionNum();//提问数
        int score = userVO.getAccountData().getScore();//积分
        MessageExample example = new MessageExample();
        example.createCriteria().andObjectIdEqualTo(user.getId());
        int messageNum = messageDao.selectByExample(example).size();//消息数

        //查看是否成功插入数据库
        Assert.assertEquals(questionService.getQuestionById(question.getId()).getId(), question.getId());
        userVO = userService.convertToUserVo(user);
        //用户提问数是否+1
        Assert.assertEquals(questionNum+1, (long)userVO.getAccountData().getQuestionNum());
        //用户积分是否有增加
        Assert.assertEquals(score + ScoreNum.CREATE_QUESTION_SCORE,
                (long)userVO.getAccountData().getScore());
        //消息数是否+1
        Assert.assertEquals(questionNum+1,messageDao.selectByExample(example).size());
    }

    //测试根据热度获取问题列表
    @Test
    void getQuestionPageByHeat() {
        QuestionPage questionPage = new QuestionPage();
        questionPage.setPageIndex(1);
        questionPage.setCount(3);
        questionPage.setSortApproach(QuestionSortApproach.SORT_BY_HEAT);
        questionPage = questionService.getQuestionPage(questionPage);
        List<QuestionVO> questionList = questionPage.getQuestions();
        //判断是否是按热度（评论数）排序
        Assert.assertTrue(questionList.get(0).getQuestion().getResponseNum()
                >= questionList.get(1).getQuestion().getResponseNum());
        Assert.assertTrue(questionList.get(1).getQuestion().getResponseNum()
                >= questionList.get(2).getQuestion().getResponseNum());
    }

    //测试根据时间获取问题列表
    @Test
    void getQuestionPageByDate() {
        QuestionPage questionPage = new QuestionPage();
        questionPage.setPageIndex(1);
        questionPage.setCount(3);
        questionPage.setSortApproach(QuestionSortApproach.SORT_BY_DATE);
        questionPage = questionService.getQuestionPage(questionPage);
        List<QuestionVO> questionList = questionPage.getQuestions();
        //判断是否是按时间由近到远排序
        Assert.assertTrue(questionList.get(0).getQuestion().getCreateTime().getTime()
                >= questionList.get(1).getQuestion().getCreateTime().getTime());
        Assert.assertTrue(questionList.get(1).getQuestion().getCreateTime().getTime()
                >= questionList.get(2).getQuestion().getCreateTime().getTime());
    }

    //测试根据关键字获取问题列表
    @Test
    void getQuestionPageByKey() {
        String keyWord = "title";
        QuestionPage questionPage = new QuestionPage();
        questionPage.setPageIndex(1);
        questionPage.setCount(3);
        questionPage.setKeyWord(keyWord);
        questionPage.setSortApproach(QuestionSortApproach.SORT_BY_KEY_WORD);
        questionPage = questionService.getQuestionPage(questionPage);
        List<QuestionVO> questionList = questionPage.getQuestions();
        for(QuestionVO questionVO : questionList){//判断标题是否包含关键字
            String title = questionVO.getTitle();
            Assert.assertTrue(title.indexOf(keyWord) != -1);
        }
    }

    //测试根据类别+热度获取问题列表
    @Test
    void getQuestionPageByKindAndHeat() {
        for(int i = 0; i <10; i++){//添加10个类别为1的问题
            QuestionVO questionVO = addQuestion(i);
            questionVO.getQuestion().setKindId(1);
            questionService.updateQuestion(questionVO);
        }
        for (int i = 0; i <10; i++){//添加10个类别为0的问题
            QuestionVO questionVO = addQuestion(i);
            questionService.updateQuestion(questionVO);
        }
        int kindId = 1;
        String kind = kindDao.selectByPrimaryKey(kindId).getName();
        QuestionPage questionPage = new QuestionPage();
        questionPage.setPageIndex(1);
        questionPage.setCount(10);
        questionPage.setKind(kind);//根据类别查找
        questionPage.setSortApproach(QuestionSortApproach.SORT_BY_KIND_AND_HEAT);
        questionPage = questionService.getQuestionPage(questionPage);
        List<QuestionVO> questionList = questionPage.getQuestions();
        for(QuestionVO questionVO : questionList){//判断标题是否类别均为1
            Assert.assertEquals(kindId,(long)questionVO.getQuestion().getKindId());
        }
        for(int i = 1; i < questionList.size(); i++){//判断是否按照热度排序
            Assert.assertTrue(questionList.get(i-1).getQuestion().getResponseNum()
                    >= questionList.get(i).getQuestion().getResponseNum());
        }
    }

    //测试根据类别+时间获取问题列表
    @Test
    void getQuestionPageByKindAndDate() {
        for(int i = 0; i <10; i++){//添加10个类别为1的问题
            QuestionVO questionVO = addQuestion(i);
            questionVO.getQuestion().setKindId(1);
            questionService.updateQuestion(questionVO);
        }
        for (int i = 0; i <10; i++){//添加10个类别为0的问题
            QuestionVO questionVO = addQuestion(i);
            questionService.updateQuestion(questionVO);
        }
        int kindId = 1;
        String kind = kindDao.selectByPrimaryKey(kindId).getName();
        QuestionPage questionPage = new QuestionPage();
        questionPage.setPageIndex(1);
        questionPage.setCount(10);
        questionPage.setKind(kind);//根据类别查找
        questionPage.setSortApproach(QuestionSortApproach.SORT_BY_KIND_AND_DATE);
        questionPage = questionService.getQuestionPage(questionPage);
        List<QuestionVO> questionList = questionPage.getQuestions();
        for(QuestionVO questionVO : questionList){//判断标题是否类别均为1
            Assert.assertEquals(kindId,(long)questionVO.getQuestion().getKindId());
        }
        for(int i = 1; i < questionList.size(); i++){//判断是否按照时间由近到远排序
            Assert.assertTrue(questionList.get(i-1).getQuestion().getCreateTime().getTime()
                    >= questionList.get(i).getQuestion().getCreateTime().getTime());
        }
    }

    //测试用户关注问题列表
    @Test
    void getQuestionPageBeAttention() {
        for(int i =0; i < 10; i++){//创建10个问题
            QuestionVO questionVO = addQuestion(i);
            Attention attention = new Attention();
            attention.setCreateTime(new Date());
            attention.setQuestionId(questionVO.getQuestion().getId());
            attention.setUserId(user.getId());
            attention.setFlag(1);
            attentionService.insertAttention(attention);//插入关注记录
        }
        QuestionPage questionPage = new QuestionPage();
        questionPage.setPageIndex(1);
        questionPage.setCount(10);
        questionPage.setSortApproach(QuestionSortApproach.SORT_BY_DATE);
        questionService.getQuestionPage(user.getId(),questionPage,2);//查询关注问题分页
        for(QuestionVO questionVO : questionPage.getQuestions()){
            AttentionExample example = new AttentionExample();
            example.createCriteria().andUserIdEqualTo(user.getId()).
                    andQuestionIdEqualTo(questionVO.getQuestion().getId());
            //有且仅有一条该用户对该问题的关注记录
            Assert.assertTrue(attentionDao.selectByExample(example).size() == 1);
            Attention attention = attentionDao.selectByExample(example).get(0);
            Assert.assertEquals(1,(long)attention.getFlag());//关注标志位为1
        }
    }

    //测试用户回复问题列表
    @Test
    void getQuestionPageBeResponse() {
        for(int i =0; i < 10; i++){//创建10个问题
            QuestionVO questionVO = addQuestion(i);
            ResponseVO responseVO = new ResponseVO();//创建回复
            Response response = new Response();
            response.setCreateTime(new Date());
            response.setAuthorId(user.getId());
            response.setQuestionId(questionVO.getQuestion().getId());
            responseVO.setResponse(response);
            responseVO.setContent("content" + i);
            responseService.insertResponse(responseVO);//将回复保存到数据库
        }
        QuestionPage questionPage = new QuestionPage();
        questionPage.setPageIndex(1);
        questionPage.setCount(10);
        questionPage.setSortApproach(QuestionSortApproach.SORT_BY_DATE);
        questionService.getQuestionPage(user.getId(),questionPage,0);//查询回复问题分页
        for(QuestionVO questionVO : questionPage.getQuestions()){
            ResponseExample example = new ResponseExample();
            example.createCriteria().andQuestionIdEqualTo(questionVO.getQuestion().getId())
                    .andAuthorIdEqualTo(user.getId());
            List<Response> responses = responseDao.selectByExample(example);
            Assert.assertTrue(responses.size() > 0);//存在对该问题的回复
        }
    }

    //测试用户提问列表
    @Test
    void getUserQuestionPage() {
        for(int i =0; i < 10; i++){//创建10个问题
            QuestionVO questionVO = addQuestion(i);
        }
        QuestionPage questionPage = new QuestionPage();
        questionPage.setPageIndex(1);
        questionPage.setCount(10);
        questionPage.setSortApproach(QuestionSortApproach.SORT_BY_DATE);
        questionService.getQuestionPage(user.getId(),questionPage,1);//查询回复问题分页
        for(QuestionVO questionVO : questionPage.getQuestions()){
            assertEquals(user.getId(),questionVO.getQuestion().getAuthorId());//判断问题是否是查询用户的
        }
        Assert.assertTrue(questionPage.getQuestions().size() == 10);
    }

    @Test
    void deleteQuestionById() {
        //删除不存在的问题
        boolean result;
        result = questionService.deleteQuestionById(1);
        Assert.assertEquals(false,result);

        //添加问题
        QuestionVO questionVO = addQuestion(1);
        Question question = questionVO.getQuestion();

        //提问数
        userVO = userService.convertToUserVo(user);
        int questionNum = userVO.getAccountData().getQuestionNum();
        MessageExample example = new MessageExample();
        example.createCriteria().andObjectIdEqualTo(user.getId());
        //消息数
        int messageNum = messageDao.selectByExample(example).size();

        //删除存在问题
        result = questionService.deleteQuestionById(question.getId());
        Assert.assertEquals(true, result);
        //判断数据库是否成功删除
        Assert.assertEquals(null, questionService.getQuestionById(question.getId()));
        //用户消息数是否+1
        Assert.assertEquals(messageNum + 1, messageDao.selectByExample(example).size());
        //作者提问数是否-1
        userVO = userService.convertToUserVo(user);
        Assert.assertEquals(questionNum - 1, (long)userVO.getAccountData().getQuestionNum());
    }

    //批量删除问题
    @Test
    void deleteQuestionsById() {
        List<Integer> failedList = new ArrayList<>();//删除失败的id列表
        List<Integer> questionIdList = new ArrayList<>();//要删除问题的id列表
        int questionNum;//作者问题数
        int messageNum;//作者消息数
        MessageExample messageExample = new MessageExample();//查询作者消息的查询条件
        messageExample.createCriteria().andObjectIdEqualTo(user.getId());
        //添加问题
        for(int i = 0; i < 10; i++){
            QuestionVO questionVO = addQuestion(i);
            questionIdList.add(questionVO.getQuestion().getId());
        }
        userVO = userService.convertToUserVo(user);
        questionNum = userVO.getAccountData().getQuestionNum();//作者当前问题数
        messageNum = messageDao.selectByExample(messageExample).size();//作者当前消息数

        //批量删除刚添加的问题（全对）
        failedList = questionService.deleteQuestionsById(questionIdList);
        for(int id : questionIdList){//验证问题在数据库中都已经被删除
            Assert.assertEquals(null, questionService.getQuestionById(id));
        }
        assertEquals(0,failedList.size());//预期没有删除失败的问题
        userVO = userService.convertToUserVo(user);//获取用户当前最新数据
        assertEquals(questionNum - 10, userVO.getAccountData().getQuestionNum());//提问数-10
        //消息数+10
        Assert.assertEquals(messageNum + 10, messageDao.selectByExample(messageExample).size());

        //为删除问题id列表添加不存在问题的id(全错，刚才已经删了)
        failedList = questionService.deleteQuestionsById(questionIdList);
        questionNum = userVO.getAccountData().getQuestionNum();
        messageNum = messageDao.selectByExample(messageExample).size();
        //失败列表长度为10
        Assert.assertEquals(10,failedList.size());
        for(int i = 0; i < questionIdList.size(); i++){//检查返回失败的id列表
            Assert.assertEquals(questionIdList.get(i),failedList.get(i));
        }
        userVO = userService.convertToUserVo(user);//获取用户当前最新数据
        //提问数不变
        Assert.assertEquals(questionNum, (long)userVO.getAccountData().getQuestionNum());
        //消息数不变
        Assert.assertEquals(messageNum, (long)messageDao.selectByExample(messageExample).size());

        //删除存在的问题+不存在的问题（部分对）
        questionIdList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            QuestionVO questionVO = addQuestion(i);
            questionIdList.add(questionVO.getQuestion().getId());
        }
        //不存在的问题id列表
        for(int i = 0; i < 5; i ++){
            questionIdList.add(i);
        }
        userVO = userService.convertToUserVo(user);//获取最新的用户信息
        questionNum = userVO.getAccountData().getQuestionNum();//用户问题数
        messageNum = messageDao.selectByExample(messageExample).size();
        //批量删除
        failedList = questionService.deleteQuestionsById(questionIdList);
        userVO = userService.convertToUserVo(user);//获取最新用户信息
        //失败列表长度为5
        Assert.assertEquals(5,failedList.size());
        for(int i = 0; i < 5; i++){//失败id列表是刚添加进去不存在的id列表
            Assert.assertEquals(i,(long)failedList.get(i));
        }
        //问题数-10
        Assert.assertEquals(questionNum - 10,
                (long)userVO.getAccountData().getQuestionNum());
        //消息数+10
        Assert.assertEquals(messageNum + 10, messageDao.selectByExample(messageExample).size());
    }
}