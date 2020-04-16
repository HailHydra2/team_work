package com.fzu.teamwork.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class AjaxResponse {

    @ApiModelProperty("是否请求成功")
    private boolean isok;
    private int code;
    private String message;
    private Object data;

    private AjaxResponse() {

    }

    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        return resultBean;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(200);
        resultBean.setMessage("success");
        resultBean.setData(data);
        return resultBean;
    }

    //登录界面账号不存在
    public static AjaxResponse noAccount() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(400);
        resultBean.setMessage("error");
        return resultBean;
    }


    //登录界面密码错误
    public static AjaxResponse errorPassword() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setIsok(true);
        resultBean.setCode(401);
        resultBean.setMessage("error");
        return resultBean;
    }



}