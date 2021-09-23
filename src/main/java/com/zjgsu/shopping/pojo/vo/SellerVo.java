package com.zjgsu.shopping.pojo.vo;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SellerVo {
    /**
     * 卖家编号
     * 姓名
     * 账户
     * 密码
     * 地址
     * 电话
     * 头像(暂定)
     * 构造方法 Seller(null,name,account,password,location,phone)
     */
    private String name;
    private String account;
    private String password;
    private String location;
    private String phone;
    //private String profilePicture;//base64

    public SellerVo(String name, String account, String password, String location, String phone) {
        this.name = name;
        this.account = account;
        this.password = password;
        this.location = location;
        this.phone = phone;
    }
}