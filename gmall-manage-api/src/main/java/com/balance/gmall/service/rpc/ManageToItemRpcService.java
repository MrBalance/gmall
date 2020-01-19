package com.balance.gmall.service.rpc;

import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.po.spu.PmsProductSaleAttr;

import java.util.List;
import java.util.Map;

/**
 * 向item项目提供的rpc服务
 *
 * @author: yunzhang.du
 * @date: 2019年10月06日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface ManageToItemRpcService {

    /**
     * 根据商品skuId获取商品skuInfo对象
     * @param: skuId
     * @return: PmsSkuInfo
     * @throw:
     * @Date: 2019/9/26 - 11:01
     * @author: yunzhang.du
     */
    PmsSkuInfo selectPmsSkuInfoBySkuId(String skuId);

    /**
     * 根据spuId获取spu销售属性列表被选中的信息
     * @param: spuId 平台商品信息id
     * @return:
     * @throw:
     * @Date: 2019/9/26 - 15:58
     * @author: yunzhang.du
     */
    List<PmsProductSaleAttr> selectPmsProductSaleAttrListCheckedBySpuId(Long productId, String skuId);

    /**
     * 根据spuId获取相关系列sku的映射关系
     * @param: productId
     * @return: Map<String,String>
     * @throw:
     * @Date: 2019/9/27 - 8:42
     * @author: yunzhang.du
     */
    public Map selectPmsSkuInfoJsonBySpuId(Long productId);
}
