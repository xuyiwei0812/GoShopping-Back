package com.zjgsu.shopping;

import com.zjgsu.shopping.mapper.GoodImagineMapper;
import com.zjgsu.shopping.pojo.DealHistory;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.GoodImagine;
import com.zjgsu.shopping.pojo.vo.DealHistoryList;
import com.zjgsu.shopping.pojo.vo.GoodList;

import javax.annotation.Resource;
import java.util.List;

public class Mytool {
    @Resource
    GoodImagineMapper goodImagineMapper;

    public Object soutErr(String s,Exception e){
        System.out.println("发生错误,错误方法名: " + s + "错误: " + e.toString());
        return null;
    }

    public GoodList toGoodList(List<Good> li){
        GoodList goodList = new GoodList();
        System.out.println("123123"+ li);
        for (Good item : li) {
            System.out.println("-1" + goodImagineMapper.getImagine(1));
            System.out.println("0" + goodImagineMapper.getImagine(item.getGoodId()));
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            System.out.println("1"+ goodImg);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            System.out.println("2" + img);
            goodList.AddItem(item.getGoodId(), item.getGoodPrice(), item.getGoodName(), img, item.getDescription(), item.getFrozen(), item.getSold());
            System.out.println("3");
        }
        System.out.println(123);
        return goodList;
    }
    public DealHistoryList toDealHistoryList(List<DealHistory> li){
        DealHistoryList dealHistoryList = new DealHistoryList();
        for (DealHistory item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            dealHistoryList.AddItem(item.getGoodId(), item.getPrice(), item.getName(), item.getDealDate(), img);
        }
        return dealHistoryList;
    }
}
