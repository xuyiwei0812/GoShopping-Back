package com.zjgsu.shopping.interior.SuperAdmin.mapper;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.pojo.BuyerHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SuperAdminMapper {

    @Select("select * from buyer")
    List<Buyer> getAllBuyerInfo();

    @Select("select * from buyerhistory")
    List<BuyerHistory> getAllBuyerHistory();
}
