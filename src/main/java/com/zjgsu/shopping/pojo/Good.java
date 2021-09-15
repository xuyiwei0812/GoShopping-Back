package com.zjgsu.shopping.pojo;

public class Good {
    /**
     * 商品的编号
     * 商品的名称
     * 商品的描述
     * 商品的图片
     * 商品的价格
     * 商品是否处于冻结状态
     * 商品是否已经出售(进入历史记录)
     */

    private int goodId;
    private String name;
    private String describe;
    private String imagine;//base64
    private int price;
    private Boolean frozen;
    private Boolean sold;
}
