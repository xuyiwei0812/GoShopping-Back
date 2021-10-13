package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodMapper {
    /**
     * 上传一个商品
     *
     * @param good 整个商品信息
     * @return 失败返回-1
     */
    @Options(useGeneratedKeys = true , keyProperty = "good.goodId" , keyColumn = "goodId")
    @Insert("insert into good (name,description,price,frozen,sellerId,wanted) values( " +
            "#{good.name},#{good.description} , #{good.price} , #{good.frozen}, #{good.sellerId} , #{good.wanted} )")
    Boolean putOnGood(@Param("good") Good good);

    /**
     * 下架一个商品
     *
     * @param goodId 商品Id
     * @return 失败返回-1
     */
    @Delete("delete from good where goodId =#{goodId}")
    Long putOffGood(@Param("goodId") Integer goodId);

    /**
     * 冻结一个商品
     *
     * @param goodId 商品Id
     * @return 失败返回-1
     */
    @Update("update good set frozen = 1 where goodId = #{goodId}")
    Long freezeGood(@Param("goodId") Integer goodId);

    /**
     * 解冻一个商品
     *
     * @param goodId 商品Id
     * @return 失败返回-1
     */
    @Update("update good set frozen = 0 where goodId = #{goodId}")
    Long unfreezeGood(@Param("goodId") Integer goodId);

    @Update("update good set wanted = 1 where goodId = #{goodId}")
    Long setGoodWant(@Param("goodId") Integer goodId);

    @Update("update good set wanted = 0 where goodId = #{goodId}")
    Long cancelGoodWant(@Param("goodId") Integer goodId);
    /**
     * 得到一个商品的信息
     *
     * @param goodId 商品Id
     * @return 商品的信息
     */
    @Select("select * from good where goodId =#{goodId}")
    Good getGoodInfo(@Param("goodId") Integer goodId);

    /**
     * 得到卖家的商品列表
     *
     * @param sellerId 卖家id
     * @return 商品列表
     */
    @Select("select * from good where sellerId =#{sellerId}")
    List<Good> getGoodListBySellerId(@Param("sellerId") Integer sellerId);

    @Select("select * from good where sellerId =#{sellerId} && wanted = 1")
    List<Good> getWantedGoodListBySellerId(@Param("sellerId" )Integer sellerId);

    @Select("select * from good where frozen = 0")
    List<Good> getUnfrozenGoodList();

    @Select("select * from good")
    List<Good> getAllGoodList();

    /**
     *
     * @param goodId 商品id
     * @return 选择已经卖光的商品
     */
    @Update("update good set sold = 1 where goodId = #{goodId}")
    Long soldOutGood(@Param("goodId") Integer goodId);


    /**
     * 上货
     * @param goodId 商品id
     */
    @Update("update good set sold = 0 where goodId = #{goodId}")
    Long exhibitGood(@Param("goodId") Integer goodId);

}
