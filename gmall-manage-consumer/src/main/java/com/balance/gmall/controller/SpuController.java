package com.balance.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.po.attr.PmsBaseSaleAttr;
import com.balance.gmall.po.spu.PmsProductImage;
import com.balance.gmall.po.spu.PmsProductInfo;
import com.balance.gmall.po.spu.PmsProductSaleAttr;
import com.balance.gmall.response.Response;
import com.balance.gmall.service.BaseAttrService;
import com.balance.gmall.service.SpuService;
import com.balance.gmall.util.FileUploadUtil;
import com.balance.gmall.util.response.ResponseUtil;
import org.csource.common.MyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * SPU = Standard Product Unit （标准产品单位）SPU是商品信息聚合的最小单位，
 * 是一组可复用、易检索的标准化信息的集合，该集合描述了一个产品的特性。
 *
 * 在商品信息电子化过程中，商品的特性可以由多个“属性|属性值对”进行描述。
 * “属性|属性值对”完全相同的商品，可以抽象成为一个SPU
 *
 * @author: yunzhang.du
 * @date: 2019年09月23日
 * @version: v1.0
 * @since: JDK 1.8
 */
// 解决跨域问题
@CrossOrigin
@RestController
public class SpuController {

    @Reference
    private SpuService spuService;

    @Reference
    private BaseAttrService baseAttrService;

    /**
     * 根据Catalog3Id获取spu列表信息
     * @param: catalog3Id 平台三级列表id
     * @return: Response<List<PmsProductInfo>>
     * @throw:
     * @Date: 2019/9/23 - 10:38
     * @author: yunzhang.du
     */
    @GetMapping("/spuList/{catalog3Id}")
    public Response<List<PmsProductInfo>> spuList(@PathVariable("catalog3Id") Long catalog3Id) {
        List<PmsProductInfo> data = spuService.selectPmsProductInfoListByCatalog3Id(catalog3Id);
        return ResponseUtil.success(data);
    }

    /**
     * 根据spuId获取spu信息
     * @param: spuId 平台商品信息id
     * @return: Response<PmsProductInfo>
     * @throw:
     * @Date: 2019/9/25 - 11:10
     * @author: yunzhang.du
     */
    @GetMapping("/getSpuInfo/{spuId}")
    public Response<PmsProductInfo> getSpuInfo(@PathVariable("spuId") Long spuId) {
        return ResponseUtil.success(spuService.selectPmsProductInfoBySpuId(spuId));
    }

    /**
     * 根据spuId获取spu销售属性列表信息
     * @param: spuId 平台商品信息id
     * @return: Response<List<PmsProductInfo>>
     * @throw:
     * @Date: 2019/9/24 - 16:47
     * @author: yunzhang.du
     */
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Response<List<PmsProductSaleAttr>> spuSaleAttrList(@PathVariable("spuId") Long spuId) {
        return ResponseUtil.success(spuService.selectPmsProductSaleAttrListBySpuId(spuId));
    }

    /**
     * 根据spuId获取spu图片列表信息
     * @param: spuId 平台商品信息id
     * @return: Response<List<PmsProductInfo>>
     * @throw:
     * @Date: 2019/9/24 - 16:47
     * @author: yunzhang.du
     */
    @GetMapping("/spuImageList/{spuId}")
    public Response<List<PmsProductImage>> spuImageList(@PathVariable("spuId") Long spuId) {
        return ResponseUtil.success(spuService.selectPmsProductImageListBySpuId(spuId));
    }

    /**
     * 根据传入平台三级分类，列表展示平台商品属性
     * @param: catalog3Id 平台三级分类
     * @return: Response<List<PmsBaseSaleAttr>>
     * @throw:
     * @Date: 2019/9/23 - 15:11
     * @author: yunzhang.du
     */
    @PostMapping("baseSaleAttrList/{catalog3Id}")
    public Response<List<PmsBaseSaleAttr>> baseSaleAttrList(@PathVariable("catalog3Id") Long catalog3Id) {
        return ResponseUtil.success(baseAttrService.selectBaseSaleAttrListByCatalog3Id(catalog3Id));
    }

    /**
     * 保存商品spu对象(info image attr)
     * @param: pmsProductInfo 商品spu对象
     * @return: Response<Integer>
     * @throw:
     * @Date: 2019/9/24 - 10:33
     * @author: yunzhang.du
     */
    @PostMapping("saveSpuInfo")
    public Response<Integer> saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {
        return ResponseUtil.success(spuService.saveSpuInfo(pmsProductInfo));
    }

    /**
     * 上传图片至fastfds服务器
     * @param: file 上传的图片文件
     * @return: Response<ImageUploadResponse>
     * @throw:
     * @Date: 2019/9/24 - 11:08
     * @author: yunzhang.du
     */
    @PostMapping("fileUpload")
    public Response<String> fileUpload(@RequestParam("file") MultipartFile file) throws IOException, MyException {
        return ResponseUtil.success(FileUploadUtil.imageUpload(file));
    }

}
