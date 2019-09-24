package com.balance.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.spu.PmsProductImageDao;
import com.balance.gmall.dao.spu.PmsProductInfoDao;
import com.balance.gmall.dao.spu.PmsProductSaleAttrDao;
import com.balance.gmall.dao.spu.PmsProductSaleAttrValueDao;
import com.balance.gmall.dictionary.PmsProductInfoField;
import com.balance.gmall.po.spu.PmsProductImage;
import com.balance.gmall.po.spu.PmsProductInfo;
import com.balance.gmall.po.spu.PmsProductSaleAttr;
import com.balance.gmall.po.spu.PmsProductSaleAttrValue;
import com.balance.gmall.service.SpuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class SpuServiceImpl implements SpuService {

    @Resource
    PmsProductInfoDao pmsProductInfoDao;
    @Resource
    PmsProductImageDao pmsProductImageDao;
    @Resource
    PmsProductSaleAttrDao pmsProductSaleAttrDao;
    @Resource
    PmsProductSaleAttrValueDao pmsProductSaleAttrValueDao;

    @Override
    public List<PmsProductInfo> getPmsProductInfoListByCatalog3Id(Long catalog3Id) {
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
            saveSpuImage(spuImage);
        });
        List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
        spuSaleAttrList.forEach(spuSaleAttr -> {
            spuSaleAttr.setProductId(id);
            saveSpuSaleAttr(spuSaleAttr);
        });
        return result;
    }

    /**
     * 保存商品属性
     * @param: spuSaleAttr 商品属性对象
     * @return: Integer
     * @throw:
     * @Date: 2019/9/23 - 15:38
     * @author: yunzhang.du
     */
    private Integer saveSpuSaleAttr(PmsProductSaleAttr spuSaleAttr) {
        int result = pmsProductSaleAttrDao.insert(spuSaleAttr);
        Long productId = spuSaleAttr.getProductId();
        List<PmsProductSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
        spuSaleAttrValueList.forEach(spuSaleAttrValue -> {
            spuSaleAttrValue.setProductId(productId);
            saveSpuSaleAttrValue(spuSaleAttrValue);

        });
        return result;
    }

    /**
     * 保存商品属性值
     * @param: spuSaleAttrValue 商品属性值对象
     * @return: Integer
     * @throw:
     * @Date: 2019/9/23 - 15:40
     * @author: yunzhang.du
     */
    private Integer saveSpuSaleAttrValue(PmsProductSaleAttrValue spuSaleAttrValue) {
        return pmsProductSaleAttrValueDao.insert(spuSaleAttrValue);
    }

    /**
     * 保存商品图片
     * @param: pmsProductImage 商品图片对象
     * @return: Integer
     * @throw:
     * @Date: 2019/9/23 - 15:38
     * @author: yunzhang.du
     */
    private Integer saveSpuImage(PmsProductImage pmsProductImage) {
        return pmsProductImageDao.insert(pmsProductImage);
    }
}
