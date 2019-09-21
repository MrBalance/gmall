package com.balance.gmall.service.catalog;

import com.balance.gmall.po.catalog.PmsBaseCatalog3;

import java.util.List;

/**
 * @description: 三级分类service层
 * @author: yunzhang.du
 * @date: 2019年09月16日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface PmsBaseCatalog3Service {

    List<PmsBaseCatalog3> selectListByCatalog2IdId(Long catalog2Id);
}
