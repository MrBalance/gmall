package com.balance.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.po.attr.PmsBaseAttrInfo;
import com.balance.gmall.po.attr.PmsBaseAttrValue;
import com.balance.gmall.response.Response;
import com.balance.gmall.service.attr.PmsBaseAttrInfoService;
import com.balance.gmall.service.attr.PmsBaseAttrValueService;
import com.balance.gmall.util.response.ResponseUtil;
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
@CrossOrigin()
public class PmsBaseAttrController {
    @Reference
    private PmsBaseAttrInfoService pmsBaseAttrInfoService;
    @Reference
    private PmsBaseAttrValueService pmsBaseAttrValueService;

    @GetMapping("/attrInfoList/{catalog3Id}")
    public Response<List<PmsBaseAttrInfo>> selectAllList(@PathVariable("catalog3Id") Long catalog3Id) {
        return ResponseUtil.success(pmsBaseAttrInfoService.selectListByCatalog3IdId(catalog3Id));
    }

    @RequestMapping("/saveAttrInfo")
    public Response<Integer> saveAttrInfo(@RequestBody PmsBaseAttrInfo attrInfo) {
        return ResponseUtil.success(pmsBaseAttrInfoService.saveAttrInfo(attrInfo));
    }

    @PostMapping("/getAttrValueList/{attrId}")
    public Response<List<PmsBaseAttrValue>> getAttrValueList(@PathVariable("attrId") Long attrId) {
        return ResponseUtil.success(pmsBaseAttrValueService.selectListByAttrId(attrId));
    }



}
