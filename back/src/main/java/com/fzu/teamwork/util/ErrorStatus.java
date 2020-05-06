package com.fzu.teamwork.util;


/**
 * 出错返回的状态码
 */
public class ErrorStatus {
    public static int NOT_LOGGED_IN = 400;//未登录
    public static int PASSWORD_ERROR= 401;//密码错误
    public static int ACCOUNT_NOT_EXIT = 402;//用户不存在
    public static int BEYOND_IDENTITY_LIMIT = 403;//权限不足
    public static int BAD_TOKEN = 405;//错误的token
    public static int CHANGE_PWD_FAILED =406;//修改密码失败（密保错误）
}
