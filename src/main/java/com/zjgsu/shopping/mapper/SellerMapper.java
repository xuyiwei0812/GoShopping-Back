package com.zjgsu.shopping.mapper;


import com.zjgsu.shopping.pojo.Seller;
import org.apache.ibatis.annotations.*;
import java.util.List;

//存放卖家的信息
@Mapper
public interface SellerMapper {
    /**
     * 注册
     *
     * @param seller 卖家
     * @return 商家编号,或者无法注册返回-1
     *
     */
    @Options (useGeneratedKeys = true, keyProperty = "sellerId", keyColumn = "sellerId")
    @Insert("insert into seller (name,account,password,location,phone) values (#{seller.name},#{seller.account},#{seller.password},#{seller.location},#{seller.phone})")
    Boolean register(@Param("seller") Seller seller);

    /**
     * 登录
     *
     * @param account 用户
     * @param password 密码
     * @return 用户
     */
    @Select("select * from seller where account=#{account} and password=#{password}")
    Seller login(@Param ("account")String account , @Param ("password") String password);

    /**
     * 修改密码
     *
     * @param password 密码
     * @param sellerId 用户编号
     * @return 是否更新成功
     */
    @Update("update seller set password=#{password} where sellerId=#{sellerId}")
    Long updatePassword(@Param ("sellerId") Integer sellerId,@Param ("password") String password);

    /**
     *
     * @param account 账号
     * @return 返回查找到的账号的列表
     */
    @Select("select * from seller where account=#{account}")
    List<Seller> searchAccount(@Param("account") String account);

    /**
     *
     */
    @Update("update seller set location = #{seller.location} , phone = #{seller.phone} , name = #{seller.name} where sellerId = #{seller.sellerId}")
    Long updateInfo(@Param("seller") Seller seller);


}
