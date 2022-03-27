package com.zjgsu.shopping.controller.Common;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Common.pojo.vo.Response;
import com.zjgsu.shopping.interior.Common.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("api/common")
public class CommonOrder {

    @Resource
    OrderService orderService;

    @ResponseBody
    @PostMapping("/updateOrderStatement")
    public Response<Boolean> updateOrderStatement(@RequestBody com.zjgsu.shopping.interior.Common.pojo.Order order){
        try{
            return Response.createSuc(orderService.updateOrderStatement(order.getOrderId(),order.getStmt()));
        }catch(Exception e){
            return Response.BUG();
        }
    }

}
