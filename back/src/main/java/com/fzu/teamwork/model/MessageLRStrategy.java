package com.fzu.teamwork.model;


import com.fzu.teamwork.service.ResponseService;
import com.fzu.teamwork.service.UserService;
import com.fzu.teamwork.util.MessageWay;
import com.fzu.teamwork.util.ScoreNum;
import com.fzu.teamwork.view.ResponseVO;
import com.fzu.teamwork.view.UserVO;

//MessageService点赞某个回复消息进行处理的策略类（LR:Like Response）
public class MessageLRStrategy extends MessageOperateStrategy{

    //点赞的人
    private UserVO viewer;
    //被点赞回复的作者
    private UserVO author;
    //被点赞的回复
    private ResponseVO responseVO;
    //消息
    private InternalMessage message;

    private UserService userService;

    private ResponseService responseService;

    //构造函数
    public MessageLRStrategy(InternalMessage message, UserService userService, ResponseService responseService){
        this.userService = userService;
        this.responseService = responseService;
        this.message = message;
        //点赞的人
        User u = userService.getUserById(message.getOperator_id());
        viewer = userService.convertToUserVo(u);
        //被点赞的回复
        Response r = responseService.getResponseById(message.getObject_id());
        responseVO = responseService.convertToVO(r);
        //被点赞回复作者
        u = userService.getUserById(r.getAuthorId());
        author = userService.convertToUserVo(u);
    }


    //根据消息进行处理,返回要保存的Message信息
    public Message operate(){
        //更新回复数据（点赞数）
        updateResponse();
        //更新回复作者积分
        updateAuthor();
        //返回要保存到数据库的消息
        return createMessage();
    }

    //更新被点赞回复数据
    public void updateResponse(){
        Response r = responseVO.getResponse();

        if(message.getFlag() == 1){
            //点赞
            if(message.getFlag2() == -1){
                //原来是点灭
                //点灭数-1
                r.setDislikeNum(r.getDislikeNum() - 1);
            }
            //点赞数+1
            r.setLikeNum(r.getLikeNum() +1);
        }else{
            //取消点赞
            r.setLikeNum(r.getLikeNum() - 1);
        }

        responseVO.setResponse(r);
        //更新数据库信息
        responseService.updateResponse(responseVO);
    }

    //更新作者信息（积分）
    public void updateAuthor(){
        AccountData accountData = author.getAccountData();
        int score = accountData.getScore();
        if(message.getFlag() == 1){
            //点赞
            score += ScoreNum.LIKE_SCORE;
        }else{
            //取消点赞
            score -= ScoreNum.LIKE_SCORE;
        }
        accountData.setScore(score);
        //更新用户数据
        author.setAccountData(accountData);
        //更新数据库信息
        userService.updateUser(author);
    }

    //创建要保存到数据库的消息
    public Message createMessage(){
        Message message2 = null;
        if(message.getFlag() == 1){
            //点赞
            message2 = new Message();
            //设置消息类型为点赞回复
            message2.setWay(MessageWay.LIKE_RESPONSE);
            //消息描述
            String description = viewer.getUser().getName() + "点赞了您的回复";
            message2.setDescription(description);
            //设置消息对象为回复作者
            message2.setObjectId(author.getUser().getId());
        }
        //取消点赞不创建消息
        return message2;
    }
}
