package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.*;
import com.zjgsu.shopping.pojo.*;
import com.zjgsu.shopping.pojo.vo.DealHistoryList;
import com.zjgsu.shopping.pojo.vo.GoodList;
import com.zjgsu.shopping.service.BuyerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Resource
    private DealMapper dealMapper;
    @Resource
    private IntentionMapper intentionMapper;
    @Resource
    private BuyerMapper buyerMapper;
    @Resource
    private GoodMapper goodMapper;
    @Resource
    private GoodImagineMapper goodImagineMapper;
    @Resource
    private DealHistoryMapper dealHistoryMapper;

    @Override
    public Buyer createBuyer(String name, String location, String phone) {
        Buyer buyer = new Buyer(null,name,location,phone);
        return (buyerMapper.creatBuyer(buyer) ? buyer : null);
    }

    @Override
    public Buyer createBuyer(Buyer buyer){
        return (buyerMapper.creatBuyer(buyer) ? buyer : null);
    }

    //添加一个意向
    @Override
    public Boolean raiseIntention(Integer buyerId, Integer goodId) {
        Intention intention = new Intention(null,buyerId,goodId);
        return intentionMapper.raiseIntention(intention);
    }

    @Override
    public Boolean raiseIntention(Intention intention) {
        return intentionMapper.raiseIntention(intention);
    }

    @Override
    public Boolean cancelIntention(Integer intentionId) {
        return intentionMapper.cancelIntention(intentionId);
    }

    @Override
    public List<Good> getUnfrozenGoodForSaleList() {
        return  goodMapper.getUnfrozenGoodList();
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


}
