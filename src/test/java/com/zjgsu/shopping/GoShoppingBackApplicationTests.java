package com.zjgsu.shopping;

import com.zjgsu.shopping.mapper.BuyerMapper;
import com.zjgsu.shopping.mapper.SellerMapper;
import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.GoodForSale;
import com.zjgsu.shopping.pojo.Seller;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.BuyerService;
import com.zjgsu.shopping.service.SellerService;
import com.zjgsu.shopping.service.impl.BuyerServiceImpl;
import com.zjgsu.shopping.service.impl.SellerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoShoppingBackApplication.class)
public class GoShoppingBackApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(1);
    }


    @Autowired
    private SellerService sellerService;
    @Resource
    private SellerMapper sellerMapper;
    @Autowired
    private BuyerService buyerService;
    @Resource
    private BuyerMapper buyerMapper;

    @Test
    public void test1() {
        Buyer buyer = buyerService.createBuyer("aa","bb","1212");
        System.out.println(buyer);
    }

    @Test
    public void test2() {
        Seller seller = sellerService.register("aaa","123","123","a","1212");
        System.out.println(seller);
    }

    @Test
    public void test3(){
        Boolean bo = buyerService.raiseIntention(1,1);
        System.out.println(bo);
    }

    @Test
    public void test4(){
        Boolean bo2 = buyerService.cancelIntention(1);
        System.out.println(bo2);
    }

    @Test
    public void test5(){
        List<GoodForSale> list = buyerService.getUnfrozenGoodForSaleList();
        System.out.println(list);
    }

    @Test
    public void test6(){
        Integer n = sellerService.login("1234","123");
        System.out.println(n);
    }

    @Test
    public void test7(){
        Boolean bo = sellerService.updatePassword(8,"12345");
        System.out.println(bo);
    }
}

