package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.GoodForSale;
import com.zjgsu.shopping.pojo.GoodImagine;
import lombok.Data;

import java.util.List;

@Data
public class GoodForSaleDetalVo {
    GoodForSale goodForSale;
    List<GoodImagine> img;
}
