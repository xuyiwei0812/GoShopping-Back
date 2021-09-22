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
    private IntentionBuyerMapper intentionBuyerMapper;
    @Resource
    private BuyerMapper buyerMapper;

    @Override
    public Seller register(String name, String account, String password,String location, String phone) {
        Seller seller = new Seller();
        seller.setName(name);
        seller.setAccount(account);
        seller.setPassword(password);
        seller.setLocation(location);
        seller.setPhone(phone);
        if(!sellerMapper.register(seller))return null;
        return seller;
    }

    @Override
    public Boolean login(String account, String password) {
        return sellerMapper.login(account,password) != null;
    }

    @Override
    public Boolean updatePassword(int sellerId, String password) {
       return sellerMapper.updatePassword(sellerId,password) > 0;
    }

    @Override
    public GoodForSaleListVo getGoodForSaleList(int sellerId) {
        GoodForSaleListVo goodForSaleListVo = new GoodForSaleListVo();
        List<GoodForSale> goodForSales = goodForSaleMapper.getGoodList(sellerId);
        List<GoodForSaleShort> goodForSaleShorts = new ArrayList<>() ;
        for(GoodForSale goodForSale:goodForSales){
            GoodForSaleShort goodForSaleShort = new GoodForSaleShort();
            goodForSaleShort.setGoodId(goodForSale.getGoodId());
            goodForSaleShort.setName(goodForSale.getName());
            goodForSaleShort.setImg(goodForSale.getName());
            goodForSaleShort.setPrice(goodForSale.getPrice());
            goodForSaleShorts.add(goodForSaleShort);
        }
        goodForSaleListVo.setGoodsForSale(goodForSaleShorts);
        return goodForSaleListVo;
    }

    @Override
    public GoodForHistoryListVo getGoodForHistoryList(int sellerId) {
        GoodForHistoryListVo goodForHistoryListVo = new GoodForHistoryListVo();
        List<GoodForHistory> goodForHistories = goodForHistoryMapper.getGoodList(sellerId);
        List<GoodForHistoryShort> goodForHistoryShorts = new ArrayList<>();
        for(GoodForHistory goodForHistory :goodForHistories){
            GoodForHistoryShort goodForHistoryShort = new GoodForHistoryShort();
            goodForHistoryShort.setPrice(goodForHistory.getPrice());
            goodForHistoryShort.setName(goodForHistory.getName());
            goodForHistoryShort.setDealDate(goodForHistory.getDealDate());
            goodForHistoryShorts.add(goodForHistoryShort);
        }
        goodForHistoryListVo.setGoodsForHistory(goodForHistoryShorts);
        return goodForHistoryListVo;

    }

    @Override
    public GoodForSaleDetalVo getGoodForSaleDetal(int goodId) {
        GoodForSaleDetalVo goodForSaleDetalVo = new GoodForSaleDetalVo();
        goodForSaleDetalVo.setGoodForSale(goodForSaleMapper.getGoodInfo(goodId));
        goodForSaleDetalVo.setImg(goodImagineMapper.getImagine(goodId));
        return goodForSaleDetalVo;
    }

    @Override
    public GoodForHistoryDetalVo getGoodForHistoryDetal(int goodId) {
        GoodForHistoryDetalVo goodForHistoryDetalVo = new GoodForHistoryDetalVo();
        goodForHistoryDetalVo.setGoodForHistory(goodForHistoryMapper.getGoodInfo(goodId));
        goodForHistoryDetalVo.setImg(goodImagineMapper.getImagine(goodId));
        return goodForHistoryDetalVo;
    }

    @Override
    public IntentionBuyerListVo getIntentionBuyers(int goodId) {
        IntentionBuyerListVo intentionBuyerListVo = new IntentionBuyerListVo();
        List<IntentionBuyerShort> intentionBuyerShorts = new ArrayList<>();
        List<IntentionBuyer> intentionBuyers = intentionBuyerMapper.getIntentionList(goodId);
        for(IntentionBuyer intentionBuyer :intentionBuyers){
            IntentionBuyerShort intentionBuyerShort = new IntentionBuyerShort();
            intentionBuyerShort.setGoodId(intentionBuyer.getGoodId());
            intentionBuyerShort.setName(intentionBuyer.getName());
            intentionBuyerShort.setPhone(intentionBuyer.getPhone());
            intentionBuyerShort.setLocation(intentionBuyer.getLocation());
            intentionBuyerShorts.add(intentionBuyerShort);
        }
        intentionBuyerListVo.setIntentionBuyers(intentionBuyerShorts);
        return intentionBuyerListVo;
    }

    @Override
    public IntentionBuyerDetalVo getIntentionButerDetal(int intentionId) {
        IntentionBuyerDetalVo intentionBuyerDetalVo = new IntentionBuyerDetalVo();
        IntentionBuyer intentionBuyer = intentionBuyerMapper.getIntentionInfo(intentionId);
        intentionBuyerDetalVo.setIntentionId(intentionBuyer.getIntentionId());
        intentionBuyerDetalVo.setBuyerId(intentionBuyer.getBuyerId());
        intentionBuyerDetalVo.setLocation(intentionBuyer.getLocation());
        intentionBuyerDetalVo.setPhone(intentionBuyer.getPhone());
        intentionBuyerDetalVo.setGoodId(intentionBuyer.getGoodId());
        intentionBuyerDetalVo.setName(intentionBuyer.getName());
        return intentionBuyerDetalVo;
    }

    @Override
    public Boolean startDeal(int buyerId,int sellerId,int goodId,String locate,int price) {
        Business business = new Business();
        business.setPrice(price);
        business.setGoodId(goodId);
        business.setLocate(locate);
        business.setBuyerId(buyerId);
        business.setSellerId(sellerId);
        goodForSaleMapper.freezeGood(goodId);
        return businessMapper.startBusiness(business);
    }

    @Override
    public Boolean cancelDeal(int businessId) {
        Business business = businessMapper.getBusinessInfo(businessId);
        goodForSaleMapper.unfreezeGood(business.getGoodId());
        return businessMapper.detelBusiness(businessId) > 0;
    }

    @Override
    public Boolean finishDeal(int businessId, Date dealDate) {
        Business business = businessMapper.getBusinessInfo(businessId);
        goodForSaleMapper.unfreezeGood(business.getGoodId());
        GoodForSale goodForSale = goodForSaleMapper.getGoodInfo(business.getGoodId());
        Buyer buyer = buyerMapper.getBuyerInfo(business.getBuyerId());
        GoodForHistory goodForHistory = new GoodForHistory();
        goodForHistory.setGoodId(goodForHistory.getGoodId());
        goodForHistory.setPhone(buyer.getPhone());
        goodForHistory.setDealDate(dealDate);
        goodForHistory.setPrice(business.getPrice());
        goodForHistory.setName(goodForSale.getName());
        goodForHistory.setDescription(goodForHistory.getDescription());
        goodForHistoryMapper.addGoodForHistory(goodForHistory);
        return businessMapper.detelBusiness(businessId) > 0;
    }

    @Override
    public Boolean putOnGood(int goodId,String name,String description,int price) {
        GoodForSale goodForSale = new GoodForSale(name,description,price);
        return goodForSaleMapper.putOnGood(goodForSale);
    }

    @Override
    public Boolean putOffGood(int goodId) {
        return goodForSaleMapper.putOffGood(goodId);
    }


}
