//package com.zjgsu.shopping.mapper;
//
//import com.zjgsu.shopping.pojo.Buyer;
//import com.zjgsu.shopping.pojo.IntentionBuyer;
//import org.apache.ibatis.annotations.*;
//
//import java.util.List;
//
////弃用
////存放意向购买人信息
//@Mapper
//public interface IntentionBuyerMapper {
//    /**
//     * 提出一个购买意向
//     *
//     * @param intentionBuyer 买家信息
//     * @return 提出失败返回-1
//     */
//    @Options (useGeneratedKeys = true, keyProperty = "intentionId", keyColumn = "intentionId")
//    @Insert("insert into intentionbuyer (buyerId,name,location,phone,goodId) values (#{intentionBuyer.buyerId}," +
//            "#{intentionBuyer.name},#{intentionBuyer.location},#{intentionBuyer.phone}),#{intentionBuyer.goodId}")
//    Boolean raiseIntention(@Param("intentionBuyer")IntentionBuyer intentionBuyer);
//
//    /**
//     * 撤销一个购买意向
//     *
//     * @param intentionId 意向编号
//     * @return 撤销失败返回-1
//     *
//     */
//    @Delete("delete from intentionbuyer where buyerId=#{intentionId}")
//    Boolean cancelIntention(@Param("intentionId")  Integer intentionId);
//
//    /**
//     * 返回某个信息的意向人列表
//     *
//     * @param goodId 商品Id
//     */
//    @Select("select * from intentionbuyer where goodId=#{goodId}")
//    List<IntentionBuyer> getIntentionList(@Param("goodId")  Integer goodId);
//
//    /**
//     * 返回某个意向的具体信息
//     *
//     * @param intentionId 意向Id
//     */
//    @Select("select * from intentionbuyer where intention=#{intentionId}")
//    IntentionBuyer getIntentionInfo(@Param("intentionId")  Integer intentionId);
//
//}