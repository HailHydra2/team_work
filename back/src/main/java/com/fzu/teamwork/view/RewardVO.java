package com.fzu.teamwork.view;

import com.fzu.teamwork.model.Reward;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class RewardVO{
    private Reward reward;
    private String name;//申请用户的姓名
    private String account;//申请用户学号
}
