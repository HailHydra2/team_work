package com.fzu.teamwork.view;

import com.fzu.teamwork.model.Question;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
public class QuestionVO{
    private Question question;
    private String title;
    private String content;
    //是否已经关注该问题
    private boolean doesAttention;
    //是否已经投诉该问题
    private boolean doesReported;
    //提问者姓名
    private String authorName;
}
