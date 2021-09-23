package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.mapper.BusinessMapper;
import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.pojo.GoodForSale;
import com.zjgsu.shopping.pojo.Seller;
import com.zjgsu.shopping.pojo.vo.AccountVo;
import com.zjgsu.shopping.pojo.vo.Response;
import com.zjgsu.shopping.pojo.vo.SellerVo;
import com.zjgsu.shopping.service.SellerService;
import com.zjgsu.shopping.service.impl.SellerServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

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
        sellerService.putOnGood(good);
        return Response.createSuc(123);
    }


//    public Response<Integer> login (@RequestBody GoodForSale good) {
//        sellerService.putOnGood(good);
//        String username = map.get ("username");
//        String password = map.get ("password");
//        User user = userService.getUserByUsername (username);
//        if (user != null) {
//            if (bcryptService.checkPassword (password,user.getPassword ())) {
//                return Response.createSuc (user.getId ());
//            }
//        }
//        return Response.createErr ("用户名或密码错误!");
//    }

    @ResponseBody
    @GetMapping("/test")
    public int test(@Param("x") int x){
        return x + 1;
       // sellerService.startDeal(1,2,3);
       // return 1;
    }

}
