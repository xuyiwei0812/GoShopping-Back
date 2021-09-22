package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.GoodForHistory;
import com.zjgsu.shopping.pojo.GoodImagine;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GoodForHistoryDetalVo {
    GoodForHistory goodForHistory;
    List<GoodImagine> img;
}
