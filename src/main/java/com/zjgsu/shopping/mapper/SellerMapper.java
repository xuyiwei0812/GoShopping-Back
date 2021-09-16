package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.vo.*;
import org.apache.ibatis.annotations.*;

//存放卖家的信息
@Mapper
public interface SellerMapper {
    /**
     * 登录
     *
     * @param account 用户
     * @param password 密码
     * @return 用户编号,或者无法登录返回-1
     */
    @Select("select * where account=#{account} and password=#{password}")
    int login(@Param ("account")String account ,@Param ("password") String password);

    /**
     * 修改密码
     *
     * @param password 密码
     * @param sellerId 用户编号
     * @return 是否更新成功
     */
    @Update("update seller set password=#{password} where sellerId=#{sellerId}")
    Boolean updatePassword(@Param ("sellerId") int sellerId,@Param ("password") String password);

    /**
     * 取得待售商品列表
     *
     * @param sellerId 用户编号
     * @return 全部待售商品的信息
     */
    @Select("select * from goodForSale where sellerId=#{sellerId}")
    GoodForSaleListVo getGoodForSaleList(@Param ("sellerId") int sellerId);

    /**
     * 取得历史商品列表
     *
     * @param sellerId 用户编号
     * @return 全部历史销售商品的信息
     */
    @Select("select * from goodForHistory where sellerId=#{sellerId}")
    GoodForHistoryListVo getGoodForHistoryList(@Param ("sellerId") int sellerId);

    /**
     * 取得某一在售商品的详细信息
     *
     * @param goodId 商品编号
     * @return  某一商品的详细信息
     */
    @Select("select * from goodForSale where sellerId={#goodId}")
    GoodForSaleDetalVo getGoodForSaleDetal(@Param ("goodId") int goodId);

    /**
     * 取得某一商品的意向购买人列表
     *
     * @param goodId 商品编号
     * @return 某一商品的意向购买人列表
     */
    @Select("select buyerId from business where goodId={#goodId}")
    IntentionBuyerListVo getIntentionBuyers(@Param ("goodId") int goodId);

    /**
     * 取得某一意向购买人的详细信息
     *
     * @param buyerId 买家编号
     * @return 意向购买人详细信息
     */
    @Select("select * from buyer where buyerId={#buyerId}")
    IntentionBuyerDetalVo getIntentionButerDetal(@Param ("buyerId") int buyerId);

    /**
     * 开始一场交易
     *
     * @param goodId 商品编号
     * @param sellerId 卖家编号
     * @param buyerId 买家编号
     * @param price 价格
     * @param location 地点
     * @return 如果返回商品状态码
     */
    @Options (useGeneratedKeys = true, keyProperty = "businessId", keyColumn = "businessId")
    @Insert("insert into business goodId={#goodId}, sellerId={#sellerId}, buyerId={#buyerId}, price={#price}, location={#location}")
    int startDeal(@Param ("goodId") int goodId,@Param ("sellerId") int sellerId, @Param ("buyerId") int buyerId, @Param ("price") double price, @Param ("location") String location);


}
