package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.Tool.Mytool;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.pojo.Account;
import com.zjgsu.shopping.interior.Common.pojo.vo.AccountVo;
import com.zjgsu.shopping.interior.Common.pojo.vo.Response;
import com.zjgsu.shopping.interior.Common.service.AccountService;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import com.zjgsu.shopping.interior.Seller.service.SellerService;
//import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("api/common")
public class CommonController {
    @Resource
    SellerService sellerService;
    @Resource
    BuyerService buyerService;
    @Resource
    AccountService accountService;
    @Resource
    Mytool tool;



    @ResponseBody
    @PostMapping("/register")
    public Response<Integer> Register(@RequestBody AccountVo account){
        if(accountService.checkAccount(account.getAccount()))
            return Response.createErr("账号已经存在");
        if(!(tool.checkPasswordLegitimacy(account.getPassword())))
            return Response.createErr("密码不符合规范");
        Account tmp = new Account(account.getAuthority(),account.getAccount());
        System.out.println("auth"+account.getAuthority());
        accountService.raiseAccount(tmp);
        if(account.getAuthority() == 1){
            Seller seller = new Seller(null,account.getName(),account.getAccount(),account.getPassword(),account.getLocation(),account.getPhone());
            sellerService.sellerRegister(seller);
            return Response.createSuc(seller.getSellerId());
        }else if(account.getAuthority() == 2){
            Buyer buyer = new Buyer(null,account.getName(),account.getAccount(),account.getPassword(),account.getLocation(),account.getPhone());
            buyerService.buyerRegister(buyer);
            return Response.createSuc(buyer.getBuyerId());
        }
        return Response.createErr("注册失败");
    }



    @ResponseBody
    @PostMapping("/login")
    public Response<Integer> Login(@RequestBody AccountVo accountVo) {
        try {
            Integer authority = accountService.getAccountAuthority(accountVo.getAccount());
            Integer response = 0;
            System.out.println(authority+" "+accountVo.getAccount()+" "+accountVo.getPassword());
            if (authority == 1) {
                response = sellerService.sellerLogin(accountVo.getAccount(), accountVo.getPassword());
                System.out.println("responce"+response);
                if (response != -1)
                    return Response.createSuc(response);
                else
                    return Response.createErr("账号不存在或者密码错误");
            } else if (authority == 2) {//buyer返回负数
                response = buyerService.buyerLogin(accountVo.getAccount(), accountVo.getPassword());
                if (response != -1)
                    return Response.createSuc(-response);
                else
                    return Response.createErr("账号不存在或者密码错误");

            }
        } catch (Exception e) {
            tool.soutErr("login", e);
            return Response.BUG();
        }
        return Response.createErr("登录失败");
    }
}
