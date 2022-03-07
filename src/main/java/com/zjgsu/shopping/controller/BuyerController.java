package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import com.zjgsu.shopping.interior.Common.pojo.vo.*;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import com.zjgsu.shopping.Tool.Mytool;
import com.zjgsu.shopping.interior.Common.pojo.Good;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/buyer")
public class BuyerController {
    @Resource
    private BuyerService buyerService;
    @Resource
    private Mytool tool;


    /**
     * 登录买家信息
     *
     * @param buyer xx
     *              buyer.name 买家名称
     *              buyer.location 买家地址
     *              buyer.phone 买家电话
     * @return 成功返回买家id, 失败....
     * 注:在登录一个意向的时候需要调用两个接口,先调用这个,然后拿着这个返回的id去调用下面的那个
     */
    @ResponseBody
    @PostMapping("/uploadBuyerInfo")
    public Response<Integer> uploadBuyerInfo(@RequestBody Buyer buyer) {
        try {
            System.out.println("1221"+buyer);
            if (buyerService.buyerRegister(buyer) == null)
                return Response.createErr("登录买家信息失败");
            else
                return Response.createSuc(buyer.getBuyerId());
        } catch (Exception e) {
            tool.soutErr("uploadBuyerInfo", e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/changeBuyerInfo")
    public Response<Boolean> changeBuyerInfo(@RequestBody Buyer buyer){
        try {
            System.out.println("修改买家信息");
            return Response.createSuc(buyerService.updateBuyerInfo(buyer));
        } catch (Exception e) {
            tool.soutErr("uploadBuyerInfo", e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/checkBuyerPassword")
    public Response<Object> checkBuyerPassword(@RequestBody AccountVo accountVo) {
        try {
            if(buyerService.checkBuyerPassword(accountVo.getUserId(), accountVo.getPassword()))
                return Response.createSuc(null);
            else
                return Response.createErr("账号不存在或者密码错误");
        }catch (Exception e){
            tool.soutErr("checkSellerPassword" ,e);
            return  Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/updateBuyerPassword")
    public Response<Object> updateBuyerPassword(@RequestBody AccountVo accountVo) {
        try {
            System.out.println(accountVo.getUserId()+" "+accountVo.getPassword()+" "+accountVo.getNewPassword());
            Long re = buyerService.updateBuyerPassword(accountVo.getUserId(), accountVo.getPassword(), accountVo.getNewPassword());
            if (re == -2) return Response.createErr("密码错误");
            else if (re == 0) return Response.createErr("无此账号");
            else return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("updateSellerPassword" ,e);
            return  Response.BUG();
        }
    }

    /**
     * 提出一个订单
     *
     * 改自raiseIntention
     *

     * @return 成功返回意向编号, 失败....
     * 注:在登录一个意向的时候需要调用两个接口,后调用这个,然后拿着上面那个返回的id来调用这个
     */
    @ResponseBody
    @PostMapping("/placeAnOrder")
    public Response<Integer> placeAnOrder(@RequestBody OrderVo order) {
        try {
            if (buyerService.placeAnOrder(order))
                return Response.createSuc(order.getOrderId());
            else return Response.createErr("意向添加失败");
        } catch (Exception e) {
            tool.soutErr("raiseIntention", e);
            return Response.BUG();
        }
    }


    //FB : for buyer
    @ResponseBody
    @PostMapping("/getAllGoodListFB")
    public Response<GoodList> getAllGoodListFB() {
        try {
            //System.out.println("1"+buyerService.getAllGoodListForBuyers());
            GoodList goodList = tool.toGoodList(buyerService.getAllGoodListForBuyers());
            //System.out.println("2"+goodList);
            return Response.createSuc(goodList);
        } catch (Exception e) {
            tool.soutErr("getAllGoodListFB", e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getUnfrozenGoodListFB")
    public Response<GoodList> getUnfrozenGoodListFB(){
        try {
            return Response.createSuc(tool.toGoodList(buyerService.getUnfrozenGoodListForBuyers()));
        }catch (Exception e){
            tool.soutErr("getUnforzenGoodFB" , e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getAllGoodListBySellerIdFB")
    public Response<GoodList> getAllGoodListBySellerIdFB(@RequestBody Seller seller){
        try{
            return Response.createSuc(tool.toGoodList(buyerService.getAllGoodListBySellerIdForBuyers(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getAllGoodListBySellerIdFB" , e);
            return Response.BUG();

        }
    }

    @ResponseBody
    @PostMapping("/getUnfrozenGoodListBySellerIdFB")
    public Response<GoodList> getUnfrozenGoodListBySellerIdFB(@RequestBody Seller seller){
        try{
            return Response.createSuc(tool.toGoodList(buyerService.getUnfrozenGoodListBySellerIdForBuyers(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getAllGoodListBySellerIdFB" , e);
            return Response.BUG();
        }

    }

    /**
     * 通过商品id取得商品具体信息
     *
     * @param good xx
     *             good.goodId 商品编号
     * @return 成功返回商品详细信息, 失败返回信息
     */
    @ResponseBody
    @PostMapping("/getGoodInfo")
    public Response<GoodwithImg> getGoodInfo(@RequestBody Good good) {
        try {
            GoodwithImg re = buyerService.getGoodInfo(good.getGoodId());
            if (re == null)
                return Response.createErr("查询失败");
            else
                return Response.createSuc(re);

        } catch (Exception e) {
            tool.soutErr("getGoodInfo" , e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getVideoByGood")
    public Response<String> getVideoByGood(@RequestBody Good good){
        try{
            return Response.createSuc(buyerService.getVideoByGood(good));
        }
        catch (Exception e){
            System.out.println("发生错误" + e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getBuyerHistoryByBuyerId")
    public Response< OrderList> getBuyerHistoryByBuyerId(@RequestBody Buyer buyer){
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
    @PostMapping("/getClass2GoodListByClassId")
    public Response<GoodList> getClass2GoodListByClassId(@RequestBody Mode mode){
        try{
            System.out.println(mode);
            System.out.println("收到一个查类2");
            GoodList list = tool.toGoodList(buyerService.getClass2GoodListByClassId(mode));
            System.out.println(list);
            return Response.createSuc(list);
        }catch (Exception e){
            tool.soutErr("getClass2GoodListByClassId" ,e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getClass1GoodListByClassId")
    public Response<GoodList> getClass1GoodListByClassId(@RequestBody Mode mode){
        try{
            System.out.println(mode);
            GoodList list = tool.toGoodList(buyerService.getClass1GoodListByClassId(mode));
            return Response.createSuc(list);
        }catch (Exception e){
            tool.soutErr("getClass1GoodListByClassId" ,e);
            return Response.BUG();
        }
    }


    @ResponseBody
    @PostMapping("/searchGood")
    public Response<GoodList> searchGood(@RequestBody String keyword0){
        try {
            System.out.println("收到了一个搜索的请求");
            Integer begin=0,end=0;
            begin=keyword0.indexOf(":");
            end=keyword0.lastIndexOf(",");
            String keyword=keyword0.substring(begin+2,end-1);
            System.out.println("keyword:" + keyword);
            Integer len = keyword.length();
            if(keyword.substring(len-1).equals("=")){
                System.out.println("=");
                keyword=keyword.substring(0, len-1);
            }
            System.out.println("now keyword:"+keyword);
            List<Good> goodList = buyerService.searchGood(keyword);
            GoodList goodList1 = tool.toGoodList(goodList);
            return Response.createSuc(goodList1);
        }
        catch (Exception e){
            tool.soutErr("searchGood" ,e);
            return Response.BUG();
        }
    }
    @ResponseBody
    @PostMapping("/getOrderListOfStatement")
    public Response<OrderList> getOrderListOfStatement(@RequestBody Statement st){
        try{
            Integer code = st.getStatement();Integer buyerId = st.getBuyerId();
            List<Order> li = new ArrayList<>();
            if(code == 1)li = buyerService.getOrderListOfStatement1(buyerId);
            else if (code == 2) li = buyerService.getOrderListOfStatement2(buyerId);
            else if (code == 5) li = buyerService.getOrderListOfStatement5(buyerId);
            else if (code == 6) li = buyerService.getOrderListOfStatement6(buyerId);
            else if (code == -1) li = buyerService.getOrderListOfStatement_1(buyerId);
            return Response.createSuc(tool.toOrderList(li));
        }catch (Exception e){
            return Response.BUG();
        }
    }

}
