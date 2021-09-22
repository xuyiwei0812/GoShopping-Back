package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class GoodForHistoryShort {
    /**
     * 商品名称
     * 商品交易时间
     * 商品价格
     */
    private Integer goodId;
    private Integer price;
    private String name;
    private Date dealDate;


}
