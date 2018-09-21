package com.jyh.scm.rest.code;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.jyh.scm.dao.CodeItemMapper;
import com.jyh.scm.dao.CodeMapper;
import com.jyh.scm.entity.CodeItem;
import com.jyh.scm.entity.code.Code;
import com.jyh.scm.service.code.CodeService;
import com.jyh.scm.util.TimeUtil;

/**
 * 码表API
 * 
 * @author jiangyonghua,wangmingyue
 * @date 2017年12月27日 下午4:17:33
 */
@RestController
@RequestMapping(path = "code/codes")
public class CodeRest {

    private static final Logger log = LoggerFactory.getLogger(CodeRest.class);

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CodeMapper codeMapper;

    @Autowired
    private CodeItemMapper codeItemMapper;

    @Autowired
    private CodeService service;

    @PostMapping
    public ResponseEntity<Object> addCode(@RequestBody Code code) {
        code.setAppid(SessionManager.getAppid());
        code.setCreatedBy(SessionManager.getAccount());
        code.setCreatedTime(TimeUtil.getTime());
        codeMapper.insertSelective(code);
        cacheManager.refreshCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping("/item")
    public ResponseEntity<Object> addItem(@RequestBody CodeItem item) {
        item.setAppid(SessionManager.getAppid());
        item.setCreatedBy(SessionManager.getAccount());
        item.setCreatedTime(TimeUtil.getTime());
        codeItemMapper.insertSelective(item);
        cacheManager.refreshCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/cacheMap")
    public ResponseEntity<Map<String, List<CodeItem>>> cacheMap() {
        return new ResponseEntity<Map<String, List<CodeItem>>>(
                cacheManager.loadCode().get(String.valueOf(SessionManager.getAppid())), HttpStatus.OK);
    }

    @GetMapping("/cachePathMap")
    public ResponseEntity<Map<String, Map<String, Object>>> cachePathMap() {
        return new ResponseEntity<Map<String, Map<String, Object>>>(
                cacheManager.loadCodePathMap().get(String.valueOf(SessionManager.getAppid())), HttpStatus.OK);
    }

    @PatchMapping("/item")
    public ResponseEntity<Object> editItem(@RequestBody CodeItem item) {
        item.setUpdatedBy(SessionManager.getAccount());
        item.setUpdatedTime(TimeUtil.getTime());
        codeItemMapper.updateByPrimaryKeySelective(item);
        cacheManager.refreshCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Code>> load() {
        return new ResponseEntity<List<Code>>(service.codes(), HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> removeCode(@PathVariable String code) {
        codeMapper.deleteByPrimaryKey(code);
        cacheManager.refreshCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Object> removeItem(@PathVariable("id") int id) {
        try {
            codeItemMapper.deleteByPrimaryKey(id);
            cacheManager.refreshCode();
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if (e instanceof DataIntegrityViolationException) {
                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
