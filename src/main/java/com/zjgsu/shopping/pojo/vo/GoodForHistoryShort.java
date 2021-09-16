package com.zjgsu.shopping.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class GoodForHistoryShort {
    /**
     * 商品名称
     * 商品交易时间
     * 商品价格
     */
    private String name;
    private Date dealDate;
    private int price;

}
