package com.zjgsu.shopping.controller.Buyer;

import com.zjgsu.shopping.Tool.Mytool;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.pojo.Address;
import com.zjgsu.shopping.interior.Common.pojo.PostSale;
import com.zjgsu.shopping.interior.Common.pojo.vo.OrderList;
import com.zjgsu.shopping.interior.Common.pojo.vo.OrderVo;
import com.zjgsu.shopping.interior.Common.pojo.vo.Response;
import com.zjgsu.shopping.interior.Common.pojo.vo.Statement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 提交一个订单
 */
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/buyer")
public class BuyerOrder {
    @Resource
    private BuyerService buyerService;
    @Resource
    private Mytool tool;

    /**
     * x -> 1
     * 提交订单
     * @param order
     *  buyerId
     *  goodId
     *  number
     *  phone
     * @return orderId
     */
    @ResponseBody
    @PostMapping("/placeAnOrder")
    public Response<Integer> placeAnOrder(@RequestBody OrderVo order) {
        try {
            System.out.println("收到下单请求");
            System.out.println("order"+order);
            if (buyerService.placeAnOrder(order))
                return Response.createSuc(order.getOrderId());
            else return Response.createErr("提出订单失败");
        } catch (Exception e) {
            tool.soutErr("placeAnOrder", e);
            return Response.BUG();
        }
    }

    /**
     *  1 -> 2
     *  确认付款
     *  @param order
     *      orderId
     */
    @ResponseBody
    @PostMapping("/finishThePayment")
    public Response<Boolean> finishThePayment(@RequestBody OrderVo order){
        try{
            return Response.createSuc(buyerService.finishThePayment(order));
        }catch (Exception e){
            tool.soutErr("finishThePayment" , e);
            return Response.BUG();
        }
    }


    /**
     * 买家确认收货
     *  5 -> 6
     * @param order
     *      orderId
     */
    @ResponseBody
    @PostMapping("/ConfirmReceipt")
    public Response<Boolean> ConfirmReceipt(@RequestBody OrderVo order){
        try {
            return Response.createSuc(buyerService.ConfirmReceipt(order.getOrderId()));
        }
        catch (Exception e){
            tool.soutErr("buyerConformReceipt",e);
            return Response.BUG();
        }
    }

    /**
     * 买家取消订单
     * x -> -1
     * @param order
     *      orderId
     */
    @ResponseBody
    @PostMapping("/buyerCancelOrder")
    public Response<Boolean> buyerCancelOrder(@RequestBody OrderVo order){
        try {
            return Response.createSuc(buyerService.CancelOrder(order.getOrderId()));
        }
        catch (Exception e){
            tool.soutErr("buyerCancelOrder",e);
            return Response.BUG();
        }
    }


    /**
     * 买家查询某状态的订单
     * @param st st
     *           buyerId
     *           statement
     *   1 ->  1
     *   2 ->  2 + 3 + 4
     *   5 ->  5
     *   6 ->  6
     *  -1 -> -1 + -2
     */

    @ResponseBody
    @PostMapping("/getOrderListOfStatement")
    public Response<OrderList> getOrderListOfStatement(@RequestBody Statement st){
        try{
            Integer code = st.getStatement();
            Integer buyerId = st.getBuyerId();
            System.out.println("code"+code);
            System.out.println("buyerId"+buyerId);
            List<com.zjgsu.shopping.interior.Common.pojo.Order> li = new ArrayList<>();
            if      (code ==  1)  li = buyerService.getOrderListOfStatement1(buyerId);
            else if (code ==  2)  li = buyerService.getOrderListOfStatement2(buyerId);
            else if (code ==  5)  li = buyerService.getOrderListOfStatement5(buyerId);
            else if (code ==  6)  li = buyerService.getOrderListOfStatement6(buyerId);
            else if (code == -1)  li = buyerService.getOrderListOfStatement_1(buyerId);
            System.out.println("li"+li);
            return Response.createSuc(tool.toOrderList(li));
        }catch (Exception e){
            return Response.BUG();
        }
    }

    /**
     *
     * 查买家的历史记录
     * @param buyer buyer
     *              buyerId
     * -> 6
     */

    @ResponseBody
    @PostMapping("/getHistoryByBuyerId")
    public Response<OrderList> getHistoryByBuyerId(@RequestBody Buyer buyer){
        try {
            System.out.println(buyer);
            System.out.println("1 "+buyer.getBuyerId());
            OrderList list = tool.toOrderList(buyerService.getBuyerHistory(buyer.getBuyerId()));
            return Response.createSuc(list);
        }catch (Exception e){
            tool.soutErr("getBuyerHistoryByBuyerId" ,e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/putForwardPostSaleRequest")
    public Response<Boolean> putForwardPostSaleRequest(@RequestBody PostSale postSale){
        try {
            System.out.println(postSale);
            System.out.println("postSale"+postSale);
            return Response.createSuc(buyerService.putForwardPostSaleRequest(postSale));
        }catch (Exception e){
            tool.soutErr("putForwardPostSaleRequest",e);
            return Response.BUG();
        }
    }


}
