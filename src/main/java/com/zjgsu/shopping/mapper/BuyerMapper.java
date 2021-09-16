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
    @Insert("insert into intentionbuyer (buyerId,name,location,phone) values (#{buyer.buyerId},#{buyer.name},#{buyer.location},#{buyer.phone})")
    Boolean raiseIntention(@Param("buyer") Buyer buyer);

    /**
     * 撤销一个购买意向
     *
     * @param buyer
     * @return 撤销失败 返回-1
     *
     */
    @Delete("delete from intentionbuyer where buyerId=#{buyer.buyerId}")
    Boolean cancelIntention(@Param("buyer") Buyer buyer);
}
