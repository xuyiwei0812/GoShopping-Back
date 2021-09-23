package com.zjgsu.shopping;

import com.zjgsu.shopping.mapper.SellerMapper;
import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.Seller;
import com.zjgsu.shopping.service.BuyerService;
import com.zjgsu.shopping.service.SellerService;
import com.zjgsu.shopping.service.impl.BuyerServiceImpl;
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
    @Resource
    SellerService sellerService;

    @Test
    public void test1(){
        Seller seller = sellerService.register("111","123456","1234","a","12");
    }
}
