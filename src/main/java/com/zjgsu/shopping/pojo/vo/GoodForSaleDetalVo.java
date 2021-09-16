package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.GoodForSale;
import lombok.Data;

import java.util.List;

@Data
public class GoodForSaleDetalVo {
    GoodForSale goodForSale;
    List<String> img;
}
