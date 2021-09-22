package com.zjgsu.shopping.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoodImagine {

    /**
     * 图片id
     * 商品id
     * 图片内容
     * 构造方式 GoodImagine(null,goodId,imagine)
     */
    private Integer imagineId;
    private Integer goodId;
    private String imagine;
    public GoodImagine(Integer imagineId, Integer goodId, String imagine) {
        this.goodId = goodId;
        this.imagine = imagine;
    }
}
