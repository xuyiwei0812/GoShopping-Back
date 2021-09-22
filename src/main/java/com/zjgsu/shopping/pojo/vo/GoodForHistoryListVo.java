package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.GoodForHistory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GoodForHistoryListVo {
    List<GoodForHistoryShort> goodsForHistory;
}
