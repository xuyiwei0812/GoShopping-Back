// God and me wrote this code,
// but only God know what it is now.
//                        -- K-desperate

//暂时没什么用

package com.zjgsu.shopping.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Buyer {

    /**
     * 用户编号
     * 姓名
     * 住址
     * 联系电话
     * Buyer(null,name,location,phone)
     */
    private Integer buyerId;
    private String name;
    private String location;
    private String phone;

    public Buyer(Integer buyerId, String name, String location, String phone) {
        this.name = name;
        this.location = location;
        this.phone = phone;
    }
}
