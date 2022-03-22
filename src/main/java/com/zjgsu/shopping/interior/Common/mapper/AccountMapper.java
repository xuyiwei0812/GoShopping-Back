package com.zjgsu.shopping.interior.Common.mapper;


import com.zjgsu.shopping.interior.Common.pojo.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountMapper {


    @Insert("insert into account (account,authority) values(#{account.account},#{account.authority})")
    Boolean raiseAccount(@Param("account") Account account);


    @Select("select * from account where account =#{account}")
    List<Account> searchAccount(@Param("account") String account);

}
