package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.IntentionBuyer;
import org.apache.ibatis.annotations.*;

//存放意向购买人信息
@Mapper
public interface IntentionBuyerMapper {
    /**
     * 提出一个购买意向
     *
     * @param intentionBuyer 买家信息
     * @return 提出失败返回-1
     */
    @Options (useGeneratedKeys = true, keyProperty = "sellerId", keyColumn = "sellerId")
    @Insert("insert into intentionbuyer (buyerId,name,location,phone) values (#{intentionBuyer.buyerId}," +
            "#{intentionBuyer.name},#{intentionBuyer.location},#{intentionBuyer.phone})")
    Boolean raiseIntention(@Param("intentionBuyer")IntentionBuyer intentionBuyer);

    /**
     * 撤销一个购买意向
     *
     * @param intentionId 意向编号
     * @return 撤销失败返回-1
     *
     */
    @Delete("delete from intentionbuyer where buyerId=#{intentionId}")
    Boolean cancelIntention(@Param("intentionId") int intentionId);

    /**
     *
     */
}