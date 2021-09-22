package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntentionShort {
    /**
     * 意向编号
     * 用户编号
     * 姓名
     * 住址
     * 联系电话
     *
     */
    private String name;
    private String location;
    private String phone;

}
