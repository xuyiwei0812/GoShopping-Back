package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.pojo.*;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.BuyerService;
import com.zjgsu.shopping.service.SellerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//Attention : 增删改查只有查是get
@Controller
@CrossOrigin(origins = "*",maxAge = 3600)

@RequestMapping("/api")
public class UserContorller {
    @Resource
    private SellerService sellerService;

    @Resource
    private BuyerService buyerService;

    @ResponseBody
    @PostMapping("/register")
    public Response<Integer> register(@RequestBody Seller seller){
        System.out.println("收到一条注册请求,注册账号为 " + seller.getAccount() +",密码为" + seller.getPassword());
        String msg = "";
        if(sellerService.searchAccount(seller.getAccount())){
            msg = "账号已经存在";
        } else if(seller.getPassword().length()<6 || seller.getPassword().length()>12) {
            msg = "密码长度必须大于等于6位，小于等于12位";
        }else if(!seller.getPassword().matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{1,}$"))
            msg = "必须至少有1个数字、1个大写字母、1个小写字母和其它字符共同组成";

        sellerService.register(seller);
        Integer response = seller.getSellerId();
        System.out.println("注册成功");
        return Response.response(response,msg);
    }


    @ResponseBody
    @PostMapping("/login")
    public Response<Integer> login(@RequestBody AccountVo accountVo){
        System.out.println("接收到登录请求");
        System.out.println(accountVo);
        Integer response = sellerService.login(accountVo.getAccount(),accountVo.getPassword());
        System.out.println(response);

        if(response != -1)
            return Response.createSuc(response);
        else
            return Response.createErr("账号不存在或者密码错误");
    }

    @ResponseBody
    @PostMapping("/updateSellerInfo")
    public Response<String> updateSellerInfo(@RequestBody Seller seller){
        if(sellerService.updateInfo(seller))
            return Response.createSuc("success");
        else
            return Response.createErr("fail");
    }


    @ResponseBody
    @PostMapping ("/putOnGood")
    public Response<Integer> putOnGood(@RequestBody Good good){
        if(sellerService.putOnGood(good) != null)
            return Response.createSuc(sellerService.putOnGood(good).getGoodId());
        else
            return Response.createErr("提交商品失败");
    }

    @ResponseBody
    @PostMapping("/putOffGood")
    public Response<String> putOffGood(@RequestBody Good good){
        if(sellerService.putOffGood(good.getGoodId()))
            return Response.createSuc("下架成功");
        else
            return Response.createErr("下架失败,请稍后重试");
    }


    @ResponseBody
    @PostMapping("/startBusiness")
    public Response<String> startBusiness(@RequestBody Deal deal){
        if(sellerService.startDeal(deal))
            return Response.createSuc("交易开始,商品已经冻结");
        else
            return Response.createErr("交易创建失败,请重试");
    }

    @ResponseBody
    @PostMapping("/getDealHistfoyListByGoodId")
    public Response<DealHistoryList> getDealHistoryByGoodId(@RequestBody Good good){
        DealHistoryList li = sellerService.getDealHistoryByGoodId(good.getGoodId());
        if(li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);

    }
    @ResponseBody
    @PostMapping("/getGoodInfo")
    public Response<GoodwithImg> getGoodInfo(@RequestBody Good good){
        System.out.println("收到对特定商品信息的查询,商品id为" + good.toString());
        GoodwithImg re = sellerService.getGoodInfo(good.getGoodId());
        if(re == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(re);
    }

    @ResponseBody
    @PostMapping("/getDealHistoryBySellerId")
    public Response<DealHistoryList> getDealHistoryBySellerId(@RequestBody Seller seller) {
        System.out.println("收到查询特定卖家的交易历史");
        DealHistoryList li = sellerService.getDealHistoryListBySellerId(seller.getSellerId());
        if(li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);
    }

    @ResponseBody
    @GetMapping("/getGoodListBySellerId")
    public Response<GoodList> getGoodListBySellerId(@Param("sellerId") Integer sellerId){
        System.out.println("收到对于特定卖家商品信息的查询");
        GoodList li = buyerService.getGoodListBySellerId(sellerId);
        if(li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);
    }

    @ResponseBody
    @GetMapping("/getAllGoodList")
    public Response<GoodList> getAllGoodList(){
        System.out.println("收到一个获取全部商品信息的请求");
        GoodList li = buyerService.getAllGoodList();
        if(li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);
    }

    @ResponseBody
    @PostMapping("/updatePassword")
    public Response<Long> updatePassword(@RequestBody AccountVo accountVo){
        System.out.println("收到一个修改密码的请求");
        Long re = sellerService.updatePassword(accountVo.getUserId(),accountVo.getPassword(),accountVo.getNewPassword());
        if(re == -2)return Response.createErr("密码错误");
        else if(re == 0)return Response.createErr("无此账号");
        else return Response.createSuc(re);
    }
    @ResponseBody
    @GetMapping("/setBuyerInfo")
    public Response<Buyer> setBuyerInfo(@Param("name") String name ,@Param("location") String location ,@Param("phone") String phone){
        Buyer buyer = buyerService.createBuyer(name,location,phone);
        if(buyer == null) return Response.createErr("创建账号失败");
        else return Response.createSuc(buyer);
    }


    @ResponseBody
    @PostMapping("/getIntentionListByGoodId")
    public Response<IntentionList> getIntentionListByGoodId(@RequestBody Good good){
        System.out.println("收到查询商品意向购买人的请求");
        if(good.getGoodId() == null) return Response.createErr("参数错误");
        System.out.println("商品id为" + good.getGoodId().toString());
        IntentionList li = sellerService.getIntentionListByGoodId(good.getGoodId());
        if(li == null) return Response.createErr("查询失败");
        else return Response.createSuc(li);
    }



    @ResponseBody
    @GetMapping("/test")
    public int test(@Param("x") int x){
        return x + 1;
    }

}
