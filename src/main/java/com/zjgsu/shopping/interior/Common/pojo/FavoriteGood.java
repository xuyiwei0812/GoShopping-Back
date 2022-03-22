package com.zjgsu.shopping.interior.Common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteGood {
    Integer favoriteId;
    Integer buyerId;
    Integer goodId;
    String goodName;
    Double goodPrice;
    String description;
}
