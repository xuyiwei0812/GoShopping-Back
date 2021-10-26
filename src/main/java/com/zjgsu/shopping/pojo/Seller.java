// God and me wrote this code,
// but only God know what it is now.
//                        -- K-desperate

package com.zjgsu.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Seller {
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
    private Integer sellerId;
    private String name;
    private String account;
    private String password;
    private String location;
    private String phone;
    //private String profilePicture;//base64

    public Seller(Integer sellerId, String name, String account, String password, String location, String phone) {
        this.name = name;
        this.account = account;
        this.password = password;
        this.location = location;
        this.phone = phone;
    }
}
