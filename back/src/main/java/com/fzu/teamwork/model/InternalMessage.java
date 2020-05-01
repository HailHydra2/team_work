package com.fzu.teamwork.model;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
public class InternalMessage {

    //操作者id
    private int operator_id;
    //目标对象id
    private int object_id;
    //操作方式
    private String way;
    // 变化标志
    private int flag;
}
