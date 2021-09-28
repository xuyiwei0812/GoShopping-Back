package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.*;
import com.zjgsu.shopping.pojo.*;
import com.zjgsu.shopping.pojo.Deal;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 注意: 只有在查询简短brief的时候会返回带图片,查询具体信息的时候
@Service
public class SellerServiceImpl implements SellerService {
    @Resource SellerMapper sellerMapper;
    @Resource BuyerMapper  buyerMapper;
    @Resource GoodMapper   goodMapper;
    @Resource DealMapper   dealMapper;
    @Resource GoodImagineMapper  goodImagineMapper;
    @Resource DealHistoryMapper  dealHistoryMapper;
    @Resource IntentionMapper    intentionMapper;
    @Override
    public Seller register(String name, String account, String password, String location, String phone) {
        Seller seller = new Seller(null,name,account,password,location,phone);
        return (sellerMapper.register(seller) ? seller : null);
    }

    @Override
    public Seller register(Seller seller) {
        return (sellerMapper.register(seller) ? seller : null);
    }

    @Override
    public Integer login(String account, String password) {
        Seller seller = sellerMapper.login(account,password);
        return (seller != null ? seller.getSellerId() : -1);
    }

    @Override
    public Boolean updatePassword(Integer sellerId, String password) {
        return sellerMapper.updatePassword(sellerId,password) > 0;
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
    public Good getGoodInfo(Integer goodId) {
        return goodMapper.getGoodInfo(goodId);
    }

    @Override
    public DealHistoryList getDealHistoryByGoodId(Integer goodId) {
        List<DealHistory> li = dealHistoryMapper.getDealHistoryListByGoodId(goodId);
        DealHistoryList dealHistoryList = new DealHistoryList();
        for(DealHistory item :li){
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            dealHistoryList.AddItem(item.getGoodId(),item.getPrice(),item.getName(),item.getDealDate(),img);
        }
        return dealHistoryList;
    }

    @Override
    public IntentionList getIntentionListByGoodId(Integer goodId) {
        List<Intention> li = intentionMapper.getIntentionListByGoodId(goodId);
        IntentionList intentionList = new IntentionList();
        for(Intention item :li){
            intentionList.AddItem(item.getIntentionId(),item.getBuyerId(),item.getGoodId());
        }
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
        return dealMapper.detelDeal(dealId) > 0;
    }

    @Override
    public Boolean finishDeal(Integer dealId, Date dealDate) {
        Deal deal = dealMapper.getDealInfo(dealId);
        goodMapper.unfreezeGood(deal.getGoodId());
        if(goodMapper.soldOutGood(deal.getGoodId())  == 0 )return false;
        dealHistoryMapper.addDealHsitory(new DealHistory(goodMapper.getGoodInfo(deal.getGoodId()),
                buyerMapper.getBuyerInfo(deal.getBuyerId()).getPhone(),dealDate));
        return dealMapper.detelDeal(dealId) > 0;
    }

    @Override
    public Good putOnGood(String name, String description, Double price, Integer sellerId) {
        return null;
    }

    @Override
    public Good putOnGood(Good good) {
        if(goodMapper.putOnGood(good) == null)
            return null;
        return good;
    }

    @Override
    public Boolean putOffGood(Integer goodId) {
        return goodMapper.putOffGood(goodId) > 0;
    }

    @Override
    public Boolean searchAccount(String account) {
        return !sellerMapper.searchAccount(account) .isEmpty();
    }

    @Override
    public Boolean updateInfo(Seller seller) {
        return sellerMapper.updateInfo(seller) > 0 ;
    }

    @Override
    public Boolean exhibitGood(Integer goodId) {
        return goodMapper.exhibitGood(goodId) > 0;
    }
//    @Resource
//    private BusinessMapper businessMapper;
//    @Resource
//    private SellerMapper sellerMapper;
//    @Resource
//    private GoodForSaleMapper goodForSaleMapper;
//    @Resource
//    private GoodForHistoryMapper goodForHistoryMapper;
//    @Resource
//    private GoodImagineMapper goodImagineMapper;
//    @Resource
//    private IntentionMapper intentionMapper;
//    @Resource
//    private BuyerMapper buyerMapper;
//


//
//    @Override
//    public GoodForSaleListVo getGoodFromCertainSellerList(Integer sellerId){
//        List<GoodForSale> goodForSales = goodForSaleMapper.getGoodList(sellerId);
//        return getGoodForSaleListVo(goodForSales);
//    }
//
//    @Override
//    public  GoodForSaleListVo getAllGoodList(){
//        List<GoodForSale> goodForSales = goodForSaleMapper.getAllGoodList();
//        return getGoodForSaleListVo(goodForSales);
//    }
//
//    private GoodForSaleListVo getGoodForSaleListVo(List<GoodForSale> goodForSales) {
//        List<GoodForSaleShort> goodForSaleShorts = new ArrayList<>() ;
//        for(GoodForSale goodForSale:goodForSales){
//            GoodImagine goodImg = goodImagineMapper.getImagine(goodForSale.getGoodId()).stream().findFirst().orElse(null);
//            String img = (goodImg != null ? goodImg.getImagine() : null);
//            goodForSaleShorts.add(new GoodForSaleShort(goodForSale.getGoodId(),goodForSale.getPrice(),goodForSale.getName(),img));
//        }
//        return new GoodForSaleListVo(goodForSaleShorts);
//    }
////
////    @Override
////    public GoodForHistoryListVo getGoodForHistoryList(Integer sellerId) {
////        List<GoodForHistory> goodForHistories = goodForHistoryMapper.getGoodList(sellerId);
////        List<GoodForHistoryShort> goodForHistoryShorts = new ArrayList<>();
////        for(GoodForHistory goodForHistory :goodForHistories){
////            GoodImagine goodImg = goodImagineMapper.getImagine(goodForHistory.getGoodId()).stream().findFirst().orElse(null);
////            String img = (goodImg != null ? goodImg.getImagine() : null);
////            goodForHistoryShorts.add(new GoodForHistoryShort(goodForHistory.getGoodId(),goodForHistory.getPrice(),goodForHistory.getName(),goodForHistory.getDealDate(),img));
////        }
////        return new GoodForHistoryListVo(goodForHistoryShorts);
////
////    }
//
//    @Override
//    public GoodForSaleDetailVo getGoodForSaleDetail(Integer goodId) {
//        return new GoodForSaleDetailVo(goodForSaleMapper.getGoodInfo(goodId),goodImagineMapper.getImagine(goodId));
//    }
//
//    @Override
//    public GoodForHistoryDetailVo getGoodForHistoryDetail(Integer goodId) {
//        return null;
//        //return new GoodForHistoryDetailVo(goodForHistoryMapper.getGoodInfo(goodId),goodImagineMapper.getImagine(goodId));
//    }
//
//    @Override
//    public IntentionListVo getIntentionBuyers(Integer goodId) {
//        List<Intention> intentions = intentionMapper.getIntentionList(goodId);
//        List<IntentionShort> intentionShorts = new ArrayList<>();
//        for(Intention intention :intentions){
//            Buyer buyer = buyerMapper.getBuyerInfo(intention.getBuyerId());
//            IntentionShort intentionShort = new IntentionShort(intention.getIntentionId() , buyer.getName() , buyer.getLocation() , buyer.getPhone());
//            intentionShorts.add(intentionShort);
//        }
//        System.out.println("hahah");
//        return new IntentionListVo(intentionShorts);
//    }//空指针错误
//
//    @Override
//    public IntentionDetailVo getIntentionDetail(Integer intentionId) {
//        return new IntentionDetailVo(intentionMapper.getIntentionInfo(intentionId));
//    }
//
//    @Override
//    public Boolean startDeal(Integer buyerId,Integer sellerId,Integer goodId) {
//        Business business = new Business(null,buyerId,sellerId,goodId);
//        goodForSaleMapper.freezeGood(goodId);
//        return businessMapper.startBusiness(business);
//    }
//
//    @Override
//    public Boolean startDeal(Business business) {
//        return businessMapper.startBusiness(business);
//    }
//
//    @Override
//    public Boolean cancelDeal(Integer businessId) {
//        Business business = businessMapper.getBusinessInfo(businessId);
//        goodForSaleMapper.unfreezeGood(business.getGoodId());
//        return businessMapper.detelBusiness(businessId) > 0;
//    }
//
//    @Override
//    public Boolean finishDeal(Integer businessId, Date dealDate) {
//        Business business = businessMapper.getBusinessInfo(businessId);
//        Buyer buyer = buyerMapper.getBuyerInfo(business.getBuyerId());
//        GoodForSale good = goodForSaleMapper.getGoodInfo(business.getGoodId());
//        GoodForHistory goodForHistory = new GoodForHistory(good.getGoodId(),good.getSellerId(),good.getName(),good.getDescription(),
//                good.getPrice(),dealDate,buyer.getPhone());
//        goodForHistoryMapper.addGoodForHistory(goodForHistory);
//        goodForSaleMapper.soldOutGood(good.getGoodId());
//        return true;
//    }
//
//    @Override
//    public GoodForSale putOnGood(String name, String description, Double price, Integer sellerId) {
//        GoodForSale good = new GoodForSale(null,sellerId,price,name,description,null,null);
//        return (goodForSaleMapper.putOnGood(good) ? good : null);
//    }
//
//    @Override
//    public GoodForSale putOnGood(GoodForSale good) {
//        return (goodForSaleMapper.putOnGood(good) ? good : null);
//    }
//
//    @Override
//    public Boolean putOffGood(Integer goodId) {
//        return goodForSaleMapper.putOffGood(goodId) > 0;
//    }
//
//    @Override
//    public Boolean searchAccount(String account) {
//        List<Seller> sellers = sellerMapper.searchAccount(account);
//        return !sellers.isEmpty();
//    }
//
//    @Override
//    public Boolean updateInfo(Seller seller) {
//        return !sellerMapper.searchAccount(seller.getAccount()).isEmpty();
//    }
//
//    @Override
//    public Boolean exhibitGood(Integer goodId) {
//        return goodForSaleMapper.exhibitGood(goodId) > 0;
//    }
//
//    @Override
//    public GoodForHistoryListVo getGoodForHistoryList(Integer goodId){
//        List<GoodForHistory> li =  goodForHistoryMapper.getGoodForHistoryList(goodId);
//        List<GoodForHistoryShort> goodlist = new ArrayList<>();
//        for(GoodForHistory good : li){
//            GoodImagine goodImg = goodImagineMapper.getImagine(good.getGoodId()).stream().findFirst().orElse(null);
//            String img = (goodImg != null ? goodImg.getImagine() : null);
//            goodlist.add(new GoodForHistoryShort(good.getGoodId(),good.getPrice(),good.getName(),
//                    good.getDealDate(),img));
//        }
//        return new GoodForHistoryListVo(goodlist);
//    }

}
