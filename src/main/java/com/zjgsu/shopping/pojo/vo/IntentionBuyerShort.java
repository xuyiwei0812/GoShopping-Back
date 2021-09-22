//弃用
package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.Buyer;
import lombok.Data;

@Data
public class IntentionBuyerShort {
    /**
     * 意向编号
     * 用户编号
     * 姓名
     * 住址
     * 联系电话
     *
     */
    private Integer goodId;
    private String name;
    private String location;
    private String phone;

    public IntentionBuyerShort(Integer goodId, String name, String location, String phone) {
        this.name = name;
        this.location = location;
        this.phone = phone;
    }
}
