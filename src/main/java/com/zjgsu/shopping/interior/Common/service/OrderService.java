package com.zjgsu.shopping.interior.Common.service;


import java.util.List;

public interface OrderService {

    Boolean updateOrderStatement(Integer orderId,Integer orderStatement);

    List<Object> getDealHistfoyListByGoodId(Integer goodId);
}
