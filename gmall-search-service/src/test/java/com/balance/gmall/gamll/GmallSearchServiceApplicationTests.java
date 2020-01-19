package com.balance.gmall.gamll;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.po.PmsSearchSkuInfo;
import com.balance.gmall.po.sku.PmsSkuInfo;
import com.balance.gmall.service.rpc.ManageToSearchRpcService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallSearchServiceApplicationTests {

    @Reference(timeout = 6000)
    private ManageToSearchRpcService manageToSearchRpcService;
    @Resource
    private JestClient jestClient;

    @Test
    public void contextLoads() {
        // 1.获取mysql数据
        List<PmsSkuInfo> pmsSkuInfoList = new ArrayList<>();
        // 2.转化为es数据结构
        List<PmsSearchSkuInfo> pmsSearchSkuInfoList = new ArrayList<>();
        pmsSkuInfoList.forEach(pmsSkuInfo -> {
            PmsSearchSkuInfo pmsSearchSkuInfo = new PmsSearchSkuInfo();
            BeanUtils.copyProperties(pmsSkuInfo,pmsSearchSkuInfo);
            pmsSearchSkuInfoList.add(pmsSearchSkuInfo);
        });
    }

    @Test
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
