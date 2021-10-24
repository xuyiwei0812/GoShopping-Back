package com.zjgsu.shopping;

import com.zjgsu.shopping.mapper.BuyerMapper;
import com.zjgsu.shopping.mapper.GoodMapper;
import com.zjgsu.shopping.mapper.IntentionMapper;
import com.zjgsu.shopping.mapper.SellerMapper;
import com.zjgsu.shopping.pojo.*;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.BuyerService;
import com.zjgsu.shopping.service.SellerService;
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

//    @Resource
//    SellerService sellerService;
//    @Test
//    public void test(){
//        Seller seller = new Seller(null,"lyy","1912190524","lyy","浙江上虞","1234567");
//        sellerService.register(seller);
//        System.out.println(seller.getSellerId());
//        System.out.println(seller);
//    }
//    @Test
//    public void contextLoads() {
//        System.out.println(1);
//    }

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

//    @Test
//    public void test(){
//        System.out.println("hello world");
//    }
//    @Test
//    public void raiseGood(){
//        System.out.println(goodMapper.putOnGood(new Good(null,3,3.0,"3","3",null,null,null,null)));
//    }
//    @Test
//    public void pullOffGood(){
//        System.out.println(123);
//        Good g = new Good(8);
//        System.out.println(goodMapper.pullOffGood(g));
//    }
}

