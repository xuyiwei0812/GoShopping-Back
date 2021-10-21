package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountVo {
    private Integer userId;
    private String account;
    private String password;
    private String newPassword;

}
