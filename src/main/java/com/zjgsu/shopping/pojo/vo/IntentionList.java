package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.Intention;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
class IntentionBrief{
    private Integer intentionId;
    private Integer buyerId;
    private Integer goodId;
    private Date date;
    private String name;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntentionList {
    List<IntentionBrief> intentionList= new ArrayList<>();
    public void AddItem(Integer intentionId, Integer buyerId , Integer goodId, Date date , String name){
        intentionList.add(new IntentionBrief(intentionId,buyerId,goodId,date,name));
    }
}
