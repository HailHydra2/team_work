package com.fzu.teamwork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.fzu.teamwork.dao"})
public class TeamworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamworkApplication.class, args);
    }

}
