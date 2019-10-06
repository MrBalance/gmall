package com.balance.gmall.service;

import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.po.sku.PmsSkuSaleAttrValue;

/**
 * @author: yunzhang.du
 * @date: 2019年09月25日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface SkuService {

    /**
     * 保存商品sku对象(info
     *                skuAttrValueList      sku平台属性
     *                skuSaleAttrValueList  sku销售属性值
     *                skuImageList          sku图片列表
     *                )
     * @param: pmsProductInfo 商品spu对象
     * @return: Integer
     * @throw:
     * @Date: 2019/9/25 - 15:06
     * @author: yunzhang.du
     */
    Integer saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    /**
     * 根据skuId和saleAttrId获取sku销售属性值列表信息
     * @param: skuId
     * @param: saleAttrId 销售属性id
     * @return:
     * @throw:
     * @Date: 2019/9/26 - 16:19
     * @author: yunzhang.du
     */
    PmsSkuSaleAttrValue selectPmsSkuSaleAttrValueListBySkuIdAndSaleAttrId(String skuId, Long saleAttrId);
}
