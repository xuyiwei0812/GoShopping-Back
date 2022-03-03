package com.zjgsu.shopping.interior.Buyer.service.impl;

import com.zjgsu.shopping.interior.Buyer.mapper.BuyerMapper;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.mapper.GoodImagineMapper;
import com.zjgsu.shopping.interior.Common.mapper.GoodMapper;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Common.mapper.One2TwoClassMapper;
import com.zjgsu.shopping.interior.Common.mapper.OrderMapper;
import com.zjgsu.shopping.interior.Common.pojo.Good;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import com.zjgsu.shopping.interior.Common.pojo.vo.GoodwithImg;
import com.zjgsu.shopping.interior.Common.pojo.vo.Mode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private BuyerMapper buyerMapper;
    @Resource
    private GoodMapper goodMapper;
    @Resource
    private GoodImagineMapper goodImagineMapper;
    @Resource
    private One2TwoClassMapper one2TwoClassMapper;


    @Override
    public Buyer buyerRegister(Buyer buyer) {
        return (buyerMapper.register(buyer) ? buyer : null);
    }

    @Override
    public Integer buyerLogin(String account, String password) {
        Buyer buyer = buyerMapper.login(account,password);
        return (buyer != null ? buyer.getBuyerId() : -1);
    }

    @Override
    public Long updateBuyerPassword(Integer buyerId, String password, String newPassword) {
        Buyer buyer = buyerMapper.getBuyerInfo(buyerId);
        if (!Objects.equals(buyer.getBuyerPassword(), password)) return (long) -2;
        return buyerMapper.updateBuyerPassword(buyerId,newPassword);
    }

    @Override
    public Boolean checkBuyerPassword(Integer buyerId, String password) {
        Buyer buyer = buyerMapper.getBuyerInfo(buyerId);
        return Objects.equals(buyer.getBuyerPassword(),password);
    }



    @Override
    public Boolean searchBuyerAccount(String account) {
        return !buyerMapper.searchAccount(account).isEmpty();
    }

    @Override
    public Boolean placeAnOrder(Order order) { //下订单
      try {
          goodMapper.WantGood(order.getGoodId());

          SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Date nowDate1 = format1.parse(format1.format(new Date()));
          System.out.println("nowdate : "+nowDate1);
          order.setStartDate(nowDate1);

      } catch (Exception e) {
          e.printStackTrace();
      }
        return (orderMapper.placeAnOrder(order));
    }

    @Override
    public Boolean cancelTheOrderByBuyer(Integer orderId) {
       try {
           Order order = orderMapper.getOrder(orderId);
           if(order.getStatement() >= 4)return false;
           order.setStatement(6);
           return (orderMapper.updateOrderStatement(order) > 0 );
       }catch (Exception e){
           e.printStackTrace();
       }
       return false;
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
    public List<Good> getClass2GoodListByClassId(Mode mode) {
       return goodMapper.getClass2GoodListByClassId(mode);
    }



    @Override
    public List<Good> getClass1GoodListByClassId(Mode mode) {
        List<Good> li = goodMapper.getAllGoodList(mode);
        List<Good> re = new ArrayList<>();
        for(Good item:li){
            if(Objects.equals(one2TwoClassMapper.getClassInfoBySecondClass(item.getClass2()).getClass1(), mode.getClass1()))
                re.add(item);
        }
        return re;
    }

    @Override
    public GoodwithImg getGoodInfo(Integer goodId) {
        return new GoodwithImg(goodMapper.getGoodInfo(goodId), goodImagineMapper.getImagine(goodId));
    }

    /**
     * 拿某个goodId的视频
     * @param good
     * @return
     */
    @Override
    public String getVideoByGood(Good good){
        return goodMapper.getVideoByGood(good);
    }

    /**
     * 模糊搜索
     */
    @Override
    public List<Good> searchGood(String keyword){
        return goodMapper.searchGood(keyword);
    }

    @Override
    public Boolean updateBuyerInfo(Buyer buyer){
        return buyerMapper.updateBuyerInfo(buyer);
    }


//    @Override
//    public List<Order> getBuyerHistory(Integer buyerId) {
//        List<Order> li = buyerMapper.getHistoryOrderListByBuyerId(buyerId);
//        System.out.println("buyerHistoryList"+buyerHistoryList);
//        for(Integer i=0;i<li.size();i++) {
//            Integer goodId = li.get(i).getGoodId();
//            System.out.println("goodId:"+goodId);
//            Double goodPrice = buyerHistoryMapper.getGoodPriceByGoodId(goodId);
//            System.out.println("goodPrice"+goodPrice);
//            List<String> img = buyerHistoryMapper.getGoodImageByGoodId(goodId);
//            buyerHistoryList.get(i).setGoodPrice(goodPrice);
//            buyerHistoryList.get(i).setImg(img);
//        }
//        return buyerHistoryList;
//    }
}
