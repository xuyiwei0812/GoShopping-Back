package com.zjgsu.shopping.pojo;

import lombok.Data;

@Data
public class Business {
   private Integer businessId;
   private Integer price;
   private Integer buyerId;
   private Integer sellerId;
   private Integer goodId;
   private String locate;

//   public Business(int price, int buyerId, int sellerId, int goodId, String locate) {
//      this.price = price;
//      this.buyerId = buyerId;
//      this.sellerId = sellerId;
//      this.goodId = goodId;
//      this.locate = locate;
//   }
}
