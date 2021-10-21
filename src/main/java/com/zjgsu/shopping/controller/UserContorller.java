package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.pojo.*;
import com.zjgsu.shopping.pojo.vo.*;
import com.zjgsu.shopping.service.BuyerService;
import com.zjgsu.shopping.service.SellerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

//Attention : 增删改查只有查是get
@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api")
public class UserContorller {
    @Resource
    private SellerService sellerService;

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
    @PostMapping("/register")
    public Response<Integer> register(@RequestBody Seller seller) {
        System.out.println("收到一条注册请求,注册账号为 " + seller.getAccount() + ",密码为" + seller.getPassword());
        String msg = "";
        if (sellerService.searchAccount(seller.getAccount())) {
            msg = "账号已经存在";
        } else if (seller.getPassword().length() < 6 || seller.getPassword().length() > 12) {
            msg = "密码长度必须大于等于6位，小于等于12位";
        } else if (!seller.getPassword().matches("^(?![A-Za-z0-9]+$)(?![a-z0-9\\W]+$)(?![A-Za-z\\W]+$)(?![A-Z0-9\\W]+$)[a-zA-Z0-9\\W]{1,}$"))
            msg = "必须至少有1个数字、1个大写字母、1个小写字母和其它字符共同组成";
        sellerService.register(seller);
        Integer response = seller.getSellerId();
        System.out.println("注册成功");
        return Response.response(response, msg);
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
    @PostMapping("/login")
    public Response<Integer> login(@RequestBody AccountVo accountVo) {
        System.out.println("接收到登录请求");
        System.out.println(accountVo);
        Integer response = sellerService.login(accountVo.getAccount(), accountVo.getPassword());
        System.out.println(response);
        if (response != -1)
            return Response.createSuc(response);
        else
            return Response.createErr("账号不存在或者密码错误");
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
        if (sellerService.updateInfo(seller))
            return Response.createSuc(null);
        else
            return Response.createErr("信息更新失败");
    }

    /**
     * 上架一个货物
     *
     * @param good xx
     *             good.sellerId 卖家id
     *             good.price    商品价格
     *             good.name     商品名称
     *             good.descript 商品描述
     * @return 上架成功返回商品id, 失败返回错误信息
     */

    @ResponseBody
    @PostMapping("/putOnGood")
    public Response<Integer> putOnGood(@RequestBody Good good) {
        if (sellerService.putOnGood(good) != null)
            return Response.createSuc(sellerService.putOnGood(good).getGoodId());
        else
            return Response.createErr("提交商品失败");
    }

    /**
     * 下架一个商品
     *
     * @param good xx
     *             good.goodId 商品编号
     * @return 下架成功code0, 失败返回错误信息
     */
    @ResponseBody
    @PostMapping("/putOffGood")
    public Response<Object> putOffGood(@RequestBody Good good) {
        if (sellerService.putOffGood(good.getGoodId()))
            return Response.createSuc(null);
        else
            return Response.createErr("下架失败,请稍后重试");
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
    public Response<Object> startDeal(@RequestBody DealVo dealVo) {
        Deal deal = new Deal(null, dealVo.getBuyerId(), dealVo.getSellerId(), dealVo.getGoodId());
        buyerService.cancelIntention(dealVo.getIntentionId());
        if (sellerService.startDeal(deal))
            return Response.createSuc(null);
        else
            return Response.createErr("交易创建失败,请重试");
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
        DealHistoryList li = sellerService.getDealHistoryByGoodId(good.getGoodId());
        if (li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);

    }

    /**
     * 通过商品id取得商品具体信息
     *
     * @param good xx
     *             good.goodId 商品编号
     * @return 成功返回商品详细信息, 失败返回信息
     */
    @ResponseBody
    @PostMapping("/getGoodInfo")
    public Response<GoodwithImg> getGoodInfo(@RequestBody Good good) {
        System.out.println("收到对特定商品信息的查询,商品id为" + good.toString());
        GoodwithImg re = sellerService.getGoodInfo(good.getGoodId());
        if (re == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(re);
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
        System.out.println("收到查询特定卖家的交易历史");
        DealHistoryList li = sellerService.getDealHistoryListBySellerId(seller.getSellerId());
        if (li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);
    }

    /**
     * 通过卖家id取得卖家全部商品
     *
     * @param seller 卖家id
     * @return 成功返回商品列表, 失败....
     */
//    @ResponseBody
//    @GetMapping("/getGoodListBySellerId")
    @ResponseBody
    @PostMapping("/getGoodListBySellerId")
    public Response<GoodList> getGoodListBySellerId(@RequestBody Seller seller) {
        System.out.println("收到对于特定卖家商品信息的查询");
        GoodList li = sellerService.getGoodListBySellerId(seller.getSellerId());

        if (li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);
    }

    @ResponseBody
    @PostMapping("/getWantedGoodListBySellerId")
    public Response<GoodList> getWantedGoodListBySellerId(@RequestBody Seller seller) {
        System.out.println("收到对于有意向购买人的卖家商品信息");
        GoodList li = sellerService.getWantedGoodListBySellerId(seller.getSellerId());

        if (li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);
    }

    /**
     * 取得全部商品信息
     *
     * @return 成功返回商品列表, 失败....
     */
    @ResponseBody
    @GetMapping("/getAllGoodList")
    public Response<GoodList> getAllGoodList() {
        System.out.println("收到一个获取全部商品信息的请求");
        GoodList li = buyerService.getAllGoodList();
        if (li == null)
            return Response.createErr("查询失败");
        else
            return Response.createSuc(li);
    }

    /**
     * @param accountVo xx
     *                  accountVo.userId 用户编号
     *                  accountVo.password 新密码
     *                  accountVo.oldPassword 旧密码
     * @return 成功返回code0, 失败返回错误信息
     * 注:返回已经区分更新失败和旧密码错误
     * 命名稍有区别
     */
    @ResponseBody
    @PostMapping("/updatePassword")
    public Response<Object> updatePassword(@RequestBody AccountVo accountVo) {
        System.out.println("收到一个修改密码的请求");
        System.out.println(accountVo);
        Long re = sellerService.updatePassword(accountVo.getUserId(), accountVo.getPassword(), accountVo.getNewPassword());
        if (re == -2) return Response.createErr("密码错误");
        else if (re == 0) return Response.createErr("无此账号");
        else return Response.createSuc(null);
    }

    @ResponseBody
    @PostMapping("/checkPassword")
    public Response<Object> checkPassword(@RequestBody AccountVo accountVo) {
        System.out.print("收到检验密码正确性的请求 ");
        System.out.println(accountVo);
        if (sellerService.checkPassword(accountVo.getUserId(), accountVo.getPassword())) {
            return Response.createSuc(null);
        } else {
            return Response.createErr("密码不正确");
        }

    }

    /**
     * 登录买家信息
     *
     * @param buyer xx
     *              buyer.name 买家名称
     *              buyer.location 买家地址
     *              buyer.phone 买家电话
     * @return 成功返回买家id, 失败....
     * 注:在登录一个意向的时候需要调用两个接口,先调用这个,然后拿着这个返回的id去调用下面的那个
     */
    @ResponseBody
    @PostMapping("/uploadBuyerInfo")
    public Response<Integer> uploadBuyerInfo(@RequestBody Buyer buyer) {
        System.out.println("收到一个添加买家的请求 :" + buyer);
        if (buyerService.createBuyer(buyer) == null)
            return Response.createErr("登录买家信息失败");
        else
            return Response.createSuc(buyer.getBuyerId());
    }

    /**
     * 提出一个意向
     *
     * @param intention xx
     *                  intention.goodId 商品id
     *                  intention.buyerId 卖家id
     * @return 成功返回意向编号, 失败....
     * 注:在登录一个意向的时候需要调用两个接口,后调用这个,然后拿着上面那个返回的id来调用这个
     */
    @ResponseBody
    @PostMapping("/raiseIntention")
    public Response<Integer> raiseIntention(@RequestBody Intention intention) {
        System.out.println("收到一个添加意向的请求");
        if (buyerService.raiseIntention(intention.getBuyerId(), intention.getGoodId()))
            return Response.createSuc(intention.getIntentionId());
        else return Response.createErr("意向添加失败");
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
        System.out.println("收到查询商品意向购买人的请求");
        if (good.getGoodId() == null) return Response.createErr("参数错误");
        System.out.println("商品id为" + good.getGoodId().toString());
        IntentionList li = sellerService.getIntentionListByGoodId(good.getGoodId());
        if (li == null) return Response.createErr("查询失败");
        else return Response.createSuc(li);
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
        if (sellerService.cancelDeal(deal.getDealId()))
            return Response.createSuc(null);
        else
            return Response.createErr("交易取消失败");
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
        if (sellerService.finishDeal(deal.getDealId(), new Date()))
            return Response.createSuc(null);
        else
            return Response.createErr("交易结束失败");
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

    //    @ResponseBody
//    @PostMapping("/getDealByGoodId")
//    public Response<Deal>
//
//
//
//
    @ResponseBody
    @GetMapping("/test")
    public int test(@Param("x") int x) {
        return x + 1;
    }

}
