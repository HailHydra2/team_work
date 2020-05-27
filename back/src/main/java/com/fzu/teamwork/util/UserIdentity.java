package com.fzu.teamwork.util;

public class UserIdentity {
    static public String teacher = "teacher";//老师
    static public String admin = "administrator";//管理员
    static public String student = "student";//学生

    //判断身份是否合法
    public static boolean doesLegal(String identity){
        if(identity.equals(teacher) || identity.equals(admin) || identity.equals(student)){
            return true;
        }else{
            return false;
        }
    }
}
