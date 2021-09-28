package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.Intention;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
class IntentionBrief{
    private Integer intentionId;
    private Integer buyerId;
    private Integer goodId;
}
@Data
@AllArgsConstructor
@NoArgsConstructor

public class IntentionList {
    List<IntentionBrief> intentionList;
    public void AddItem(Integer intentionId, Integer buyerId , Integer goodId){
        intentionList.add(new IntentionBrief(intentionId,buyerId,goodId));
    }
}
