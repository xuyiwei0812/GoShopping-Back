package com.zjgsu.shopping.interior.Buyer.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class BuyerHistroyBrief{
    private Integer historyId;
    private Integer goodId;
    private Integer sellerId;
    private Integer buyerId;
    private String goodName;
    private String date;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerHistoryList {
    List<BuyerHistroyBrief> historyList = new ArrayList<>();
    public void AddItem(Integer historyId, Integer goodId,Integer buyerId, Integer sellerId ,String goodName, Date date) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dt = ft.format(date);
        historyList.add(new BuyerHistroyBrief(historyId,goodId,sellerId,buyerId,goodName,dt));
    }
}
