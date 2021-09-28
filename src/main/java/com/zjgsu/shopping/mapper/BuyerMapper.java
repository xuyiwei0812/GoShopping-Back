package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.Buyer;
import org.apache.ibatis.annotations.*;

//存放买家的信息
@Mapper
public interface BuyerMapper {
    /**
     * 出现一个购买人
     *
     * @param buyer 买家信息
     * @return 提出失败返回-1
     */
    @Options (useGeneratedKeys = true, keyProperty = "buyer.buyerId", keyColumn = "buyerId")
    @Insert("insert into buyer (name,location,phone) values (#{buyer.name},#{buyer.location},#{buyer.phone})")
    Boolean creatBuyer(@Param("buyer") Buyer buyer);

    /**
     * 撤销一个购买意向
     *
     * @param buyer 商品信息
     * @return 撤销失败 返回-1
     *
     * 无用,暂时没必要删除掉写入数据库的信息
     */
    @Delete("delete from buyer where buyerId=#{buyer.buyerId}")
    Long deleteBuyer(@Param("buyer") Buyer buyer);

    /**
     * 返回买家的信息
     * @param buyerId 用户编号
     */
    @Select("select * from buyer where buyerId=#{buyerId}")
    Buyer getBuyerInfo(@Param("buyerId")Integer buyerId);
}
