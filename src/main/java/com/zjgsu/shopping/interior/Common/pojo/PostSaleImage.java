package com.zjgsu.shopping.interior.Common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSaleImage {
    Integer imageId;
    Integer postSaleId;
    String image;
}
