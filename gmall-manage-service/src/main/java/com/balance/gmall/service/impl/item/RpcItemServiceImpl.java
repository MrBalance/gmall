package com.balance.gmall.service.impl.item;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.sku.PmsSkuImageDao;
import com.balance.gmall.dao.sku.PmsSkuInfoDao;
import com.balance.gmall.dao.sku.PmsSkuSaleAttrValueDao;
import com.balance.gmall.dao.spu.PmsProductSaleAttrDao;
import com.balance.gmall.dao.spu.PmsProductSaleAttrValueDao;
import com.balance.gmall.dictionary.*;
import com.balance.gmall.exception.DescribeException;
import com.balance.gmall.po.sku.PmsSkuImage;
import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.po.sku.PmsSkuSaleAttrValue;
import com.balance.gmall.po.spu.PmsProductSaleAttr;
import com.balance.gmall.po.spu.PmsProductSaleAttrValue;
import com.balance.gmall.response.ExceptionEnum;
import com.balance.gmall.service.SkuService;
import com.balance.gmall.service.rpc.RpcItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author: yunzhang.du
 * @date: 2019年10月06日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Service(interfaceClass = RpcItemService.class)
@Component
@Slf4j
public class RpcItemServiceImpl implements RpcItemService {

    @Resource
    PmsSkuInfoDao pmsSkuInfoDao;
    @Resource
    PmsProductSaleAttrDao pmsProductSaleAttrDao;
    @Resource
    PmsSkuSaleAttrValueDao pmsSkuSaleAttrValueDao;
    @Resource
    PmsSkuImageDao pmsSkuImageDao;
    @Resource
    PmsProductSaleAttrValueDao pmsProductSaleAttrValueDao;
    @Resource
    SkuService skuService;
    @Resource
    RedissonClient redissonClient;

    @Override
    public PmsSkuInfo selectPmsSkuInfoBySkuId(String skuId) {
        PmsSkuInfo pmsSkuInfo = null;
        RBucket<PmsSkuInfo> bucket = redissonClient.getBucket("skuInfo:" + skuId);
        if (null == bucket.get()) {
            // 获取分布式锁
            RLock lock = redissonClient.getLock("skuInfo:lock:" + skuId);
            try {
                if (lock.tryLock(3, 6, TimeUnit.SECONDS)) {
                    pmsSkuInfo = pmsSkuInfoDao.selectById(skuId);
                    if (null != pmsSkuInfo) {
                        List<PmsSkuImage> pmsSkuImages = selectPmsSkuImageListBySkuId(skuId);
                        if (!pmsSkuImages.isEmpty()) {
                            pmsSkuInfo.setSkuImageList(pmsSkuImages);
                        }
                    } else {
                        pmsSkuInfo = new PmsSkuInfo();
                    }
                    bucket.set(pmsSkuInfo);
                }
            } catch (InterruptedException e) {
                lock.unlock();
                log.error("|分布式锁异常|-|" + e.toString() + "|");
                throw new DescribeException(ExceptionEnum.DATA_LOAD_FAIL);
            }
        } else {
            pmsSkuInfo = bucket.get();
        }
        return pmsSkuInfo;
    }

    @Override
    public List<PmsProductSaleAttr> selectPmsProductSaleAttrListCheckedBySpuId(Long productId, String skuId) {
        List<PmsProductSaleAttr> pmsProductSaleAttrs = null;
        RBucket<List<PmsProductSaleAttr>> bucket = redissonClient.getBucket("PmsProductSaleAttrList:productId:" + productId + ":skuId" + skuId);
        if (null == bucket.get()) {
            RLock lock = redissonClient.getLock("PmsProductSaleAttrListLock:" + productId + ":skuId" + skuId);
            try {
                if (lock.tryLock(3, 6, TimeUnit.SECONDS)) {
                    QueryWrapper<PmsProductSaleAttr> wrapper = new QueryWrapper<>();
                    wrapper.eq(PmsProductSaleAttrField.PRODUCT_ID, productId);
                    pmsProductSaleAttrs = pmsProductSaleAttrDao.selectList(wrapper);
                    pmsProductSaleAttrs.forEach(pmsProductSaleAttr -> {
                        Long saleAttrId = pmsProductSaleAttr.getSaleAttrId();
                        List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = selectPmsProductSaleAttrListCheckedBySpuIdAndSaleAttrId(productId, saleAttrId, skuId);
                        pmsProductSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValues);
                    });
                    if (pmsProductSaleAttrs.isEmpty()) {
                        pmsProductSaleAttrs = new ArrayList<>();
                    }
                    bucket.set(pmsProductSaleAttrs);
                }
            } catch (InterruptedException e) {
                lock.unlock();
                log.error("|分布式锁异常|-|" + e.toString() + "|");
                throw new DescribeException(ExceptionEnum.DATA_LOAD_FAIL);
            }
        } else {
            pmsProductSaleAttrs = bucket.get();
        }
        return pmsProductSaleAttrs;
    }

    public Map selectPmsSkuInfoJsonBySpuId(Long productId) {
        Map<String, String> pmsSkuInfoJson = new HashMap<>();
        RBucket<Map> bucket = redissonClient.getBucket("pmsSkuInfoJson:" + productId);
        if (null == bucket.get()) {
            RLock lock = redissonClient.getLock("PmsSkuInfoJsonLock:" + productId);
            try {
                if (lock.tryLock(3, 6, TimeUnit.SECONDS)) {
                    List<PmsSkuInfo> pmsSkuInfoList = selectPmsSkuInfoBySpuId(productId);
                    for (PmsSkuInfo pmsSkuInfo : pmsSkuInfoList) {
                        Long skuId = pmsSkuInfo.getId();
                        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues = selectPmsSkuSaleAttrValueListBySkuId(skuId);
                        StringJoiner key = new StringJoiner("|");
                        pmsSkuSaleAttrValues.forEach(pmsSkuSaleAttrValue -> {
                            Long saleAttrValueId = pmsSkuSaleAttrValue.getSaleAttrValueId();
                            key.add(saleAttrValueId.toString());
                        });
                        pmsSkuInfoJson.put(key.toString(), skuId.toString());
                    }
                }
                bucket.set(pmsSkuInfoJson);
            } catch (InterruptedException e) {
                lock.unlock();
                log.error("|分布式锁异常|-|" + e.toString() + "|");
                throw new DescribeException(ExceptionEnum.DATA_LOAD_FAIL);
            }
        } else {
            pmsSkuInfoJson = bucket.get();
        }
        return pmsSkuInfoJson;
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

    /**
     * 根据spuId、saleAttrId和skuId获取spu销售属性值和被选中状态列表信息
     *
     * @param: spuId 平台商品信息id
     * @param: saleAttrId 销售属性id
     * @param: skuId
     * @return:
     * @throw:
     * @Date: 2019/9/26 - 16:02
     * @author: yunzhang.du
     */
    private List<PmsProductSaleAttrValue> selectPmsProductSaleAttrListCheckedBySpuIdAndSaleAttrId(Long productId, Long saleAttrId, String skuId) {
        QueryWrapper<PmsProductSaleAttrValue> spuWrapper = new QueryWrapper<>();
        spuWrapper.eq(PmsProductSaleAttrValueField.PRODUCT_ID, productId);
        spuWrapper.eq(PmsProductSaleAttrValueField.SALE_ATTR_ID, saleAttrId);
        List<PmsProductSaleAttrValue> pmsProductSaleAttrValues = pmsProductSaleAttrValueDao.selectList(spuWrapper);
        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = skuService.selectPmsSkuSaleAttrValueListBySkuIdAndSaleAttrId(skuId, saleAttrId);
        String saleAttrValueName = pmsSkuSaleAttrValue.getSaleAttrValueName();
        if (StringUtils.isNotBlank(saleAttrValueName)) {
            pmsProductSaleAttrValues.forEach(pmsProductSaleAttrValue -> {
                if (saleAttrValueName.equals(pmsProductSaleAttrValue.getSaleAttrValueName())) {
                    pmsProductSaleAttrValue.setChecked(true);
                } else {
                    pmsProductSaleAttrValue.setChecked(false);
                }
            });
        }
        return pmsProductSaleAttrValues;
    }

    /**
     * 根据spuId获取skuInfo列表信息
     *
     * @param: spuId
     * @return: List<PmsSkuInfo>
     * @throw:
     * @Date: 2019/9/27 - 11:49
     * @author: yunzhang.du
     */
    private List<PmsSkuInfo> selectPmsSkuInfoBySpuId(Long spuId) {
        QueryWrapper<PmsSkuInfo> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsSkuInfoField.PRODUCT_ID, spuId);
        return pmsSkuInfoDao.selectList(wrapper);
    }

    /**
     * 通过skuId获取sku销售属性值对象集合
     *
     * @param:
     * @return:
     * @throw:
     * @Date: 2019/9/27 - 9:11
     * @author: yunzhang.du
     */
    private List<PmsSkuSaleAttrValue> selectPmsSkuSaleAttrValueListBySkuId(Long skuIds) {
        QueryWrapper<PmsSkuSaleAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsSkuSaleAttrValueField.SKU_ID, skuIds);
        return pmsSkuSaleAttrValueDao.selectList(wrapper);
    }
}
