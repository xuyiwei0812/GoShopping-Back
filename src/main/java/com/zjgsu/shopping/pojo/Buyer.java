// God and me wrote this code,
// but only God know what it is now.
//                        -- K-desperate

//暂时没什么用

package com.zjgsu.shopping.pojo;

import lombok.Data;

@Data
public class Buyer {

    /**
     * 用户编号
     * 姓名
     * 住址
     * 联系电话
     */
    private int buyerId;
    private String name;
    private String location;
    private String phone;

}
