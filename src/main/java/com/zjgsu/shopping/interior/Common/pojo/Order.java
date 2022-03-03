package com.zjgsu.shopping.interior.Common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import javax.swing.table.DefaultTableCellRenderer;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
     * 订单编号
     * 买家编号
     * 卖家编号
     * 商品编号
     * 商品数量
     * 下单时间
     * 订单状态
        买家提出 --> 买家完成支付 -- >商家确认 --> 备货完成 --> 开始发货 --> 交易完成
                1           2          3          4          5          6
                                                             |在此之前客户、商家可以取消
        特殊状态: 交易取消 7
     * 交易达成时间
     */

    private Integer orderId;
    private Integer buyerId;
    private Integer sellerId;
    private Integer goodId;
    private Integer number;
    private Integer statement;
    private String phone;
    private Date startDate;
    private Date dealDate;

    /**
     * 用于更改订单状态的简单声明
     */
    public  Order(Integer orderId,Integer orderStatement){
        this.orderId = orderId;
        this.statement = orderStatement;
    }


}
