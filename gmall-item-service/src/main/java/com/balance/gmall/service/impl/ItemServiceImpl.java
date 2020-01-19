package com.balance.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.po.spu.PmsProductSaleAttr;
import com.balance.gmall.service.ItemService;
import com.balance.gmall.service.rpc.ManageToItemRpcService;
import net.sf.json.JSONObject;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: yunzhang.du
 * @date: 2019年09月27日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Component
@Service(interfaceClass = ItemService.class)
public class ItemServiceImpl implements ItemService {

    @Reference(timeout = 6000)
    private ManageToItemRpcService manageRpcService;
    @Resource
    RedissonClient redissonClient;


    @Override
    public Map<String, Object> initItem(String skuId) {
        Map<String, Object> resultMap = new HashMap<>(3);
        PmsSkuInfo pmsSkuInfo = manageRpcService.selectPmsSkuInfoBySkuId(skuId);
        Long productId = pmsSkuInfo.getProductId();
        if(null != productId) {
            List<PmsProductSaleAttr> pmsProductSaleAttrs = manageRpcService.selectPmsProductSaleAttrListCheckedBySpuId(productId,skuId);
            Map skuInfoMap = manageRpcService.selectPmsSkuInfoJsonBySpuId(productId);
            resultMap.put("skuInfo",pmsSkuInfo);
            resultMap.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);
            resultMap.put("skuInfoMap", JSONObject.fromObject(skuInfoMap));
        }
        return resultMap;
    }



}
