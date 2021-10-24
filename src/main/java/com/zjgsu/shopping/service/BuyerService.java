package com.zjgsu.shopping.service;

import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.Intention;
import com.zjgsu.shopping.pojo.vo.GoodList;

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

    GoodList getAllGoodList();

    GoodList getUnfrozenGoodList();

    GoodList getAllGoodListBySellerId(Integer sellerId);

    GoodList getUnfrozenGoodListBySellerId(Integer sellerId);


}
