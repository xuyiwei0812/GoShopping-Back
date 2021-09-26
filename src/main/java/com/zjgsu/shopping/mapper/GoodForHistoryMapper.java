package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.GoodForHistory;
import com.zjgsu.shopping.pojo.vo.GoodForHistoryListVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodForHistoryMapper {
    /**
     * 添加一个历史商品
     *
     * @param goodForHistory 历史商品
     * @return 失败返回0
     */
   @Insert("insert into goodforhistory (goodId,name,description,price,dealDate,phone,sellerId) values(#{goodForHistory.goodId}," +
           "#{goodForHistory.name},#{goodForHistory.description},#{goodForHistory.price},#{goodForHistory.dealDate},#{goodForHistory.phone},#{goodForHistory.sellerId})")
   Boolean addGoodForHistory(@Param("goodForHistory") GoodForHistory goodForHistory);


//
//    /**
//     * 查询某一卖家的历史商品
//     *
//     * @param sellerId 商品id
//     */
//
//    @Select("select * from goodForHistory where sellerId =#{sellerId}")
//    List<GoodForHistory> getGoodList(@Param("sellerId") Integer sellerId);

    /**
     * 获得一个商品的历史出售信息
     */
    @Select("select * from goodForHistory where goodId =#{goodId}")
    List<GoodForHistory> getGoodForHistoryList(@Param("goodId") Integer goodId);




}
