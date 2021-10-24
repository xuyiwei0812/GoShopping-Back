package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.*;
import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.GoodImagine;
import com.zjgsu.shopping.pojo.Intention;
import com.zjgsu.shopping.pojo.vo.GoodList;
import com.zjgsu.shopping.service.BuyerService;
import org.springframework.data.relational.core.sql.In;
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
    public Buyer raiseBuyer(Buyer buyer) {
        return (buyerMapper.raiseBuyer(buyer) ? buyer : null);
    }




    @Override
    public Boolean raiseIntention(Intention intention) {
        goodMapper.WantGood(intention.getGoodId());
        return intentionMapper.raiseIntention(intention);
    }

    @Override
    public Boolean cancelIntention(Integer intentionId) {
        Intention intention = intentionMapper.getIntentionInfo(intentionId);
        if (!intentionMapper.cancelIntention(intentionId)) return false;
        if (intentionMapper.getIntentionListByGoodId(intention.getGoodId()).isEmpty())
            goodMapper.refuseGood(intention.getGoodId());
        return true;
    }


    @Override
    public GoodList getAllGoodList() {
        List<Good> li = goodMapper.getAllGoodListForBuyer();
        GoodList goodList = new GoodList();
        for (Good item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            goodList.AddItem(item.getGoodId(), item.getGoodPrice(), item.getGoodName(), img, item.getDescription(), item.getFrozen(), item.getSold());
        }
        return goodList;
    }

    @Override
    public GoodList getUnfrozenGoodList() {
        List<Good> li = goodMapper.getUnfrozenGoodForBuyer();
        GoodList goodList = new GoodList();
        for (Good item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            goodList.AddItem(item.getGoodId(), item.getGoodPrice(), item.getGoodName(), img, item.getDescription(), item.getFrozen(), item.getSold());
        }
        return goodList;
    }

    @Override
    public GoodList getUnfrozenGoodListBySellerId(Integer sellerId){
        List<Good> li = goodMapper.getUnfrozenGoodBySellerIdForBuyer(sellerId);
        GoodList goodList = new GoodList();
        for (Good item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            goodList.AddItem(item.getGoodId(), item.getGoodPrice(), item.getGoodName(), img, item.getDescription(), item.getFrozen(), item.getSold());
        }
        return goodList;
    }

    @Override
    public GoodList getAllGoodListBySellerId(Integer sellerId){
        List<Good> li = goodMapper.getUnfrozenGoodBySellerIdForBuyer(sellerId);
        GoodList goodList = new GoodList();
        for (Good item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            goodList.AddItem(item.getGoodId(), item.getGoodPrice(), item.getGoodName(), img, item.getDescription(), item.getFrozen(), item.getSold());
        }
        return goodList;
    }


}
