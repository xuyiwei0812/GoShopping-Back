package com.zjgsu.shopping.interior.Seller.service;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Seller.pojo.Deal;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import com.zjgsu.shopping.interior.Seller.pojo.vo.DealVo;
import com.zjgsu.shopping.interior.Common.pojo.*;

import java.util.List;

/**
 * 如果是返回list,命名时请 getXXXXlistbyXXX
 * 如果是返回单个类,请命名  getXXXinfo
 */
public interface SellerService {

    /**
     * 对账号信息进行操作
     * 1.账号注册
     * 2.账号登录
     * 3.更新账号密码
     * 4.检查账号密码
     * 5.更新卖家的信息
     * 6.查询账号是否存在
     */
    Seller sellerRegister(Seller seller);
    Integer sellerLogin(String account, String password);
    Long updateSellerPassword(Integer sellerId, String password, String newPassword);
    Boolean checkSellerPassword(Integer sellerId, String password);
    Boolean updateSellerInfo(Seller seller);
    Boolean searchSellerAccount(String account);



    /**
     * 针对商品信息的查询:
     * 1.全部商品
     * 2.非下架商品
     * 3.非下架商品中的有意向商品
     * 4.非下架商品中的非冻结商品
     * 5.已下架商品
     * @param sellerId 商品id
     */
    List<Good> getAllGoodListBySellerId(Integer sellerId);
    List<Good> getUnremovedGoodListBySellerId(Integer sellerId);
    List<Good> getWantedGoodListBySellerId(Integer sellerId);
    List<Good> getRemovedGoodListBySellerId(Integer sellerId);
    List<Good> getUnfrozenGoodListBySellerId(Integer sellerId);


    /**
     * 针对历史交易信息的查询
     * 1.某一买家的历史交易信息
     * 2.某一商品的历史交易信息
     */
    List<DealHistory> getDealHistoryListBySellerId(Integer sellerId);
    List<DealHistory> getDealHistoryListByGoodId(Integer goodId);


    /**
     * 取得某一商品的意向购买人列表
     *
     * @param goodId 商品编号
     * @return 某一商品的意向购买人列表
     */
    List<Intention> getIntentionListByGoodId(Integer goodId);


    /**
     * 取得某一购买人的详细信息
     *
     * @param buyerId 买家编号
     * @return 意向购买人详细信息
     */
    Buyer getBuyerInfo(Integer buyerId);

    /**
     * 对交易的操作:
     * 1.开始一场交易:添加到交易列表,同时删除对应意向,商品冻结
     * 2.取消一场交易:在交易列表中删除这项交易,商品解冻
     * 3.结束一场交易:商品列表中删除该交易,商品解冻,商品售空,历史交易记录中添加这项交易
     * @return 操作成功返回1,失败返回0
     */

    Boolean startDeal(DealVo deal);
    Boolean cancelDeal(Integer dealId);
    Boolean finishDeal(Integer dealId);

    /**
     * 对于商品的操作:
     * 1.查询商品信息
     * 2.发布一个全!新!的商品,商品发布之后就不会从数据库中删除,发布后默认上架,非冻结
     * 3.将一个已下架商品上架
     * 4.将一个上架的商品下架
     */
    Good getGoodInfo(Integer goodId);
    Good raiseGood(Good good);
    Good updateGoodInfo(Good good);
    Boolean putOnGood(Integer goodId);
    Boolean pullOffGood(Integer goodId);
    Boolean exhibitGood(Good good);
    Boolean soldOutGood(Integer goodId);


    List<Deal> getDealListByGoodId(Integer goodId);
    Boolean uploadGoodImg(Integer goodId ,List<String> li);

    //视频
    public Integer saveVideoToDatabase(Video video);
    public String getVideoByGoodId(Integer goodId);
}
