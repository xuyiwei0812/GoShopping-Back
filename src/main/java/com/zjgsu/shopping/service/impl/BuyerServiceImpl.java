package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.BusinessMapper;
import com.zjgsu.shopping.mapper.BuyerMapper;

import com.zjgsu.shopping.mapper.GoodForSaleMapper;
import com.zjgsu.shopping.mapper.IntentionMapper;
import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.GoodForSale;
import com.zjgsu.shopping.pojo.Intention;
import com.zjgsu.shopping.service.BuyerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Resource
    private BusinessMapper businessMapper;
    @Resource
    private IntentionMapper intentionMapper;
    @Resource
    private BuyerMapper buyerMapper;
    @Resource
    private GoodForSaleMapper goodForSaleMapper;

    @Override
    public Buyer createBuyer(String name, String location, String phone) {
        Buyer buyer = new Buyer(null,name,location,phone);
        return (buyerMapper.creatBuyer(buyer) ? buyer : null);
    }

    //添加一个意向
    @Override
    public Boolean raiseIntention(Integer buyerId, Integer goodId) {
        Intention intention = new Intention(null,buyerId,goodId);
        return intentionMapper.raiseIntention(intention);
    }



    @Override
    public Boolean cancelIntention(Integer intentionId) {
        return intentionMapper.cancelIntention(intentionId);
    }

    @Override
    public List<GoodForSale> getUnfrozenGoodForSaleList() {
        return goodForSaleMapper.getUnfrozenGoodList();
    }
}
