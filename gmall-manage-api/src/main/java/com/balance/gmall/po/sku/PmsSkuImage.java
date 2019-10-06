package com.balance.gmall.po.sku;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * pms_sku_image
 * @author
 */
@Data
public class PmsSkuImage implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.ID_WORKER)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 商品id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long skuId;

    /**
     * 图片名称（冗余）
     */
    private String imgName;

    /**
     * 图片路径(冗余)
     */
    private String imgUrl;

    /**
     * 商品图片id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long productImgId;

    /**
     * 是否默认
     */
    private String isDefault;
}
