package com.zjgsu.shopping.pojo;

import java.util.List;

public class GoodList {
    private int goodId;
    private List<Good> list;

    public GoodList(List<Good> list) {
        this.list = list;
    }

    public int getGoodId() {
        return goodId;
    }
}
