package com.balance.gmall.po.sku;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * pms_sku_info
 * @author
 */
@Data
public class PmsSkuInfo implements Serializable {
    /**
     * 库存id(itemID)
     */
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 商品id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long productId;

    /**
     * 价格
     */
    private Double price;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 商品规格描述
     */
    private String skuDesc;

    private Double weight;

    /**
     * 品牌(冗余)
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long tmId;

    /**
     * 三级分类id（冗余)
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long catalog3Id;

    /**
     * 默认显示图片(冗余)
     */
    private String skuDefaultImg;

    /** sku平台属性 */
    @TableField(exist = false)
    private List<PmsSkuAttrValue> skuAttrValueList;

    /**sku图片列表*/
    @TableField(exist = false)
    private List<PmsSkuImage> skuImageList;

    /** sku销售属性值 */
    @TableField(exist = false)
    private List<PmsSkuSaleAttrValue> skuSaleAttrValueList;
}
