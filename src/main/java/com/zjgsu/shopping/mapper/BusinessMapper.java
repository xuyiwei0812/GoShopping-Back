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
    @Insert("insert into business (buyerId,sellerId,goodId) values (#{business.buyerId}," +
            "#{business.sellerId},#{business.goodId})")
    Boolean startBusiness(@Param("business") Business business);

    /**
     * 删除一个交易
     *
     * @param businessId 交易id
     * @return 删除是否成功
     */
    @Delete("delete from business where businessId =#{businessId}")
    Long detelBusiness(@Param("businessId")Integer businessId);

    /**
     * 查询交易的具体信息
     *
     * @param businessId 交易id
     * @return 交易信息
     */
    @Select("select * from business where businessId=#{businessId}")
    Business getBusinessInfo(@Param("businessId") Integer businessId);
}
