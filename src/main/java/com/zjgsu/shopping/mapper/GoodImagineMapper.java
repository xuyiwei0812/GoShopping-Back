package com.zjgsu.shopping.mapper;

import com.zjgsu.shopping.pojo.GoodImagine;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodImagineMapper {
    /**
     * 添加一张图片
     *
     * @param goodImagine 图片商品
     * @return 失败返回0
     */
    @Options(useGeneratedKeys = true, keyProperty = "imagineId", keyColumn = "imagineId")
    @Insert("insert into goodimagine(goodId,imagine) values (#{goodImagine.goodId}," +
            "#{goodImagine.imagine})")
    Boolean addImagine(@Param("goodImagine")GoodImagine goodImagine);

    /**
     * 删除一张图片
     *
     * @param imagineId 图片编号
     * @return 失败返回0
     */
    @Delete("delete from goodimagine where imagineId =#{imagineId}")
    Boolean deleteImagine(@Param("imagineId") Integer imagineId);

    /**
     * 查询某商品的图片
     *
     * @param goodId 商品编号
     * @return List<GoodImagine>
     */
    @Select("select * from goodimagine where goodId =#{goodId}")
    List<GoodImagine> getImagine(@Param("goodId") Integer goodId);
}