package com.balance.gmall.service;

import java.util.Map;

/**
 * @author: yunzhang.du
 * @date: 2019年09月27日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface ItemService {

    /**
     * 根据skuId初始化商品详情页中的信息
     * @param: skuId
     * @param:
     * @return: skuInfo 商品sku信息详情
     * @return: spuSaleAttrListCheckBySku 商品销售属性列表信息（包含是否选中信息）
     * @return: skuInfoJson skuId和商品属性映射关系
     * @throw:
     * @Date: 2019/9/27 - 11:54
     * @author: yunzhang.du
     */
    Map<String,Object>  initItem(String skuId);
}
