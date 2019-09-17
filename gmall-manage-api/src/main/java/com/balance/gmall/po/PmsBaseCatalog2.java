package com.balance.gmall.po;

import lombok.Data;

import java.io.Serializable;

/**
 * 二级分类
 * pms_base_catalog2
 *
 * @author
 */
@Data
public class PmsBaseCatalog2 implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 二级分类名称
     */
    private String name;

    /**
     * 一级分类编号
     */
    private Integer catalog1Id;

}
