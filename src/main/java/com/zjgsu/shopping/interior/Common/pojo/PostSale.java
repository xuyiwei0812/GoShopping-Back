package com.zjgsu.shopping.interior.Common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSale {
    Integer postSaleId;
    Integer postSaleType;//1换货 2退货退款 3退款
    String reasons;
    Integer orderId;
    Integer goodId;
    Integer buyerId;
    Integer sellerId;
    List<PostSaleImage> images;
    Integer stmt;//换货：1买家提出 —> 2卖家同意 3卖家拒绝 -> 4卖家收到货物 -> 5买家确认收到换的货物
                //退货退款：1买家提出 —> 2卖家同意 3卖家拒绝 -> 4卖家确认收到货物、买家收到退款
                //退款：1买家提出 —> 2卖家同意 3卖家拒绝
}
