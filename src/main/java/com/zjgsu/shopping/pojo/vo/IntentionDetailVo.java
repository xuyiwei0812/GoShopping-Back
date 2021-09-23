package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.Intention;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IntentionDetailVo {
    /**
     * 姓名
     * 住址
     * 联系电话
     */
    private Intention intention;
}
