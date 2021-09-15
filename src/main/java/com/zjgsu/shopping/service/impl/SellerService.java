package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.pojo.vo.GoodForHistory;
import com.zjgsu.shopping.pojo.vo.GoodForSale;

import java.util.List;

public interface SellerService {
    /**
     * 登录
     *
     * @param account 用户
     * @param password 密码
     * @return 用户编号,或者无法登录返回-1
     */
    int login(String account , String password);

    /**
     * @param password 密码
     * @param sellerId 用户编号
     * @return 是否更新成功
     */
    Boolean updatePassword(int sellerId,String password);

    /**
     * @param sellerId 用户编号
     * @return 全部代售商品的信息
     */
    List<GoodForSale> getGoodForSale(int sellerId);

    /**
     * @param sellerId 用户编号
     * @return 全部历史销售商品的信息
     */
    List<GoodForHistory> getGoodForHistory(int sellerId);
}
