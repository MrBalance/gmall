package com.balance.gmall.po;

import lombok.Data;

import java.io.Serializable;

/**
 * 一级分类
 * pms_base_catalog1
 *
 * @author
 */
@Data
public class PmsBaseCatalog1 implements Serializable {
    /**
     * 编号
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

}
