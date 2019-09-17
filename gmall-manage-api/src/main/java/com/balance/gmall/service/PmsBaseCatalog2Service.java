package com.balance.gmall.service;

import com.balance.gmall.po.PmsBaseCatalog2;

import java.util.List;

/**
 * @description: 一级分类service层
 * @author: yunzhang.du
 * @date: 2019年09月16日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface PmsBaseCatalog2Service {

    List<PmsBaseCatalog2> selectListByCatalog1IdId(Long catalog1Id);
}
