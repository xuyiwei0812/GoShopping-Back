package com.zjgsu.shopping.interior.SuperAdmin.service.Impl;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.pojo.BuyerHistory;
import com.zjgsu.shopping.interior.SuperAdmin.mapper.SuperAdminMapper;
import com.zjgsu.shopping.interior.SuperAdmin.service.SuperAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
    @Resource
    SuperAdminMapper superAdminMapper;

    @Override
    public List<Buyer> getAllBuyerInfo() {
        return superAdminMapper.getAllBuyerInfo();
    }

    @Override
    public List<BuyerHistory> getAllBuyerHistory() {
        return superAdminMapper.getAllBuyerHistory();
    }
}
