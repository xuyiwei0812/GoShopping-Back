package com.zjgsu.shopping.service;

import com.zjgsu.shopping.pojo.DealHistory;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.Intention;
import com.zjgsu.shopping.pojo.vo.DealHistoryList;
import com.zjgsu.shopping.pojo.vo.GoodList;
import com.zjgsu.shopping.pojo.vo.IntentionList;

import java.util.List;

public interface Mytool {


    Object soutErr(String s,Exception e);


    GoodList toGoodList(List<Good> li);


    DealHistoryList toDealHistoryList(List<DealHistory> li);

    IntentionList toIntentionList(List<Intention> li);
}
