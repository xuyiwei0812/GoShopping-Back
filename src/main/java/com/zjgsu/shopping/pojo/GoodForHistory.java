package com.zjgsu.shopping.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GoodForHistory {
    /**
     * 商品编号(非自增)
     * 商品名称
     * 商品描述
     * 商品成交价格
     * 商品成交日期
     * 成交人电话
     */
    private int goodId;
    private String name;
    private String description;
    private int price;
    private Date dealDate;
    private String phone;
}
