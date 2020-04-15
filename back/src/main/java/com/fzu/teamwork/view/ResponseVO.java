package com.fzu.teamwork.view;

import com.fzu.teamwork.model.Response;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class ResponseVO {
    private Response response;
    private int quality;//回复质量 -1：差   0：普通   1：优质
    private String content;//回复内容
}
