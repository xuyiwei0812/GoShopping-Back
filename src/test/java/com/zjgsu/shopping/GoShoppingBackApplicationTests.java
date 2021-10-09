package com.zjgsu.shopping;

import com.zjgsu.shopping.mapper.BuyerMapper;
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
    public void test1() {
        Buyer buyer = buyerService.createBuyer("aa", "bb", "1212");
        System.out.println(buyer);
    }

    @Test
    public void test2() {
        Seller seller = sellerService.register("aaa", "123", "123", "a", "1212");
        System.out.println(seller);
    }

    @Test
    public void test3() {
        Boolean bo = buyerService.raiseIntention(1, 1);
        System.out.println(bo);
    }

    @Test
    public void test4() {
        Boolean bo2 = buyerService.cancelIntention(1);
        System.out.println(bo2);
    }

    @Test
    public void test5() {
        List<Good> list = buyerService.getUnfrozenGoodForSaleList();
        System.out.println(list);
    }

    @Test
    public void test6() {
        Integer n = sellerService.login("1234", "123");
        System.out.println(n);
    }

    @Test
    public void test7() {
        Boolean bo = sellerService.updatePassword(8, "12345");
        System.out.println(bo);
    }

    @Test
    public void test8() {
        GoodList list = buyerService.getAllGoodList();
        System.out.println(list);
    }

    @Test
    public void test9() {
        DealHistoryList list = sellerService.getDealHistoryByGoodId(2);
        System.out.println(list);
    }

//    @Test
//    public void test10() {
//        Good detail = sellerService.getGoodInfo(1);
//        System.out.println(detail);
//    }

    @Test
    public void test12() {
        IntentionList li = sellerService.getIntentionListByGoodId(1);
        System.out.println(li);
    }

    @Test
    public void test13() {
        Buyer buyer = sellerService.getBuyerInfo(1);
        System.out.println(buyer);
    }

    @Test
    public void test14(){
        Deal deal = new Deal(null,1,1,1);
        Boolean bo = sellerService.startDeal(deal);
        System.out.println(bo);
    }

    @Test
    public void test15(){
        Date date = new Date();
        Boolean bo = sellerService.finishDeal(4,date);
    }

    @Test
    public void test16(){
        Boolean bo = sellerService.exhibitGood(1);
    }
}

