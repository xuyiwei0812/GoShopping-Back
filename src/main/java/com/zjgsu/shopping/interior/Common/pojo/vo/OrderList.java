package com.zjgsu.shopping.interior.Common.pojo.vo;

import com.zjgsu.shopping.interior.Common.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
class OrderBrief{
    private Integer orderId;
    private Integer buyerId;
    private Integer sellerId;
    private Integer goodId;
    private Integer number;
    private Integer stmt;
    private String phone;
    private Date startDate;
    private Date dealDate;

    public OrderBrief(Order order){
        this.orderId = order.getOrderId();
        this.stmt = order.getStmt();
        this.dealDate = order.getDealDate();
        this.goodId = order.getGoodId();
        this.buyerId = order.getBuyerId();
        this.phone = order.getPhone();
        this.number = order.getNumber();
        this.sellerId = order.getSellerId();
        this.startDate = order.getStartDate();
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderList {
    List<OrderBrief> orderList = new ArrayList<>();

//    public void AddItem(Integer goodId, Double price, String name, Date dealDate, String img) {
//        orderList.add(new DealHistoryBrief(goodId, price, name, dealDate, img));
//    }

    public void AddItem(Order order){
        orderList.add(new OrderBrief(order));
    }
}
