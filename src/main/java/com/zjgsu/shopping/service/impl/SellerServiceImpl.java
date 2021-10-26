package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.*;
import com.zjgsu.shopping.pojo.*;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.SellerService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

// 注意: 只有在查询简短brief的时候会返回带图片,查询具体信息的时候
@Service
public class SellerServiceImpl implements SellerService {
    @Resource
    SellerMapper sellerMapper;
    @Resource
    BuyerMapper buyerMapper;
    @Resource
    GoodMapper goodMapper;
    @Resource
    DealMapper dealMapper;
    @Resource
    DealHistoryMapper dealHistoryMapper;
    @Resource
    IntentionMapper intentionMapper;


    @Override
    public Seller sellerRegister(Seller seller){
        sellerMapper.register(seller);
        return seller;
    }


    @Override
    public Integer sellerLogin(String account, String password) {
        Seller seller = sellerMapper.login(account, password);
        return (seller != null ? seller.getSellerId() : -1);
    }

    @Override
    public Long updateSellerPassword(Integer sellerId, String password, String newPassword) {
        Seller seller = sellerMapper.getInfo(sellerId);
        if (!Objects.equals(seller.getPassword(), password)) return (long) -2;
        return sellerMapper.updatePassword(sellerId, newPassword);
    }


    @Override
    public Boolean checkSellerPassword(Integer sellerId, String password) {
        Seller seller = sellerMapper.getInfo(sellerId);
        return Objects.equals(seller.getPassword(), password);
    }


    @Override
    public Boolean searchAccount(String account) {
        return !sellerMapper.searchAccount(account).isEmpty();
    }

    @Override
    public List<Good> getAllGoodListBySellerId(Integer sellerId) {
        return goodMapper.getAllGoodListBySellerId(sellerId);
    }

    @Override
    public List<Good> getUnremovedGoodListBySellerId(Integer sellerId) {
        return goodMapper.getUnremovedGoodListBySellerId(sellerId);
    }

    @Override
    public List<Good> getWantedGoodListBySellerId(Integer sellerId) {
        return goodMapper.getWantedGoodListBySellerId(sellerId);
    }

    @Override
    public List<Good> getRemovedGoodListBySellerId(Integer sellerId) {
        return goodMapper.getRemovedGoodListBySellerId(sellerId);
    }

    @Override
    public List<Good> getUnfrozenGoodListBySellerId(Integer sellerId) {
        return goodMapper.getUnfrozenGoodListBySellerId(sellerId);
    }

    @Override
    public List<DealHistory> getDealHistoryListBySellerId(Integer sellerId) {
        return dealHistoryMapper.getDealHistoryListBySellerId(sellerId);
    }

    @Override
    public List<DealHistory> getDealHistoryListByGoodId(Integer goodId) {
        return dealHistoryMapper.getDealHistoryListByGoodId(goodId);
    }

    @Override
    public List<Intention> getIntentionListByGoodId(Integer goodId) {
        return intentionMapper.getIntentionListByGoodId(goodId);
    }

    @Override
    public Boolean updateInfo(Seller seller) {
        Seller oldSeller = sellerMapper.getInfo(seller.getSellerId());
        if (seller.getAccount() == null) seller.setAccount(oldSeller.getAccount());
        seller.setPassword(oldSeller.getPassword());
        if (seller.getName() == null) seller.setName(oldSeller.getName());
        if (seller.getLocation() == null) seller.setLocation(oldSeller.getLocation());
        if (seller.getPhone() == null) seller.setPhone(oldSeller.getPhone());
        return sellerMapper.updateInfo(seller) > 0;
    }

    @Override
    public Buyer getBuyerInfo(Integer buyerId) {
        return buyerMapper.getBuyerInfo(buyerId);
    }



    @Override
    public Boolean startDeal(DealVo deal) {
        goodMapper.freezeGood(deal.getGoodId());
        intentionMapper.cancelIntention(deal.getIntentionId());
        return dealMapper.startDeal(deal);
    }


    @Override
    public Boolean cancelDeal(Integer dealId) {
        goodMapper.unfreezeGood(dealMapper.getDealInfo(dealId).getGoodId());
        return dealMapper.cancelDeal(dealId) > 0;
    }

    @Override
    public Boolean finishDeal(Integer dealId, Date dealDate) {
        if (goodMapper.soldOutGood(dealId) == 0) return false;
        Deal deal = dealMapper.getDealInfo(dealId);
        goodMapper.unfreezeGood(deal.getGoodId());
        dealHistoryMapper.raiseDealHsitory(new DealHistory(goodMapper.getGoodInfo(deal.getGoodId()),
                buyerMapper.getBuyerInfo(deal.getBuyerId()).getPhone(), dealDate));
        return dealMapper.cancelDeal(dealId) > 0;
    }

    @Override
    public Good getGoodInfo(Integer goodId) {
        return goodMapper.getGoodInfo(goodId);
    }

    @Override
    public Good raiseGood(Good good) {
        goodMapper.raiseGood(good);
        return good;
    }

    @Override
    public Boolean putOnGood(Integer goodId) {
        return goodMapper.putOnGood(goodId) > 0;
    }

    @Override
    public Boolean pullOffGood(Integer goodId) {
        return goodMapper.pullOffGood(goodId) > 0;
    }



    //添货
    @Override
    public Boolean exhibitGood(Integer goodId) {
        return goodMapper.exhibitGood(goodId) > 0;
    }


    @Override
    public Boolean soldOutGood(Integer goodId) {
        return goodMapper.soldOutGood(goodId) > 0;
    }
}
