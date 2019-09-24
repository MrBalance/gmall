package com.balance.gmall.service;

import com.balance.gmall.po.spu.PmsProductInfo;

import java.util.List;

/**
 * 商品spu的api
 *
 * @author: yunzhang.du
 * @date: 2019年09月23日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface SpuService {

    /**
     * 根据Catalog3Id获取spu列表信息
     * @param: catalog3Id 平台三级分类id
     * @return: List<PmsProductInfo>
     * @throw:
     * @Date: 2019/9/23 - 10:37
     * @author: yunzhang.du
     */
    public List<PmsProductInfo> getPmsProductInfoListByCatalog3Id(Long catalog3Id);

    /**
     * 保存商品spu对象(info image attr)
     * @param: pmsProductInfo 商品spu对象
     * @return: Integer
     * @throw:
     * @Date: 2019/9/23 - 15:14
     * @author: yunzhang.du
     */
    Integer saveSpuInfo(PmsProductInfo pmsProductInfo);
}
