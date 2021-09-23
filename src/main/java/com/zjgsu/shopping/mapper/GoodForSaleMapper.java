package com.zjgsu.shopping.mapper;


import com.zjgsu.shopping.pojo.GoodForSale;
import com.zjgsu.shopping.pojo.vo.GoodForSaleListVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodForSaleMapper {
    /**
     * 上传一个商品
     *
     * @param goodForSale 整个商品信息
     * @return 失败返回-1
      */
    @Options(useGeneratedKeys = true , keyProperty = "goodId" , keyColumn = "goodId")
    @Insert("insert into goodforsale (name,description,price,frozen) values( " +
            "#{goodForSale.name},#{goodForSale.description} , #{goodForSale.price} , #{goodForSale.frozen} )")
    Boolean putOnGood(@Param("goodForSale")GoodForSale goodForSale);

    /**
     * 下架一个商品
     *
     * @param goodId 商品Id
     * @return 失败返回-1
     */
    @Delete("delete from goodforsale where goodId =#{goodId}")
    Long putOffGood(@Param("goodId") Integer goodId);

    /**
     * 冻结一个商品
     *
     * @param goodId 商品Id
     * @return 失败返回-1
     */
    @Update("update goodforsale set frozen = 1 where goodforsale = #{goodId}")
    Long freezeGood(@Param("goodId") Integer goodId);

    /**
     * 解冻一个商品
     *
     * @param goodId 商品Id
     * @return 失败返回-1
     */
    @Update("update goodforsale set frozen = 0 where goodforsale = #{goodId}")
    Long unfreezeGood(@Param("goodId") Integer goodId);

    /**
     * 得到一个商品的信息
     *
     * @param goodId 商品Id
     * @return 商品的信息
     */
    @Select("select * from goodforsale where goodId =#{goodId}")
    GoodForSale getGoodInfo(@Param("goodId") Integer goodId);

    /**
     * 得到卖家的商品列表
     *
     * @param sellerId 卖家id
     * @return 商品列表
     */
    @Select("select * from goodforsale where sellerId =#{sellerId}")
    List<GoodForSale> getGoodList(@Param("sellerId") Integer sellerId);


    @Select("select * from goodforsale where frozen = 0")
    List<GoodForSale> getUnfrozenGoodList();

    @Select("select * from goodforsale")
    List<GoodForSale> getAllGoodList();

}
