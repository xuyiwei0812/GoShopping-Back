package com.zjgsu.shopping.interior.Buyer.service;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Common.pojo.Cart;
import com.zjgsu.shopping.interior.Common.pojo.Good;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import com.zjgsu.shopping.interior.Common.pojo.vo.*;

import java.util.List;

public interface BuyerService {



    /**
     * 对账号信息进行操作
     * 1.账号注册
     * 2.账号登录
     * 3.更新账号密码
     * 4.检查账号密码
     * 5.更新卖家的信息
     * 6.查询账号是否存在
     */
    Buyer buyerRegister(Buyer buyer);
    Integer  buyerLogin(String account, String password);
    Long updateBuyerPassword(Integer buyerId, String password, String newPassword);
    Boolean checkBuyerPassword(Integer buyerId, String password);
    Boolean updateBuyerInfo(Buyer buyer);
    Boolean searchBuyerAccount(String account);


    /**
     * 提交一个订单
     * @param order 订单信息
     * @return 失败返回-1
     */

    Boolean placeAnOrder(Order order);

    /**
     * 撤销一个购买意向
     *
     * @param  orderId 意向编号
     * @return 撤销失败返回-1
     */
    Boolean cancelTheOrderByBuyer(Integer orderId);

    List<Good> getAllGoodListForBuyers();

    List<Good> getUnfrozenGoodListForBuyers();

    List<Good> getAllGoodListBySellerIdForBuyers(Integer sellerId);

    List<Good> getUnfrozenGoodListBySellerIdForBuyers(Integer sellerId);

    List<Good> getClass2GoodListByClassId(Mode mode);

    List<Good> getClass1GoodListByClassId(Mode mode);


    /**
     * 取得某一商品的详细信息
     *
     * @param goodId 商品编号
     * @return 某一商品的详细信息
     */
    GoodwithImg getGoodInfo(Integer goodId);

    String getVideoByGood(Good good);

    List<Good> searchGood(String keyword);

    List<Order> getBuyerHistory(Integer buyerId) ;

    List<Order> getOrderListOfStatement1(Integer buyerId);

    List<Order> getOrderListOfStatement2(Integer buyerId);

    List<Order> getOrderListOfStatement5(Integer buyerId);

    List<Order> getOrderListOfStatement6(Integer buyerId);

    List<Order> getOrderListOfStatement_1(Integer buyerId);

    public Boolean favoriteGood(Integer goodId,Integer buyerId);

    public List<FavoriteGoodWithImg> getFavoriteByBuyer(Buyer buyer);

    public Boolean getGoodIntoCart(Integer goodId, Integer buyerId, Integer number);

    public List<CartWithImg> getCartByBuyer(Buyer buyer);

    public Boolean getFavoriteGoodsIntoCart(List<Cart> cartList);
}
