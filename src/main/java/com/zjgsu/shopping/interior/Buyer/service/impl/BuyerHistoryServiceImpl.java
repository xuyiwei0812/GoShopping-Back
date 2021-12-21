package com.zjgsu.shopping.interior.Buyer.service.impl;

import com.zjgsu.shopping.interior.Buyer.mapper.BuyerHistoryMapper;
import com.zjgsu.shopping.interior.Buyer.pojo.BuyerHistory;
import com.zjgsu.shopping.interior.Buyer.service.BuyerHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class BuyerHistoryServiceImpl implements BuyerHistoryService {
    @Resource
    BuyerHistoryMapper buyerHistoryMapper;


    @Override
    public BuyerHistory raiseBuyerHistory(BuyerHistory buyerHistory) {
        buyerHistoryMapper.raiseBuyerHistory(buyerHistory);
        return buyerHistory;
    }

    @Override
    public List<BuyerHistory> getBuyerHistory(Integer buyerId) {
        List<BuyerHistory> buyerHistoryList = buyerHistoryMapper.getBuyerHistory(buyerId);
        System.out.println("buyerHistoryList"+buyerHistoryList);
        for(Integer i=0;i<buyerHistoryList.size();i++) {
            Integer goodId = buyerHistoryList.get(i).getGoodId();
            System.out.println("goodId:"+goodId);
            Double goodPrice = buyerHistoryMapper.getGoodPriceByGoodId(goodId);
            System.out.println("goodPrice"+goodPrice);
            List<String> img = buyerHistoryMapper.getGoodImageByGoodId(goodId);
            buyerHistoryList.get(i).setGoodPrice(goodPrice);
            buyerHistoryList.get(i).setImg(img);
        }
        return buyerHistoryList;
    }
}
