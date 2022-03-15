package com.zjgsu.shopping.interior.Common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    Integer addressId;
    Integer buyerId;
    String buyerName;
    String buyerAddress;
    String buyerPhone;
    Integer isDefault;
}
