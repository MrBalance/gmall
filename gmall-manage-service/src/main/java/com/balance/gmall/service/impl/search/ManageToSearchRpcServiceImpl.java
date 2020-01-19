package com.balance.gmall.service.impl.search;

import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.dao.sku.PmsSkuAttrValueDao;
import com.balance.gmall.dao.sku.PmsSkuInfoDao;
import com.balance.gmall.dictionary.PmsSkuAttrValueField;
import com.balance.gmall.po.sku.PmsSkuAttrValue;
import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.service.SkuService;
import com.balance.gmall.service.rpc.ManageToSearchRpcService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 向search项目提供的rpc服务
 *
 * @author: yunzhang.du
 * @date: 2019年12月18日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Service(interfaceClass = ManageToSearchRpcService.class)
@Component
@Slf4j
public class ManageToSearchRpcServiceImpl implements ManageToSearchRpcService {

    @Resource
    private PmsSkuInfoDao pmsSkuInfoDao;
    @Resource
    private PmsSkuAttrValueDao pmsSkuAttrValueDao;
    @Resource
    private SkuService skuService;

    @Override
    public List<PmsSkuInfo> selectAllPmsSkuInfo() {
        List<PmsSkuInfo> pmsSkuInfoList = pmsSkuInfoDao.selectList(new QueryWrapper<>());
        pmsSkuInfoList.forEach(pmsSkuInfo -> {
            Long id = pmsSkuInfo.getId();
            List<PmsSkuAttrValue> pmsSkuSaleAttrValueList = selectPmsSkuAttrValueListBySkuId(id);
            pmsSkuInfo.setSkuAttrValueList(pmsSkuSaleAttrValueList);
        });
        return pmsSkuInfoList;
    }

    private List<PmsSkuAttrValue> selectPmsSkuAttrValueListBySkuId(Long skuId) {
        QueryWrapper<PmsSkuAttrValue> wrapper = new QueryWrapper<>();
        wrapper.eq(PmsSkuAttrValueField.SKU_ID, skuId);
        return pmsSkuAttrValueDao.selectList(wrapper);
    }
}
