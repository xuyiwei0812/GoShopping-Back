//package com.zjgsu.shopping.interior.Common.mapper;
//
//import com.zjgsu.shopping.interior.Common.pojo.DealHistory;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//
//import java.util.List;
//
//@Mapper
//public interface DealHistoryMapper {
//    /**
//     * 添加一个历史商品
//     *
//     * @param deal 历史商品
//     * @return 失败返回0
//     */
//    @Insert("insert into dealhistory (goodId,name,description,price,dealDate,phone,sellerId,number) values(#{deal.goodId}," +
//            "#{deal.name},#{deal.description},#{deal.price},#{deal.dealDate},#{deal.phone},#{deal.sellerId},#{deal.number})")
//    Boolean raiseDealHistory(@Param("deal") DealHistory deal);
//
//
//    /**
//     * 查询某一卖家的历史商品
//     *
//     * @param sellerId 商品id
//     */
//
//    @Select("select * from dealhistory where sellerId =#{sellerId}")
//    List<DealHistory> getDealHistoryListBySellerId(@Param("sellerId") Integer sellerId);
//
//    /**
//     * 获得一个商品的历史出售信息
//     */
//    @Select("select * from dealhistory where goodId =#{goodId}")
//    List<DealHistory> getDealHistoryListByGoodId(@Param("goodId") Integer goodId);
//
//}
