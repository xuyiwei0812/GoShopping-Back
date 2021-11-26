package com.zjgsu.shopping.interior.SuperAdmin.service;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.pojo.BuyerHistory;

import java.util.List;

public interface SuperAdminService {

    List<Buyer> getAllBuyerInfo();
    List<BuyerHistory> getAllBuyerHistory();
    List<BuyerHistory> getBuyerHistoryByBuyerId(Integer buyerId);
}
