package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.Buyer;
import lombok.Data;

@Data
public class IntentionBuyerShort {
    /**
     * 意向编号
     * 用户编号
     * 姓名
     * 住址
     * 联系电话
     */
    private int goodId;
    private String name;
    private String location;
    private String phone;
}
