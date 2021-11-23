package com.zjgsu.shopping.interior.Common.mapper;

import com.zjgsu.shopping.interior.Common.pojo.One2Two;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface One2TwoClassMapper {
    @Select("select * from one2twoclass where class2 = #{class2}")
    One2Two getClassInfoBySecondClass(@Param("class2")Integer class2);
}
