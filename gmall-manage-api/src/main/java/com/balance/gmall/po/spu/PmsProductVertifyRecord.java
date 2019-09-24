package com.balance.gmall.po.spu;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * pms_product_vertify_record
 * @author
 */
@Data
public class PmsProductVertifyRecord implements Serializable {
    private Long id;

    private Long productId;

    private Date createTime;

    /**
     * 审核人
     */
    private String vertifyMan;

    private Integer status;

    /**
     * 反馈详情
     */
    private String detail;

}
