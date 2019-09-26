package com.balance.gmall.po.sku;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * pms_sku_sale_attr_value
 * @author
 */
@Data
@JsonSerialize(using= ToStringSerializer.class)
public class PmsSkuSaleAttrValue implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 库存单元id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long skuId;

    /**
     * 销售属性id（冗余)
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long saleAttrId;

    /**
     * 销售属性值id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long saleAttrValueId;

    /**
     * 销售属性名称(冗余)
     */
    private String saleAttrName;

    /**
     * 销售属性值名称(冗余)
     */
    private String saleAttrValueName;
}
