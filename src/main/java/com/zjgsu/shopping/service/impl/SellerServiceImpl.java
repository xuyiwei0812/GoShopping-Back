package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.*;
import com.zjgsu.shopping.pojo.*;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Resource
    private BusinessMapper businessMapper;
    @Resource
    private SellerMapper sellerMapper;
    @Resource
    private GoodForSaleMapper goodForSaleMapper;
    @Resource
    private GoodForHistoryMapper goodForHistoryMapper;
    @Resource
    private GoodImagineMapper goodImagineMapper;
    @Resource
    private IntentionMapper intentionMapper;
    @Resource
    private BuyerMapper buyerMapper;

    @Override
    public Seller register(String name, String account, String password,String location, String phone) {
        Seller seller = new Seller(null,name,account,password,location,phone);
        return (sellerMapper.register(seller) ? seller : null);
    }

    @Override
    public Integer login(String account, String password) {
        Seller seller = sellerMapper.login(account,password);
        return (seller != null ? seller.getSellerId() : -1);
    }

    @Override
    public Boolean updatePassword(Integer sellerId, String password) {
       return sellerMapper.updatePassword(sellerId,password) > 0;
    }

    @Override
    public GoodForSaleListVo getGoodForSaleList(Integer sellerId) {
        List<GoodForSale> goodForSales = goodForSaleMapper.getGoodList(sellerId);
        List<GoodForSaleShort> goodForSaleShorts = new ArrayList<>() ;
        for(GoodForSale goodForSale:goodForSales){
            GoodImagine goodImg = goodImagineMapper.getImagine(goodForSale.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            goodForSaleShorts.add(new GoodForSaleShort(goodForSale.getGoodId(),goodForSale.getPrice(),goodForSale.getName(),img));
        }
        return new GoodForSaleListVo(goodForSaleShorts);
    }

    @Override
    public GoodForHistoryListVo getGoodForHistoryList(Integer sellerId) {
        List<GoodForHistory> goodForHistories = goodForHistoryMapper.getGoodList(sellerId);
        List<GoodForHistoryShort> goodForHistoryShorts = new ArrayList<>();
        for(GoodForHistory goodForHistory :goodForHistories){
            GoodImagine goodImg = goodImagineMapper.getImagine(goodForHistory.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            goodForHistoryShorts.add(new GoodForHistoryShort(goodForHistory.getGoodId(),goodForHistory.getPrice(),goodForHistory.getName(),goodForHistory.getDealDate(),img));
        }
        return new GoodForHistoryListVo(goodForHistoryShorts);

    }

    @Override
    public GoodForSaleDetalVo getGoodForSaleDetal(Integer goodId) {
        return new GoodForSaleDetalVo(goodForSaleMapper.getGoodInfo(goodId),goodImagineMapper.getImagine(goodId));
    }

    @Override
    public GoodForHistoryDetalVo getGoodForHistoryDetal(Integer goodId) {
        return new GoodForHistoryDetalVo(goodForHistoryMapper.getGoodInfo(goodId),goodImagineMapper.getImagine(goodId));
    }

    @Override
    public IntentionListVo getIntentionBuyers(Integer goodId) {
        List<Intention> intentions = intentionMapper.getIntentionList(goodId);
        List<IntentionShort> intentionShorts = new ArrayList<>();
        for(Intention intention :intentions){
            Buyer buyer = buyerMapper.getBuyerInfo(intention.getBuyerId());
            IntentionShort intentionShort = new IntentionShort(intention.getIntentionId() , buyer.getName() , buyer.getLocation() , buyer.getPhone());
            intentionShorts.add(intentionShort);
        }
        return new IntentionListVo(intentionShorts);
    }

    @Override
    public IntentionDetalVo getIntentionDetal(Integer intentionId) {
        return new IntentionDetalVo(intentionMapper.getIntentionInfo(intentionId));
    }

    @Override
    public Boolean startDeal(Integer buyerId,Integer sellerId,Integer goodId) {
        Business business = new Business(null,buyerId,sellerId,goodId);
        goodForSaleMapper.freezeGood(goodId);
        return businessMapper.startBusiness(business);
    }

    @Override
    public Boolean cancelDeal(Integer businessId) {
        Business business = businessMapper.getBusinessInfo(businessId);
        goodForSaleMapper.unfreezeGood(business.getGoodId());
        return businessMapper.detelBusiness(businessId) > 0;
    }

    @Override
    public Boolean finishDeal(Integer businessId, Date dealDate) {
        Business business = businessMapper.getBusinessInfo(businessId);
        Buyer buyer = buyerMapper.getBuyerInfo(business.getBuyerId());
        GoodForSale good = goodForSaleMapper.getGoodInfo(business.getGoodId());
        GoodForHistory goodForHistory = new GoodForHistory(good.getGoodId(),good.getName(),good.getDescription(),
                good.getPrice(),dealDate,buyer.getPhone());
        goodForHistoryMapper.addGoodForHistory(goodForHistory);
        goodForSaleMapper.putOffGood(business.getGoodId());
        return true;
    }

    @Override
    public GoodForSale putOnGood(String name,String description,Integer price) {
        GoodForSale good = new GoodForSale(null,price,name,description,false);
        return (goodForSaleMapper.putOnGood(good) ? good : null);
    }

    @Override
    public Boolean putOffGood(Integer goodId) {
        return goodForSaleMapper.putOffGood(goodId) > 0;
    }

    @Override
    public Boolean searchAccount(String account) {
        List<Seller> sellers = sellerMapper.searchAccount(account);
        return !sellers.isEmpty();
    }

}
