package com.zjgsu.shopping.interior.Common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodVo {
    private Integer goodId;
    private Integer sellerId;
    private Integer storage;
    private Integer class2;
    private Double  goodPrice;
    private String  goodName;
    private String  description;
    private Boolean frozen;
    private Boolean sold;
    private Boolean wanted;
    private Boolean removed;
    private List<String> img;
}
