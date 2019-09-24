package com.balance.gmall.po.spu;

import lombok.Data;

import java.io.Serializable;

/**
 * pms_product_image
 * @author
 */
@Data
public class PmsProductImage implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 图片名称
     */
    private String imgName;

    /**
     * 图片路径
     */
    private String imgUrl;

}
