package com.zjgsu.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Deal {
    /**
     * 交易编号
     * 买家编号
     * 卖家编号
     * 商品编号
     * Business(null,buyerId,sellerId,goodId)
     */
    private Integer dealId;
    private Integer buyerId;
    private Integer sellerId;
    private Integer goodId;

    public Deal(Integer dealId, Integer buyerId, Integer sellerId, Integer goodId) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.goodId = goodId;
    }
}
