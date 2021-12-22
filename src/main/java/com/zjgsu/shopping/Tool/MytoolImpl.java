package com.zjgsu.shopping.Tool;

import com.zjgsu.shopping.interior.Buyer.mapper.BuyerMapper;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.pojo.BuyerHistory;
import com.zjgsu.shopping.interior.Buyer.pojo.vo.BuyerHistoryList;
import com.zjgsu.shopping.interior.Common.mapper.One2TwoClassMapper;
import com.zjgsu.shopping.interior.Seller.pojo.Deal;
import com.zjgsu.shopping.interior.SuperAdmin.pojo.vo.BuyerList;
import com.zjgsu.shopping.interior.Common.mapper.GoodImagineMapper;
import com.zjgsu.shopping.interior.Common.mapper.GoodMapper;
import com.zjgsu.shopping.interior.Common.pojo.*;
import com.zjgsu.shopping.interior.Common.pojo.vo.DealHistoryList;
import com.zjgsu.shopping.interior.Seller.pojo.vo.DealList;
import com.zjgsu.shopping.interior.Common.pojo.vo.GoodList;
import com.zjgsu.shopping.interior.Common.pojo.vo.IntentionList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MytoolImpl implements Mytool {
    @Resource
    GoodImagineMapper goodImagineMapper ;
    @Resource
    BuyerMapper buyerMapper;
    @Resource
    GoodMapper goodMapper;
    @Resource
    One2TwoClassMapper one2TwoClassMapper;
    public Object soutErr(String s,Exception e){
        System.out.println("发生错误,错误方法名: " + s + "错误: " + e.toString());
        return null;
    }

    public GoodList toGoodList(List<Good> li){
        GoodList goodList = new GoodList();
        for (Good item : li) {
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            //System.out.println("3"+goodImg);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            //System.out.println("4"+img);
            Integer class1 = one2TwoClassMapper.getClassInfoBySecondClass(item.getClass2()).getClass1();
            //System.out.println("5"+class1);
            goodList.AddItem(item.getGoodId(), item.getStorage(),class1,item.getClass2(),item.getGoodPrice(), item.getGoodName(), img, item.getDescription(), item.getFrozen(), item.getSold());
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
            list.AddItem(item.getIntentionId(),item.getBuyerId(),item.getGoodId(),item.getDate(),buyerMapper.getBuyerInfo(item.getBuyerId()).getBuyerName(),item.getNumber());
        }
        return list;
    }

    public DealList toDealList(List<Deal> li){
        DealList list = new DealList();
        for(Deal item : li){
            list.AddItem(item.getDealId(),item.getGoodId(),buyerMapper.getBuyerInfo(item.getBuyerId()).getBuyerName(),
                    goodMapper.getGoodInfo(item.getGoodId()).getGoodName(),item.getDate());
        }
        return list;
    }

    @Override
    public BuyerHistoryList toBuyerHistoryList(List<BuyerHistory> li) {
      BuyerHistoryList list = new BuyerHistoryList();
      for(BuyerHistory item :li ){
          list.AddItem(item.getHistoryId(),item.getGoodId(),item.getBuyerId(),item.getSellerId(),goodMapper.getGoodInfo(item.getGoodId()).getGoodName(),item.getDate(),item.getNumber(),item.getBuyerName(),item.getGoodPrice(),item.getImg());
      }
      return list;
    }

    @Override
    public BuyerList toBuyerList(List<Buyer> li) {
        BuyerList list = new BuyerList();
        for(Buyer item: li){
            list.AddItem(item);
        }
        return list;
    }

    /**
     * 检查密码合法性
     */
    @Override
    public Boolean checkPasswordLegitimacy(String s) {
        if(s == null) return false;
        int len = s.length();
        return len >= 6 && len <= 12;
    }

}
