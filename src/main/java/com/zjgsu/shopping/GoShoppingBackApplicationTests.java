package com.zjgsu.shopping;

import com.zjgsu.shopping.GoShoppingBackApplication;

import com.zjgsu.shopping.interior.Buyer.mapper.BuyerMapper;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.mapper.GoodMapper;
import com.zjgsu.shopping.interior.Common.mapper.IntentionMapper;
import com.zjgsu.shopping.interior.Common.pojo.Good;
import com.zjgsu.shopping.interior.Seller.mapper.SellerMapper;
import com.zjgsu.shopping.interior.Seller.service.SellerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoShoppingBackApplication.class)
public class GoShoppingBackApplicationTests {

    @Resource
    private GoodMapper goodMapper;
    @Autowired
    private SellerService sellerService;
    @Resource
    private SellerMapper sellerMapper;
    @Autowired
    private BuyerService buyerService;
    @Resource
    private BuyerMapper buyerMapper;
    @Resource
    private IntentionMapper intentionMapper;

    @Test
    public void test(){
        System.out.println("hello world");
    }
    @Test
    public void test1(){
        System.out.println(buyerService.getAllGoodListForBuyers());
    }
    @Test
    public void test2(){
        System.out.println(sellerService.checkSellerPassword(1,"123456"));
    }
}

