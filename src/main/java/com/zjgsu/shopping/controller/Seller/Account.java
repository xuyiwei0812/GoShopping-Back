package com.zjgsu.shopping.controller.Seller;

import com.zjgsu.shopping.Tool.Mytool;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Common.pojo.vo.AccountVo;
import com.zjgsu.shopping.interior.Common.pojo.vo.Response;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import com.zjgsu.shopping.interior.Seller.service.SellerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/seller/account")
public class Account {

    @Resource
    private SellerService sellerService;
    @Resource
    private Mytool tool;

    @ResponseBody
    @PostMapping("/sellerRegister")
    public Response<Seller> sellerRegister(@RequestBody Seller seller) {


        try {
            if(!(tool.checkPasswordLegitimacy(seller.getPassword())))
                return Response.createErr("密码不符合规范");
            if (sellerService.searchSellerAccount(seller.getAccount()))
                return Response.createErr("账号已经存在");
            sellerService.sellerRegister(seller);
            return Response.createSuc(seller);
        }catch(Exception e){
            tool.soutErr("sellerRegister" , e);
            return Response.BUG();
        }
    }

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
            if (sellerService.updateSellerInfo(seller))
                return Response.createSuc(null);
            else
                return Response.createErr("信息更新失败");
        }catch(Exception e){
            System.out.println("发生错误" + e);
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
}
