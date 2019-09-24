package com.balance.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.po.catalog.PmsBaseCatalog1;
import com.balance.gmall.po.catalog.PmsBaseCatalog2;
import com.balance.gmall.po.catalog.PmsBaseCatalog3;
import com.balance.gmall.response.Response;
import com.balance.gmall.service.BaseCatalogService;
import com.balance.gmall.util.response.ResponseUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类controller层
 *
 * @author: yunzhang.du
 * @date: 2019年09月16日
 * @version: v1.0
 * @since: JDK 1.8
 */
// 解决跨域问题
@CrossOrigin
@RestController
public class BaseCatalogController {

    @Reference
    private BaseCatalogService pmsBaseCatalogService;

    /**
     * 获取一级分类所有数据
     *
     * @param:
     * @return: List<PmsBaseCatalog1>
     * @throw:
     * @Date: 2019/9/17 - 16:37
     * @author: yunzhang.du
     */
    @PostMapping("/getCatalog1")
    public Response<List<PmsBaseCatalog1>> selectCatalog1AllList() {
        return ResponseUtil.success(pmsBaseCatalogService.selectAllList());
    }


    /**
     * 根据一级分类id获取对应的二级分类
     *
     * @param: catalog1Id
     * @return: List<PmsBaseCatalog2>
     * @throw:
     * @Date: 2019/9/17 - 16:38
     * @author: yunzhang.du
     */
    @PostMapping("/getCatalog2/{catalog1Id}")
    public Response<List<PmsBaseCatalog2>> selectCatalog2List(@PathVariable("catalog1Id") Long catalog1Id) {
        return ResponseUtil.success(pmsBaseCatalogService.selectListByCatalog1IdId(catalog1Id));
    }


    /**
     * 根据二级分类id获取对应的三级分类
     *
     * @param: catalog2Id
     * @return: jList<PmsBaseCatalog3>
     * @throw:
     * @Date: 2019/9/17 - 16:39
     * @author: yunzhang.du
     */
    @PostMapping("/getCatalog3/{catalog2Id}")
    public Response<List<PmsBaseCatalog3>> selectCatalog3List(@PathVariable("catalog2Id") Long catalog2Id) {
        return ResponseUtil.success(pmsBaseCatalogService.selectListByCatalog2IdId(catalog2Id));
    }
}
