package com.zjgsu.shopping.interior.Common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statement {
    private Integer buyerId;
    private Integer statement;
    private Integer sellerId;
}
