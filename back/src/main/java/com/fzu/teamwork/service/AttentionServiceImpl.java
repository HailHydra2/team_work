package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.AttentionDao;
import com.fzu.teamwork.dao.UserDao;
import com.fzu.teamwork.model.Attention;
import com.fzu.teamwork.model.AttentionExample;
import com.fzu.teamwork.model.InternalMessage;
import com.fzu.teamwork.model.User;
import com.fzu.teamwork.util.MessageWay;
import com.fzu.teamwork.view.UserVO;
import io.swagger.annotations.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//关注的服务层实体类
@Slf4j
@Service
public class AttentionServiceImpl implements AttentionService{

    @Resource
    private AttentionDao attentionDao;

    @Resource
    private UserDao userDao;

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    //向数据库插入关注记录（返回改变后的用户数据—关注数发生改变）
    public UserVO insertAttention(Attention record){
        //创建关注记录中关注问题id和关注用户id和要插入记录一样的记录
        AttentionExample example = new AttentionExample();
        AttentionExample.Criteria criteria = example.createCriteria();
        criteria.andQuestionIdEqualTo(record.getQuestionId());
        criteria.andUserIdEqualTo(record.getUserId());
        System.out.println("questionId:" + record.getQuestionId() + "  userId:" + record.getUserId());
        System.out.println("record:" + record);
        List<Attention> attentionList = attentionDao.selectByExample(example);

        //判断数据库中是否已经有该记录
        if(attentionList.size() == 0){
            //没有
            attentionDao.insert(record);
        }else{
            Attention attention = attentionList.get(0);
            record.setId(attention.getId());
            //根据条件更新用户id和关注问题id与本次记录一样的记录
            attentionDao.updateByExample(record,example);
        }

        //发送消息给message更新数据
        InternalMessage message = new InternalMessage();
        //消息操作者设置为关注用户
        message.setOperator_id(record.getUserId());
        //用标记flag代表关注或取消关注
        if(record.getFlag() == 1){
            //关注问题
            message.setFlag(1);
        }else{
            //取消关注
            message.setFlag(-1);
        }
        //消息产生方式是关注问题
        message.setWay(MessageWay.ATTENTION);
        //发送消息对用户数据进行更新（关注数）
        messageService.updateInfoByMessage(message);
        User user = userDao.selectByPrimaryKey(record.getUserId());
        return userService.convertToUserVo(user);
    }

    //根据uid查询用户关注问题的id列表
    public List<Integer> getAttentionQuestionList(int uid){
        List<Integer> list = attentionDao.getAttentionQuestionList(uid);
        log.info("list:   {}", list);
        return list;
    }
}
