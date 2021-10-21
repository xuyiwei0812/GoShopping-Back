package com.zjgsu.shopping.service;

import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.Deal;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.Seller;
import com.zjgsu.shopping.pojo.vo.DealHistoryList;
import com.zjgsu.shopping.pojo.vo.GoodList;
import com.zjgsu.shopping.pojo.vo.GoodwithImg;
import com.zjgsu.shopping.pojo.vo.IntentionList;

import java.util.Date;

/**
 * 如果是返回list,命名时请 getXXXXlistbyXXX
 * 如果是返回单个类,请命名  getXXXinfo
 */
public interface SellerService {

    /**
     * 注册一个账号
     *
     * @return 注册是否成功
     */
    Seller register(String name, String account, String password, String location, String phone);

    /**
     * 重载register
     */
    Seller register(Seller seller);

    /**
     * 登录
     *
     * @param account  用户
     * @param password 密码
     * @return 用户编号, 或者无法登录返回-1
     */
    Integer login(String account, String password);

    /**
     * 修改密码
     *
     * @param password 密码
     * @param sellerId 用户编号
     * @return 是否更新成功
     */
    Long updatePassword(Integer sellerId, String password, String newPassword);

    Boolean checkPassword(Integer sellerId, String password);

    /**
     * 查询某一商家的商品
     *
     * @param sellerId 用户编号-
     * @return 全部代售商品的信息
     */
    GoodList getGoodListBySellerId(Integer sellerId);

    /**
     * 通过商家id 查询有意向购买人的商品
     *
     * @param sellerId 商品id
     */
    GoodList getWantedGoodListBySellerId(Integer sellerId);

    /**
     * @return 全部在出售的货物信息
     */
    GoodList getAllGoodList();

    /**
     * @return 返回全部的历史交易信息
     */
    DealHistoryList getAllDealHistoryList();

    /**
     * @return 返回某个卖家的历史商品信息
     */
    DealHistoryList getDealHistoryListBySellerId(Integer sellerId);

    /**
     * 取得某一商品的详细信息
     *
     * @param goodId 商品编号
     * @return 某一商品的详细信息
     */
    GoodwithImg getGoodInfo(Integer goodId);

    /**
     * 取得某一商品的历史交易信息列表
     *
     * @param goodId 商品编号
     * @return 某一历史商品的详细信息
     */
    DealHistoryList getDealHistoryByGoodId(Integer goodId);

    /**
     * 取得某一商品的意向购买人列表
     *
     * @param goodId 商品编号
     * @return 某一商品的意向购买人列表
     */
    IntentionList getIntentionListByGoodId(Integer goodId);

//    /**
//     *
//     * @param sellerId
//     * @return
//     */
//    IntentionList getIntentionListBySellerId(Integer sellerId);


    /**
     * 取得某一购买人的详细信息
     *
     * @param buyerId 买家编号
     * @return 意向购买人详细信息
     */
    Buyer getBuyerInfo(Integer buyerId);

    /**
     * 开始一场交易
     * 把交易信息放入数据库之中
     *
     * @return 如果返回商品状态码
     */
    Boolean startDeal(Integer buyerId, Integer sellerId, Integer goodId);

    Boolean startDeal(Deal deal);

    /**
     * 取消一场交易
     *
     * @param dealId 交易编号
     * @return 取消失败返回-1
     */
    Boolean cancelDeal(Integer dealId);

    /**
     * 完成一场交易
     *
     * @param dealId 交易编号
     * @return 完成失败返回-1
     */
    Boolean finishDeal(Integer dealId, Date dealDate);

    /**
     * 上架一个商品
     * <p>
     * 上架成功之后返回整个商品信息
     */
    //GoodForSale putOnGood(String name,String description,Integer price);

    Good putOnGood(String name, String description, Double price, Integer sellerId);

    Good putOnGood(Good good);

    /**
     * 下架一个商品
     *
     * @param goodId 商品编号
     * @return 下架失败返回-1
     */
    Boolean putOffGood(Integer goodId);

    /**
     * 查询这个账号是否存在
     * 有值返回true
     */
    Boolean searchAccount(String account);

    /**
     * 更新卖家信息
     */
    Boolean updateInfo(Seller seller);


    /**
     * 添货
     *
     * @param goodId 商品id
     */
    Boolean exhibitGood(Integer goodId);

}
