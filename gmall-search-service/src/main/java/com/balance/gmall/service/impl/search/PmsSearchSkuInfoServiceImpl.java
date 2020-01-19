package com.balance.gmall.service.impl.search;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.balance.gmall.po.PmsSearchSkuInfo;
import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.service.PmsSearchSkuInfoService;
import com.balance.gmall.service.rpc.ManageToSearchRpcService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yunzhang.du
 * @date: 2019年12月11日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Service(interfaceClass = PmsSearchSkuInfoService.class)
@Component
public class PmsSearchSkuInfoServiceImpl implements PmsSearchSkuInfoService {

    @Reference(timeout = 6000)
    private ManageToSearchRpcService manageToSearchRpcService;
    @Resource
    private JestClient jestClient;

    @Override
    public void loadPmsSearchSkuInfo() throws IOException {
        List<PmsSkuInfo> pmsSkuInfoList = manageToSearchRpcService.selectAllPmsSkuInfo();
        List<PmsSearchSkuInfo> pmsSearchSkuInfoList = new ArrayList<>();
        pmsSkuInfoList.forEach(pmsSkuInfo -> {
            PmsSearchSkuInfo pmsSearchSkuInfo = new PmsSearchSkuInfo();
            // 类转换
            BeanUtils.copyProperties(pmsSkuInfo, pmsSearchSkuInfo);
            pmsSearchSkuInfoList.add(pmsSearchSkuInfo);
        });
        // 导入elasticSearch
        for (PmsSearchSkuInfo pmsSearchSkuInfo : pmsSearchSkuInfoList) {
            Index build = new Index.Builder(pmsSearchSkuInfo).index("gmall0105").type("PmsSkuInfo")
                    .id(pmsSearchSkuInfo.getId().toString()).build();
            jestClient.execute(build);
        }

    }
}
