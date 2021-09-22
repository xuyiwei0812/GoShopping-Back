package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GoodForSaleListVo {
    List<GoodForSaleShort> goodsForSale;
}
