package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.mapper.BusinessMapper;
import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.service.impl.SellerService;
import com.zjgsu.shopping.service.impl.impl.SellerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin(origins = "*")

@RequestMapping()
public class UserContorller {
    @Resource
    private SellerService sellerService;


    @ResponseBody
    @PostMapping()
    int test(){
//        sellerService.startDeal(1,2,3);
        return 1;
    }

}
