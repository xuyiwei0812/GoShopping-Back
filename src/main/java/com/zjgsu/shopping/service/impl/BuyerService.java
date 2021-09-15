package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.pojo.Buyer;

public interface BuyerService {
    /**
     * 提出一个购买意向
     *
     * @param goodId 商品编号
     * @param buyer 买家信息
     *
     * @return 提出失败返回-1
     */
    Boolean raiseIntention(Buyer buyer , int goodId);

    /**
     * 撤销一个购买意向
     *
     * @param goodId 商品编号
     * @return 撤销失败返回-1
     *
     */
    Boolean cancelIntention(int goodId);
}
