package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
class IntentionBrief {
    private Integer intentionId;
    private Integer buyerId;
    private Integer goodId;
    private String date;
    private String buyerName;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntentionList {
    List<IntentionBrief> intentionList = new ArrayList<>();

    public void AddItem(Integer intentionId, Integer buyerId, Integer goodId, Date date, String name) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dt = ft.format(date);
        intentionList.add(new IntentionBrief(intentionId, buyerId, goodId, dt, name));
    }
}
