package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IntentionDetalVo {
    /**
     * 姓名
     * 住址
     * 联系电话
     */
    private String name;
    private String location;
    private String phone;

}
