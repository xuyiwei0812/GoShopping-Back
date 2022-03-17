package com.zjgsu.shopping.interior.Buyer.mapper;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Common.pojo.*;
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
    @Options(useGeneratedKeys = true, keyProperty = "address.addressId", keyColumn = "addressId")
    @Insert("insert into address (buyerId,buyerName,buyerAddress,buyerPhone,isDefault) values(#{buyer.buyerId},#{buyer.buyerName},#{buyer.buyerLocation},#{buyer.buyerPhone},1)")
    Boolean addDefaultAddress(@Param("buyer")Buyer buyer);

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

    @Select("select * from goodorder where buyerId=#{buyerId} and stmt = 5")
    List<Order> getHistoryOrderListByBuyerId(@Param("buyerId")Integer buyerId);


    @Select("select * from goodorder where buyerId=#{buyerId} and stmt = 1")
    List<Order> getOrderListOfStatement1(@Param("buyerId")Integer buyerId);

    @Select("select * from goodorder where buyerId=#{buyerId} and stmt = 5")
    List<Order> getOrderListOfStatement5(@Param("buyerId")Integer buyerId);

    @Select("select * from goodorder where buyerId=#{buyerId} and stmt = 6")
    List<Order> getOrderListOfStatement6(@Param("buyerId")Integer buyerId);

    @Select("select * from goodorder where buyerId=#{buyerId} and (stmt = 2 or stmt = 3 or stmt = 4)")
    List<Order> getOrderListOfStatement2(@Param("buyerId")Integer buyerId);

    @Select("select * from goodorder where buyerId=#{buyerId} and (stmt = -1 or stmt = -2)")
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

    //查有没有收藏过
    @Select("select * from favorite where buyerId=#{buyerId} and goodId=#{goodId}")
    FavoriteGood checkFavorite(@Param("buyerId")Integer buyerId,@Param("goodId")Integer goodId);

    /**
     * 购物车
     */
    @Options(useGeneratedKeys = true, keyProperty = "cart.cartId", keyColumn = "cartId")
    @Insert("insert into cart(buyerId,goodId,goodName,goodPrice,description,number) values(#{buyerId},#{good.goodId},#{good.goodName},#{good.goodPrice},#{good.description},#{number})")
    Boolean getGoodIntoCart(@Param("good")Good good,@Param("buyerId")Integer buyerId,@Param("number")Integer number);

    @Select("select * from cart where buyerId=#{buyer.buyerId}")
    List<Cart> getCartByBuyer(@Param("buyer")Buyer buyer);

    @Options(useGeneratedKeys = true, keyProperty = "favorite.favoriteId", keyColumn = "favoriteId")
    @Insert("insert into favorite(buyerId,goodId,goodName,goodPrice,description) values(#{cart.buyerId},#{cart.goodId},#{cart.goodName},#{cart.goodPrice},#{cart.description})")
    Boolean getCartGoodIntoFavorite(@Param("cart")Cart cart);

    //查有没有加过购物车
    @Select("select * from cart where buyerId=#{buyerId} and goodId=#{goodId}")
    Cart checkCart(@Param("buyerId")Integer buyerId,@Param("goodId")Integer goodId);

    @Select("select number from cart where buyerId=#{buyerId} and goodId=#{goodId} limit 1")
    Integer getCartNumber(@Param("buyerId")Integer buyerId,@Param("goodId")Integer goodId);

    @Update("update cart set number=#{nowNumber} where buyerId=#{buyerId} and goodId=#{goodId}")
    Boolean addCartNumber(@Param("goodId")Integer goodId,@Param("buyerId")Integer buyerId,@Param("nowNumber")Integer nowNumber);

    //删
    @Delete("delete from cart where cartId=#{cartId}")
    Boolean deleteCartGood(@Param("cartId")Integer cartId);

    @Delete("delete from favorite where favoriteId=#{favoriteId}")
    Boolean deleteFavoriteGood(@Param("favoriteId")Integer favoriteId);

    /**
     * 拿某个人的地址
     */
    @Select("select * from address where buyerId=#{buyerId}")
    List<Address> getAddressByBuyer(@Param("buyerId")Integer buyerId);

    /**
     * 买家取消订单
     */
    @Update("update goodorder set stmt=-1 where orderId=#{orderId}")
    Long buyerCancelOrder(@Param("orderId")Integer orderId);

    /**
     * 买家确认收货
     */
    @Update("update goodorder set stmt=6 where orderId=#{orderId}")
    Boolean buyerConformReceipt(@Param("orderId")Integer orderId);

}
