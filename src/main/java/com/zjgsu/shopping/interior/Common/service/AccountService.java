package com.zjgsu.shopping.interior.Common.service;

import com.zjgsu.shopping.interior.Common.pojo.Account;

public interface AccountService {
    Boolean checkAccount(String account);

    Boolean raiseAccount(Account account);

    Integer getAccountAuthority(String accout);

}
