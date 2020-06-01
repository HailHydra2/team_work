package com.fzu.teamwork.model;

import com.fzu.teamwork.dao.ResponseDao;
import com.fzu.teamwork.view.ResponsePage;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//ResponseService用于获取某个问题回复列表的策略子类
@Log4j
public class ResponseInAQuestion extends ResponseStrategy{

    //所属问题id
    private int questionId;
    //查询问题列表的分页信息
    private ResponsePage responsePage;

    private ResponseDao responseDao;

    //构造函数
    public ResponseInAQuestion(int questionId, ResponsePage page, ResponseDao responseDao){
        this.questionId = questionId;
        this.responsePage = page;
        this.responseDao = responseDao;
    }

    //获取回复列表
    public List<Response> getResponseList(){
        //log.info("page:{}",page);
        Map<String, Integer> map = new HashMap<>();
        map.put("questionId",questionId);
        //获取该页第一个回复的索引[（页码-1）*一页所包含的数量]
        int firstIndex = (responsePage.getPageIndex() - 1) * responsePage.getCount();
        map.put("start",firstIndex);
        map.put("count",responsePage.getCount());
        //查找id=questionId列表中start到start+count的子列表
        List<Response> responses = responseDao.selectSublistByQuestionId(map);
        //List<Response> responses = responseDao.test();
        //responses = responseDao.selectByExample(null);
        getButtonList();
        return responses;
    }

    //获取分页下面的按钮列表
    public void getButtonList(){
        //每页有几条数据
        int count = responsePage.getCount();
        //问题总条数
        ResponseExample example = new ResponseExample();
        example.createCriteria().andQuestionIdEqualTo(questionId);
        int total = (int)responseDao.countByExample(example);
        //当前页号
        int pageIndex = responsePage.getPageIndex();
        //总页数
        int maxIndex = total%count == 0 ? total/count : total/count +1;
        List<Integer> list = new ArrayList<>();
        //按钮的最后一个是当前页+5/最大页
        int lastIndex = (pageIndex + 5 > maxIndex) ? maxIndex : pageIndex + 5;
        //按钮列表的第一个
        int startIndex;
        if(lastIndex == pageIndex + 5){//最后一个没到最后一页，第一个就是当前页
            startIndex = pageIndex;
        }else{//最后一个是最后一页，第一个是
            int t = 5 - (maxIndex - pageIndex + 1);//前面要补几个按钮
            startIndex = pageIndex - t > 0 ? pageIndex - t : 1;
        }

        for(int i = startIndex; i <= lastIndex; i++){
            list.add(i);
        }
        responsePage.setButtonList(list);

        responsePage.setHasPrevious(true);//是否有上一页
        responsePage.setHasNext(true);//是否还有下一页
        responsePage.setPageNum(maxIndex);//设置总页数
        if(pageIndex == 1){
            responsePage.setHasPrevious(false);
        }
        if(pageIndex == maxIndex){
            responsePage.setHasNext(false);
        }
    }
}
