package com.zjgsu.shopping.Tool;

import com.zjgsu.shopping.interior.Buyer.mapper.BuyerMapper;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Common.mapper.One2TwoClassMapper;
import com.zjgsu.shopping.interior.Common.pojo.vo.OrderList;
import com.zjgsu.shopping.interior.SuperAdmin.pojo.vo.BuyerList;
import com.zjgsu.shopping.interior.Common.mapper.GoodImagineMapper;
import com.zjgsu.shopping.interior.Common.mapper.GoodMapper;
import com.zjgsu.shopping.interior.Common.pojo.*;
import com.zjgsu.shopping.interior.Common.pojo.vo.GoodList;
import org.apache.ibatis.annotations.Param;
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

    @Override
    public OrderList toOrderList(List<Order> li){
        OrderList list = new OrderList();
        for(Order item : li) {
            Good good = goodMapper.getGoodInfo(item.getGoodId());
            GoodImagine goodImg = goodImagineMapper.getImagine(item.getGoodId()).stream().findFirst().orElse(null);
            //System.out.println("3"+goodImg);
            String img = (goodImg != null ? goodImg.getImagine() : null);
            //System.out.println("4"+img);
            Integer class1 = one2TwoClassMapper.getClassInfoBySecondClass(good.getClass2()).getClass1();
            list.AddItem(item, good, img);
        }

        return list;
    }

}
