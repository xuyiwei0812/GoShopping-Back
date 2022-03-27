package com.zjgsu.shopping.interior.Common.pojo.vo;

import com.zjgsu.shopping.interior.Common.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class OrderVo extends Order {
    /**
     * 订单编号
     * 买家编号
     * 卖家编号
     * 商品编号
     * 商品数量
     * 下单时间
     * 订单状态
     买家提出 --> 商家确认 --> 备货完成 --> 开始发货 --> 交易完成
     1           2          3          4          5
     特殊状态: 交易取消 6
     * 交易达成时间
     */

    private Integer orderId;
    private Integer buyerId;
    private Integer sellerId;
    private Integer goodId;
    private Integer number;
    private Integer stmt;
    private String phone;
    private Date startDate;
    private Date dealDate;
    private String trackingNumber;
    private Integer addressId;

    /**
     * 用于更改订单状态的简单声明
     */
    public OrderVo(Integer orderId, Integer orderStatement){
        this.orderId = orderId;
        this.stmt = orderStatement;
    }
}
