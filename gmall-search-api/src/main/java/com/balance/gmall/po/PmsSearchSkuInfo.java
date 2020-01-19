package com.balance.gmall.po;

import com.balance.gmall.po.sku.PmsSkuAttrValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品搜索对象
 *
 * @author: yunzhang.du
 * @date: 2019年12月06日
 * @version: v1.0
 * @since: JDK 1.8
 */
@Data
public class PmsSearchSkuInfo implements Serializable {

    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using= ToStringSerializer.class)
    /**
     * 主键id
     */
    private Long id;

    /**
     * 商品sku名称
     */
    private String skuName;

    /**
     * 商品sku描述
     */
    private String skuDesc;

    /**
     * 三级分类id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long catalog3Id;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 图片路径
     */
    private String skuDefaultImg;

    /**
     * 热度
     */
    private Double hotScore;

    /**
     *  商品id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long productId;

    /**
     * 商品sku属性值集合
     */
    private List<PmsSkuAttrValue> skuAttrValueList;

}
