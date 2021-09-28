package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.*;
import com.zjgsu.shopping.pojo.*;
import com.zjgsu.shopping.pojo.Deal;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 注意: 只有在查询简短brief的时候会返回带图片,查询具体信息的时候
@Service
public class SellerServiceImpl implements SellerService {
    @Resource SellerMapper sellerMapper;
    @Resource BuyerMapper  buyerMapper;
    @Resource GoodMapper   goodMapper;
    @Resource DealMapper   dealMapper;
    @Resource GoodImagineMapper  goodImagineMapper;
    @Resource DealHistoryMapper  dealHistoryMapper;
    @Resource IntentionMapper    intentionMapper;


    @Override
    public Seller register(String name, String account, String password, String location, String phone) {
        Seller seller = new Seller(null,name,account,password,location,phone);
        return (sellerMapper.register(seller) ? seller : null);
    }

    @Override
    public Seller register(Seller seller) {
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
    public GoodList getGoodListBySellerId(Integer sellerId) {
        List<Good> li = goodMapper.getGoodListBySellerId(sellerId);
        GoodList goodList = new GoodList();
        for(Good item :li){
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
           String img = (goodImg != null ? goodImg.getImagine() : null);
            goodList.AddItem(item.getGoodId(),item.getPrice(),item.getName(),img);
        }
        return goodList;
    }

    @Override
    public GoodList getAllGoodList() {
        List<Good> li = goodMapper.getAllGoodList();
        GoodList goodList = new GoodList();
        for(Good item :li){
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            goodList.AddItem(item.getGoodId(),item.getPrice(),item.getName(),img);
        }
        return goodList;
    }

    @Override
    public Good getGoodInfo(Integer goodId) {
        return goodMapper.getGoodInfo(goodId);
    }

    @Override
    public DealHistoryList getDealHistoryByGoodId(Integer goodId) {
        List<DealHistory> li = dealHistoryMapper.getDealHistoryListByGoodId(goodId);
        DealHistoryList dealHistoryList = new DealHistoryList();
        for(DealHistory item :li){
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            dealHistoryList.AddItem(item.getGoodId(),item.getPrice(),item.getName(),item.getDealDate(),img);
        }
        return dealHistoryList;
    }

    @Override
    public IntentionList getIntentionListByGoodId(Integer goodId) {
        List<Intention> li = intentionMapper.getIntentionListByGoodId(goodId);
        IntentionList intentionList = new IntentionList();
        for(Intention item :li){
            intentionList.AddItem(item.getIntentionId(),item.getBuyerId(),item.getGoodId());
        }
        return intentionList;
    }

    @Override
    public Buyer getBuyerInfo(Integer buyerId) {
        return buyerMapper.getBuyerInfo(buyerId);
    }

    //弃用
    @Override
    public Boolean startDeal(Integer buyerId, Integer sellerId, Integer goodId) {
        return null;
    }

    @Override
    public Boolean startDeal(Deal deal) {
        goodMapper.freezeGood(deal.getGoodId());
        return dealMapper.startDeal(deal);
    }


    @Override
    public Boolean cancelDeal(Integer dealId) {
        goodMapper.unfreezeGood(dealMapper.getDealInfo(dealId).getGoodId());
        return dealMapper.detelDeal(dealId) > 0;
    }

    @Override
    public Boolean finishDeal(Integer dealId, Date dealDate) {
        Deal deal = dealMapper.getDealInfo(dealId);
        goodMapper.unfreezeGood(deal.getGoodId());
        if(goodMapper.soldOutGood(deal.getGoodId())  == 0 )return false;
        dealHistoryMapper.addDealHsitory(new DealHistory(goodMapper.getGoodInfo(deal.getGoodId()),
                buyerMapper.getBuyerInfo(deal.getBuyerId()).getPhone(),dealDate));
        return dealMapper.detelDeal(dealId) > 0;
    }

    @Override
    public Good putOnGood(String name, String description, Double price, Integer sellerId) {
        return null;
    }

    @Override
    public Good putOnGood(Good good) {
        if(goodMapper.putOnGood(good) == null)
            return null;
        return good;
    }

    @Override
    public Boolean putOffGood(Integer goodId) {
        return goodMapper.putOffGood(goodId) > 0;
    }

    @Override
    public Boolean searchAccount(String account) {
        return !sellerMapper.searchAccount(account) .isEmpty();
    }

    @Override
    public Boolean updateInfo(Seller seller) {
        return sellerMapper.updateInfo(seller) > 0 ;
    }

    @Override
    public Boolean exhibitGood(Integer goodId) {
        return goodMapper.exhibitGood(goodId) > 0;
    }
}
