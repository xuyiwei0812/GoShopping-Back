package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.Business;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BusinessMapper {
    /**
     * 添加一个交易
     *
     * @param business 交易信息
     * @return 失败返回-1
     */
    @Options(useGeneratedKeys = true, keyProperty = "businessId", keyColumn = "businessId")
    @Insert("insert into business (buyerId,sellerId,goodId,locate) values (#{business.buyerId}," +
            "#{business.sellerId},#{business.goodId},#{business.locate})")
    Boolean createBusiness(@Param("business") Business business);
}
