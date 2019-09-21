package com.balance.gmall.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.balance.gmall.po.attr.PmsBaseAttrInfo;
import com.balance.gmall.po.attr.PmsBaseAttrValue;
import com.balance.gmall.response.Response;
import com.balance.gmall.service.PmsBaseAtrrService;
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
    private PmsBaseAtrrService pmsBaseAtrrService;

    /**
     * 根据平台属性id，列出平台属性值
     *
     * @param: attrId 平台属性id
     * @return: List<PmsBaseAttrValue>
     * @throw:
     * @Date: 2019/9/21 - 17:25
     * @author: yunzhang.du
     */
    @PostMapping("/getAttrValueList/{attrId}")
    public Response<List<PmsBaseAttrValue>> getAttrValueList(@PathVariable("attrId") Long attrId) {
        return ResponseUtil.success(pmsBaseAtrrService.selectListByAttrId(attrId));
    }

    /**
     * 根据传入的平台属性对象，保存平台属性和平台值
     *
     * @param: attrInfo
     * @return: Integer
     * @throw: DescribeException
     * @Date: 2019/9/21 - 17:24
     * @author: yunzhang.du
     */
    @RequestMapping("/saveAttrInfo")
    public Response<Integer> saveAttrInfo(@RequestBody PmsBaseAttrInfo attrInfo) {
        return ResponseUtil.success(pmsBaseAtrrService.saveAttrInfo(attrInfo));
    }

    /**
     * 根据三级列表id，列出平台属性
     *
     * @param: catalog3Id 根据三级列表id
     * @return: List<PmsBaseAttrInfo>
     * @throw:
     * @Date: 2019/9/21 - 17:21
     * @author: yunzhang.du
     */
    @GetMapping("/attrInfoList/{catalog3Id}")
    public Response<List<PmsBaseAttrInfo>> selectAllList(@PathVariable("catalog3Id") Long catalog3Id) {
        return ResponseUtil.success(pmsBaseAtrrService.selectListByCatalog3IdId(catalog3Id));
    }


}
