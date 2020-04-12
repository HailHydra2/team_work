package com.fzu.teamwork.view;

import com.fzu.teamwork.model.Message;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class MessagePage extends Page{
    private List<Message> messages;
}
