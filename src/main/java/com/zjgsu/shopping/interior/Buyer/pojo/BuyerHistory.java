package com.zjgsu.shopping.interior.Buyer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerHistory {

    private Integer historyId;
    private Integer buyerId;
    private Integer goodId;
    private Integer sellerId;
    private Date date;
}
