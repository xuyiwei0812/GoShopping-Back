package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.BuyerMapper;
import com.zjgsu.shopping.mapper.DealMapper;
import com.zjgsu.shopping.mapper.GoodMapper;
import com.zjgsu.shopping.mapper.IntentionMapper;
import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.Intention;
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
    public List<Good> getAllGoodList(){
        return goodMapper.getAllGoodList();
    }
}
