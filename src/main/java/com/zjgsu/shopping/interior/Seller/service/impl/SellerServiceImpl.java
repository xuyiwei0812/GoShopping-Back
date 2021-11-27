package com.zjgsu.shopping.interior.Seller.service.impl;

import com.zjgsu.shopping.interior.Buyer.mapper.BuyerMapper;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Common.mapper.DealHistoryMapper;
import com.zjgsu.shopping.interior.Common.mapper.GoodImagineMapper;
import com.zjgsu.shopping.interior.Common.mapper.GoodMapper;
import com.zjgsu.shopping.interior.Common.mapper.IntentionMapper;
import com.zjgsu.shopping.interior.Seller.mapper.DealMapper;
import com.zjgsu.shopping.interior.Seller.mapper.SellerMapper;
import com.zjgsu.shopping.interior.Seller.pojo.Deal;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import com.zjgsu.shopping.interior.Seller.pojo.vo.DealVo;
import com.zjgsu.shopping.interior.Common.pojo.*;
import com.zjgsu.shopping.interior.Seller.service.SellerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Resource
    GoodImagineMapper goodImagineMapper;

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
    public Boolean searchSellerAccount(String account) {
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
    public List<Deal> getDealListByGoodId(Integer goodId) {
        return dealMapper.getDealList(goodId);
    }

    @Override
    public List<Intention> getIntentionListByGoodId(Integer goodId) {
        return intentionMapper.getIntentionListByGoodId(goodId);
    }

    @Override
    public Boolean updateSellerInfo(Seller seller) {
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
        deal.setDate(new Date());
        if(Objects.equals(goodMapper.getGoodInfo(deal.getGoodId()).getStorage(), deal.getNumber()))
            goodMapper.freezeGood(deal.getGoodId());
        intentionMapper.cancelIntention(deal.getIntentionId());
        if(intentionMapper.getIntentionListByGoodId(deal.getGoodId()).isEmpty())
            goodMapper.refuseGood(deal.getGoodId());
        return dealMapper.startDeal(deal);
    }


    @Override
    public Boolean cancelDeal(Integer dealId) {
        goodMapper.unfreezeGood(dealMapper.getDealInfo(dealId).getGoodId());
        return dealMapper.cancelDeal(dealId) > 0;
    }

    /**
     * 卖空
     */

    @Override
    public Boolean finishDeal(Integer dealId) {
        Date dealDate = new Date();
        Deal deal = dealMapper.getDealInfo(dealId);
        int l = goodMapper.getGoodInfo(deal.getGoodId()).getStorage()  - deal.getNumber();
        if(l < 0) return false;
        else if( l == 0) goodMapper.soldOutGood(deal.getGoodId());
        Good good = goodMapper.getGoodInfo(deal.getGoodId());
        good.setStorage(l);
        goodMapper.updateGoodStorage(good);
        goodMapper.unfreezeGood(deal.getGoodId());
        dealHistoryMapper.raiseDealHsitory(new DealHistory(goodMapper.getGoodInfo(deal.getGoodId()),
                buyerMapper.getBuyerInfo(deal.getBuyerId()).getBuyerPhone(), dealDate));
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
    public Good updateGoodInfo(Good good) {
        goodMapper.pullOffGood(good.getGoodId());
        good.setGoodId(null);
        goodMapper.raiseGood(good);
        return good;
    }

    @Override
    public Boolean putOnGood(Integer goodId) {
        return goodMapper.putOnGood(goodId) > 0;
    }

    @Override
    public Boolean pullOffGood(Integer goodId) {
        goodMapper.soldOutGood(goodId);
        return goodMapper.pullOffGood(goodId) > 0;
    }



    //添货
    @Override
    public Boolean exhibitGood(Good good) {
        good.setStorage(goodMapper.getGoodInfo(good.getGoodId()).getStorage() + good.getStorage());
        goodMapper.updateGoodStorage(good);
        return goodMapper.exhibitGood(good.getGoodId()) > 0;
    }


    @Override
    public Boolean soldOutGood(Integer goodId) {
        return goodMapper.soldOutGood(goodId) > 0;
    }

    @Override
//    public Boolean uploadGoodImg(Integer goodId, List<String> li){
//        for(String item:li){
//            goodImagineMapper.addImagine(new GoodImagine(null,goodId,item));
//        }
//        return true;
//    }
    public Boolean uploadGoodImg(GoodImagine goodImagine){
        return goodImagineMapper.addImagine(goodImagine);
    }

    //视频
    public Boolean saveVideoToDatabase(Video video){
        return goodMapper.saveVideoToDatabase(video);
    }

}
