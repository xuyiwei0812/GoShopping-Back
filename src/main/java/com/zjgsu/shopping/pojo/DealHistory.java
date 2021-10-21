package com.zjgsu.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealHistory {
    /**
     * 商品编号
     * 商品价格
     * 商品描述
     * 商品名称
     * 商品成交价格
     * 商品成交日期
     * 成交人电话
     * 全参数构造
     */

    private Integer goodId;
    private Integer sellerId;
    private Double price;
    private String name;
    private String description;
    private String phone;
    private Date dealDate;


    public DealHistory(Integer goodId, Integer sellerId, String name, String description, Double price, Date dealDate, String phone) {
        this.goodId = goodId;
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.dealDate = dealDate;
        this.phone = phone;
    }

    public DealHistory(Good good, String phone, Date date) {
        this.goodId = good.getGoodId();
        this.sellerId = good.getSellerId();
        this.name = good.getName();
        this.description = good.getDescription();
        this.price = good.getPrice();
        this.phone = phone;
        this.dealDate = date;
    }
}
