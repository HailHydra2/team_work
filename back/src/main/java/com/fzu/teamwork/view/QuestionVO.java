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
}
