package com.fzu.teamwork.model;

import javax.xml.crypto.Data;

public class Report {
    private int id;
    private String type;//question：投诉问题   response：投诉回复
    private int questionId;//被投诉问题id
    private int responseId;//被投诉回复id
    private Data reportTime;//投诉时间
}
