package com.zjgsu.shopping.service;

import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.Intention;
import com.zjgsu.shopping.pojo.vo.GoodList;

import java.util.List;

public interface BuyerService {
    /**
     * @param name     姓名
     * @param location 地址
     * @param phone    电话
     * @return 买家对象
     */
    Buyer createBuyer(String name, String location, String phone);

    Buyer createBuyer(Buyer buyer);

    /**
     * 提出一个购买意向
     *
     * @param buyerId 意向
     * @param goodId  商品信息
     * @return 提出失败返回-1
     */
    Boolean raiseIntention(Integer buyerId, Integer goodId);

    Boolean raiseIntention(Intention intention);

    /**
     * 撤销一个购买意向
     *
     * @param intentionId 意向编号
     * @return 撤销失败返回-1
     */
    Boolean cancelIntention(Integer intentionId);

    /**
     * @return 非冻结状态的商品列表
     */
    List<Good> getUnfrozenGoodForSaleList();

    /**
     *
     */
    GoodList getAllGoodList();


    GoodList getGoodListBySellerId(Integer sellerId);

}
