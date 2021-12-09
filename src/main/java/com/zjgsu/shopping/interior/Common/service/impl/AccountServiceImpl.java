package com.zjgsu.shopping.interior.Common.service.impl;

import com.zjgsu.shopping.interior.Common.mapper.AccountMapper;
import com.zjgsu.shopping.interior.Common.pojo.Account;
import com.zjgsu.shopping.interior.Common.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountMapper accountMapper;


    //cunzaichu1
    @Override
    public Boolean checkAccount(String account) {
        return !accountMapper.searchAccount(account).isEmpty();
    }

    @Override
    public Boolean raiseAccount(Account account) {
        return accountMapper.raiseAccount(account);
    }

    @Override
    public Integer getAccountAuthority(String accout) {
        List<Account> li = accountMapper.searchAccount(accout);
        if(li.isEmpty()) return -1;
        else return li.get(0).getAuthority();
    }


}
