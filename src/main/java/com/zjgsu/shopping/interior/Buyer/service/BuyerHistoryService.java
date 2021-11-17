package com.zjgsu.shopping.interior.Buyer.service;

import com.zjgsu.shopping.interior.Buyer.pojo.BuyerHistory;

import java.util.List;


public interface BuyerHistoryService {

    BuyerHistory raiseBuyerHistory(BuyerHistory buyerHistory);

    List<BuyerHistory> getBuyerHistory(Integer buyerId);

}
