package com.zjgsu.shopping.interior.Common.pojo.vo;

import com.zjgsu.shopping.interior.Common.pojo.Cart;
import com.zjgsu.shopping.interior.Common.pojo.GoodImagine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartWithImg {
    Cart cart;
    GoodImagine goodImagine;
}
