package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.vo.GoodForHistoryListVo;
import com.zjgsu.shopping.pojo.vo.GoodForSaleListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//存放卖家的信息
@Mapper
public interface SellerMapper {

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


}
