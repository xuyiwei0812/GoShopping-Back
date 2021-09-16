// God and me wrote this code,
// but only God know what it is now.
//                        -- K-desperate

package com.zjgsu.shopping.pojo;

import lombok.Data;

@Data
public class Seller {
    /**
     * 卖家编号
     * 姓名
     * 头像(暂定)
     * 账户
     * 密码
     * 地址
     * 电话

     */
    private int sellerId;
    private String name;
    //private String profilePicture;//base64
    private String account;
    private String password;
    private String location;
    private String phone;

}
