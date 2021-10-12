package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
@AllArgsConstructor
public class AccountVo {
    private Integer userId;
    private String  account;
    private String  password;
    private String  newPassword;

}
