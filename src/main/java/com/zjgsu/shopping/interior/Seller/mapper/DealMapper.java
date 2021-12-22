package com.zjgsu.shopping.interior.Seller.mapper;

import com.zjgsu.shopping.interior.Seller.pojo.Deal;
import com.zjgsu.shopping.interior.Seller.pojo.vo.DealVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DealMapper {
    /**
     * 添加一个交易
     *
     * @param deal  交易信息
     * @return 失败返回-1
     */
    @Options(useGeneratedKeys = true, keyProperty = "deal.dealId", keyColumn = "dealId")
    @Insert("insert into deal (buyerId,sellerId,goodId,date,number) values (#{deal.buyerId}," +
            "#{deal.sellerId},#{deal.goodId},#{deal.date},#{deal.number})")
    Boolean startDeal(@Param("deal") DealVo deal);

    /**
     * 删除一个交易
     *
     * @param dealId 交易id
     * @return 删除是否成功
     */
    @Delete("delete from deal where dealId =#{dealId}")
    Long cancelDeal(@Param("dealId") Integer dealId);

    /**
     * 查询交易的具体信息
     *
     * @param dealId 交易id
     * @return 交易信息
     */
    @Select("select * from deal where dealId=#{dealId}")
    Deal getDealInfo(@Param("dealId") Integer dealId);

    @Select("select * from deal where goodId=#{goodId}")
    List<Deal> getDealList(@Param("goodId") Integer goodId);
}
