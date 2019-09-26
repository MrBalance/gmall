package com.balance.gmall.po.spu;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * pms_product_info
 * @author
 */
@Data
public class PmsProductInfo implements Serializable {
    /**
     * 商品id
     *
     * 防止传给前端后精确度缺失序列化为String
     */
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品描述(后台简述）
     */
    private String description;

    /**
     * 三级分类id
     */
    private Long catalog3Id;

    /**
     * 品牌id
     */
    private Long tmId;

    /**
     * 上传图片列表
     */
    @TableField(exist = false)
    private List<PmsProductImage> spuImageList;

    /**
     * 商品属性列表
     */
    @TableField(exist = false)
    private List<PmsProductSaleAttr> spuSaleAttrList;

}
