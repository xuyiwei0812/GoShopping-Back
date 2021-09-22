package com.zjgsu.shopping.pojo;

import lombok.Data;

@Data
public class Business {
   /**
    * 交易编号
    * 买家编号
    * 卖家编号
    * 商品编号
    * Business(null,buyerId,sellerId,goodId)
    */
   private Integer businessId;
   private Integer buyerId;
   private Integer sellerId;
   private Integer goodId;

   public Business(Integer businessId, Integer buyerId, Integer sellerId, Integer goodId) {
      this.buyerId = buyerId;
      this.sellerId = sellerId;
      this.goodId = goodId;
   }
}
