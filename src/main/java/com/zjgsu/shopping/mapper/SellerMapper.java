package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.vo.GoodForHistoryListVo;
import com.zjgsu.shopping.pojo.vo.GoodForSaleDetalVo;
import com.zjgsu.shopping.pojo.vo.GoodForSaleListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    Long updatePassword(@Param ("sellerId") int sellerId,@Param ("password") String password);

    /**
     * 取得待售商品列表
     *
     * @param sellerId 用户编号
     * @return 全部待售商品的信息
     */
    @Select("select * from goodForSale where id=#{sellerId}")
    GoodForSaleListVo getGoodForSaleList(@Param ("sellerId") int sellerId);

    /**
     * 取得历史商品列表
     *
     * @param sellerId 用户编号
     * @return 全部历史销售商品的信息
     */
    @Select("select * from goodForHistory where id=#{sellerId}")
    GoodForHistoryListVo getGoodForHistoryList(@Param ("sellerId") int sellerId);

    /**
     * 取得某一在售商品的详细信息
     *
     * @param goodId 商品编号
     * @return  某一商品的详细信息
     */
    @Select("select * from goodFaoSale where id={#goodId}")
    GoodForSaleDetalVo getGoodForSaleDetal(@Param ("goodId") int goodId);

}
