package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.Deal;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DealMapper {
    /**
     * 添加一个交易
     *
     * @param deal 交易信息
     * @return 失败返回-1
     */
    @Options(useGeneratedKeys = true, keyProperty = "deal.dealId", keyColumn = "dealId")
    @Insert("insert into deal (buyerId,sellerId,goodId) values (#{deal.buyerId}," +
            "#{deal.sellerId},#{deal.goodId})")
    Boolean startDeal(@Param("deal") Deal deal);

    /**
     * 删除一个交易
     *
     * @param dealId 交易id
     * @return 删除是否成功
     */
    @Delete("delete from deal where dealId =#{dealId}")
    Long detelDeal(@Param("dealId")Integer dealId);

    /**
     * 查询交易的具体信息
     *
     * @param dealId 交易id
     * @return 交易信息
     */
    @Select("select * from deal where dealId=#{dealId}")
    Deal getDealInfo(@Param("dealId") Integer dealId);
}
