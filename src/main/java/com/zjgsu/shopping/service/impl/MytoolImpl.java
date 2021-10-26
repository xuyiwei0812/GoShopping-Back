package com.zjgsu.shopping.service.impl;

import com.zjgsu.shopping.mapper.BuyerMapper;
import com.zjgsu.shopping.mapper.GoodImagineMapper;
import com.zjgsu.shopping.pojo.DealHistory;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.GoodImagine;
import com.zjgsu.shopping.pojo.Intention;
import com.zjgsu.shopping.pojo.vo.DealHistoryList;
import com.zjgsu.shopping.pojo.vo.GoodList;
import com.zjgsu.shopping.pojo.vo.IntentionList;
import com.zjgsu.shopping.service.Mytool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MytoolImpl implements Mytool {
    @Resource
    GoodImagineMapper goodImagineMapper ;
    @Resource
    BuyerMapper buyerMapper;

    public Object soutErr(String s,Exception e){
        System.out.println("发生错误,错误方法名: " + s + "错误: " + e.toString());
        return null;
    }

    public GoodList toGoodList(List<Good> li){
        GoodList goodList = new GoodList();
        for (Good item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            goodList.AddItem(item.getGoodId(), item.getGoodPrice(), item.getGoodName(), img, item.getDescription(), item.getFrozen(), item.getSold());
        }
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

    public IntentionList toIntentionList(List<Intention> li){
        IntentionList  list = new IntentionList();
        for(Intention item :li){
            list.AddItem(item.getIntentionId(),item.getBuyerId(),item.getGoodId(),item.getDate(),buyerMapper.getBuyerInfo(item.getBuyerId()).getName());
        }
        return list;
    }

}
