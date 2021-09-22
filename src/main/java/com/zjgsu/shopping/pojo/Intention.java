package com.zjgsu.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Intention {
    /**
     * 意向编号
     * 用户编号
     *
     * 构造方式Intention(null,buyer,goodId)
     */
    private Integer intentionId;
    private Integer buyerId;
    private Integer goodId;

    public Intention(Integer intentionId, Integer buyerId, Integer goodId) {
        this.buyerId = buyerId;
        this.goodId = goodId;
    }
}
