package com.balance.gmall.po.sku;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * pms_sku_attr_value
 * @author
 */
@Data
public class PmsSkuAttrValue implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 属性id（冗余)
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long attrId;

    /**
     * 属性值id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long valueId;

    /**
     * skuid
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long skuId;
}
