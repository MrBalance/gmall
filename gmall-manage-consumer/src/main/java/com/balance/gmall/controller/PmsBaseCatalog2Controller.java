package com.balance.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.po.PmsBaseCatalog2;
import com.balance.gmall.service.PmsBaseCatalog2Service;
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
public class PmsBaseCatalog2Controller {
    @Reference
    private PmsBaseCatalog2Service pmsBaseCatalog2Service;

    @ResponseBody
    @PostMapping("/getCatalog2/{catalog1Id}")
    public List<PmsBaseCatalog2> selectAllList(@PathVariable("catalog1Id") Long catalog1Id) {
        return pmsBaseCatalog2Service.selectListByCatalog1IdId(catalog1Id);
    }

}
