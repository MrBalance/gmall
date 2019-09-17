package com.balance.gmall.po;

import lombok.Data;

import java.io.Serializable;

/**
 * 三级分类
 * pms_base_catalog3
 *
 * @author
 */
@Data
public class PmsBaseCatalog3 implements Serializable {
    /**
     * 编号
     */
    private Long id;

    /**
     * 三级分类名称
     */
    private String name;

    /**
     * 二级分类编号
     */
    private Long catalog2Id;

}
