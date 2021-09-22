package com.zjgsu.shopping.service;

import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.GoodForSale;

import java.util.List;

public interface BuyerService {
    /**
     *
     * @param name 姓名
     * @param location 地址
     * @param phone 电话
     * @return 买家对象
     */
    Buyer createBuyer(String name,String location,String phone);
    /**
     * 提出一个购买意向
     *
     * @param buyerId 意向
     * @param goodId 商品信息
     *
     * @return 提出失败返回-1
     */
    Boolean raiseIntention(Integer buyerId, Integer goodId);

    /**
     * 撤销一个购买意向
     *
     * @param intentionId 意向编号
     * @return 撤销失败返回-1
     *
     */
    Boolean cancelIntention(Integer intentionId);

    /**
     *
     * @return 在出售的商品列表
     */
    List<GoodForSale> getUnfrozenGoodForSaleList();

}
