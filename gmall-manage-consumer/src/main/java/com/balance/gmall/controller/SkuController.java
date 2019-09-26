package com.balance.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.response.Response;
import com.balance.gmall.service.SkuService;
import com.balance.gmall.util.response.ResponseUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * SKU=Stock Keeping Unit（库存量单位）
 *
 * 针对电商而言：
 *   1、SKU是指一款商品，每款都有出现一个SKU，便于电商品牌识别商品。
 *   2、一款商品多色，则是有多个SKU，例：一件衣服，有红色、白色、蓝色，则SKU编码也不相同，如相同则会出现混淆，发错货。
 *
 * @author: yunzhang.du
 * @date: 2019年09月25日
 * @version: v1.0
 * @since: JDK 1.8
 */
// 解决跨域问题
@CrossOrigin
@RestController
public class SkuController {

    @Reference
    private SkuService skuService;

    /**
     * 保存商品sku对象(info
     *                skuAttrValueList      sku平台属性
     *                skuSaleAttrValueList  sku销售属性值
     *                skuImageList          sku图片列表
     *                )
     * @param: pmsProductInfo 商品spu对象
     * @return: Response<Integer>
     * @throw:
     * @Date: 2019/9/24 - 10:33
     * @author: yunzhang.du
     */
    @PostMapping("saveSkuInfo")
    public Response<Integer> saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo) {
        return ResponseUtil.success(skuService.saveSkuInfo(pmsSkuInfo));
    }
}
