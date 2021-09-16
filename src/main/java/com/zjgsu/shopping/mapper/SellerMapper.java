package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.GoodForSale;
import com.zjgsu.shopping.pojo.Seller;
import com.zjgsu.shopping.pojo.vo.*;
import org.apache.ibatis.annotations.*;

//存放卖家的信息
@Mapper
public interface SellerMapper {
    /**
     * 注册
     *
     * @param seller
     * @return 商家编号,或者无法注册返回-1
     * 初步测试通过
     */
    @Options (useGeneratedKeys = true, keyProperty = "sellerId", keyColumn = "sellerId")
    @Insert("insert into seller (name,account,password,location,phone) values (#{seller.name},#{seller.account},#{seller.password},#{seller.location},#{seller.phone})")
    Boolean register(@Param("seller") Seller seller);

    /**
     * 登录
     *
     * @param account 用户
     * @param password 密码
     * @return 用户
     */
    @Select("select * from seller where account=#{account} and password=#{password}")
    Seller login(@Param ("account")String account , @Param ("password") String password);

    /**
     * 修改密码
     *
     * @param password 密码
     * @param sellerId 用户编号
     * @return 是否更新成功
     */
    @Update("update seller set password=#{password} where sellerId=#{sellerId}")
    Long updatePassword(@Param ("sellerId") int sellerId,@Param ("password") String password);

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
    @Select("select * from goodForSale where sellerId=#{goodId}")
    GoodForSaleDetalVo getGoodForSaleDetal(@Param ("goodId") int goodId);

    /**
     * 取得某一商品的意向购买人列表
     *
     * @param goodId 商品编号
     * @return 某一商品的意向购买人列表
     */
    @Select("select buyerId from business where goodId=#{goodId}")
    IntentionBuyerListVo getIntentionBuyers(@Param ("goodId") int goodId);

    /**
     * 取得某一意向购买人的详细信息
     *
     * @param buyerId 买家编号
     * @return 意向购买人详细信息
     */
    @Select("select * from buyer where buyerId=#{buyerId}")
    Buyer getIntentionButerDetal(@Param ("buyerId") int buyerId);
}
