package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
class GoodBrief {
    private Integer goodId;
    private Double price;
    private String name;
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

    public void AddItem(Integer goodId, Double price, String name, String img, String description, Boolean frozen, Boolean sold) {
        GoodBrief good = new GoodBrief(goodId, price, name, img, description, frozen, sold);
        goodlist.add(good);
    }
}
