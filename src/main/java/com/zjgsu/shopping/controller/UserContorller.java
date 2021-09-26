package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.mapper.BusinessMapper;
import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.pojo.GoodForHistory;
import com.zjgsu.shopping.pojo.GoodForSale;
import com.zjgsu.shopping.pojo.Seller;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.SellerService;
import com.zjgsu.shopping.service.impl.SellerServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

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
        Integer response = sellerService.register(seller).getSellerId();
        return Response.createSuc(response);
    }


    @ResponseBody
    @PostMapping("/login")
    public Response<Integer> login(@RequestBody AccountVo accountVo){
        Integer response = sellerService.login(accountVo.getAccount(),accountVo.getPassword());
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
    public Response<Integer> putOnGood(@RequestBody GoodForSale good){
        if(sellerService.putOnGood(good) != null)
            return Response.createSuc(sellerService.putOnGood(good).getGoodId());
        else
            return Response.createErr("提交商品失败");
    }

    @ResponseBody
    @PostMapping("/putOffGood")
    public Response<String> putOffGood(@RequestBody GoodForSale good){
        if(sellerService.putOffGood(good.getGoodId()))
            return Response.createSuc("下架成功");
        else
            return Response.createErr("下架失败,请稍后重试");
    }

    @ResponseBody
    @GetMapping("/getAllGoodForSale")
    public Response<GoodForSaleListVo> getAllGoodForSale(){
       GoodForSaleListVo goodlist =  sellerService.getAllGoodList();
       if(goodlist == null) return Response.createErr("查询失败");
       else return Response.createSuc(goodlist);
    }

    @ResponseBody
    @PostMapping("/startBusiness")
    public Response<String> startBusiness(@RequestBody Business business){
        if(sellerService.startDeal(business))
            return Response.createSuc("交易开始,商品已经冻结");
        else
            return Response.createErr("交易创建失败,请重试");
    }

    @ResponseBody
    @PostMapping("/getGoodForHistoryList")
    public Response<GoodForHistoryListVo> getGoodForHistoryList(@RequestBody GoodForHistory good){
        GoodForHistoryListVo li = sellerService.getGoodForHistoryList(good.getGoodId());
        if(li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);

    }


    @ResponseBody
    @GetMapping("/test")
    public int test(@Param("x") int x){
        return x + 1;
       // sellerService.startDeal(1,2,3);
       // return 1;
    }

}
