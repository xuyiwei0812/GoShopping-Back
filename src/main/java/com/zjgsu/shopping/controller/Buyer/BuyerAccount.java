package com.zjgsu.shopping.controller.Buyer;


import com.zjgsu.shopping.Tool.Mytool;
import com.zjgsu.shopping.interior.Buyer.pojo.Buyer;
import com.zjgsu.shopping.interior.Buyer.service.BuyerService;
import com.zjgsu.shopping.interior.Common.pojo.Address;
import com.zjgsu.shopping.interior.Common.pojo.vo.AccountVo;
import com.zjgsu.shopping.interior.Common.pojo.vo.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 账号相关接口
 *
 * 端口前缀 /api/buyer/account
 *
 * 注册买家账号 /buyerRegister
 * 更新卖家信息 /changeBuyerInfo
 */

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/buyer")
public class BuyerAccount {
    @Resource
    private BuyerService buyerService;
    @Resource
    private Mytool tool;

    /**
     * 注册买家账号,返回买家id
     * @param buyer 买家账号信息
     *
     * @return 买家id
     */

    @ResponseBody
    @PostMapping("/buyerRegister")
    public Response<Integer> buyerRegister(@RequestBody Buyer buyer) {
        try {
            if (buyerService.buyerRegister(buyer) == null)
                return Response.createErr("登录买家信息失败");
            else
                return Response.createSuc(buyer.getBuyerId());
        } catch (Exception e) {
            tool.soutErr("buyerRegister", e);
            System.out.println(buyer);
            return Response.BUG();
        }
    }

    /**
     * 更新卖家信息
     * @param buyer 买家的账号信息
     * @return 更新是否成功
     * 更新信息不完整会更新为null
     */

    @ResponseBody
    @PostMapping("/changeBuyerInfo")
    public Response<Boolean> changeBuyerInfo(@RequestBody Buyer buyer){
        try {
            return Response.createSuc(buyerService.updateBuyerInfo(buyer));
        } catch (Exception e) {
            tool.soutErr("uploadBuyerInfo", e);
            System.out.println(buyer);
            return Response.BUG();
        }
    }


    /**
     * 查看密码是否正确
     */
    @ResponseBody
    @PostMapping("/checkBuyerPassword")
    public Response<Object> checkBuyerPassword(@RequestBody AccountVo accountVo) {
        try {
            if(buyerService.checkBuyerPassword(accountVo.getUserId(), accountVo.getPassword()))
                return Response.createSuc(null);
            else
                return Response.createErr("账号不存在或者密码错误");
        }catch (Exception e){
            tool.soutErr("checkSellerPassword" ,e);
            return  Response.BUG();
        }
    }

    /**
     * 更新密码
     */
    @ResponseBody
    @PostMapping("/updateBuyerPassword")
    public Response<Object> updateBuyerPassword(@RequestBody AccountVo accountVo) {
        try {
            System.out.println(accountVo.getUserId()+" "+accountVo.getPassword()+" "+accountVo.getNewPassword());
            if(accountVo.getPassword().equals(accountVo.getNewPassword())) return Response.createErr("新密码与旧密码不能一致");
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

}
