package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.BusinessMapper;
import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SellerServiceImpl implements SellerService {
    @Resource
    private BusinessMapper businessMapper;

    @Override
    public int login(String account, String password) {
        return 0;
    }

    @Override
    public Boolean updatePassword(int sellerId, String password) {
        return null;
    }

    @Override
    public GoodForSaleListVo getGoodForSaleList(int sellerId) {
        return null;
    }

    @Override
    public GoodForHistoryListVo getGoodForHistoryList(int sellerId) {
        return null;
    }

    @Override
    public GoodForSaleDetalVo getGoodForSaleDetal(int goodId) {
        return null;
    }

    @Override
    public GoodForHistoryDetalVo getGoodForHistoryDetal(int goodId) {
        return null;
    }

    @Override
    public IntentionBuyerListVo getIntentionBuyers(int goodId) {
        return null;
    }

    @Override
    public IntentionBuyerDetalVo getIntentionButerDetal(int buyerId) {
        return null;
    }

    //    @Override
//    public  int startDeal(int goodId,int sellerId, int buyerId){
//        Business business = new Business(13,buyerId,sellerId,goodId,"hha");
//        businessMapper.createBusiness(business);
//        return 1;
//    }
    @Override
    public int startDeal(Business business) {
        businessMapper.createBusiness(business);
        return 1;
    }

    @Override
    public Boolean cancelDeal(int businessId) {
        return null;
    }

    @Override
    public Boolean finishDeal(int businessId) {
        return null;
    }

    @Override
    public Boolean putOnGood(GoodForSaleDetalVo good) {
        return null;
    }

    @Override
    public Boolean putOffGood(int goodId) {
        return null;
    }

}
