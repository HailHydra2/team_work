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

    @Resource(name = "contentServiceImpl")
    ContentService contentService;

}
