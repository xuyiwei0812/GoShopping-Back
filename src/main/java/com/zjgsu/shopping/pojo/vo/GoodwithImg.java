package com.zjgsu.shopping.pojo.vo;

import com.zjgsu.shopping.pojo.Good;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodwithImg {
    Good good;
    List<String> img;

}
