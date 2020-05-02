package com.fzu.teamwork.model;

import com.fzu.teamwork.service.QuestionService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.ScoreNum;
import com.fzu.teamwork.view.QuestionVO;
import com.fzu.teamwork.view.UserVO;

//MessageService对投诉问题消息进行处理的策略类(RepQ: Report Question)
public class MessageRepQStrategy extends MessageOperateStrategy{

    //投诉者
    private UserVO complainant;
    //被投诉问题的作者
    private UserVO author;
    //被投诉的问题
    private QuestionVO questionVO;
    //内部传递消息
    private InternalMessage internalMessage;

    private UserService userService;
    private QuestionService questionService;

    //构造函数
    public MessageRepQStrategy(InternalMessage message, UserService userService, QuestionService questionService){
        this.userService = userService;
        this.questionService = questionService;
        internalMessage = message;

        //获取投诉者实体
        User user = userService.getUserById(message.getOperator_id());
        complainant = userService.convertToUserVo(user);
        //获取被投诉问题实体
        Question question = questionService.getQuestionById(message.getObject_id());
        questionVO = questionService.convertToVO(question);
        //获取被投诉问题作者实体
        user = userService.getUserById(question.getAuthorId());
        author = userService.convertToUserVo(user);
    }

    //根据消息进行处理,返回要保存的Message信息
    public Message operate(){
        //更新被投诉问题信息（被投诉数+1）
        updateQuestion();
        //更新被投诉问题作者信息（扣除积分）
        updateAuthor();
        //投诉问题消息不保存到数据库
        return null;
    }

    //更新被投诉问题数据（被投诉数）
    public void updateQuestion(){
        Question question = questionVO.getQuestion();
        //问题被投诉数+1/-1
        question.setReportNum(question.getReportNum() + internalMessage.getFlag());
        questionVO.setQuestion(question);
        //更新数据库信息
        questionService.updateQuestion(questionVO);
    }

    //更新被投诉问题作者信息（扣除积分）
    public void updateAuthor(){
        AccountData accountData = author.getAccountData();
        int score = accountData.getScore();
        if(internalMessage.getFlag() == 1){
            //扣除积分
            score -= ScoreNum.REPORT_QUESTION_SCORE;
        }else{
            //取消投诉
            //恢复积分
            score += ScoreNum.REPORT_QUESTION_SCORE;
        }
        accountData.setScore(score);
        //更新账户信息
        author.setAccountData(accountData);
        //保存到数据库
        userService.updateUser(author);
    }
}
