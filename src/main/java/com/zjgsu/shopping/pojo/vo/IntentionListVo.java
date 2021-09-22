package com.zjgsu.shopping.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class IntentionListVo {
    List<IntentionShort> intentions;

    public IntentionListVo(List<IntentionShort> intentions) {
        this.intentions = intentions;
    }
}
