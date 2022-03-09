package com.zjgsu.shopping.interior.Seller.service.impl;

import com.zjgsu.shopping.interior.Buyer.mapper.BuyerMapper;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
//import com.zjgsu.shopping.interior.Buyer.service.BuyerHistoryService;
import com.zjgsu.shopping.interior.Common.mapper.*;
import com.zjgsu.shopping.interior.Common.pojo.vo.GoodIds;
import com.zjgsu.shopping.interior.Common.pojo.vo.OrderVo;
import com.zjgsu.shopping.interior.Seller.mapper.SellerMapper;
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
    GoodImagineMapper goodImagineMapper;
    @Resource
    OrderMapper orderMapper;
    @Resource
    One2TwoClassMapper one2TwoClassMapper;

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
    public List<Order> getHistoryOrderListBySellerId(Integer sellerId) {
        return sellerMapper.getHistoryOrderListBySellerId(sellerId);
    }

    @Override
    public List<Order> getHistoryOrderByGoodId(Integer goodId) {
       return  sellerMapper.getHistoryOrderByGoodId(goodId);
    }

    @Override
    public List<Order> getWillingOrderListByGoodId(Integer goodId) {
        return sellerMapper.getWillingOrderListByGoodId(goodId);
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
    public Boolean cancelTheOrderBySeller(Integer orderId) {
        try {
            Order order = orderMapper.getOrder(orderId);
            if(order.getStmt() >= 5)return false;
            order.setStmt(-2);
            return (orderMapper.updateOrderStatement(order) > 0 );
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }




    @Override
    public Boolean acceptTheOrder(OrderVo order) {
        order.setStartDate(new Date());
        if(Objects.equals(goodMapper.getGoodInfo(order.getGoodId()).getStorage(), order.getNumber()))
            goodMapper.freezeGood(order.getGoodId());
        if(sellerMapper.updateOrderToStatementTwo(order.getOrderId()) == 0) return false;
        if(sellerMapper.getWillingOrderListByGoodId(order.getGoodId()).isEmpty())
            goodMapper.refuseGood(order.getGoodId());
        System.out.println(order);
        return orderMapper.placeAnOrder(order);
    }



    /**
     * 卖空
     */

    @Override
    public Boolean finishTheOrder(Integer orderId){
        Date dealDate = new Date();
        Order order = orderMapper.getOrder(orderId);
        order.setDealDate(dealDate);
        int l = goodMapper.getGoodInfo(order.getGoodId()).getStorage() - order.getNumber();
        if(l < 0) return false;
        else if( l == 0) goodMapper.soldOutGood(order.getGoodId());
        Good good = goodMapper.getGoodInfo(order.getGoodId());
        good.setStorage(l);
        goodMapper.updateGoodStorage(good);
        goodMapper.unfreezeGood(order.getGoodId());
        Integer buyerId = order.getBuyerId();
        String buyerName = buyerMapper.getBuyerInfo(buyerId).getBuyerName();
        return sellerMapper.finishTheOrder(orderId) > 0;
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

    @Override
    public Boolean pullOffMultipleGood(GoodIds goodIds){
        Long l= Long.valueOf(0);
        for(Integer i=0;i<goodIds.getGoodIds().size();i++){
            goodMapper.soldOutGood(goodIds.getGoodIds().get(i));
            l = goodMapper.pullOffGood(goodIds.getGoodIds().get(i));
            if(l<=0) return false;
        }
        return l>0;
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
    public List<Order> getOrderListByGoodId(Integer goodId) {
        return sellerMapper.getOrderListByGoodId(goodId);
    }

    @Override
    public Boolean uploadGoodImg(Integer goodId, List<String> li){
        for(String item:li){
            goodImagineMapper.addImagine(new GoodImagine(null,goodId,item));
        }
        return true;
    }
//    public Boolean uploadGoodImg(GoodImagine goodImagine){
//        return goodImagineMapper.addImagine(goodImagine);
//    }

    //视频
    public Boolean saveVideoToDatabase(Video video){
        return goodMapper.saveVideoToDatabase(video);
    }

    public List<Class1> getAllClass1(){
        return one2TwoClassMapper.getAllClass1();
    }

    public List<One2Two> getAllClass2ByClass1Id(Integer class1){
        return one2TwoClassMapper.getAllClass2ByClass1Id(class1);
    }

    @Override
    public List<Order> getOrderListOfStatement1(Integer sellerId) {
        return sellerMapper.getOrderListOfStatement1(sellerId);
    }

    @Override
    public List<Order> getOrderListOfStatement2(Integer sellerId) {
        return sellerMapper.getOrderListOfStatement2(sellerId);
    }

    @Override
    public List<Order> getOrderListOfStatement5(Integer sellerId) {
        return sellerMapper.getOrderListOfStatement5(sellerId);
    }

    @Override
    public List<Order> getOrderListOfStatement6(Integer sellerId) {
        return sellerMapper.getOrderListOfStatement6(sellerId);
    }

    @Override
    public List<Order> getOrderListOfStatement_1(Integer sellerId) {
        return sellerMapper.getOrderListOfStatement_1(sellerId);
    }

    @Override
    public Boolean cancelTheOrder(Integer orderId) {
        return null;
    }

    @Override
    public Boolean deliverTheGoods(Order order) {
       if(orderMapper.getOrderStatement(order.getOrderId()) != 4) return false;
       return orderMapper.setTrackingNumber(order) > 0 ;
    }

    @Override
    public String getTrackingNumber(Integer orderId) {
        return orderMapper.getTrackingNumber(orderId);
    }
}
