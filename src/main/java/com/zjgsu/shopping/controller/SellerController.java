package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.pojo.vo.GoodVo;
import com.zjgsu.shopping.pojo.Buyer;
import com.zjgsu.shopping.pojo.Deal;
import com.zjgsu.shopping.pojo.Good;
import com.zjgsu.shopping.pojo.Seller;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.BuyerService;
import com.zjgsu.shopping.service.Mytool;
import com.zjgsu.shopping.service.SellerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/seller")
public class SellerController {
    @Resource
    private SellerService sellerService;
    @Resource
    private Mytool tool;
    @Resource
    private BuyerService buyerService;
    /**
     * 注册一个卖家账号
     *
     * @param seller xx
     *               seller.account 卖家账号
     *               seller.password 卖家密码
     * @return 成功返回卖家id , 失败Response.code 返回-1 , msg返回错误信息
     */
    @ResponseBody
    @PostMapping("/sellerRegister")
    public Response<Seller> sellerRegister(@RequestBody Seller seller) {
        try {
            if(!(tool.checkPasswordLegitimacy(seller.getPassword())))
                return Response.createErr("密码不符合规范");
            if (sellerService.searchAccount(seller.getAccount()))
                return Response.createErr("账号已经存在");
            sellerService.sellerRegister(seller);
            return Response.createSuc(seller);
        }catch(Exception e){
            tool.soutErr("sellerRegister" , e);
            return Response.BUG();
        }
    }


    /**
     * 登录账号
     *
     * @param accountVo xx
     *                  accountVo.account 登录账号
     *                  accountVo.password 登录密码
     * @return 登录成功返回账号Id, 失败返回错误信息
     */
    @ResponseBody
    @PostMapping("/sellerLogin")
    public Response<Integer> sellerLogin(@RequestBody AccountVo accountVo) {
        try {
            Integer response = sellerService.sellerLogin(accountVo.getAccount(), accountVo.getPassword());
            System.out.println(response);
            if (response != -1)
                return Response.createSuc(response);
            else
                return Response.createErr("账号不存在或者密码错误");
        }catch (Exception e){
            System.out.println("发生错误" + e);
            return Response.BUG();
        }
    }

    /**
     * 更新卖家信息
     *
     * @param seller xx
     *               seller.location 卖家地址
     *               seller.name 卖家姓名
     *               seller.phone 卖家电话
     *               seller.account 卖家账号(影响登录)
     *               注:传的空值不更新原先的属性
     *               更新密码无效
     * @return 成功code0, 失败code-1
     */
    @ResponseBody
    @PostMapping("/updateSellerInfo")
    public Response<Object> updateSellerInfo(@RequestBody Seller seller) {
        try {
            if (sellerService.updateInfo(seller))
                return Response.createSuc(null);
            else
                return Response.createErr("信息更新失败");
        }catch(Exception e){
            System.out.println("发生错误" + e.toString());
            return Response.BUG();
        }
    }


    @ResponseBody
    @PostMapping("/updateSellerPassword")
    public Response<Object> updateSellerPassword(@RequestBody AccountVo accountVo) {
        try {

            Long re = sellerService.updateSellerPassword(accountVo.getUserId(), accountVo.getPassword(), accountVo.getNewPassword());
            if (re == -2) return Response.createErr("密码错误");
            else if (re == 0) return Response.createErr("无此账号");
            else return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("updateSellerPassword" ,e);
            return  Response.BUG();
        }
    }


    @ResponseBody
    @PostMapping("/checkSellerPassword")
    public Response<Object> checkSellerPassword(@RequestBody AccountVo accountVo) {
        try {
            if(sellerService.checkSellerPassword(accountVo.getUserId(), accountVo.getPassword()))
                return Response.createSuc(null);
            else
                return Response.createErr("账号不存在或者密码错误");
        }catch (Exception e){
            tool.soutErr("checkSellerPassword" ,e);
            return  Response.BUG();
        }
    }



    /**
     * 开始一个交易
     *
     * @param dealVo dealVo.intentionId 意向id
     *               dealVo.buyerId  买家id
     *               dealVo.sellerId 卖家id
     *               dealVo.goodId   商品id
     * @return 成功code0, 失败返回信息
     * 注:交易开始后商品自动冻结,相应购买意向删除
     */
    @ResponseBody
    @PostMapping("/startDeal")
    public Response<DealVo> startDeal(@RequestBody DealVo dealVo) {
        try{
            sellerService.startDeal(dealVo);
            return Response.createSuc(dealVo);
        }catch (Exception e){
            tool.soutErr("startDeal" ,e) ;
            return Response.BUG();
        }
    }





    /**
     * 通过商品id得到某一商品的历史交易记录
     *
     * @param good xx
     *             good.goodId 商品id
     * @return 成功返回信息列表, 失败返回错误信息
     */
    @ResponseBody
    @PostMapping("/getDealHistfoyListByGoodId")
    public Response<DealHistoryList> getDealHistoryByGoodId(@RequestBody Good good) {
        try {
            return Response.createSuc(tool.toDealHistoryList(sellerService.getDealHistoryListByGoodId(good.getGoodId())));
        }catch (Exception e){
            tool.soutErr("getDealHistfoyListByGoodId" ,e);
            return  Response.BUG();
        }
    }


    /**
     * 通过卖家id取得卖家的全部交易记录
     *
     * @param seller xx
     *               seller.sellerId 卖家id
     * @return 成功返回交易记录列表, 失败....
     */
    @ResponseBody
    @PostMapping("/getDealHistoryBySellerId")
    public Response<DealHistoryList> getDealHistoryBySellerId(@RequestBody Seller seller) {
        try{
            return Response.createSuc(tool.toDealHistoryList(sellerService.getDealHistoryListBySellerId(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getDealHistoryBySellerId" ,e);
            return  Response.BUG();
        }
    }



    /**
     * 通过商品id拿商品意向列表
     *
     * @param good xx
     *             good.goodId 商品id
     * @return 成功返回意向列表, 失败....
     */
    @ResponseBody
    @PostMapping("/getIntentionListByGoodId")
    public Response<IntentionList> getIntentionListByGoodId(@RequestBody Good good) {
        System.out.println(good.getGoodId());
        try {
            return Response.createSuc(tool.toIntentionList(sellerService.getIntentionListByGoodId(good.getGoodId())));
        }catch (Exception e){
            tool.soutErr("getIntentionListByGoodId",e );
            return  Response.BUG();
        }
    }

    /**
     * 取消一场交易
     *
     * @param deal xx
     *             deal.dealId 交易编号
     * @return 成功code0, 失败....
     * 注:取消交易后商品自动解冻
     */
    @ResponseBody
    @PostMapping("/cancelDeal")
    public Response<Object> cancelDeal(@RequestBody Deal deal) {
        try{
            sellerService.cancelDeal(deal.getDealId());
            return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("cancelDeal",e );
            return  Response.BUG();
        }
    }

    /**
     * 达成一个交易
     *
     * @param deal xx
     *             deal.dealId 交易编号
     * @return 成功code0, 失败....
     * 注:交易达成后商品变为出售状态,同时自动解冻
     */

    @ResponseBody
    @PostMapping("/finishDeal")
    public Response<Object> finishDeal(@RequestBody Deal deal) {
        try{
            sellerService.finishDeal(deal.getDealId());
            return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("finishDeal",e );
            return  Response.BUG();
        }
    }




    /**
     * 上架一个货物
     *
     * @param goodVo xx
     *             good.sellerId 卖家id
     *             good.price    商品价格
     *             good.name     商品名称
     *             good.descript 商品描述
     * @return 上架成功返回商品id, 失败返回错误信息
     */

    @ResponseBody
    @PostMapping("/raiseGood")
    public Response<Good> raiseGood(@RequestBody GoodVo goodVo) {
        try {
            Good good = new Good(null,goodVo.getSellerId(),goodVo.getGoodPrice(),goodVo.getGoodName(),goodVo.getDescription(),null,null,null,null);
            sellerService.raiseGood(good);
            sellerService.uploadGoodImg(good.getGoodId(),goodVo.getImg());
            return Response.createSuc(good);
        }catch (Exception e){
            tool.soutErr("raiseGood" , e);
            return Response.BUG();
        }

    }


    /**
     * 下架一个商品
     *
     * @param good xx
     *             good.goodId 商品编号
     * @return 下架成功code0, 失败返回错误信息
     */
    @ResponseBody
    @PostMapping("/pullOffGood")
    public Response<Object> pullOffGood(@RequestBody Good good) {
        try {
            sellerService.pullOffGood(good.getGoodId());
            return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("pulloffGood" ,e);
            return  Response.BUG();
        }
    }



    /**
     * 给一个商品添货,从sold状态解除
     *
     * @param good xx
     *             goodId 商品编号
     * @return 成功code0, 失败....
     */
    @ResponseBody
    @PostMapping("/exhibitGood")
    public Response<Object> exhibitGood(@RequestBody Good good) {
        if (sellerService.exhibitGood(good.getGoodId()))
            return Response.createSuc(null);
        else
            return Response.createErr("商品补货失败");
    }

    @ResponseBody
    @PostMapping("/getGoodInfo")
    public Response<Good> getGoodInfo(@RequestBody Good good){
        try{
            return Response.createSuc(sellerService.getGoodInfo(good.getGoodId()));
        }catch (Exception e){
            tool.soutErr("getgoodInfo" ,e);
            return  Response.BUG();
        }
    }


    @ResponseBody
    @PostMapping("/putOnGood")
    public Response<Object> putOnGood(@RequestBody Good good) {
        try {
            sellerService.putOnGood(good.getGoodId());
            return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("putOnGood" ,e);
            return  Response.BUG();
        }
    }



    @ResponseBody
    @PostMapping("/soldOutGood")
    public Response<Object> soldOutGood(@RequestBody Good good) {
        try {
            sellerService.soldOutGood(good.getGoodId());
            return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("soldOutGood" ,e);
            return  Response.BUG();
        }
    }


    /**
     * 通过买家id查询买家的具体信息
     *
     * @param buyer xx
     *              buyer.id 买家id
     * @return 成功返回具体信息, 失败....
     */
    @ResponseBody
    @PostMapping("/getBuyerInfo")
    public Response<Buyer> getBuyerInfo(@RequestBody Buyer buyer) {
        buyer = sellerService.getBuyerInfo(buyer.getBuyerId());
        if (buyer == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(buyer);
    }

    @ResponseBody
    @PostMapping("/getAllGoodListBySellerId")
    public Response<GoodList> getAllGoodListBySellerId(@RequestBody Seller seller){
        try{
            return Response.createSuc(tool.toGoodList(sellerService.getAllGoodListBySellerId(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getAllGoodListBySellerId" ,e) ;
            return Response.BUG();
        }
    }



    @ResponseBody
    @PostMapping("/getUnremovedGoodListBySellerId")
    public Response<GoodList> getUnremovedGoodListBySellerId(@RequestBody Seller seller){
        try{
            return Response.createSuc(tool.toGoodList(sellerService.getUnremovedGoodListBySellerId(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getUnremovedGoodListBySellerId" ,e) ;
            return Response.BUG();
        }
    }


    @ResponseBody
    @PostMapping("/getWantedGoodListBySellerId")
    public Response<GoodList> getWantedGoodListBySellerId(@RequestBody Seller seller){
        try{
            return Response.createSuc(tool.toGoodList(sellerService.getWantedGoodListBySellerId(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getWantedGoodListBySellerId" ,e) ;
            return Response.BUG();
        }
    }


    @ResponseBody
    @PostMapping("/getRemovedGoodListBySellerId")
    public Response<GoodList> getRemovedGoodListBySellerId(@RequestBody Seller seller){
        try{
            return Response.createSuc(tool.toGoodList(sellerService.getRemovedGoodListBySellerId(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getRemovedGoodListBySellerId" ,e) ;
            return Response.BUG();
        }
    }
    @ResponseBody
    @PostMapping("/getUnfrozenGoodListBySellerId")
    public Response<GoodList> getUnfrozenGoodListBySellerId(@RequestBody Seller seller){
        try{
            return Response.createSuc(tool.toGoodList(sellerService.getUnfrozenGoodListBySellerId(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getUnfrozenGoodListBySellerId" ,e) ;
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getDealListByGoodId")
    public Response<DealList> getDealListByGoodId(@RequestBody Good good){
        try{

            return Response.createSuc(tool.toDealList(sellerService.getDealListByGoodId(good.getGoodId())));
        }catch (Exception e){
            tool.soutErr("getDealListByGoodId",e);
            return Response.BUG();
        }
    }
}
