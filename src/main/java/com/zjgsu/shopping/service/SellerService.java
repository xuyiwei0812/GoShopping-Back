package com.zjgsu.shopping.service;

import com.zjgsu.shopping.pojo.Business;
import com.zjgsu.shopping.pojo.GoodForHistory;
import com.zjgsu.shopping.pojo.GoodForSale;
import com.zjgsu.shopping.pojo.Seller;
import com.zjgsu.shopping.pojo.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SellerService {

    /**
     * @param seller 注册用户
     * @return 注册是否成功
     */
    Boolean register(Seller seller);

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
    GoodForSaleListVo getGoodForSaleList(int sellerId);

    /**
     * 取得历史商品列表
     *
     * @param sellerId 用户编号
     * @return 全部历史销售商品的信息
     */
    GoodForHistoryListVo getGoodForHistoryList(int sellerId);

    /**
     * 取得某一商品的详细信息
     *
     * @param goodId 商品编号
     * @return  某一商品的详细信息
     */
    GoodForSaleDetalVo getGoodForSaleDetal(int goodId);

    /**
     * 取得某一历史商品的详细信息
     *
     * @param goodId 商品编号
     * @return 某一历史商品的详细信息
     */
    GoodForHistoryDetalVo getGoodForHistoryDetal(int goodId);

    /**
     * 取得某一商品的意向购买人列表
     *
     * @param goodId 商品编号
     * @return 某一商品的意向购买人列表
     */
    IntentionBuyerListVo getIntentionBuyers(int goodId);

    /**
     * 取得某一意向购买人的详细信息
     *
     * @param buyerId 买家编号
     * @return 意向购买人详细信息
     */
    IntentionBuyerDetalVo getIntentionButerDetal(int buyerId);

    /**
     * 开始一场交易
     *

     * @return 如果返回商品状态码
     */
    int startDeal(Business business);
    /**
     * 取消一场交易
     *
     * @param businessId 交易编号
     * @return 取消失败返回-1
     */
    Boolean cancelDeal(int businessId);

    /**
     * 完成一场交易
     *
     * @param businessId 交易编号
     * @return 完成失败返回-1
     */
    Boolean finishDeal(int businessId);

    /**
     * 上架一个商品
     *
     * @param good 商品详细信息
     * @return 上架失败返回-1
     */
    Boolean putOnGood(GoodForSale good);

    /**
     * 下架一个商品
     *
     * @param goodId 商品编号
     * @return 下架失败返回-1
     */
    Boolean putOffGood(int goodId);

}
