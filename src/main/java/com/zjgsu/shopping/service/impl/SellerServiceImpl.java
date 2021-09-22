package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.*;
import com.zjgsu.shopping.pojo.*;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        //Buyer buyer =
        return null;
    }

    @Override
    public IntentionBuyerDetalVo getIntentionButerDetal(int buyerId) {
        return null;
    }

    @Override
    public int startDeal(Business business) {
       // businessMapper.createBusiness(business);
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
    public Boolean putOnGood(GoodForSale good) {
        return null;
    }

    @Override
    public Boolean putOffGood(int goodId) {
        return null;
    }


}
