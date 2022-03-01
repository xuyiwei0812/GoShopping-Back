package com.zjgsu.shopping.Tool;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.SuperAdmin.pojo.vo.BuyerList;
import com.zjgsu.shopping.interior.Common.pojo.Good;
import com.zjgsu.shopping.interior.Common.pojo.vo.GoodList;

import java.util.List;

public interface Mytool {


    Object soutErr(String s,Exception e);

    GoodList toGoodList(List<Good> li);

//    DealHistoryList toDealHistoryList(List<DealHistory> li);
//
//    IntentionList toIntentionList(List<Intention> li);
//
    Boolean checkPasswordLegitimacy(String s);
//
//    DealList toDealList(List<Deal> li);
//
//    BuyerHistoryList toBuyerHistoryList(List<BuyerHistory> li);

    BuyerList toBuyerList(List<Buyer> li);
}
