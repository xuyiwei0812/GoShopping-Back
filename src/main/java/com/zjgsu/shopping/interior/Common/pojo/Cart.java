package com.zjgsu.shopping.interior.Common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    Integer cartId;
    Integer buyerId;
    Integer goodId;
    String goodName;
    Double goodPrice;
    String description;
    Integer number;
}
