package com.balance.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.sku.PmsSkuAttrValueDao;
import com.balance.gmall.dao.sku.PmsSkuImageDao;
import com.balance.gmall.dao.sku.PmsSkuInfoDao;
import com.balance.gmall.dao.sku.PmsSkuSaleAttrValueDao;
import com.balance.gmall.dictionary.PmsSkuSaleAttrValueField;
import com.balance.gmall.po.sku.PmsSkuAttrValue;
import com.balance.gmall.po.sku.PmsSkuImage;
import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.po.sku.PmsSkuSaleAttrValue;
import com.balance.gmall.service.SkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: yunzhang.du
 * @date: 2019年09月25日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Component
@Service(interfaceClass = SkuService.class)
@Slf4j
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
    public PmsSkuSaleAttrValue selectPmsSkuSaleAttrValueListBySkuIdAndSaleAttrId(String skuId, Long saleAttrId) {
        QueryWrapper<PmsSkuSaleAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsSkuSaleAttrValueField.SKU_ID, skuId);
        wrapper.eq(PmsSkuSaleAttrValueField.SALE_ATTR_ID, saleAttrId);
        return pmsSkuSaleAttrValueDao.selectOne(wrapper);
    }



}
