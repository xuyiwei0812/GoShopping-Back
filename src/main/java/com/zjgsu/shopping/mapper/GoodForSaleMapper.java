package com.zjgsu.shopping.mapper;


import com.zjgsu.shopping.pojo.GoodForSale;
import org.apache.ibatis.annotations.*;

@Mapper
public interface GoodForSaleMapper {
    /**
     * 上传一个商品
     *
     * @param goodForSale 整个商品信息
     * @return 失败返回-1
      */
    @Options(useGeneratedKeys = true , keyProperty = "goodId" , keyColumn = "goodId")
    @Insert("insert into goodforsale (goodId,name,description,price,frozen) values(#{goodForSale.goodId} " +
            "#{goodForSale.name},#{goodForSale.description} , #{goodForSale.price} , #{goodForSale.frozen} )")
    Boolean putOnGood(@Param("goodForSale")GoodForSale goodForSale);

    /**
     * 下架一个商品
     *
     * @param goodId 商品Id
     * @return 失败返回-1
     */
    @Delete("delete from goodforsale where goodId =#{goodId}")
    Boolean putOffGood(@Param("goodId") int goodId);

    /**
     * 冻结一个商品
     *
     * @param goodId 商品Id
     * @return 失败返回-1
     */
    @Update("update goodforsale set forzen = 1 where goodforsale = #{goodId}")
    Boolean freezeGood(@Param("goodId") int goodId);

    /**
     * 解冻一个商品
     *
     * @param goodId 商品Id
     * @return 失败返回-1
     */
    @Update("update goodforsale set forzen = 0 where goodforsale = #{goodId}")
    Boolean unfreezeGood(@Param("goodId") int goodId);

    /**
     * 得到一个商品的信息
     *
     * @param goodId 商品Id
     * @return 商品的信息
     */
    @Select("select * from goodforsale where goodId =#{goodId}")
    GoodForSale getGoodInfo(@Param("goodId") int goodId);
}
