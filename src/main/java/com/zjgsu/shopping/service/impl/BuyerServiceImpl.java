package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.BusinessMapper;
import com.zjgsu.shopping.mapper.BuyerMapper;
import com.zjgsu.shopping.mapper.IntentionBuyerMapper;
import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.IntentionBuyer;
import com.zjgsu.shopping.service.BuyerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Resource
    private BusinessMapper businessMapper;
    @Resource
    private IntentionBuyerMapper intentionBuyerMapper;
    @Resource
    private BuyerMapper buyerMapper;

    @Override
    public Buyer createBuyer(String name, String location, String phone) {
        Buyer buyer = new Buyer();
        buyer.setName(name);
        buyer.setLocation(location);
        buyer.setPhone(phone);
        if(!buyerMapper.creatBuyer(buyer))return null;
        return buyer;
    }

    //添加一个意向
    @Override
    public Boolean raiseIntention(Buyer buyer, int goodId) {
       // intentionBuyerMapper.raiseIntention()
        IntentionBuyer intentionBuyer = new IntentionBuyer();
        intentionBuyer.setBuyerId(buyer.getBuyerId());
        intentionBuyer.setName(buyer.getName());
        intentionBuyer.setLocation(buyer.getLocation());
        intentionBuyer.setPhone(buyer.getPhone());
        intentionBuyer.setGoodId(goodId);
        return intentionBuyerMapper.raiseIntention(intentionBuyer);
    }



    @Override
    public Boolean cancelIntention(int intentionId) {
        return intentionBuyerMapper.cancelIntention(intentionId);
    }
    //@Resource
}
