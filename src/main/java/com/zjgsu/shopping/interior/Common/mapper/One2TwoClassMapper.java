package com.zjgsu.shopping.interior.Common.mapper;

import com.zjgsu.shopping.interior.Common.pojo.Class1;
import com.zjgsu.shopping.interior.Common.pojo.One2Two;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface One2TwoClassMapper {
    @Select("select * from one2twoclass where class2 = #{class2}")
    One2Two getClassInfoBySecondClass(@Param("class2") Integer class2);

    @Select("select * from class1")
    List<Class1> getAllClass1();

    @Select("select * from one2twoclass where class1 = #{class1}")
    List<One2Two> getAllClass2ByClass1Id(@Param("class1") Integer class1);
}
