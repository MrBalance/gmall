package com.balance.gmall.service;

import com.balance.gmall.po.spu.PmsProductImage;
import com.balance.gmall.po.spu.PmsProductInfo;
import com.balance.gmall.po.spu.PmsProductSaleAttr;

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
    public List<PmsProductInfo> selectPmsProductInfoListByCatalog3Id(Long catalog3Id);

    /**
     * 保存商品spu对象(info image attr)
     * @param: pmsProductInfo 商品spu对象
     * @return: Integer
     * @throw:
     * @Date: 2019/9/23 - 15:14
     * @author: yunzhang.du
     */
    Integer saveSpuInfo(PmsProductInfo pmsProductInfo);

    /**
     * 根据spuId获取spu销售属性列表信息
     * @param: spuId 平台商品信息id
     * @return: Response<List<PmsProductInfo>>
     * @throw:
     * @Date: 2019/9/24 - 16:47
     * @author: yunzhang.du
     */
    List<PmsProductSaleAttr> selectPmsProductSaleAttrListBySpuId(Long spuId);

    /**
     * 根据spuId获取spu图片列表信息
     * @param: spuId 平台商品信息id
     * @return: Response<List<PmsProductInfo>>
     * @throw:
     * @Date: 2019/9/24 - 16:47
     * @author: yunzhang.du
     */
    List<PmsProductImage> selectPmsProductImageListBySpuId(Long spuId);

    /**
     * 根据spuId获取spu信息
     * @param: spuId 平台商品信息id
     * @return: Response<List<PmsProductInfo>>
     * @throw:
     * @Date: 2019/9/25 - 11:10
     * @author: yunzhang.du
     */
    PmsProductInfo selectPmsProductInfoBySpuId(Long spuId);

    /**
     * 根据spuId获取spu销售属性列表被选中的信息
     * @param: spuId 平台商品信息id
     * @return:
     * @throw:
     * @Date: 2019/9/26 - 15:58
     * @author: yunzhang.du
     */
    List<PmsProductSaleAttr> selectPmsProductSaleAttrListCheckedBySpuId(Long productId, String skuId);
}
