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
    public static int SCORE_INSUFFICIENT = 407;//兑换奖励积分不足
    public static int QUESTION_NOT_EXIT = 408;//要操作的问题不存在
    public static int RESPONSE_NOT_EXIT = 409;//要操作的回复不存在
    public static int BLOCK_HAS_EXIT = 410;//临时板块已存在
    public static int BLOCK_NOT_EXIT = 411;//临时板块不存在
    public static int ACCOUNT_HAS_EXIT = 412;//账号（学号）已经存在
    public static int ID_CARD_HAS_EXIT = 413;//身份证号已经存在
    public static int ACCOUNT_ILLEGAL = 414;//账号（学号不合法）
    public static int ID_CARD_ILLEGAL = 415;//身份证不合法
    public static int SOME_USER_ILLEGAL = 416;//部分账户数据不合法
    public static int ACCOUNT_NULL = 417;//账号为空
    public static int NAME_NULL = 418;//姓名为空
    public static int ID_CARD_NULL = 419;//身份证为空
    public static int IDENTITY_NULL = 420;//身份为空
    public static int IDENTITY_ERROR = 421;//身份错误
    public static int ID_CARD_NOT_MATCH = 422;//身份证不匹配
}
