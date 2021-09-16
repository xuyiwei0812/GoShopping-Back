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
        Business business=new Business();
        business.setBuyerId(2);
        business.setGoodId(3);
        business.setSellerId(4);
//        System.out.println(sellerService.startDeal(1,1,1));
        System.out.println(sellerService.startDeal(business));
    }
}
