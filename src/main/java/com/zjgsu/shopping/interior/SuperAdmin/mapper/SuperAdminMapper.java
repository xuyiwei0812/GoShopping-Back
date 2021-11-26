package com.zjgsu.shopping.interior.SuperAdmin.mapper;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.pojo.BuyerHistory;
import com.zjgsu.shopping.interior.Common.pojo.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

@Mapper
public interface SuperAdminMapper {

    @Select("select * from buyer")
    List<Buyer> getAllBuyerInfo();

    @Select("select * from buyerhistory")
    List<BuyerHistory> getAllBuyerHistory();

    @Select("select * from buyerhistory where buyerId = #{buyerId}")
    List<BuyerHistory> getBuyerHistoryByBuyerId(@Param("buyerId")Integer buyerId);

}
