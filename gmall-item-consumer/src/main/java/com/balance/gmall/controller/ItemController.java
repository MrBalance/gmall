package com.balance.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: yunzhang.du
 * @date: 2019年09月26日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Controller
public class ItemController {

    @Reference(timeout = 6000)
    private ItemService itemService;

    /**
     * 初始化商品详情页中的信息
     * @param: modelMap
     * @return: "item" 页面url地址
     * @throw:
     * @Date: 2019/9/27 - 11:21
     * @author: yunzhang.du
     */
    @GetMapping("{skuId}.html")
    public String item(@PathVariable String skuId, ModelMap modelMap) {
        modelMap.putAll(itemService.initItem(skuId));
        return "item";
    }
}
