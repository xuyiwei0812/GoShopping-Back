package com.zjgsu.shopping.controller;

import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.pojo.*;
import com.zjgsu.shopping.interior.Common.pojo.vo.*;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import com.zjgsu.shopping.Tool.Mytool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/buyer")
public class BuyerController {
    @Resource
    private BuyerService buyerService;
    @Resource
    private Mytool tool;




    @ResponseBody
    @PostMapping("/updateBuyerPassword")
    public Response<Object> updateBuyerPassword(@RequestBody AccountVo accountVo) {
        try {
            System.out.println(accountVo.getUserId()+" "+accountVo.getPassword()+" "+accountVo.getNewPassword());
            Long re = buyerService.updateBuyerPassword(accountVo.getUserId(), accountVo.getPassword(), accountVo.getNewPassword());
            if (re == -2) return Response.createErr("密码错误");
            else if (re == 0) return Response.createErr("无此账号");
            else return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("updateSellerPassword" ,e);
            return  Response.BUG();
        }
    }

    /**
     * 提出一个订单
     *
     * 改自raiseIntention
     *

     * @return 成功返回订单编号, 失败....
     * 注:在登录一个意向的时候需要调用两个接口,后调用这个,然后拿着上面那个返回的id来调用这个
     */
    @ResponseBody
    @PostMapping("/placeAnOrder")
    public Response<Integer> placeAnOrder(@RequestBody OrderVo order) {
        try {

            System.out.println(order);
            if (buyerService.placeAnOrder(order))
                return Response.createSuc(order.getOrderId());
            else return Response.createErr("提出订单失败");
        } catch (Exception e) {
            tool.soutErr("placeAnOrder", e);
            return Response.BUG();
        }
    }


    //FB : for buyer
    @ResponseBody
    @PostMapping("/getAllGoodListFB")
    public Response<GoodList> getAllGoodListFB() {
        try {
            //System.out.println("1"+buyerService.getAllGoodListForBuyers());
            GoodList goodList = tool.toGoodList(buyerService.getAllGoodListForBuyers());
            //System.out.println("2"+goodList);
            return Response.createSuc(goodList);
        } catch (Exception e) {
            tool.soutErr("getAllGoodListFB", e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getUnfrozenGoodListFB")
    public Response<GoodList> getUnfrozenGoodListFB(){
        try {
            return Response.createSuc(tool.toGoodList(buyerService.getUnfrozenGoodListForBuyers()));
        }catch (Exception e){
            tool.soutErr("getUnforzenGoodFB" , e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getAllGoodListBySellerIdFB")
    public Response<GoodList> getAllGoodListBySellerIdFB(@RequestBody Seller seller){
        try{
            return Response.createSuc(tool.toGoodList(buyerService.getAllGoodListBySellerIdForBuyers(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getAllGoodListBySellerIdFB" , e);
            return Response.BUG();

        }
    }

    @ResponseBody
    @PostMapping("/getUnfrozenGoodListBySellerIdFB")
    public Response<GoodList> getUnfrozenGoodListBySellerIdFB(@RequestBody Seller seller){
        try{
            return Response.createSuc(tool.toGoodList(buyerService.getUnfrozenGoodListBySellerIdForBuyers(seller.getSellerId())));
        }catch (Exception e){
            tool.soutErr("getAllGoodListBySellerIdFB" , e);
            return Response.BUG();
        }

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
        try {
            GoodwithImg re = buyerService.getGoodInfo(good.getGoodId());
            if (re == null)
                return Response.createErr("查询失败");
            else
                return Response.createSuc(re);

        } catch (Exception e) {
            tool.soutErr("getGoodInfo" , e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getVideoByGood")
    public Response<String> getVideoByGood(@RequestBody Good good){
        try{
            return Response.createSuc(buyerService.getVideoByGood(good));
        }
        catch (Exception e){
            System.out.println("发生错误" + e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getBuyerHistoryByBuyerId")
    public Response< OrderList> getBuyerHistoryByBuyerId(@RequestBody Buyer buyer){
        try {
            System.out.println(buyer);
            System.out.println("1 "+buyer.getBuyerId());
            OrderList list = tool.toOrderList(buyerService.getBuyerHistory(buyer.getBuyerId()));
            return Response.createSuc(list);
        }catch (Exception e){
            tool.soutErr("getBuyerHistoryByBuyerId" ,e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getClass2GoodListByClassId")
    public Response<GoodList> getClass2GoodListByClassId(@RequestBody Mode mode){
        try{
            System.out.println(mode);
            System.out.println("收到一个查类2");
            GoodList list = tool.toGoodList(buyerService.getClass2GoodListByClassId(mode));
            System.out.println(list);
            return Response.createSuc(list);
        }catch (Exception e){
            tool.soutErr("getClass2GoodListByClassId" ,e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getClass1GoodListByClassId")
    public Response<GoodList> getClass1GoodListByClassId(@RequestBody Mode mode){
        try{
            System.out.println(mode);
            GoodList list = tool.toGoodList(buyerService.getClass1GoodListByClassId(mode));
            return Response.createSuc(list);
        }catch (Exception e){
            tool.soutErr("getClass1GoodListByClassId" ,e);
            return Response.BUG();
        }
    }


    @ResponseBody
    @PostMapping("/searchGood")
    public Response<GoodList> searchGood(@RequestBody String keyword0){
        try {
            System.out.println("收到了一个搜索的请求");
            Integer begin=0,end=0;
            begin=keyword0.indexOf(":");
            end=keyword0.lastIndexOf(",");
            String keyword=keyword0.substring(begin+2,end-1);
            System.out.println("keyword:" + keyword);
            Integer len = keyword.length();
            if(keyword.substring(len-1).equals("=")){
                System.out.println("=");
                keyword=keyword.substring(0, len-1);
            }
            System.out.println("now keyword:"+keyword);
            List<Good> goodList = buyerService.searchGood(keyword);
            GoodList goodList1 = tool.toGoodList(goodList);
            return Response.createSuc(goodList1);
        }
        catch (Exception e){
            tool.soutErr("searchGood" ,e);
            return Response.BUG();
        }
    }
    @ResponseBody
    @PostMapping("/getOrderListOfStatement")
    public Response<OrderList> getOrderListOfStatement(@RequestBody Statement st){
        try{
            Integer code = st.getStatement();Integer buyerId = st.getBuyerId();
            List<Order> li = new ArrayList<>();
            if(code == 1)li = buyerService.getOrderListOfStatement1(buyerId);
            else if (code == 2) li = buyerService.getOrderListOfStatement2(buyerId);
            else if (code == 5) li = buyerService.getOrderListOfStatement5(buyerId);
            else if (code == 6) li = buyerService.getOrderListOfStatement6(buyerId);
            else if (code == -1) li = buyerService.getOrderListOfStatement_1(buyerId);
            System.out.println("321" + li);
            OrderList ord = tool.toOrderList(li);
            System.out.println("123" + ord);
            return Response.createSuc(tool.toOrderList(li));
        }catch (Exception e){

            return Response.BUG();
        }
    }

    /**
     * 收藏商品
     * @param buyerWithGood
     * @return
     */
    @ResponseBody
    @PostMapping("/favoriteGood")
    public Response<String> favoriteGood(@RequestBody BuyerWithGood buyerWithGood) {
        try{
            System.out.println("收藏商品");
            System.out.println("goodId" + buyerWithGood.getGoodId());
            System.out.println("buyerId" + buyerWithGood.getBuyerId());
            Boolean check = buyerService.checkFavorite(buyerWithGood.getBuyerId(),buyerWithGood.getGoodId());
            System.out.println("check"+check);
            if(check==true){
                System.out.println("已经收藏过了");
                return Response.createSuc("already");
            }
            Integer n = buyerService.favoriteGood(buyerWithGood.getGoodId(),buyerWithGood.getBuyerId());
            System.out.println("n"+n);
            return Response.createSuc("successful");
        }
        catch (Exception e){
            tool.soutErr("favoriteGood",e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getFavoriteByBuyer")
    public Response<List<FavoriteGoodWithImg>> getFavoriteByBuyer(@RequestBody Buyer buyer) {
        try{
            System.out.println("查看收藏商品");
            return Response.createSuc(buyerService.getFavoriteByBuyer(buyer));
        }
        catch (Exception e){
            tool.soutErr("getFavoriteByBuyer" ,e);
            return Response.BUG();
        }
    }

    /**
     * 购物车
     * @param buyerWithGoodAndNumber
     * @return
     */
    @ResponseBody
    @PostMapping("/addGoodIntoCart")
    public Response<Boolean> addGoodIntoCart(@RequestBody BuyerWithGoodAndNumber buyerWithGoodAndNumber) {
        try {
            System.out.println("商品加购物车");
            System.out.println("goodId" + buyerWithGoodAndNumber.getGoodId());
            System.out.println("buyerId" + buyerWithGoodAndNumber.getBuyerId());
            System.out.println("number"+buyerWithGoodAndNumber.getNumber());
            return Response.createSuc(buyerService.getGoodIntoCart(buyerWithGoodAndNumber.getGoodId(),buyerWithGoodAndNumber.getBuyerId(),buyerWithGoodAndNumber.getNumber()));
        }
        catch (Exception e){
            tool.soutErr("getGoodIntoCart" ,e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getCartByBuyerId")
    public Response<List<CartWithImg>> getCartByBuyer(@RequestBody Buyer buyer) {
        try{
            System.out.println("查看购物车");
            System.out.println("buyer"+buyer);
            return Response.createSuc(buyerService.getCartByBuyer(buyer));
        }
        catch (Exception e){
            tool.soutErr("getCartByBuyer" ,e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/changeCartNumber")
    public Response<Boolean> changeCartNumber(@RequestBody BuyerWithGoodAndNumber buyerWithGoodAndNumber) {
        try{
            System.out.println("改购物车商品数量");
            System.out.println("buyerId"+buyerWithGoodAndNumber.getBuyerId());
            System.out.println("goodId"+buyerWithGoodAndNumber.getGoodId());
            System.out.println("number"+buyerWithGoodAndNumber.getNumber());
            return Response.createSuc(buyerService.changeCartNumber(buyerWithGoodAndNumber.getBuyerId(),buyerWithGoodAndNumber.getGoodId(),buyerWithGoodAndNumber.getNumber()));
        }
        catch (Exception e){
            tool.soutErr("changeCartNumber" ,e);
            return Response.BUG();
        }
    }

    /**
     * 收藏加购物车
     * @param cartList
     * @return
     */
    @ResponseBody
    @PostMapping("/getCartGoodIntoFavorite")
    public Response<Boolean> getCartGoodIntoFavorite(@RequestBody List<Cart> cartList) {
        //传的cart里goodId buyerId必须有，其他可以没有
        try{
            System.out.println("cart"+cartList);
            System.out.println("购物车加收藏");
            return Response.createSuc(buyerService.getCartGoodIntoFavorite(cartList));
        }
        catch (Exception e){
            tool.soutErr("getFavoriteGoodIntoCart",e);
            return Response.BUG();
        }
    }

    /**
     * 购物车下单多个商品
     * @param orderList
     * @return
     */
    @ResponseBody
    @PostMapping("/orderGoodsFromCart")
    public Response<Boolean> orderGoodsFromCart(@RequestBody List<Order> orderList){
        try{
            System.out.println("orderList"+orderList);
            System.out.println("购物车下单");
            for(Order item:orderList){
                buyerService.placeAnOrder(item);
            }
            return Response.createSuc(true);
        }
        catch (Exception e){
            tool.soutErr("getFavoriteGoodIntoCart",e);
            return Response.BUG();
        }
    }

    /**
     * 拿某个人的地址
     * @param buyer
     * @return
     */
    @ResponseBody
    @PostMapping("/getAddressByBuyer")
    public Response<List<Address>> getAddressByBuyer(@RequestBody Buyer buyer) {
        try {
            System.out.println("拿某个人的地址");
            Integer buyerId = buyer.getBuyerId();
            return Response.createSuc(buyerService.getAddressByBuyer(buyerId));
        }
        catch (Exception e){
            tool.soutErr("getAddressByBuyer",e);
            return Response.BUG();
        }
    }

    /**
     * 买家取消订单
     * @param order
     * @return
     */
    @ResponseBody
    @PostMapping("/buyerCancelOrder")
    public Response<Long> buyerCancelOrder(@RequestBody Order order){
        try {
            System.out.println("买家取消订单");
            Integer orderId = order.getOrderId();
            return Response.createSuc(buyerService.buyerCancelOrder(orderId));
        }
        catch (Exception e){
            tool.soutErr("buyerCancelOrder",e);
            return Response.BUG();
        }
    }

    /**
     * 买家确认收货
     * @param order
     * @return
     */
    @ResponseBody
    @PostMapping("/buyerConfirmReceipt")
    public Response<Boolean> buyerConfirmReceipt(@RequestBody Order order){
        try {
            System.out.println("买家确认收货");
            Integer orderId = order.getOrderId();
            return Response.createSuc(buyerService.buyerConformReceipt(orderId));
        }
        catch (Exception e){
            tool.soutErr("buyerConformReceipt",e);
            return Response.BUG();
        }
    }

    /**
     * 删购物车商品
     * @param cartList
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteCartGood")
    public Response<Boolean> deleteCartGood(@RequestBody List<Cart> cartList){
        //cartlist里只要有cartId就行 剩下无所谓有没有
        try {
            System.out.println("删购物车商品");
            return Response.createSuc(buyerService.deleteCartGood(cartList));
        }
        catch (Exception e){
            tool.soutErr("deleteCartGood",e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/deleteFavoriteGood")
    public Response<Boolean> deleteFavoriteGood(@RequestBody FavoriteIds favoriteIds){
        //只要有favoriteId就行 剩下无所谓有没有
        try {
            System.out.println("删收藏夹商品");
            System.out.println("favoriteIds"+favoriteIds.getFavoriteIds());
            return Response.createSuc(buyerService.deleteFavoriteGood(favoriteIds));
        }
        catch (Exception e){
            tool.soutErr("deleteFavoriteGood",e);
            return Response.BUG();
        }
    }



    @ResponseBody
    @PostMapping("/checkFavorite")
    public Response<Boolean> checkFavorite(@RequestBody BuyerWithGood buyerWithGood){
        try {
            System.out.println("查有没有收藏过");
            System.out.println("buyer" + buyerWithGood.getBuyerId());
            System.out.println("goodId" + buyerWithGood.getGoodId());
            Boolean bo = buyerService.checkFavorite(buyerWithGood.getBuyerId(),buyerWithGood.getGoodId());
            System.out.println("bo"+bo);
            return Response.createSuc(bo);
        }
        catch (Exception e){
            tool.soutErr("checkFavorite",e);
            return Response.BUG();
        }
    }
}
