package com.zjgsu.shopping.interior.Common.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
class GoodBrief {
    private Integer goodId;
    private Integer storage;
    private Integer class1;
    private Integer class2;
    private Double goodPrice;
    private String goodName;
    private String img;
    private String description;
    private Boolean frozen;
    private Boolean sold;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodList {
    List<GoodBrief> goodlist = new ArrayList<>();

    public void AddItem(Integer goodId, Integer storage, Integer class1, Integer class2, Double price, String name, String img, String description, Boolean frozen, Boolean sold) {
        GoodBrief good = new GoodBrief(goodId, storage,class1,class2,price, name, img, description, frozen, sold);
        goodlist.add(good);
    }
}
