package com.zjgsu.shopping.service;

import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.Intention;
import com.zjgsu.shopping.pojo.vo.GoodwithImg;

import java.util.List;

public interface BuyerService {


    Buyer raiseBuyer(Buyer buyer);



    Boolean raiseIntention(Intention intention);

    /**
     * 撤销一个购买意向
     *
     * @param intentionId 意向编号
     * @return 撤销失败返回-1
     */
    Boolean cancelIntention(Integer intentionId);

    List<Good> getAllGoodListForBuyers();

    List<Good> getUnfrozenGoodListForBuyers();

    List<Good> getAllGoodListBySellerIdForBuyers(Integer sellerId);

    List<Good> getUnfrozenGoodListBySellerIdForBuyers(Integer sellerId);


    /**
     * 取得某一商品的详细信息
     *
     * @param goodId 商品编号
     * @return 某一商品的详细信息
     */
    GoodwithImg getGoodInfo(Integer goodId);

}
