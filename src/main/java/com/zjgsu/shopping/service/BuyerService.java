package com.zjgsu.shopping.service;

import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.IntentionBuyer;

public interface BuyerService {
    /**
     * 建立一个购买者
     *     private String name;
     *     private String location;
     *     private String phone;
     *
     */
    Buyer createBuyer(String name,String location,String phone);
    /**
     * 提出一个购买意向
     *
     * @param buyer 意向
     * @param goodId 商品信息
     *
     * @return 提出失败返回-1
     */
    Boolean raiseIntention(Buyer buyer, int goodId);

    /**
     * 撤销一个购买意向
     *
     * @param intentionId 意向编号
     * @return 撤销失败返回-1
     *
     */
    Boolean cancelIntention(int intentionId);

}
