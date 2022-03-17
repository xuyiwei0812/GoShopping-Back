package com.zjgsu.shopping.controller.Seller;

import com.zjgsu.shopping.Tool.Mytool;
import com.zjgsu.shopping.interior.Common.pojo.Class1;
import com.zjgsu.shopping.interior.Common.pojo.One2Two;
import com.zjgsu.shopping.interior.Common.pojo.vo.*;
import com.zjgsu.shopping.interior.Seller.pojo.Seller;
import com.zjgsu.shopping.interior.Seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;


@Controller
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/api/seller/good")
public class Good {


    @Resource
    private SellerService sellerService;
    @Resource
    private Mytool tool;

    @Autowired
    private static String FILE_ADDRESS;

    @Value("${file_address}")//保存地址，写在application.properties里
    public void setfILE_ADDRESS(String fILE_ADDRESS) {
        FILE_ADDRESS = fILE_ADDRESS;
    }



    @ResponseBody
    @PostMapping("/getAllClass1")
    public Response<List<Class1>> getAllClass1(){
        try{
            System.out.println("getAllClass1");
            List<Class1> allClass1 = sellerService.getAllClass1();
            System.out.println(allClass1);
            return Response.createSuc(allClass1);
        }catch (Exception e){
            tool.soutErr("getAllClass",e );
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/getClass2ByClass1Id")
    public Response<List<One2Two>> getAllClass2ByClass1Id(@RequestBody Class1 classa){
        try{
            System.out.println("getClass2ByClass1Id");
            System.out.println(classa.getClass1());
            Integer classaId = classa.getClass1();
            System.out.println("classaId"+classaId);
            List<One2Two> allClass2 = sellerService.getAllClass2ByClass1Id(classaId);
            System.out.println(allClass2);
            return Response.createSuc(allClass2);
        }catch (Exception e){
            tool.soutErr("getAllClass",e );
            return Response.BUG();
        }
    }

    /**
     * 上架一个货物
     *
     * @param
     * @return 上架成功返回商品id, 失败返回错误信息
     */
    @ResponseBody
    @PostMapping("/raiseGood")
    public Response<com.zjgsu.shopping.interior.Common.pojo.Good> raiseGood(@RequestBody GoodVo goodVo) throws IllegalStateException, IOException {
        try {
            System.out.println("goodVo::"+goodVo);
            //List<MultipartFile> tryimg  = new ArrayList<>();
            //tryimg.add(file);
            //GoodVo goodVo = new GoodVo(null,1,10,1,1,1.2,"aaa","description",false,false,false,false, tryimg);
            com.zjgsu.shopping.interior.Common.pojo.Good good = new com.zjgsu.shopping.interior.Common.pojo.Good(null,goodVo.getSellerId(),goodVo.getStorage(),goodVo.getGoodPrice(),goodVo.getGoodName(),goodVo.getDescription(),null,null,null,null,goodVo.getClass2());
            sellerService.raiseGood(good);
            sellerService.uploadGoodImg(good.getGoodId(),goodVo.getImg());

            //图
//            List<MultipartFile> imgFiles = goodVo.getImg();
//            for(MultipartFile imgFile:imgFiles){
//                GoodImagine goodImagine = new GoodImagine();
//                if (!imgFile.isEmpty()) {
//                    System.out.println("收到传图片的请求");
//                    //存放地址
//                    String imgPath = FILE_ADDRESS;
//                    System.out.println("path" + imgPath);
//                    //如果父文件夹不存在 则创建文件夹 文件夹为path,视频名字file.getOriginalFilename()
//                    File filepath = new File(imgPath, imgFile.getOriginalFilename());
//                    if (!filepath.getParentFile().exists()) {
//                        filepath.getParentFile().mkdirs();
//                    }
//                    File fi = new File(imgPath + File.separator + imgFile.getOriginalFilename());
//                    //下载到本地
//                    imgFile.transferTo(fi);
//                    //获取绝对路径
//                    String picLocalAddress = fi.getAbsolutePath();
//                    System.out.println("存入本地文件地址:" + picLocalAddress);
//                    goodImagine.setImagine(picLocalAddress);
//                    System.out.println("保存本地成功");
//                    goodImagine.setGoodId(good.getGoodId());
//                    System.out.println("goodId"+good.getGoodId());
//                    //图片路径存数据库
//                    sellerService.uploadGoodImg(goodImagine);
//                    System.out.println("图片路径保存数据库成功");
//                }
//                else {
//                    System.out.println("图片为空");
//                }
//            }
//
//            //视频
//            Video video = new Video();
//            if (!file.isEmpty()) {
//                System.out.println("收到传视频的请求");
//                //存放地址
//                String path = FILE_ADDRESS;
//                System.out.println("path" + path);
//                //如果父文件夹不存在 则创建文件夹 文件夹为path,视频名字file.getOriginalFilename()
//                File filepath = new File(path, file.getOriginalFilename());
//                if (!filepath.getParentFile().exists()) {
//                    filepath.getParentFile().mkdirs();
//                }
//                File fi = new File(path + File.separator + file.getOriginalFilename());
//                //下载到本地
//                file.transferTo(fi);
//                //获取绝对路径
//                String localAddress = fi.getAbsolutePath();
//                System.out.println("存入本地文件地址:" + localAddress);
//                video.setLocalAddress(localAddress);
//                //获取后缀名
//                String suffix = localAddress.substring(localAddress.lastIndexOf("."), localAddress.length());
//                System.out.println("后缀名:" + suffix);
//                video.setSuffix(suffix);
//                System.out.println("视频保存本地成功");
//                video.setGoodId(good.getGoodId());
//                System.out.println("goodId"+good.getGoodId());
//                //视频路径存数据库
//                Boolean response1=sellerService.saveVideoToDatabase(video);
//                System.out.println("视频路径保存数据库成功");
//            }
//            else {
//                System.out.println("视频为空");
//            }

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
    public Response<Object> pullOffGood(@RequestBody com.zjgsu.shopping.interior.Common.pojo.Good good) {
        try {
            sellerService.pullOffGood(good.getGoodId());
            return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("pulloffGood" ,e);
            return  Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/pullOffMultipleGood")
    public Response<Object> pullOffMultipleGood(@RequestBody GoodIds goodIds) {
        try {
            System.out.println("multiple");
            System.out.println("goodIds"+goodIds);
            sellerService.pullOffMultipleGood(goodIds);
            return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("pullOffGood" ,e);
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
    public Response<Object> exhibitGood(@RequestBody com.zjgsu.shopping.interior.Common.pojo.Good good) {
        if (sellerService.exhibitGood(good))
            return Response.createSuc(null);
        else
            return Response.createErr("商品补货失败");
    }

    @ResponseBody
    @PostMapping("/getGoodInfo")
    public Response<com.zjgsu.shopping.interior.Common.pojo.Good> getGoodInfo(@RequestBody com.zjgsu.shopping.interior.Common.pojo.Good good){
        try{
            return Response.createSuc(sellerService.getGoodInfo(good.getGoodId()));
        }catch (Exception e){
            tool.soutErr("getgoodInfo" ,e);
            return  Response.BUG();
        }
    }


    @ResponseBody
    @PostMapping("/putOnGood")
    public Response<Object> putOnGood(@RequestBody com.zjgsu.shopping.interior.Common.pojo.Good good) {
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
    public Response<Object> soldOutGood(@RequestBody com.zjgsu.shopping.interior.Common.pojo.Good good) {
        try {
            sellerService.soldOutGood(good.getGoodId());
            return Response.createSuc(null);
        }catch (Exception e){
            tool.soutErr("soldOutGood" ,e);
            return  Response.BUG();
        }
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
    public Response<OrderList> getDealListByGoodId(@RequestBody com.zjgsu.shopping.interior.Common.pojo.Good good){
        try{

            return Response.createSuc(tool.toOrderList(sellerService.getOrderListByGoodId(good.getGoodId())));
        }catch (Exception e){
            tool.soutErr("getDealListByGoodId",e);
            return Response.BUG();
        }
    }

    @ResponseBody
    @PostMapping("/updateGoodInfo")
    public Response<com.zjgsu.shopping.interior.Common.pojo.Good> updateGoodInfo(@RequestBody com.zjgsu.shopping.interior.Common.pojo.Good good){
        try{
            return Response.createSuc(sellerService.updateGoodInfo(good));
        }catch (Exception e){
            tool.soutErr("updateGoodInfo",e);
            return Response.BUG();
        }
    }



}
