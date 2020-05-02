package com.fzu.teamwork.view;

import com.fzu.teamwork.model.Content;
import com.fzu.teamwork.model.Response;
import com.fzu.teamwork.service.ContentService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;


@Data
@Slf4j
public class ResponseVO {
    private Response response;
    private int quality;//回复质量 -1：差   0：普通   1：优质
    private String content;//回复内容
    private int like;//用户点赞情况-1：点灭  0：都没有  1：点赞
    private boolean doesReported;//用户是否投诉过
    private String authorName;//回复者姓名

    @Resource(name = "contentServiceImpl")
    ContentService contentService;

}
