package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.Deal;
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
class DealBrief{
    private Integer dealId;
    private Integer goodId;
    private String buyerName;
    private String goodName;
    private String date;

}
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealList {
    List<DealBrief> dealList = new ArrayList<>();
    public void AddItem(Integer dealId, Integer goodId, String buyerName, String goodName, Date date){
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dt = ft.format(date);
        dealList.add(new DealBrief(dealId,goodId,buyerName,goodName,dt));
    }
}
