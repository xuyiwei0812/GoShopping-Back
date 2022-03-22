package com.zjgsu.shopping.interior.Common.service.impl;

import com.zjgsu.shopping.interior.Common.mapper.OrderMapper;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import com.zjgsu.shopping.interior.Common.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;


    @Override
    public Boolean updateOrderStatement(Integer orderId, Integer orderStatement) {
       return orderMapper.updateOrderStatement(new Order(orderId,orderStatement)) > 0;
    }

    @Override
    public List<Object> getDealHistfoyListByGoodId(Integer goodId) {
        return null;
    }

    @Override
    public Integer getOrderStatement(Integer orderId) {
        return orderMapper.getOrderStatement(orderId);
    }

    @Override
    public Boolean updateOrderStatement(Order order) {
       return orderMapper.updateOrderStatement(order) > 0;
    }

}
