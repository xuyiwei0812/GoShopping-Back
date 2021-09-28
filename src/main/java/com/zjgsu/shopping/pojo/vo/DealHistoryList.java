package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
class DealHistoryBrief{
    /**
     * 商品名称
     * 商品交易时间
     * 商品价格
     */
    private Integer goodId;
    private Double price;
    private String name;
    private Date dealDate;
    private String img;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealHistoryList {
    List<DealHistoryBrief> dealHistoryList;
    public void AddItem(Integer goodId,Double price ,String name,Date dealDate,String img){
        dealHistoryList.add(new DealHistoryBrief(goodId,price,name,dealDate,img));
    }
}
