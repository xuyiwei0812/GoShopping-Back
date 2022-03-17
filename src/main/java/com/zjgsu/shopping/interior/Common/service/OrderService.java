package com.zjgsu.shopping.interior.Common.service;


import com.zjgsu.shopping.interior.Common.pojo.Order;

import java.util.List;

public interface OrderService {

    Boolean updateOrderStatement(Integer orderId,Integer orderStatement);

    List<Object> getDealHistfoyListByGoodId(Integer goodId);
    
    Integer getOrderStatement(Integer orderId);

    Boolean updateOrderStatement(Order order);

}
