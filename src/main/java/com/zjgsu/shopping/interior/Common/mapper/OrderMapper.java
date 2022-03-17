package com.zjgsu.shopping.interior.Common.mapper;

import com.zjgsu.shopping.interior.Common.pojo.Account;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.data.relational.core.sql.In;

import java.util.List;
import java.util.function.BooleanSupplier;

@Mapper
public interface OrderMapper {

    /**
     * 订单状态
     买家提出 --> 商家确认 --> 备货完成 --> 开始发货 --> 交易完成
     1           2          3          4          5
     特殊状态: 交易取消 6
     */

    @Options(useGeneratedKeys = true, keyProperty = "goodorder.orderId", keyColumn = "orderId")
    @Insert("insert into goodorder (buyerId,sellerId,goodId,number,stmt,phone,startOrderDate,dealOrderDate) " +
                        "values(#{goodorder.buyerId},#{goodorder.sellerId},#{goodorder.goodId},#{goodorder.number},2,#{goodorder.phone},#{goodorder.startDate},null)")
    Boolean placeAnOrder(@Param("goodorder") Order goodorder);

    @Select("select * from goodorder where orderId = #{orderId}")
    Order getOrder(@Param("orderId") Integer orderId);

    @Select("select stmt from goodorder where orderId = #{orderId}")
    Integer getOrderStatement(@Param("orderId") Integer orderId);

    @Update("update goodorder set stmt = #{goodorder.stmt} where orderId = #{goodorder.orderId}")
    Long updateOrderStatement(@Param("goodorder")Order goodorder);



    @Select("select * from goodorder where sellerId = #{sellerId}")
    List<Order> getOrderListBySellerId(@Param("sellerId") Integer sellerId);

    @Select("select * from goodorder where buyerId = #{buyerId}")
    List<Order> getOrderListByBuyerId(@Param("buyerId") Integer buyerId);

    @Select("select * from goodorder where sellerId = #{sellerId} and stmt = 5")
    List<Order> getFinishedOrderListBySellerId(@Param("sellerId") Integer sellerId);

    @Select("select * from goodorder where buyerId  = #{buyerId}  and stmt = 5")
    List<Order> getFinishedOrderListByBuyerId(@Param("buyerId") Integer buyerId);


    @Select("select * from goodorder where sellerId = #{sellerId}  and stmt = 1")
    List<Order> getWillingOrderListBySellerId(@Param("sellerId") Integer sellerId);


    @Select("select * from goodorder where goodId = #{goodId}  and stmt = 1")
    List<Order> getWillingOrderListByGoodId(@Param("goodId") Integer goodId);


    @Update("update goodorder set trackingNumber = #{goodorder.trackingNumber}  , stmt = 5 where orderId = #{goodorder.orderId}")
    Long setTrackingNumber(@Param("goodorder") Order goodorder);


    @Select("select trackingNumber from goodorder where orderId = #{orderId}")
    String getTrackingNumber(@Param("orderId") Integer orderId);


}
