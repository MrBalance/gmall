package com.balance.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.po.spu.PmsProductSaleAttr;
import com.balance.gmall.service.SkuService;
import com.balance.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author: yunzhang.du
 * @date: 2019年09月26日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Controller
public class ItemController {

    @Reference
    private SpuService spuService;
    @Reference
    private SkuService skuService;

    @GetMapping("{skuId}.html")
    public String item(@PathVariable String skuId, ModelMap modelMap) {
        PmsSkuInfo pmsSkuInfo = skuService.selectPmsSkuInfoBySkuId(skuId);
        Long productId = pmsSkuInfo.getProductId();
        if(null != productId) {
            List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.selectPmsProductSaleAttrListCheckedBySpuId(productId,skuId);
            skuService.selectPmsSkuInfoHashMapBySpuId(productId);
            modelMap.put("skuInfo",pmsSkuInfo);
            modelMap.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);
        }
        return "item";
    }
}
