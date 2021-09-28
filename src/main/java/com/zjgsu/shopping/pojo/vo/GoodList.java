package com.zjgsu.shopping.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
class GoodBrief {
    private Integer goodId;
    private Double price;
    private String name;
    private String img;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodList {
    List<GoodBrief> goodlist;
    public void AddItem(Integer goodId , Double price , String name , String img){
        goodlist.add(new GoodBrief(goodId ,price ,name ,img));
    }
}
