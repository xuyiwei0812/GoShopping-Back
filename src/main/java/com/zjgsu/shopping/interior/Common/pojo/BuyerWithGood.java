package com.zjgsu.shopping.interior.Common.pojo;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerWithGood {
    Integer buyerId;
    Integer goodId;
}
