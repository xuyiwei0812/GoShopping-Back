package com.zjgsu.shopping;

import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.service.SellerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoShoppingBackApplication.class)
public class GoShoppingBackApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(1);
    }


    @Autowired
    private SellerService sellerService;

    @Test
    public void test1() {
        System.out.println(sellerService.login("123","1234"));
    }


}
