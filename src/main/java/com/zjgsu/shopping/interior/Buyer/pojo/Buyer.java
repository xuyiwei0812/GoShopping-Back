// God and me wrote this code,
// but only God know what it is now.
//                        -- K-desperate

//暂时没什么用

package com.zjgsu.shopping.interior.Buyer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Buyer {

    /**
     * 用户编号
     * 姓名
     * 住址
     * 联系电话
     * Buyer(null,name,location,phone)
     */
    private Integer buyerId;
    private String buyerName;
    private String buyerAccount;
    private String buyerPassword;
    private String buyerLocation;
    private String buyerPhone;


}
