package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.Business;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BusinessMapper {
    /**
     * 添加一个交易
     *
     * @param business 交易信息
     * @return 失败返回-1
     */
    @Options(useGeneratedKeys = true, keyProperty = "businessId", keyColumn = "businessId")
    @Insert("insert into business (buyerId,sellerId,goodId,locate,price) values (#{business.buyerId}," +
            "#{business.sellerId},#{business.goodId},#{business.locate},#{business.price})")
    Boolean startBusiness(@Param("business") Business business);

    /**
     * 删除一个交易
     *
     * @param businessId 交易id
     * @return 删除是否成功
     */
    @Delete("delete from business where businessId =#{businessId}")
    Long detelBusiness(@Param("businessId")int businessId);

    @Select("select * from business where businessId=#{businessId}")
    Business getBusinessInfo(@Param("businessId") int businessId);
}
