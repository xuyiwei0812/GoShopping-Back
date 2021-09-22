package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.Buyer;
import org.apache.ibatis.annotations.*;

//存放买家的信息
@Mapper
public interface BuyerMapper {
    /**
     * 提出一个购买意向
     *
     * @param buyer 买家信息
     * @return 提出失败返回-1
     */
    @Options (useGeneratedKeys = true, keyProperty = "buyerId", keyColumn = "buyerId")
    @Insert("insert into buyer (name,location,phone) values (#{buyer.name},#{buyer.location},#{buyer.phone})")
    Boolean creatBuyer(@Param("buyer") Buyer buyer);

    /**
     * 撤销一个购买意向
     *
     * @param buyer 商品信息
     * @return 撤销失败 返回-1
     *
     * 未用
     */
    @Delete("delete from buyer where buyerId=#{buyer.buyerId}")
    Boolean deleteBuyer(@Param("buyer") Buyer buyer);
}
