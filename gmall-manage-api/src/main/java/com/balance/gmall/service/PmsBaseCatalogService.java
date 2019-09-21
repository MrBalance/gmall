package com.balance.gmall.service;

import com.balance.gmall.po.catalog.PmsBaseCatalog1;
import com.balance.gmall.po.catalog.PmsBaseCatalog2;
import com.balance.gmall.po.catalog.PmsBaseCatalog3;

import java.util.List;

/**
 * 平台分类api
 *
 * @author: yunzhang.du
 * @date: 2019年09月21日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface PmsBaseCatalogService {

    /**
     * 列出平台一级分类
     *
     * @param:
     * @return: List<PmsBaseCatalog1>
     * @throw:
     * @Date: 2019/9/21 - 17:29
     * @author: yunzhang.du
     */
    List<PmsBaseCatalog1> selectAllList();

    /**
     * 根据平台一级分类id，列出平台二级分类
     *
     * @param: catalog1Id 平台一级分类id
     * @return: List<PmsBaseCatalog2>
     * @throw:
     * @Date: 2019/9/21 - 17:31
     * @author: yunzhang.du
     */
    List<PmsBaseCatalog2> selectListByCatalog1IdId(Long catalog1Id);

    /**
     * 根据平台二级分类id，列出平台三级分类
     *
     * @param: catalog2Id
     * @return: List<PmsBaseCatalog3>
     * @throw:
     * @Date: 2019/9/21 - 17:32
     * @author: yunzhang.du
     */
    List<PmsBaseCatalog3> selectListByCatalog2IdId(Long catalog2Id);
}
