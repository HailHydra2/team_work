package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.ContentDao;
import com.fzu.teamwork.model.Content;
import com.fzu.teamwork.model.ContentExample;
import io.swagger.annotations.Example;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Log4j
public class ContentServiceImpl implements ContentService{

    @Resource
    ContentDao contentDao;

    @Override
    public String getContentById(int id){
        Content content = contentDao.selectByPrimaryKey(id);
        return content.getContent();
    }
}
