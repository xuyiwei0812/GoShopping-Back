package com.zjgsu.shopping.interior.Common.pojo.vo;

import com.zjgsu.shopping.interior.Common.pojo.Good;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
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
    private String trackingNumber;
    private Integer addressId;

    private Integer storage;
    private Integer class1;
    private Integer class2;
    private Double goodPrice;
    private String goodName;
    private String img;
    private String description;

    //此处有null 要改

    public OrderBrief(Order order, Good good ,String img){
        this.orderId = order.getOrderId();
        this.stmt = order.getStmt();
        this.goodId = order.getGoodId();
        this.buyerId = order.getBuyerId();
        this.number = order.getNumber();
        this.sellerId = order.getSellerId();
        this.trackingNumber = order.getTrackingNumber();
        this.addressId = order.getAddressId();
        this.storage = good.getStorage();
        this.class2 = good.getClass2();
        this.goodPrice = good.getGoodPrice();
        this.goodName = good.getGoodName();
        this.description = good.getDescription();

        this.img = img;
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderList {
    List<OrderBrief> orderList = new ArrayList<>();



    public void AddItem(Order order,Good good ,String img){
        System.out.println("old order" + order);
        OrderBrief ord = new OrderBrief(order ,good ,img);
        System.out.println("new order = " + ord);
        orderList.add(ord);
    }

}
