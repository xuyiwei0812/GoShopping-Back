package com.zjgsu.shopping.controller.Buyer;

import com.zjgsu.shopping.Tool.Mytool;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.pojo.Address;
import com.zjgsu.shopping.interior.Common.pojo.BuyerWithGood;
import com.zjgsu.shopping.interior.Common.pojo.Cart;
import com.zjgsu.shopping.interior.Common.pojo.Order;
import com.zjgsu.shopping.interior.Common.pojo.vo.*;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/buyer")
public class Good {
    @Resource
    private BuyerService buyerService;
    @Resource
    private Mytool tool;

    //FB : for buyer
    @ResponseBody
    @PostMapping("/getAllGoodListFB")
    public Response<GoodList> getAllGoodListFB(){
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
    public Response<GoodwithImg> getGoodInfo(@RequestBody GoodVo good) {
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
            List<com.zjgsu.shopping.interior.Common.pojo.Good> goodList = buyerService.searchGood(keyword);
            GoodList goodList1 = tool.toGoodList(goodList);
            return Response.createSuc(goodList1);
        }
        catch (Exception e){
            tool.soutErr("searchGood" ,e);
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
     * 购物车加收藏
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/getCartGoodIntoFavorite")
    public Response<Boolean> getCartGoodIntoFavorite(@RequestBody CartIds cartIds) {
        try{
            System.out.println("cart"+cartIds.getCartIds());
            System.out.println("购物车加收藏");
            return Response.createSuc(buyerService.getCartGoodIntoFavorite(cartIds));
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
     * 删购物车商品
     * @param cartIds
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteCartGood")
    public Response<Boolean> deleteCartGood(@RequestBody CartIds cartIds){
        try {
            System.out.println("删购物车商品");
            return Response.createSuc(buyerService.deleteCartGood(cartIds));
        }
        catch (Exception e){
            tool.soutErr("deleteCartGood",e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/deleteFavoriteGood")
    public Response<Boolean> deleteFavoriteGood(@RequestBody FavoriteIds favoriteIds){
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
