package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.GoodForSale;
import com.zjgsu.shopping.pojo.GoodImagine;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GoodForSaleDetailVo {
    GoodForSale goodForSale;
    List<GoodImagine> img;
}
