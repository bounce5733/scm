package com.jyh.scm.entity.code;

import javax.persistence.Table;

import com.jyh.scm.entity.BaseCascaderCode;

/**
 * 商品分类
 * 
 * @author jiangyonghua
 * @date 2018年9月22日 下午11:50:34
 */
@Table(name = "code_product_catalog")
public class ProductCatalog extends BaseCascaderCode<ProductCatalog> {

    private static final long serialVersionUID = 1L;

    /**
     * 是否默认商品分类： T是|F否
     */
    private String defaulted;

    public String getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(String defaulted) {
        this.defaulted = defaulted;
    }

}
