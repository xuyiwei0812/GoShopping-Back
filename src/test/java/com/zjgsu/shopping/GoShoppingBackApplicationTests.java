package com.zjgsu.shopping;

import com.zjgsu.shopping.mapper.BuyerMapper;
import com.zjgsu.shopping.mapper.IntentionMapper;
import com.zjgsu.shopping.mapper.SellerMapper;
import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.Seller;
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

//    @Test
//    public void contextLoads() {
//        System.out.println(1);
//    }
//
//
//    @Autowired
//    private SellerService sellerService;
//    @Resource
//    private SellerMapper sellerMapper;
//    @Autowired
//    private BuyerService buyerService;
//    @Resource
//    private BuyerMapper buyerMapper;
//    @Resource
//    private IntentionMapper intentionMapper;
//
//    @Test
//    public void test1() {
//        Buyer buyer = buyerService.createBuyer("aa","bb","1212");
//        System.out.println(buyer);
//    }
//
//    @Test
//    public void test2() {
//        Seller seller = sellerService.register("aaa","123","123","a","1212");
//        System.out.println(seller);
//    }
//
//    @Test
//    public void test3(){
//        Boolean bo = buyerService.raiseIntention(1,1);
//        System.out.println(bo);
//    }
//
//    @Test
//    public void test4(){
//        Boolean bo2 = buyerService.cancelIntention(1);
//        System.out.println(bo2);
//    }
//
//    @Test
//    public void test5(){
//        List<GoodForSale> list = buyerService.getUnfrozenGoodForSaleList();
//        System.out.println(list);
//    }
//
//    @Test
//    public void test6(){
//        Integer n = sellerService.login("1234","123");
//        System.out.println(n);
//    }
//
//    @Test
//    public void test7(){
//        Boolean bo = sellerService.updatePassword(8,"12345");
//        System.out.println(bo);
//    }
////
////    @Test
////    public void test8(){
////        GoodForSaleListVo list = sellerService.getGoodForSaleList(8);
////        System.out.println(list);
////    }
//
////    @Test
////    public void test9(){
////        GoodForHistoryListVo list = sellerService.getGoodForHistoryList(8);
////        System.out.println(list);
////    }
//
//    @Test
//    public void test10(){
//        GoodForSaleDetailVo detail = sellerService.getGoodForSaleDetail(1);
//        System.out.println(detail);
//    }
//
//    @Test
//    public void test11(){
//        GoodForHistoryDetailVo de = sellerService.getGoodForHistoryDetail(1);
//        System.out.println(de);
//    }
//
//    @Test
//    public void test12(){
//        IntentionListVo li = sellerService.getIntentionBuyers(1);
//        System.out.println(li);
//    }
//
//    @Test
//    public void test13(){
//        System.out.println(intentionMapper.getIntentionList(1));
//    }
//
//    @Test
//    public void test14() {
//        IntentionDetailVo in = sellerService.getIntentionDetail(1);
//        System.out.println(in);
//    }
//
//    @Test
//    public void test15(){
//        Boolean bo = sellerService.startDeal(1,1,1);
//        System.out.println(bo);
//    }
//
//    @Test
//    public void test16(){
//        Boolean bo = sellerService.cancelDeal(1);
//        System.out.println(bo);
//    }
//
//    @Test
//    public void test17(){
//        Date date = new Date();
//        Boolean bo = sellerService.finishDeal(2, date);
//    }
//
//    @Test
//    public void test18(){
//        GoodForSale good = sellerService.putOnGood("aa","aaa",10.1,1);
//    }
//
//    @Test
//    public void test19(){
//        Boolean bo = sellerService.putOffGood(1);
//        System.out.println(bo);
//    }
//
//    @Test
//    public void test20(){
//        Boolean bo = sellerService.searchAccount("123");
//        System.out.println(bo);
//    }
//
//    @Test
//    public void test21(){
//        Seller seller = new Seller(8,"12","aa","123","a","12");
//        Boolean bo = sellerService.updateInfo(seller);
//    }
}

