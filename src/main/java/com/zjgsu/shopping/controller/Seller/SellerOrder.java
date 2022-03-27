package com.zjgsu.shopping.controller.Seller;

import com.zjgsu.shopping.Tool.Mytool;
import com.zjgsu.shopping.interior.Common.pojo.Good;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import com.zjgsu.shopping.interior.Common.pojo.PostSale;
import com.zjgsu.shopping.interior.Common.pojo.vo.OrderList;
import com.zjgsu.shopping.interior.Common.pojo.vo.OrderVo;
import com.zjgsu.shopping.interior.Common.pojo.vo.Response;
import com.zjgsu.shopping.interior.Common.pojo.vo.Statement;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import com.zjgsu.shopping.interior.Seller.service.SellerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口前缀  /api/seller/order
 *
 *
 */


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/seller")
public class SellerOrder {

    @Resource
    private SellerService sellerService;
    @Resource
    private Mytool tool;

    /**
     * 2 -> 3
     * @param order order
     *              orderId
     */
    @ResponseBody
    @PostMapping("/acceptTheOrder")
    public Response<Boolean> acceptTheOrder(@RequestBody OrderVo order) {
        try{
            return Response.createSuc(sellerService.acceptTheOrder(order));
        }catch (Exception e){
            tool.soutErr("acceptTheOrder" ,e) ;
            return Response.BUG();
        }
    }

    /**
     * 3 -> 4
     * orderId
     */
    @ResponseBody
    @PostMapping("/completeStocking")
    public Response<Boolean> completeStocking(@RequestBody OrderVo order){
        try{
            return Response.createSuc(sellerService.completeStocking(order));
        }catch (Exception e){
            tool.soutErr("/seller/completeStocking",e);
            return Response.BUG();
        }
    }

    /**
     * 4 -> 5
     * orderId
     */
    @ResponseBody
    @PostMapping("/deliverTheGoods")
    public Response<Boolean> deliverTheGoods(@RequestBody OrderVo order){
        try{
            return Response.createSuc(sellerService.deliverTheGoods(order));
        }catch (Exception e){
            return Response.BUG();
        }
    }

    /**
     * 取消一场交易
     * x -> -2
     * @param order order
     *              orderId
     */
    @ResponseBody
    @PostMapping("/cancelTheOrder")
    public Response<Boolean> cancelTheOrder(@RequestBody OrderVo order) {
        try{
            return Response.createSuc( sellerService.cancelTheOrder(order.getOrderId()));
        }catch (Exception e){
            tool.soutErr("cancelDeal",e );
            return  Response.BUG();
        }
    }

    /**
     *
     * @param seller seller
     *        sellerId
     *
     * -1 + -2
     */
    @ResponseBody
    @PostMapping("/getHistoryBySellerId")
    public Response<OrderList> getHistoryBySellerId(@RequestBody Seller seller){
        try {
            return Response.createSuc(tool.toOrderList(sellerService.getHistoryOrderListBySellerId(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getSellerHistoryBySellerId" ,e);
            return Response.BUG();
        }
    }

    /**
     * 通过商品id拿商品意向列表
     *
     * getIntentionListByGoodId
     * @param good xx
     *             good.goodId 商品id
     *  -> 2
     */
    @ResponseBody
    @PostMapping("/getWillingOrderByGood")
    public Response<OrderList> getIntentionListByGoodId(@RequestBody Good good) {
        System.out.println(good.getGoodId());
        try {
            return Response.createSuc(tool.toOrderList(sellerService.getWillingOrderListByGoodId(good.getGoodId())));
        }catch (Exception e){
            tool.soutErr("getIntentionListByGoodId",e );
            return  Response.BUG();
        }
    }


    /**
     * sellerId
     * statement
     */
    @ResponseBody
    @PostMapping("/getOrderListOfStatement")
    public Response<OrderList> getOrderListOfStatement(@RequestBody Statement st){
        try{
            Integer code = st.getStatement();Integer sellerId = st.getSellerId();
            List<com.zjgsu.shopping.interior.Common.pojo.Order> li = new ArrayList<>();
            if(code == 1)        li = sellerService.getOrderListOfStatement1(sellerId);
            else if (code == 2)  li = sellerService.getOrderListOfStatement2(sellerId);
            else if (code == 3)  li = sellerService.getOrderListOfStatement3(sellerId);
            else if (code == 4)  li = sellerService.getOrderListOfStatement4(sellerId);
            else if (code == 5)  li = sellerService.getOrderListOfStatement5(sellerId);
            else if (code == 6)  li = sellerService.getOrderListOfStatement6(sellerId);
            else if (code == -1) li = sellerService.getOrderListOfStatement_1(sellerId);
            return Response.createSuc(tool.toOrderList(li));
        }catch (Exception e){
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getTrackingNumber")
    public Response<String> getTrackingNumber(@RequestBody Integer orderId){
        try{
            return Response.createSuc(sellerService.getTrackingNumber(orderId));
        }catch (Exception e){
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getOrderIdInPostSaleBySeller")
    public Response<List<Integer>> getOrderIdInPostSaleBySeller(@RequestBody Seller seller) {
        try {
            System.out.println("sellerId "+seller.getSellerId());
            return Response.createSuc(sellerService.getOrderIdInPostSaleBySeller(seller));
        }catch (Exception e){
            tool.soutErr("getOrderIdInPostSaleBySeller",e );
            return  Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getPostSaleByOrderId")
    public Response<PostSale> getPostSaleByOrderId(@RequestBody Order order){
        try {
            System.out.println("orderId "+order.getOrderId());
            return Response.createSuc(sellerService.getPostSaleByOrderId(order.getOrderId()));
        }catch (Exception e){
            tool.soutErr("getPostSaleByOrderId",e);
            return  Response.BUG();
        }
    }

}
