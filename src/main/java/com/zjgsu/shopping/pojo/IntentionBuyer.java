package com.zjgsu.shopping.pojo;

import lombok.Data;

@Data
public class IntentionBuyer {
    /**
     * 用户编号
     * 姓名
     * 住址
     * 联系电话
     */
    private int buyerId;
    private String name;
    private String location;
    private String phone;

}
