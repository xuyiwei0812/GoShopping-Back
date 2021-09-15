package com.zjgsu.shopping.service.impl;

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
     * 修改密码
     *
     * @param password 密码
     * @param sellerId 用户编号
     * @return 是否更新成功
     */
    Boolean updatePassword(int sellerId,String password);

    /**
     * 取得待售商品列表
     *
     * @param sellerId 用户编号
     * @return 全部代售商品的信息
     */
    List<GoodForSale> getGoodForSale(int sellerId);

    /**
     * 取得历史商品列表
     *
     * @param sellerId 用户编号
     * @return 全部历史销售商品的信息
     */
    List<GoodForHistory> getGoodForHistory(int sellerId);
}
