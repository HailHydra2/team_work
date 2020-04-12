package com.fzu.teamwork.dao;

import com.fzu.teamwork.model.AccountData;
import com.fzu.teamwork.model.AccountDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountDataDao {
    long countByExample(AccountDataExample example);

    int deleteByExample(AccountDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountData record);

    int insertSelective(AccountData record);

    List<AccountData> selectByExample(AccountDataExample example);

    AccountData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountData record, @Param("example") AccountDataExample example);

    int updateByExample(@Param("record") AccountData record, @Param("example") AccountDataExample example);

    int updateByPrimaryKeySelective(AccountData record);

    int updateByPrimaryKey(AccountData record);
}