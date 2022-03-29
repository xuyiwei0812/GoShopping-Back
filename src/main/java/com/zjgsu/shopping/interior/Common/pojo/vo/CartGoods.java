package com.zjgsu.shopping.interior.Common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartGoods {
    Integer buyerId;
    List<Integer> goodIds;
    List<Integer> numbers;
    Integer addressId;
    List<Integer> cartIds;
}
