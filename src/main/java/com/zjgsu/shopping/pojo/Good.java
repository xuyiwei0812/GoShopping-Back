package com.zjgsu.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    /**
     * 商品编号(自增)
     * 商品价格
     * 商品名称
     * 商品描述
     * 商品是否处于冻结状态
     * 商品是否已经出售
     * 构造方式 GoodForSale(null,price,name,description,null)
     */
    private Integer goodId;
    private Integer sellerId;
    private Double goodPrice;
    private String goodName;
    private String description;
    private Boolean frozen;
    private Boolean sold;
    private Boolean wanted;
    private Boolean removed;
}
