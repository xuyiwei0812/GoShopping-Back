package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealVo {
    private Integer intentionId;
    private Integer dealId;
    private Integer buyerId;
    private Integer sellerId;
    private Integer goodId;
}
