package com.zjgsu.shopping.pojo;

import java.util.List;

public class IntentionBuyerList {
    private int goodId ;
    private List<Buyer> list;

    public IntentionBuyerList(List<Buyer> list) {
        this.list = list;
    }

    public int getGoodId() {
        return goodId;
    }
}
