package com.balance.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.spu.PmsProductImageDao;
import com.balance.gmall.dao.spu.PmsProductInfoDao;
import com.balance.gmall.dao.spu.PmsProductSaleAttrDao;
import com.balance.gmall.dao.spu.PmsProductSaleAttrValueDao;
import com.balance.gmall.dictionary.PmsProductImageField;
import com.balance.gmall.dictionary.PmsProductInfoField;
import com.balance.gmall.dictionary.PmsProductSaleAttrField;
import com.balance.gmall.dictionary.PmsProductSaleAttrValueField;
import com.balance.gmall.po.spu.PmsProductImage;
import com.balance.gmall.po.spu.PmsProductInfo;
import com.balance.gmall.po.spu.PmsProductSaleAttr;
import com.balance.gmall.po.spu.PmsProductSaleAttrValue;
import com.balance.gmall.service.SpuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: yunzhang.du
 * @date: 2019年09月23日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Component
@Service(interfaceClass = SpuService.class)
@Slf4j
public class SpuServiceImpl implements SpuService {

    @Resource
    private PmsProductInfoDao pmsProductInfoDao;
    @Resource
    private PmsProductImageDao pmsProductImageDao;
    @Resource
    private PmsProductSaleAttrDao pmsProductSaleAttrDao;
    @Resource
    private PmsProductSaleAttrValueDao pmsProductSaleAttrValueDao;

    @Override
    public List<PmsProductInfo> selectPmsProductInfoListByCatalog3Id(Long catalog3Id) {
        QueryWrapper<PmsProductInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsProductInfoField.CATALOG3_ID, catalog3Id);
        return pmsProductInfoDao.selectList(wrapper);
    }

    @Override
    @Transactional
    public Integer saveSpuInfo(PmsProductInfo pmsProductInfo) {
        int result = pmsProductInfoDao.insert(pmsProductInfo);
        Long id = pmsProductInfo.getId();
        List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
        spuImageList.forEach(spuImage -> {
            spuImage.setProductId(id);
            pmsProductImageDao.insert(spuImage);
        });
        List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        spuSaleAttrList.forEach(spuSaleAttr -> {
            spuSaleAttr.setProductId(id);
            saveSpuSaleAttr(spuSaleAttr);
        });
        return result;
    }

    @Override
    public List<PmsProductSaleAttr> selectPmsProductSaleAttrListBySpuId(Long spuId) {
        QueryWrapper<PmsProductSaleAttr> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsProductSaleAttrField.PRODUCT_ID,spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = pmsProductSaleAttrDao.selectList(wrapper);
        pmsProductSaleAttrs.forEach(pmsProductSaleAttr -> {
            Long saleAttrId = pmsProductSaleAttr.getSaleAttrId();
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = selectPmsProductSaleAttrListBySpuIdAndSaleAttrId(spuId,saleAttrId);
            pmsProductSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
        });
        return pmsProductSaleAttrs;
    }

    @Override
    public List<PmsProductImage> selectPmsProductImageListBySpuId(Long spuId) {
        QueryWrapper<PmsProductImage> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsProductImageField.PRODUCT_ID,spuId);
        return  pmsProductImageDao.selectList(wrapper);
    }

    @Override
    public PmsProductInfo selectPmsProductInfoBySpuId(Long spuId) {
        PmsProductInfo pmsProductInfo = pmsProductInfoDao.selectById(spuId);
        List<PmsProductImage> pmsProductImages = selectPmsProductImageListBySpuId(spuId);
        pmsProductInfo.setSpuImageList(pmsProductImages);
        List<PmsProductSaleAttr> pmsProductSaleAttrs = selectPmsProductSaleAttrListBySpuId(spuId);
        pmsProductInfo.setSpuSaleAttrList(pmsProductSaleAttrs);
        return pmsProductInfo;
    }




    /**
     * 根据spuId和saleAttrId获取spu销售属性值列表信息
     * @param: spuId 平台商品信息id
     * @param: saleAttrId 销售属性id
     * @return:
     * @throw:
     * @Date: 2019/9/24 - 17:12
     * @author: yunzhang.du
     */
    private List<PmsProductSaleAttrValue> selectPmsProductSaleAttrListBySpuIdAndSaleAttrId(Long spuId, Long saleAttrId) {
        QueryWrapper<PmsProductSaleAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsProductSaleAttrValueField.PRODUCT_ID,spuId);
        wrapper.eq(PmsProductSaleAttrValueField.SALE_ATTR_ID,saleAttrId);
        return pmsProductSaleAttrValueDao.selectList(wrapper);
    }

    /**
     * 保存商品属性和商品属性值
     *
     * @param: spuSaleAttr 商品属性对象
     * @return: Integer
     * @throw:
     * @Date: 2019/9/23 - 15:38
     * @author: yunzhang.du
     */
    private void saveSpuSaleAttr(PmsProductSaleAttr spuSaleAttr) {
        pmsProductSaleAttrDao.insert(spuSaleAttr);
        Long productId = spuSaleAttr.getProductId();
        List<PmsProductSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
        spuSaleAttrValueList.forEach(spuSaleAttrValue -> {
            spuSaleAttrValue.setProductId(productId);
            pmsProductSaleAttrValueDao.insert(spuSaleAttrValue);
        });
    }

}
