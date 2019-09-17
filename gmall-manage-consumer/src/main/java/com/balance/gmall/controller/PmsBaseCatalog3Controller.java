package com.balance.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.po.PmsBaseCatalog3;
import com.balance.gmall.service.PmsBaseCatalog3Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 一级分类controller层
 * @author: yunzhang.du
 * @date: 2019年09月16日
 * @version: v1.0
 * @since: JDK 1.8
 */
@RestController
// 解决跨域问题
@CrossOrigin
public class PmsBaseCatalog3Controller {
    @Reference
    private PmsBaseCatalog3Service pmsBaseCatalog3Service;

    @ResponseBody
    @PostMapping("/getCatalog3/{catalog2Id}")
    public List<PmsBaseCatalog3> selectAllList(@PathVariable("catalog2Id") Long catalog2Id) {
        return pmsBaseCatalog3Service.selectListByCatalog2IdId(catalog2Id);
    }

}
