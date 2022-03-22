package com.zjgsu.shopping.interior.Common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountVo {
    private Integer  authority;// 0 super 1 seller 2 buyer
    private Integer userId;
    private String account;
    private String password;
    private String newPassword;
    private String location;
    private String name;
    private String phone;

}
