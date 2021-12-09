package com.zjgsu.shopping.interior.Buyer.mapper;


import com.zjgsu.shopping.interior.Buyer.pojo.BuyerHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BuyerHistoryMapper {

    @Options(useGeneratedKeys = true, keyProperty = "buyerHistory.historyId", keyColumn = "historyId")
    @Insert("insert into buyerhistory (goodId,sellerId,buyerId,date,number) values (#{buyerHistory.goodId} , #{buyerHistory.sellerId}" +
            ",#{buyerHistory.buyerId}, #{buyerHistory.date}, #{buyerHistory.number})")
    Boolean raiseBuyerHistory(@Param("buyerHistory")BuyerHistory buyerHistory);

    @Select("select * from buyerhistory where buyerId = #{buyerId}")
    List<BuyerHistory> getBuyerHistory(@Param("buyerId")Integer buyerId);
}
