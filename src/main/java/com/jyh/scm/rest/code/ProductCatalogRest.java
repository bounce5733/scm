package com.jyh.scm.rest.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyh.scm.base.CacheManager;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.code.ProductCatalogMapper;
import com.jyh.scm.entity.code.ProductCatalog;
import com.jyh.scm.service.code.ProductCatalogService;
import com.jyh.scm.util.TimeUtil;

/**
 * 商品分类API
 * 
 * @author jiangyonghua
 * @date 2017年12月27日 下午4:17:33
 */
@RestController
@RequestMapping(path = "code/productcatalogs")
public class ProductCatalogRest {

    @Autowired
    private ProductCatalogMapper productCatalogMapper;

    @Autowired
    private ProductCatalogService productCatalogService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping
    public ResponseEntity<List<ProductCatalog>> loadProductCatalog() {
        return new ResponseEntity<List<ProductCatalog>>(productCatalogService.load(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addProductCatalog(@RequestBody ProductCatalog item) {
        item.setAppid(SessionManager.getAppid());
        item.setCreatedBy(SessionManager.getAccount());
        item.setCreatedTime(TimeUtil.getTime());
        productCatalogMapper.insertSelective(item);
        cacheManager.refreshAppCascadeCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Object> editProductCatalog(@RequestBody ProductCatalog item) {
        item.setUpdatedBy(SessionManager.getAccount());
        item.setUpdatedTime(TimeUtil.getTime());
        productCatalogMapper.updateByPrimaryKeySelective(item);
        cacheManager.refreshAppCascadeCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeProductCatalog(@PathVariable Integer id) {
        productCatalogMapper.deleteByPrimaryKey(id);
        cacheManager.refreshAppCascadeCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
