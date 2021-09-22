package com.zjgsu.shopping;

import com.zjgsu.shopping.mapper.SellerMapper;
import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.pojo.Seller;
import com.zjgsu.shopping.service.SellerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Resource;

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
    @Test
    public void test1() {
//        Seller seller = new Seller();
//        seller.setAccount("233");
//        seller.setPassword("1234");
//        seller.setName("haha");
//        seller.setPhone("1234567");
//        seller.setLocation("123123123");
//        System.out.println(seller);
//        System.out.println(sellerService.register(seller));
    }

    @Test
    public void test2() {
        System.out.println(sellerService.login("233","1234"));
    }


}
