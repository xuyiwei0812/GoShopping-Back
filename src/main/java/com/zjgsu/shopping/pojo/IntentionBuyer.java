package com.zjgsu.shopping.pojo;

import lombok.Data;

@Data
public class IntentionBuyer {
    /**
     * 意向编号
     * 用户编号
     * 姓名
     * 住址
     * 联系电话
     */
    private int intentionId;
    private int buyerId;
    private int goodId;
    private String name;
    private String location;
    private String phone;

}
