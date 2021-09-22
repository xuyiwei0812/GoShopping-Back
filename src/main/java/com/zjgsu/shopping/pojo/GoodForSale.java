package com.zjgsu.shopping.pojo;

import lombok.Data;

@Data
public class GoodForSale {
    /**
     * 商品编号(自增)
     * 商品价格
     * 商品名称
     * 商品描述
     * 商品是否处于冻结状态
     * 构造方式 GoodForSale(null,price,name,description,null)
     */
    private Integer goodId;
    private Integer price;
    private String name;
    private String description;
    private Boolean frozen;

    public GoodForSale(Integer goodId, Integer price, String name, String description, Boolean frozen) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public GoodForSale() {
    }
}
