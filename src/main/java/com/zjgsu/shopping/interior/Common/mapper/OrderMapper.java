package com.zjgsu.shopping.interior.Common.mapper;

import com.zjgsu.shopping.interior.Common.pojo.Account;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.data.relational.core.sql.In;

import java.util.function.BooleanSupplier;

@Mapper
public interface OrderMapper {

    /**
     * 订单状态
     买家提出 --> 商家确认 --> 备货完成 --> 开始发货 --> 交易完成
     1           2          3          4          5
     特殊状态: 交易取消 6
     */

    @Options(useGeneratedKeys = true, keyProperty = "order.orderId", keyColumn = "orderId")
    @Insert("insert into order (buyerId,sellerId,goodId,number,statement,phone,startDate,dealDate) " +
                        "values(#{order.buyerId},#{order.sellerId},#{order.goodId},#{order.number},1,#{order.phone},#{order.startDate},null})")
    Boolean placeAnOrder(@Param("order") Order order);

    @Select("select * from order where orderId = #{orderId}")
    Order getOrder(@Param("orderId") Integer orderId);

    @Select("select statement from order where orderId = #{orderId}")
    Integer getOrderStatement(@Param("orderId") Integer orderId);

    @Update("update order set statement = #{order.statement} where orderId = #{order.orderId}")
    Long updateOrderStatement(@Param("order")Order order);



}
