package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.*;
import com.zjgsu.shopping.pojo.*;
import com.zjgsu.shopping.pojo.vo.DealHistoryList;
import com.zjgsu.shopping.pojo.vo.GoodList;
import com.zjgsu.shopping.pojo.vo.GoodwithImg;
import com.zjgsu.shopping.pojo.vo.IntentionList;
import com.zjgsu.shopping.service.SellerService;
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
    GoodImagineMapper goodImagineMapper;
    @Resource
    DealHistoryMapper dealHistoryMapper;
    @Resource
    IntentionMapper intentionMapper;


    @Override
    public Seller register(String name, String account, String password, String location, String phone) {
        Seller seller = new Seller(null, name, account, password, location, phone);
        System.out.println(seller);
        return (sellerMapper.register(seller) ? seller : null);
    }

    @Override
    public Seller register(Seller seller) {
        return (sellerMapper.register(seller) ? seller : null);
    }

    @Override
    public Integer login(String account, String password) {
        Seller seller = sellerMapper.login(account, password);
        return (seller != null ? seller.getSellerId() : -1);
    }

    @Override
    public Long updatePassword(Integer sellerId, String password, String newPassword) {
        Seller seller = sellerMapper.getInfo(sellerId);
        if (!Objects.equals(seller.getPassword(), password)) return (long) -2;
        return sellerMapper.updatePassword(sellerId, newPassword);
    }


    @Override
    public Boolean checkPassword(Integer sellerId, String password) {
        Seller seller = sellerMapper.getInfo(sellerId);
        return Objects.equals(seller.getPassword(), password);
    }

    @Override
    public GoodList getGoodListBySellerId(Integer sellerId) {
        List<Good> li = goodMapper.getGoodListBySellerId(sellerId);
        GoodList goodList = new GoodList();
        for (Good item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            goodList.AddItem(item.getGoodId(), item.getPrice(), item.getName(), img, item.getDescription(), item.getFrozen(), item.getSold());
        }
        return goodList;
    }

    @Override
    public GoodList getWantedGoodListBySellerId(Integer sellerId) {
        List<Good> li = goodMapper.getWantedGoodListBySellerId(sellerId);
        GoodList goodList = new GoodList();
        for (Good item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            goodList.AddItem(item.getGoodId(), item.getPrice(), item.getName(), img, item.getDescription(), item.getFrozen(), item.getSold());
        }
        return goodList;
    }

    @Override
    public GoodList getAllGoodList() {
        return null;
    }


    @Override
    public DealHistoryList getAllDealHistoryList() {
        List<DealHistory> li = dealHistoryMapper.getAllDealHistoryList();
        DealHistoryList dealHistoryList = new DealHistoryList();
        for (DealHistory item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            dealHistoryList.AddItem(item.getGoodId(), item.getPrice(), item.getName(), item.getDealDate(), img);
        }
        return dealHistoryList;
    }

    @Override
    public DealHistoryList getDealHistoryListBySellerId(Integer sellerId) {
        List<DealHistory> li = dealHistoryMapper.getDealHistoryListBySellerId(sellerId);
        DealHistoryList dealHistoryList = new DealHistoryList();
        for (DealHistory item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            dealHistoryList.AddItem(item.getGoodId(), item.getPrice(), item.getName(), item.getDealDate(), img);
        }
        return dealHistoryList;
    }

    @Override
    public GoodwithImg getGoodInfo(Integer goodId) {
        return new GoodwithImg(goodMapper.getGoodInfo(goodId), goodImagineMapper.getImagine(goodId));
    }

    @Override
    public DealHistoryList getDealHistoryByGoodId(Integer goodId) {
        List<DealHistory> li = dealHistoryMapper.getDealHistoryListByGoodId(goodId);
        DealHistoryList dealHistoryList = new DealHistoryList();
        for (DealHistory item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            dealHistoryList.AddItem(item.getGoodId(), item.getPrice(), item.getName(), item.getDealDate(), img);
        }
        return dealHistoryList;
    }

    @Override
    public IntentionList getIntentionListByGoodId(Integer goodId) {
        List<Intention> li = intentionMapper.getIntentionListByGoodId(goodId);
        IntentionList intentionList = new IntentionList();

        for (Intention item : li) {
            intentionList.AddItem(item.getIntentionId(), item.getBuyerId(), item.getGoodId(), item.getDate(), buyerMapper.getBuyerInfo(item.getBuyerId()).getName());
        }
        System.out.println(intentionList);
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
        return dealMapper.deleteDeal(dealId) > 0;
    }

    @Override
    public Boolean finishDeal(Integer dealId, Date dealDate) {
        if (goodMapper.soldOutGood(dealId) == 0) return false;
        Deal deal = dealMapper.getDealInfo(dealId);
        goodMapper.unfreezeGood(deal.getGoodId());
        dealHistoryMapper.addDealHsitory(new DealHistory(goodMapper.getGoodInfo(deal.getGoodId()),
                buyerMapper.getBuyerInfo(deal.getBuyerId()).getPhone(), dealDate));
        return dealMapper.deleteDeal(dealId) > 0;
    }

    @Override
    public Good putOnGood(String name, String description, Double price, Integer sellerId) {
        return null;
    }

    @Override
    public Good putOnGood(Good good) {
        good.setSold(false);
        good.setFrozen(false);
        good.setWanted(false);
        if (goodMapper.putOnGood(good) == null)
            return null;
        return good;
    }

    @Override
    public Boolean putOffGood(Integer goodId) {
        return goodMapper.putOffGood(goodId) > 0;
    }

    @Override
    public Boolean searchAccount(String account) {
        return !sellerMapper.searchAccount(account).isEmpty();
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
    public Boolean exhibitGood(Integer goodId) {
        return goodMapper.exhibitGood(goodId) > 0;
    }
}
