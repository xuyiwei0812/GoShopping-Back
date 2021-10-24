package com.zjgsu.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.ObjectOutputStream;

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
    private Double  goodPrice;
    private String  goodName;
    private String  description;
    private Boolean frozen;
    private Boolean sold;
    private Boolean wanted;
    private Boolean removed;
//    public Good(Integer sellerId , Double goodPrice , String goodName,String description){
//        this.sellerId    = sellerId;
//        this.goodPrice   = goodPrice;
//        this.goodName    = goodName;
//        this.description = description;
//    }
    public Good(Integer goodId){
        this.goodId = goodId;
    }
}
