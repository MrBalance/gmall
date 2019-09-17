package com.balance.gmall.service;

import com.balance.gmall.po.PmsBaseCatalog1;

import java.util.List;

/**
 * @description: 一级分类service层
 * @author: yunzhang.du
 * @date: 2019年09月16日
 * @version: v1.0
 * @since: JDK 1.8
 */
public interface PmsBaseCatalog1Service {

    List<PmsBaseCatalog1> selectAllList();
}