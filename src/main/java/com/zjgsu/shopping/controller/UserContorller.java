package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.pojo.Deal;
import com.zjgsu.shopping.pojo.DealHistory;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.Seller;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.SellerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//Attention : 增删改查只有查是get
@Controller
@CrossOrigin(origins = "*")


@RequestMapping("/api")
public class UserContorller {
    @Resource
    private SellerService sellerService;


    @ResponseBody
    @PostMapping("/register")
    public Response<Integer> register(@RequestBody Seller seller){
        if(sellerService.searchAccount(seller.getAccount()))return Response.createErr("账号已经存在");
        if(seller.getPassword().length()<6 || seller.getPassword().length()>12) return Response.createErr("密码长度必须大于等于6位，小于等于12位");
        String regex="^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{1,}$";
        if(seller.getPassword().matches(regex)==false) return Response.createErr("必须至少有1个数字、1个大写字母、1个小写字母和其它字符共同组成");
        sellerService.register(seller);
        Integer response = seller.getSellerId();
        return Response.createSuc(response);
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
    @GetMapping("/getAllGoodForSale")
    public Response<GoodList> getAllGoodForSale(){
       GoodList goodlist =  sellerService.getAllGoodList();
       if(goodlist == null) return Response.createErr("查询失败");
       else return Response.createSuc(goodlist);
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
    @PostMapping("/getGoodForHistoryList")
    public Response<DealHistoryList> getGoodForHistoryList(@RequestBody DealHistory good){
        DealHistoryList li = sellerService.getDealHistoryByGoodId(good.getGoodId());
        if(li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);

    }
    @ResponseBody
    @PostMapping("/getGoodInfo")
    public Response<Good> getGoodInfo(@RequestBody Good good){
        Good re = sellerService.getGoodInfo(good.getGoodId());
        if(re == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(re);
    }



    @ResponseBody
    @GetMapping("/test")
    public int test(@Param("x") int x){
        return x + 1;
       // sellerService.startDeal(1,2,3);
       // return 1;
    }

}
