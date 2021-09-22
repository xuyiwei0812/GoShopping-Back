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
   @Insert("insert into goodforhistory (goodId,name,description,price,dealDate) values(#{goodForHistory.goodId}," +
           "#{goodForHistory.name},#{goodForHistory.description},#{goodForHistory.price},#{goodForHistory.dealDate})")
   Boolean addGoodForHistory(@Param("goodForHistory") GoodForHistory goodForHistory);

    /**
     * 查询一个历史商品
     *
     * @param seller 商品id
     */

    @Select("select * from goodForHistory where seller =#{seller}")
    List<GoodForHistory> getGoodList(@Param("sellerId") int seller);

    /**
     * 获得一个商品的信息
     */
    @Select("select * from goodForHistory where goodId =#{goodId}")
    GoodForHistory getGoodInfo(@Param("goodId") int goodId);



}
