package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.AccountDataDao;
import com.fzu.teamwork.model.AccountData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@Service
@RestController
public class AccountDataServiceImpl implements AccountDataService{

    @Resource
    private AccountDataDao accountDataDao;

    @Override
    public AccountData getById(int id){
        return accountDataDao.selectByPrimaryKey(id);
    }
}
