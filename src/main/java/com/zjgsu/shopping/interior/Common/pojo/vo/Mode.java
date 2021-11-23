package com.zjgsu.shopping.interior.Common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mode {
    private Boolean removed ;
    private Boolean frozen;
    private Boolean sold;
    private Boolean wanted;
    private Integer class2;
    private Integer class1;
    private Integer sellerId;
}
