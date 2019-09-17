package com.balance.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.po.PmsBaseCatalog1;
import com.balance.gmall.service.PmsBaseCatalog1Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
public class PmsBaseCatalog1Controller {
    @Reference
    private PmsBaseCatalog1Service pmsBaseCatalog1Service;

    @ResponseBody
    @PostMapping("/getCatalog1")
    public List<PmsBaseCatalog1> selectAllList() {
        return pmsBaseCatalog1Service.selectAllList();
    }

}
