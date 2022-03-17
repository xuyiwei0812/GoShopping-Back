package com.zjgsu.shopping.interior.Buyer.service.impl;

import com.zjgsu.shopping.interior.Buyer.mapper.BuyerMapper;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.mapper.GoodImagineMapper;
import com.zjgsu.shopping.interior.Common.mapper.GoodMapper;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Common.mapper.One2TwoClassMapper;

import com.zjgsu.shopping.interior.Common.mapper.OrderMapper;
import com.zjgsu.shopping.interior.Common.pojo.*;
import com.zjgsu.shopping.interior.Common.pojo.vo.*;
import com.zjgsu.shopping.interior.Seller.mapper.SellerMapper;
import com.zjgsu.shopping.interior.Seller.service.SellerService;
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
    @Resource
    private SellerMapper sellerMapper;


    @Override
    public Buyer buyerRegister(Buyer buyer) {
        buyerMapper.register(buyer);
        buyerMapper.addDefaultAddress(buyer);//加默认地址
        return buyer;
    }

    @Override
    public Integer buyerLogin(String account, String password) {
        Buyer buyer = buyerMapper.login(account,password);
        System.out.println("1111"+buyer);
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
          System.out.println("sellerId : " + sellerMapper.getSellerIdByGoodId(order.getGoodId()));
          order.setSellerId(sellerMapper.getSellerIdByGoodId(order.getGoodId()));
      } catch (Exception e) {
          e.printStackTrace();
      }
        return (orderMapper.placeAnOrder(order));
    }

    @Override
    public Boolean cancelTheOrderByBuyer(Integer orderId) {
       try {
           Order order = orderMapper.getOrder(orderId);
           if(order.getStmt() >= 4)return false;
           order.setStmt(6);
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


    @Override
    public List<Order> getBuyerHistory(Integer buyerId) {
        List<Order> li = buyerMapper.getHistoryOrderListByBuyerId(buyerId);
        System.out.println("buyerHistoryList : "+li);
        return li;
    }

    @Override
    public List<Order> getOrderListOfStatement1(Integer buyerId) {
        return buyerMapper.getOrderListOfStatement1(buyerId);
    }

    @Override
    public List<Order> getOrderListOfStatement2(Integer buyerId) {
        return buyerMapper.getOrderListOfStatement2(buyerId);
    }

    @Override
    public List<Order> getOrderListOfStatement5(Integer buyerId) {
        return buyerMapper.getOrderListOfStatement5(buyerId);
    }

    @Override
    public List<Order> getOrderListOfStatement6(Integer buyerId) {
        return buyerMapper.getOrderListOfStatement6(buyerId);
    }

    @Override
    public List<Order> getOrderListOfStatement_1(Integer buyerId) {
        return buyerMapper.getOrderListOfStatement_1(buyerId);
    }

    @Override
    public Integer favoriteGood(Integer goodId,Integer buyerId){
        Good good = goodMapper.getGoodInfo(goodId);
        if(buyerMapper.favoriteGood(good,buyerId)==true) return 1;
        else return 2;
    }

    @Override
    public List<FavoriteGoodWithImg> getFavoriteByBuyer(Buyer buyer){
        List<FavoriteGood> favoriteGoodList = buyerMapper.getFavoriteByBuyer(buyer);
        List<FavoriteGoodWithImg> favoriteGoodWithImgList = new ArrayList<>();
        for(FavoriteGood item:favoriteGoodList){
            Integer goodId = item.getGoodId();
            GoodImagine goodImagine = goodImagineMapper.getFirstGoodImg(goodId);
            FavoriteGoodWithImg favoriteGoodWithImg = new FavoriteGoodWithImg(item,goodImagine);
            favoriteGoodWithImgList.add(favoriteGoodWithImg);
        }
        System.out.println("favoriteGoodWithImgList"+favoriteGoodWithImgList);
        System.out.println("第一个商品图"+favoriteGoodWithImgList.get(0).getGoodImagine());
        return favoriteGoodWithImgList;
    }

    @Override
    public Boolean getGoodIntoCart(Integer goodId, Integer buyerId, Integer number){
        //System.out.println("buyerIdId::"+buyerId);
        Good good = goodMapper.getGoodInfo(goodId);
        if(buyerMapper.checkCart(buyerId,goodId)!=null){//如果已经加入过购物车了
            Integer thenNumber = buyerMapper.getCartNumber(buyerId,goodId);
            Integer nowNumber = thenNumber + number;
            buyerMapper.changeCartNumber(goodId,buyerId,nowNumber);
        }
        else buyerMapper.getGoodIntoCart(good,buyerId,number);
        return true;
    }

    @Override
    public Boolean changeCartNumber(Integer buyerId, Integer goodId, Integer nowNumber){
        if(nowNumber>0) return buyerMapper.changeCartNumber(buyerId,goodId,nowNumber);
        else return false;
    }

    @Override
    public List<CartWithImg> getCartByBuyer(Buyer buyer){
        List<Cart> cartList = buyerMapper.getCartByBuyer(buyer);
        List<CartWithImg> cartWithImgList = new ArrayList<>();
        for(Cart item:cartList){
            Integer goodId = item.getGoodId();
            GoodImagine goodImagine = goodImagineMapper.getFirstGoodImg(goodId);
            CartWithImg cartWithImg = new CartWithImg(item,goodImagine);
            cartWithImgList.add(cartWithImg);
        }
        return cartWithImgList;
    }

    @Override
    public Boolean getCartGoodIntoFavorite(List<Cart> cartList){
        for(Cart item:cartList){
            Integer goodId = item.getGoodId();
            //System.out.println("goodId"+goodId);
            Good good = goodMapper.getGoodInfo(goodId);
            //System.out.println("good"+good);
            item.setGoodName(good.getGoodName());
            item.setGoodPrice(good.getGoodPrice());
            item.setDescription(good.getDescription());
            if(buyerMapper.checkFavorite(item.getBuyerId(),goodId)!=null){//如果已经收藏过了
                continue;
            }
            buyerMapper.getCartGoodIntoFavorite(item);
        }
        return true;
    }

    @Override
    public List<Address> getAddressByBuyer(Integer buyerId){
        return buyerMapper.getAddressByBuyer(buyerId);
    }

    @Override
    public Long buyerCancelOrder(Integer orderId){
        return buyerMapper.buyerCancelOrder(orderId);
    }

    @Override
    public Boolean buyerConformReceipt(Integer orderId){
        if(orderMapper.getOrderStatement(orderId)==5) {
            buyerMapper.buyerConformReceipt(orderId);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteCartGood(List<Cart> cartList){
        for(Cart item:cartList){
            Integer cartId=item.getCartId();
            buyerMapper.deleteCartGood(cartId);
        }
        return true;
    }

    @Override
    public Boolean deleteFavoriteGood(FavoriteIds favoriteIds){
        List<Integer> ids = favoriteIds.getFavoriteIds();
        for(Integer item:ids){
            buyerMapper.deleteFavoriteGood(item);
        }
        return true;
    }

    @Override
    public Boolean checkFavorite(Integer buyerId, Integer goodId){
        if(buyerMapper.checkFavorite(buyerId,goodId)!=null) return true;
        else return false;
    }
}
