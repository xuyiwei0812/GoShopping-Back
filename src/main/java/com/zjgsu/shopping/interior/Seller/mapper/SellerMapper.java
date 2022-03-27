package com.zjgsu.shopping.interior.Seller.mapper;


//import com.sun.org.apache.xpath.internal.operations.Or;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import com.zjgsu.shopping.interior.Common.pojo.PostSale;
import com.zjgsu.shopping.interior.Common.pojo.PostSaleImage;
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


    @Select("select * from goodorder where sellerId=#{sellerId} and stmt = 6")
    List<Order> getHistoryOrderListBySellerId(@Param("sellerId")Integer sellerId);

    @Select("select * from goodorder where goodId=#{goodId} and stmt = 6")
    List<Order> getHistoryOrderByGoodId(@Param("goodId") Integer goodId);


    @Select("select * from goodorder where goodId=#{goodId} and stmt <> 5 and stmt<> 6 and stmt <> 1")
    List<Order> getOrderListByGoodId(@Param("goodId") Integer goodId);



    @Select("select * from goodorder where goodId=#{goodId} and stmt = 2")
    List<Order> getWillingOrderListByGoodId(@Param("goodId") Integer goodId);

    @Update("update goodorder set stmt = 5 where orderId=#{orderId} and stmt = 4")
    Long finishTheOrder(@Param("orderId") Integer orderId);

    @Select("select * from goodorder where sellerId=#{sellerId} and stmt = 1")
    List<Order> getOrderListOfStatement1(@Param("sellerId")Integer sellerId);

    @Select("select * from goodorder where sellerId=#{sellerId} and stmt = 2")
    List<Order> getOrderListOfStatement2(@Param("sellerId")Integer sellerId);

    @Select("select * from goodorder where sellerId=#{sellerId} and stmt = 3")
    List<Order> getOrderListOfStatement3(@Param("sellerId")Integer sellerId);

    @Select("select * from goodorder where sellerId=#{sellerId} and stmt = 4 ")
    List<Order> getOrderListOfStatement4(@Param("sellerId")Integer sellerId);

    @Select("select * from goodorder where sellerId=#{sellerId} and stmt = 5")
    List<Order> getOrderListOfStatement5(@Param("sellerId")Integer sellerId);

    @Select("select * from goodorder where sellerId=#{sellerId} and stmt = 6")
    List<Order> getOrderListOfStatement6(@Param("sellerId")Integer sellerId);

    @Select("select * from goodorder where sellerId=#{sellerId} and (stmt = -1 or stmt = -2)")
    List<Order> getOrderListOfStatement_1(@Param("sellerId")Integer sellerId);

    @Select("select sellerId from good where goodId = #{goodId}")
    Integer getSellerIdByGoodId(@Param("goodId")Integer goodId);

    @Update("update goodorder set stmt = 4 where orderId = #{goodorder.orderId}")
    Long completeStocking(@Param("goodorder") Order goodorder);

    /**
     * 买家取消订单
     */
    @Update("update goodorder set stmt=-2 where orderId=#{orderId}")
    Long sellerCancelOrder(@Param("orderId")Integer orderId);

    /**
     * 售后
     */
    @Select("select orderId from postsale where sellerId=#{seller.sellerId}")
    List<Integer> getOrderIdInPostSaleBySeller(@Param("seller")Seller seller);

    @Select("select * from postsale where orderId=#{orderId}")
    PostSale getPostSaleByOrderId(@Param("orderId")Integer orderId);

    @Select("select * from postsaleimage where postSaleId=#{postSaleId}")
    List<PostSaleImage> getPostSaleImageByPostSaleId(@Param("postSaleId")Integer postSaleId);
}
