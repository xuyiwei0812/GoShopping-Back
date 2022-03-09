package com.zjgsu.shopping.interior.Seller.mapper;


//import com.sun.org.apache.xpath.internal.operations.Or;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import org.apache.ibatis.annotations.*;

import java.util.List;

//存放卖家的信息
@Mapper
public interface SellerMapper {
    /**
     * 注册
     *
     * @param seller 卖家
     * @return 商家编号, 或者无法注册返回-1
     */
    @Options(useGeneratedKeys = true, keyProperty = "seller.sellerId", keyColumn = "sellerId")
    @Insert("insert into seller (name,account,password,location,phone) values (#{seller.name},#{seller.account},#{seller.password},#{seller.location},#{seller.phone})")
    Boolean register(@Param("seller") Seller seller);

    /**
     * 登录
     *
     * @param account  用户
     * @param password 密码
     * @return 用户
     */
    @Select("select * from seller where account=#{account} and password=#{password}")
    Seller login(@Param("account") String account, @Param("password") String password);

    /**
     * 修改密码
     *
     * @param password 密码
     * @param sellerId 用户编号
     * @return 是否更新成功
     */
    @Update("update seller set password=#{password} where sellerId=#{sellerId}")
    Long updatePassword(@Param("sellerId") Integer sellerId, @Param("password") String password);

    /**
     * @param account 账号
     * @return 返回查找到的账号的列表
     */
    @Select("select * from seller where account=#{account}")
    List<Seller> searchAccount(@Param("account") String account);

    /**
     *
     */
    @Update("update seller set location = #{seller.location} , phone = #{seller.phone} , name = #{seller.name} where sellerId = #{seller.sellerId}")
    Long updateInfo(@Param("seller") Seller seller);

    /**
     *
     */
    @Select("select * from seller where sellerId=#{sellerId}")
    Seller getInfo(@Param("sellerId") Integer sellerId);


    @Select("select * from order where sellerId=#{sellerId} and statement = 5")
    List<Order> getHistoryOrderListBySellerId(@Param("sellerId")Integer sellerId);

    @Select("select * from order where goodId=#{goodId} and statement = 5")
    List<Order> getHistoryOrderByGoodId(@Param("goodId") Integer goodId);


    @Select("select * from order where goodId=#{goodId} and statement <> 5 and statement<> 6 and statement <> 1")
    List<Order> getOrderListByGoodId(@Param("goodId") Integer goodId);



    @Select("select * from order where goodId=#{goodId} and statement = 1")
    List<Order> getWillingOrderListByGoodId(@Param("goodId") Integer goodId);




    @Update("update order set statement = 2 where orderId=#{orderId} and statement = 1")
    Long updateOrderToStatementTwo(@Param("orderId")Integer orderId);


    @Update("update order set statement = 5 where orderId=#{orderId} and statement = 4")
    Long finishTheOrder(@Param("orderId") Integer orderId);

    @Select("select * from order where sellerId=#{sellerId} and statement = 1")
    List<Order> getOrderListOfStatement1(@Param("sellerId")Integer sellerId);

    @Select("select * from order where sellerId=#{sellerId} and (statement = 2 or statement = 3 or statement = 4)")
    List<Order> getOrderListOfStatement2(@Param("sellerId")Integer sellerId);

    @Select("select * from order where sellerId=#{sellerId} and statement = 5")
    List<Order> getOrderListOfStatement5(@Param("sellerId")Integer sellerId);

    @Select("select * from order where sellerId=#{sellerId} and statement = 6")
    List<Order> getOrderListOfStatement6(@Param("sellerId")Integer sellerId);

    @Select("select * from order where sellerId=#{sellerId} and (statement = -1 or statement = -2)")
    List<Order> getOrderListOfStatement_1(@Param("sellerId")Integer sellerId);

}
