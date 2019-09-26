package com.balance.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.sku.PmsSkuAttrValueDao;
import com.balance.gmall.dao.sku.PmsSkuImageDao;
import com.balance.gmall.dao.sku.PmsSkuInfoDao;
import com.balance.gmall.dao.sku.PmsSkuSaleAttrValueDao;
import com.balance.gmall.dictionary.PmsSkuImageField;
import com.balance.gmall.dictionary.PmsSkuSaleAttrValueField;
import com.balance.gmall.po.sku.PmsSkuAttrValue;
import com.balance.gmall.po.sku.PmsSkuImage;
import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.po.sku.PmsSkuSaleAttrValue;
import com.balance.gmall.service.SkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: yunzhang.du
 * @date: 2019年09月25日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Component
@Service(interfaceClass = SkuService.class)
public class SkuServiceImpl implements SkuService {

    @Resource
    private PmsSkuAttrValueDao pmsSkuAttrValueDao;
    @Resource
    private PmsSkuInfoDao pmsSkuInfoDao;
    @Resource
    private PmsSkuImageDao pmsSkuImageDao;
    @Resource
    private PmsSkuSaleAttrValueDao pmsSkuSaleAttrValueDao;

    @Override
    public Integer saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        int result = pmsSkuInfoDao.insert(pmsSkuInfo);
        Long pmsSkuInfoId = pmsSkuInfo.getId();
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        skuAttrValueList.forEach(skuAttrValue -> {
            skuAttrValue.setSkuId(pmsSkuInfoId);
            pmsSkuAttrValueDao.insert(skuAttrValue);
        });
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        skuImageList.forEach(skuImage -> {
            skuImage.setSkuId(pmsSkuInfoId);
            pmsSkuImageDao.insert(skuImage);
        });
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        skuSaleAttrValueList.forEach(skuSaleAttrValue -> {
            skuSaleAttrValue.setSkuId(pmsSkuInfoId);
            pmsSkuSaleAttrValueDao.insert(skuSaleAttrValue);
        });
        return result;
    }

    @Override
    public PmsSkuInfo selectPmsSkuInfoBySkuId(String skuId) {
        PmsSkuInfo pmsSkuInfo = pmsSkuInfoDao.selectById(skuId);
        if (null != pmsSkuInfo) {
            List<PmsSkuImage> pmsSkuImages = selectPmsSkuImageListBySkuId(skuId);
            if (!pmsSkuImages.isEmpty()) {
                pmsSkuInfo.setSkuImageList(pmsSkuImages);
                return pmsSkuInfo;
            }
        } else {
            pmsSkuInfo = new PmsSkuInfo();
        }
        return pmsSkuInfo;
    }

    @Override
    public PmsSkuSaleAttrValue selectPmsSkuSaleAttrValueListBySkuIdAndSaleAttrId(String skuId, Long saleAttrId) {
        QueryWrapper<PmsSkuSaleAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsSkuSaleAttrValueField.SKU_ID,skuId);
        wrapper.eq(PmsSkuSaleAttrValueField.SALE_ATTR_ID,saleAttrId);
        return pmsSkuSaleAttrValueDao.selectOne(wrapper);
    }

    @Override
    public Map<String, String> selectPmsSkuInfoHashMapBySpuId(Long productId) {
        return null;
    }

    /**
     * 根据商品skuId获取商品PmsSkuSaleAttrValue列表
     *
     * @param: skuId
     * @return: List<PmsSkuImage>
     * @throw:
     * @Date: 2019/9/26 - 11:20
     * @author: yunzhang.du
     */
    private List<PmsSkuSaleAttrValue> selectPmsSkuSaleAttrValueBySkuId(String skuId) {
        QueryWrapper<PmsSkuSaleAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsSkuSaleAttrValueField.SKU_ID, skuId);
        return pmsSkuSaleAttrValueDao.selectList(wrapper);
    }

    /**
     * 根据商品skuId获取商品PmsSkuImage列表
     *
     * @param: skuId
     * @return: List<PmsSkuImage>
     * @throw:
     * @Date: 2019/9/26 - 11:20
     * @author: yunzhang.du
     */
    private List<PmsSkuImage> selectPmsSkuImageListBySkuId(String skuId) {
        QueryWrapper<PmsSkuImage> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsSkuImageField.SKU_ID, skuId);
        return pmsSkuImageDao.selectList(wrapper);
    }

}
