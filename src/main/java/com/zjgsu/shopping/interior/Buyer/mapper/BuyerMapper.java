package com.zjgsu.shopping.interior.Buyer.mapper;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Common.pojo.Cart;
import com.zjgsu.shopping.interior.Common.pojo.FavoriteGood;
import com.zjgsu.shopping.interior.Common.pojo.Good;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import com.zjgsu.shopping.interior.Common.pojo.vo.OrderList;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

//存放买家的信息
@Mapper
public interface BuyerMapper {
    /**
     * 出现一个购买人
     *
     * @param buyer 买家信息
     * @return 提出失败返回-1
     */
    @Options(useGeneratedKeys = true, keyProperty = "buyer.buyerId", keyColumn = "buyerId")
    @Insert("insert into buyer (buyerName,buyerAccount,buyerPassword,buyerLocation,buyerPhone) values (#{buyer.buyerName}," +
            "#{buyer.buyerAccount},#{buyer.buyerPassword},#{buyer.buyerLocation} , #{buyer.buyerPhone})")
    Boolean register(@Param("buyer") Buyer buyer);

    /**
     * 把默认地址放入address表里
     */
    //Boolean addDefaultAddres

    /**
     * 登录
     *
     * @param account  用户
     * @param password 密码
     * @return 用户
     */
    @Select("select * from buyer where buyerAccount=#{account} and buyerPassword=#{password}")
    Buyer login(@Param("account") String account, @Param("password") String password);

    @Select("select * from buyer where buyerId=#{buyerId}")
    Seller getInfo(@Param("buyerId") Integer buyerId);
    /**
     * 撤销一个购买意向
     *
     * @param buyer 商品信息
     * @return 撤销失败 返回-1
     * <p>
     * 无用,暂时没必要删除掉写入数据库的信息
     */
    @Delete("delete from buyer where buyerId=#{buyer.buyerId}")
    Long deleteBuyer(@Param("buyer") Buyer buyer);

    @Select("select * from buyer where buyerAccount = #{buyerAccount}")
    List<Buyer> searchAccount(@Param("buyerAccount") String buyerAccount);
    /**
     * 返回买家的信息
     *
     * @param buyerId 用户编号
     */
    @Select("select * from buyer where buyerId=#{buyerId}")
    Buyer getBuyerInfo(@Param("buyerId") Integer buyerId);

    /**
     * 改买家密码
     * @param buyerId
     * @return
     */
    @Update("update buyer set buyerPassword=#{password} where buyerId=#{buyerId}")
    Long updateBuyerPassword(@Param("buyerId") Integer buyerId,@Param("password") String password);

    @Update("update buyer set buyerLocation=#{buyer.buyerLocation}, buyerName=#{buyer.buyerName}, buyerPhone=#{buyer.buyerPhone} where buyerId=#{buyer.buyerId}")
    Boolean updateBuyerInfo(@Param("buyer") Buyer buyer);

    @Select("select * from order where buyerId=#{buyerId} and statement = 5")
    List<Order> getHistoryOrderListByBuyerId(@Param("buyerId")Integer buyerId);


    @Select("select * from order where buyerId=#{buyerId} and statement = 1")
    List<Order> getOrderListOfStatement1(@Param("buyerId")Integer buyerId);

    @Select("select * from order where buyerId=#{buyerId} and statement = 5")
    List<Order> getOrderListOfStatement5(@Param("buyerId")Integer buyerId);

    @Select("select * from order where buyerId=#{buyerId} and statement = 6")
    List<Order> getOrderListOfStatement6(@Param("buyerId")Integer buyerId);

    @Select("select * from order where buyerId=#{buyerId} and (statement = 2 or statement = 3 or statement = 4)")
    List<Order> getOrderListOfStatement2(@Param("buyerId")Integer buyerId);

    @Select("select * from order where buyerId=#{buyerId} and (statement = -1 or statement = -2)")
    List<Order> getOrderListOfStatement_1(@Param("buyerId")Integer buyerId);

    /**
     *收藏商品
     */
    @Options(useGeneratedKeys = true, keyProperty = "favorite.favoriteId", keyColumn = "favoriteId")
    @Insert("insert into favorite(buyerId,goodId,goodName,goodPrice,description) values(#{buyerId},#{good.goodId},#{good.goodName},#{good.goodPrice},#{good.description})")
    Boolean favoriteGood(@Param("good") Good good, @Param("buyerId")Integer buyerId);

    @Select("select * from favorite where buyerId=#{buyer.buyerId}")
    List<FavoriteGood> getFavoriteByBuyer(@Param("buyer")Buyer buyer);

    @Select("select * from favorite where goodId=#{goodId} limit 1")
    FavoriteGood getFavoriteGoodInfo(@Param("goodId") Integer goodId);

    /**
     * 购物车
     */
    @Options(useGeneratedKeys = true, keyProperty = "cart.cartId", keyColumn = "cartId")
    @Insert("insert into favorite(buyerId,goodId,goodName,goodPrice,description,number) values(#{buyerId},#{good.goodId},#{good.goodName},#{good.goodPrice},#{good.description},#{number})")
    Boolean getGoodIntoCart(@Param("good")Good good,@Param("buyerId")Integer buyerId,@Param("number")Integer number);

    @Select("select * from cart where buyerId=#{buyer.buyerId}")
    List<Cart> getCartByBuyer(@Param("buyer")Buyer buyer);

    @Options(useGeneratedKeys = true, keyProperty = "cart.cartId", keyColumn = "cartId")
    @Insert("insert into favorite(buyerId,goodId,goodName,goodPrice,description,number) values(#{cart.buyerId},#{cart.goodId},#{good.goodName},#{cart.goodPrice},#{cart.description},#{cart.number})")
    Boolean getFavoriteGoodIntoCart(@Param("cart")Cart cart);
}
