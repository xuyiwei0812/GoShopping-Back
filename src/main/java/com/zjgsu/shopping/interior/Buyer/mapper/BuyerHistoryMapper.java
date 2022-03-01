package com.zjgsu.shopping.interior.Buyer.mapper;


import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BuyerHistoryMapper {
//
//    @Options(useGeneratedKeys = true, keyProperty = "buyerHistory.historyId", keyColumn = "historyId")
//    @Insert("insert into buyerhistory (goodId,sellerId,buyerId,date,number,buyerName) values (#{buyerHistory.goodId} , #{buyerHistory.sellerId}" +
//            ",#{buyerHistory.buyerId}, #{buyerHistory.date}, #{buyerHistory.number}, #{buyerHistory.buyerName})")
//    Boolean raiseBuyerHistory(@Param("buyerHistory")BuyerHistory buyerHistory);
//
//    @Select("select * from buyerhistory where buyerId = #{buyerId}")
//    List<BuyerHistory> getBuyerHistory(@Param("buyerId")Integer buyerId);
//
//    @Select("select goodPrice from good where goodId = #{goodId}")
//    Double getGoodPriceByGoodId(@Param("goodId")Integer goodId);
//
//    @Select("select imagine from goodimagine where goodId = #{goodId}")
//    List<String> getGoodImageByGoodId(@Param("goodId")Integer goodId);
}
