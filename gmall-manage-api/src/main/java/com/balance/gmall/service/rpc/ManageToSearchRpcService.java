package com.balance.gmall.service.rpc;

import com.balance.gmall.po.sku.PmsSkuInfo;

import java.util.List;

/**
 * 向search项目提供的rpc服务
 *
 * @author: yunzhang.du
 * @date: 2019年12月18日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface ManageToSearchRpcService {

    /**
     * 查询所有skuInfo数据
     * @param:
     * @return: List<PmsSkuInfo>
     * @throw:
     * @Date: 2019/12/6 - 16:28
     * @author: yunzhang.du
     */
    List<PmsSkuInfo> selectAllPmsSkuInfo();
}
