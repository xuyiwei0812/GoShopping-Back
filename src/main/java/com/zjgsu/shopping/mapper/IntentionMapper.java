package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.Intention;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IntentionMapper {
    /**
     * 提出一个购买意向
     *
     * @param intention 买家信息
     * @return 提出失败返回-1
     */
    @Options(useGeneratedKeys = true, keyProperty = "intention.intentionId", keyColumn = "intentionId")
    @Insert("insert into intention (buyerId , goodId) values (#{intention.buyerId}," +
            "#{intention.goodId},#{intention.date})")
    Boolean raiseIntention(@Param("intention") Intention intention);

    /**
     * 撤销一个购买意向
     *
     * @param intentionId 意向编号
     * @return 撤销失败返回-1
     *
     */
    @Delete("delete from intention where intentionId=#{intentionId}")
    Boolean cancelIntention(@Param("intentionId") Integer intentionId);

    /**
     * 返回某个信息的意向人列表
     *
     * @param goodId 商品Id
     */
    @Select("select * from intention where goodId=#{goodId}")
    List<Intention> getIntentionListByGoodId(@Param("goodId")  Integer goodId);

    /**
     * 返回某个意向的具体信息
     *
     * @param intentionId 意向Id
     */
    @Select("select * from intention where intentionId=#{intentionId}")
    Intention getIntentionInfo(@Param("intentionId")  Integer intentionId);

//    /**
//     * 通过商家id查询购买意向
//     * @return list
//     * @param sellerId xx
//     */
//    @Select("select * from intention where sellerId=#{sellerId}")
//    List<Intention> getIntentionListBySellerId(@Param("sellerId")Integer sellerId);


}
