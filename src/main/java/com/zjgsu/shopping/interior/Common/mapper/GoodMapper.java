package com.zjgsu.shopping.interior.Common.mapper;

import com.zjgsu.shopping.interior.Common.pojo.Good;
import com.zjgsu.shopping.interior.Common.pojo.Video;
import com.zjgsu.shopping.interior.Common.pojo.vo.Mode;
import org.apache.ibatis.annotations.*;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

/**
 * Good 参数:
 *  1   Integer goodId;
 *  2   Integer sellerId;
 *  3   Double  goodPrice;
 *  4   String  goodName;
 *  5   String  description;
 *  6   Boolean frozen;
 *  7   Boolean sold;
 *  8   Boolean wanted;
 *  9   Boolean removed;
 */
@Mapper
public interface GoodMapper {

    /**
     *  上传一个商品
     *
     * @param good xx
     *             good.sellerId    卖家的id
     *             good.goodPrice   商品价格
     *             good.goodName    商品名称
     *             good.description 商品描述
     * @return 失败返回-1
     * 注意:上传商品之后商品就不会从数据库中删除
     */
    @Options(useGeneratedKeys = true, keyProperty = "good.goodId", keyColumn = "goodId")
    @Insert("insert into good (sellerId,storage,goodPrice,goodName,description,class2) values( " +
            "#{good.sellerId},#{good.storage},#{good.goodPrice} , #{good.goodName} , #{good.description} , #{good.class2})")
    Boolean raiseGood(@Param("good") Good good);

    /**
     * 得到一个商品的信息
     *
     * @param goodId 商品Id
     * @return 商品的信息
     */
    @Select("select * from good where goodId =#{goodId}")
    Good getGoodInfo(@Param("goodId") Integer goodId);

    /**
     *
     * @param good xx
     *             good.goodPrice   商品价格
     *             good.goodName    商品名称
     *             good.description 商品描述
     */
    @Update("update good set goodPrice = #{good.goodPrice} , goodName = #{good.goodName} ,+" +
            "description =  #{good.description} where goodId = #{good.goodId}")
    Long updateGoodInfo(@Param("good") Good good);


    /**
     *针对removed属性的设置
     */
    @Update("update good set removed = 0 where goodId = #{goodId}")
    Long putOnGood(@Param("goodId") Integer goodId);

    /**
     * 下架一个商品
     *
     */
    @Update("update good set removed = 1 where goodId = #{goodId}")
    Long pullOffGood(@Param("goodId") Integer goodId);

    /**
     * 针对frozen属性的设置
     *

     * @return 失败返回-1
     */
    @Update("update good set frozen = 1 where goodId = #{goodId}")
    Long freezeGood(@Param("goodId") Integer goodId);

    /**
     * 解冻一个商品

     * @return 失败返回-1
     */
    @Update("update good set frozen = 0 where goodId = #{goodId}")
    Long unfreezeGood(@Param("goodId") Integer goodId);

    /**
     * 针对want属性的设置
     */
    @Update("update good set wanted = 1 where goodId =  #{goodId}")
    Long WantGood(@Param("goodId") Integer goodId);

    @Update("update good set wanted = 0 where goodId =  #{goodId}")
    Long refuseGood(@Param("goodId") Integer goodId);

    /**
     * 针对sold属性的设置
     *
     * @param goodId 商品id
     * @return 选择已经卖光的商品
     */
    @Update("update good set sold = 1 where goodId = #{goodId}")
    Long soldOutGood(@Param("goodId") Integer goodId);


    /**
     * 添货
     *
     * @param goodId 商品id
     */
    @Update("update good set sold = 0 where goodId = #{goodId}")
    Long exhibitGood(@Param("goodId") Integer goodId);

    @Update("update good set storage = #{good.storage} where goodId = #{good.goodId}")
    Long updateGoodStorage(@Param("good" )Good good);


    /**
     * 得到卖家的全部商品列表
     *
     * @param sellerId 卖家id
     * @return 商品列表
     */
    @Select("select * from good where sellerId =#{sellerId}")
    List<Good> getAllGoodListBySellerId(@Param("sellerId") Integer sellerId);

    /**
     * 卖家的非下架商品(包括冻结,卖空,是否有意向人)
     */
    @Select("select * from good where sellerId =#{sellerId} and removed = 0")
    List<Good> getUnremovedGoodListBySellerId(@Param("sellerId") Integer sellerId);

    /**
     * 卖家的已下架商品
     */
    @Select("select * from good where sellerId =#{sellerId} and removed = 1")
    List<Good> getRemovedGoodListBySellerId(@Param("sellerId") Integer sellerId);

    /**
     *  有买家意向的商品列表
     */
    @Select("select * from good where sellerId =#{sellerId} and wanted = 1 and removed = 0")
    List<Good> getWantedGoodListBySellerId(@Param("sellerId") Integer sellerId);

    /**

     */
    @Select("select * from good where sellerId =#{sellerId} and frozen = 0 and removed = 0")
    List<Good> getUnfrozenGoodListBySellerId(@Param("sellerId") Integer sellerId);




    //*********************************************************************************
    //以下数据库操作针对买家

    /**
        得到非下架商品(包括已经冻结,已经售空)
     */

    @Select("select * from good where removed = #{mode.removed} and frozen = #{mode.frozen} and " +
            "sold = #{mode.sold}")
    List<Good> getAllGoodList(@Param("mode") Mode mode);


    @Select("select * from good where removed > #{mode.removed} and frozen > #{mode.frozen} and " +
            "sold > #{mode.sold} and sellerId > #{mode.sellerId}")
    List<Good> getGoodListBySellerId(@Param("mode") Mode mode );

    @Select("select * from good where removed > #{mode.removed} and frozen > #{mode.frozen} and " +
            "sold > #{mode.sold} and class2 > #{mode.class2}")
    List<Good> getClass2GoodListByClassId(@Param("mode")Mode mode );


    @Select("select * from good where removed = 0")
    List<Good> getAllGoodListForBuyers();

    @Select("select * from good where frozen = 0 and removed = 0")
    List<Good> getUnfrozenGoodListForBuyers();


    @Select("select * from good where removed = 0 and sellerId = #{sellerId}")
    List<Good> getAllGoodListBySellerIdForBuyers(@Param("sellerId") Integer sellerId);

    @Select("select * from good where frozen = 0 and removed = 0 and sellerId = #{sellerId}")
    List<Good> getUnfrozenGoodListBySellerIdForBuyers(@Param("sellerId") Integer sellerId);
//
//    @Select("select * from good where ")


    //视频
    /**
     * 把路径存进数据库里
     * @param video
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "video.videoId", keyColumn = "videoId")
    @Insert("insert into goodvideo (localAddress,suffix,goodId) values (#{video.localAddress},#{video.suffix},#{video.goodId})")
    Boolean saveVideoToDatabase(@Param("video") Video video);

    /**
     * 拿某个good的视频
     * @param good
     * @return
     */
    @Select("select localAddress from goodvideo where goodId=#{good.goodId}")
    String getVideoByGood(@Param("good") Good good);

    /**
     * 删视频
     * @param videoId
     * @return
     */
    @Delete("delete * from goodvideo where videoId=#{videoId}")
    Boolean deleteVideo(@Param("videoId") Integer videoId);

    /**
     * 搜索
     * @param keyword
     * @return
     */
    @Select("select * from good where goodName like '%${keyword}%'")
    List<Good> searchGood(@Param("keyword") String keyword);
}
