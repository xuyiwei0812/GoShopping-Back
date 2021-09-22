package com.zjgsu.shopping.pojo;

import lombok.Data;

@Data
public class GoodForSale {
    /**
     * 商品编号(自增)
     * 商品名称
     * 商品描述
     * 商品价格
     * 商品是否处于冻结状态
     */
    private int goodId;
    private String name;
    private String description;
    private int price;
    private Boolean frozen;

    public GoodForSale(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.frozen = false;
    }

    public GoodForSale() {
    }
}
