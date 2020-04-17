package com.fzu.teamwork.service;

import com.fzu.teamwork.dao.AccountDataDao;
import com.fzu.teamwork.model.AccountData;

import javax.annotation.Resource;

public class AccountDataServiceImpl implements AccountDataService{

    @Resource
    private AccountDataDao accountDataDao;

    @Override
    public AccountData getById(int id){
        return accountDataDao.selectByPrimaryKey(id);
    }
}
