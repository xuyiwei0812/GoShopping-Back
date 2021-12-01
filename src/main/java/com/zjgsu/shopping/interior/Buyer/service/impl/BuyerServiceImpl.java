package com.zjgsu.shopping.interior.Buyer.service.impl;

import com.zjgsu.shopping.interior.Buyer.mapper.BuyerMapper;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.mapper.GoodImagineMapper;
import com.zjgsu.shopping.interior.Common.mapper.GoodMapper;
import com.zjgsu.shopping.interior.Common.mapper.IntentionMapper;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Common.mapper.One2TwoClassMapper;
import com.zjgsu.shopping.interior.Common.pojo.Good;
import com.zjgsu.shopping.interior.Common.pojo.Intention;
import com.zjgsu.shopping.interior.Common.pojo.vo.GoodwithImg;
import com.zjgsu.shopping.interior.Common.pojo.vo.Mode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Resource
    private IntentionMapper intentionMapper;
    @Resource
    private BuyerMapper buyerMapper;
    @Resource
    private GoodMapper goodMapper;
    @Resource
    private GoodImagineMapper goodImagineMapper;
    @Resource
    private One2TwoClassMapper one2TwoClassMapper;


    @Override
    public Buyer buyerRegister(Buyer buyer) {
        return (buyerMapper.register(buyer) ? buyer : null);
    }

    @Override
    public Integer buyerLogin(String account, String password) {
        Buyer buyer = buyerMapper.login(account,password);
        return (buyer != null ? buyer.getBuyerId() : -1);
    }

    @Override
    public Long updateBuyerPassword(Integer buyerId, String password, String newPassword) {
        return null;
    }

    @Override
    public Boolean checkBuyerPassword(Integer buyerId, String password) {
        Buyer buyer = buyerMapper.getBuyerInfo(buyerId);
        return Objects.equals(buyer.getBuyerPassword(),password);
    }

    @Override
    public Boolean updateBuyerInfo(Buyer buyer) {
        Buyer old = buyerMapper.getBuyerInfo(buyer.getBuyerId());
        if(buyer.getBuyerName() == null)buyer.setBuyerName(old.getBuyerName());
        if(buyer.getBuyerAccount() == null) buyer.setBuyerAccount(old.getBuyerAccount());
        if(buyer.getBuyerLocation() == null) buyer.setBuyerLocation(old.getBuyerLocation());
        if(buyer.getBuyerPhone() == null) buyer.setBuyerPhone(old.getBuyerPhone());
        return null;
    }

    @Override
    public Boolean searchBuyerAccount(String account) {
        return !buyerMapper.searchAccount(account).isEmpty();
    }


    @Override
    public Boolean raiseIntention(Intention intention) {
        goodMapper.WantGood(intention.getGoodId());
        intention.setDate(new Date());
        return intentionMapper.raiseIntention(intention);
    }

    @Override
    public Boolean cancelIntention(Integer intentionId) {
        Intention intention = intentionMapper.getIntentionInfo(intentionId);
        if (!intentionMapper.cancelIntention(intentionId)) return false;
        if (intentionMapper.getIntentionListByGoodId(intention.getGoodId()).isEmpty())
            goodMapper.refuseGood(intention.getGoodId());
        return true;
    }

    @Override
    public List<Good> getAllGoodListForBuyers() {
        return goodMapper.getAllGoodListForBuyers();
    }

    @Override
    public  List<Good> getUnfrozenGoodListForBuyers() {
        return goodMapper.getUnfrozenGoodListForBuyers();
    }

    @Override
    public  List<Good> getAllGoodListBySellerIdForBuyers(Integer sellerId) {
        return goodMapper.getAllGoodListBySellerIdForBuyers(sellerId);
    }

    @Override
    public  List<Good> getUnfrozenGoodListBySellerIdForBuyers(Integer sellerId) {
        return goodMapper.getUnfrozenGoodListBySellerIdForBuyers(sellerId);
    }

    @Override
    public List<Good> getClass2GoodListByClassId(Mode mode) {
       return goodMapper.getClass2GoodListByClassId(mode);
    }



    @Override
    public List<Good> getClass1GoodListByClassId(Mode mode) {
        List<Good> li = goodMapper.getAllGoodList(mode);
        List<Good> re = new ArrayList<>();
        for(Good item:li){
            if(Objects.equals(one2TwoClassMapper.getClassInfoBySecondClass(item.getClass2()).getClass1(), mode.getClass1()))
                re.add(item);
        }
        return re;
    }

    @Override
    public GoodwithImg getGoodInfo(Integer goodId) {
        return new GoodwithImg(goodMapper.getGoodInfo(goodId), goodImagineMapper.getImagine(goodId));
    }

    /**
     * 拿某个goodId的视频
     * @param good
     * @return
     */
    public String getVideoByGood(Good good){
        return goodMapper.getVideoByGood(good);
    }

    /**
     * 模糊搜索
     */
    public List<Good> searchGood(String keyword){
        return goodMapper.searchGood(keyword);
    }
}
