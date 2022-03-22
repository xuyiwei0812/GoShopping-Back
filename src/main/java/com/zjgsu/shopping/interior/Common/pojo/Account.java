package com.zjgsu.shopping.interior.Common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer  authority;// 0 super 1 seller 2 buyer
    private String account;


}
