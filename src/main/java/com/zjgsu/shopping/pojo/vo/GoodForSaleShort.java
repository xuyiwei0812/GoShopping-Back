package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodForSaleShort {
    private Integer goodId;
    private double price;
    private String name;
    private String img;

}
