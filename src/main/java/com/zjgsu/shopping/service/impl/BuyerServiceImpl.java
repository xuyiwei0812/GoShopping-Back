package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.*;
import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.Intention;
import com.zjgsu.shopping.pojo.vo.GoodwithImg;
import com.zjgsu.shopping.service.BuyerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Resource
    private IntentionMapper intentionMapper;
    @Resource
    private BuyerMapper buyerMapper;
    @Resource
    private GoodMapper goodMapper;
    @Resource
    private GoodImagineMapper goodImagineMapper;


    @Override
    public Buyer raiseBuyer(Buyer buyer) {
        return (buyerMapper.raiseBuyer(buyer) ? buyer : null);
    }

    @Override
    public Boolean raiseIntention(Intention intention) {
        goodMapper.WantGood(intention.getGoodId());
        intention.setDate(new Date());
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
    public List<Good> getAllGoodListForBuyers() {
        return goodMapper.getAllGoodListForBuyers();
    }

    @Override
    public  List<Good> getUnfrozenGoodListForBuyers() {
        return goodMapper.getUnfrozenGoodListForBuyers();
    }

    @Override
    public  List<Good> getAllGoodListBySellerIdForBuyers(Integer sellerId) {
        return goodMapper.getAllGoodListBySellerIdForBuyers(sellerId);
    }

    @Override
    public  List<Good> getUnfrozenGoodListBySellerIdForBuyers(Integer sellerId) {
        return goodMapper.getUnfrozenGoodListBySellerIdForBuyers(sellerId);
    }

    @Override
    public GoodwithImg getGoodInfo(Integer goodId) {
        return new GoodwithImg(goodMapper.getGoodInfo(goodId), goodImagineMapper.getImagine(goodId));
    }
}
