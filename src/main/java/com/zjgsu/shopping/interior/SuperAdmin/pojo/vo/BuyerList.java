package com.zjgsu.shopping.interior.SuperAdmin.pojo.vo;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerList {
    List<Buyer> buyerList = new ArrayList<>();
    public void AddItem(Buyer buyer){
        buyerList.add(buyer);
    }
}
