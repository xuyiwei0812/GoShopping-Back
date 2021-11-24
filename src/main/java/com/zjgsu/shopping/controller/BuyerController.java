package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.pojo.vo.BuyerHistoryList;
import com.zjgsu.shopping.interior.Buyer.service.BuyerHistoryService;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.pojo.vo.Mode;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import com.zjgsu.shopping.Tool.Mytool;
import com.zjgsu.shopping.interior.Common.pojo.Good;
import com.zjgsu.shopping.interior.Common.pojo.Intention;
import com.zjgsu.shopping.interior.Common.pojo.vo.GoodList;
import com.zjgsu.shopping.interior.Common.pojo.vo.GoodwithImg;
import com.zjgsu.shopping.interior.Common.pojo.vo.Response;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.nio.file.attribute.AclEntryPermission;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/buyer")
public class BuyerController {
    @Resource
    private BuyerService buyerService;
    @Resource
    private Mytool tool;
    @Resource
    private BuyerHistoryService buyerHistoryService;

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
            if (buyerService.buyerRegister(buyer) == null)
                return Response.createErr("登录买家信息失败");
            else
                return Response.createSuc(buyer.getBuyerId());
        } catch (Exception e) {
            tool.soutErr("uploadBuyerInfo", e);
            return Response.BUG();
        }
    }


    /**
     * 提出一个意向
     *
     * @param intention xx
     *                  intention.goodId 商品id
     *                  intention.buyerId 卖家id
     * @return 成功返回意向编号, 失败....
     * 注:在登录一个意向的时候需要调用两个接口,后调用这个,然后拿着上面那个返回的id来调用这个
     */
    @ResponseBody
    @PostMapping("/raiseIntention")
    public Response<Integer> raiseIntention(@RequestBody Intention intention) {
        try {
            if (buyerService.raiseIntention(intention))
                return Response.createSuc(intention.getIntentionId());
            else return Response.createErr("意向添加失败");
        } catch (Exception e) {
            tool.soutErr("raiseIntention", e);
            return Response.BUG();
        }
    }


    @ResponseBody
    @PostMapping("/getAllGoodListFB")
    public Response<GoodList> getAllGoodListFB() {
        try {
            return Response.createSuc(tool.toGoodList(buyerService.getAllGoodListForBuyers()));
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
    @PostMapping("/getBuyerHistoryByBuyerId")
    public Response<BuyerHistoryList> getBuyerHistoryByBuyerId(@RequestBody Buyer buyer){
        try {
            BuyerHistoryList list = tool.toBuyerHistoryList(buyerHistoryService.getBuyerHistory(buyer.getBuyerId()));
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
    @GetMapping("/searchGood")
    public Response<GoodList> searchGood(@Param("q") String q){
        System.out.println("收到了一个搜索的请求");
        System.out.println("q"+q);

        return null;
    }


}