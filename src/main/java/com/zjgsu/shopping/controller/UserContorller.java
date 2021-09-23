package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.mapper.BusinessMapper;
import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.pojo.vo.AccountVo;
import com.zjgsu.shopping.pojo.vo.Response;
import com.zjgsu.shopping.service.SellerService;
import com.zjgsu.shopping.service.impl.SellerServiceImpl;
import org.apache.ibatis.annotations.Param;
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
    public Response<Integer> register(@RequestBody AccountVo accountVo){
        String account = accountVo.getAccount();
        String password = accountVo.getPassword();
        if(sellerService.searchAccount(account))return Response.createErr("账号已经存在");
        sellerService.register()
    }

    @ResponseBody
    @PostMapping ("/login")
    public Response<Integer> login (@RequestBody HashMap<String, String> map) {
        String username = map.get ("username");
        String password = map.get ("password");
        User user = userService.getUserByUsername (username);
        if (user != null) {
            if (bcryptService.checkPassword (password,user.getPassword ())) {
                return Response.createSuc (user.getId ());
            }
        }
        return Response.createErr ("用户名或密码错误!");
    }

    @ResponseBody
    @GetMapping()
    public int test(@Param("x") int x){
        return x + 1;
       // sellerService.startDeal(1,2,3);
       // return 1;
    }

}
