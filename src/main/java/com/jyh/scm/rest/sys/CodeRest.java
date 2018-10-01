package com.jyh.scm.rest.sys;

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
import com.jyh.scm.dao.sys.CodeItemMapper;
import com.jyh.scm.dao.sys.CodeMapper;
import com.jyh.scm.entity.BaseCascaderCode;
import com.jyh.scm.entity.sys.Code;
import com.jyh.scm.entity.sys.CodeItem;
import com.jyh.scm.service.sys.CodeService;
import com.jyh.scm.util.TimeUtil;

/**
 * 码表API
 * 
 * @author jiangyonghua,wangmingyue
 * @date 2017年12月27日 下午4:17:33
 */
@RestController
@RequestMapping(path = "sys/codes")
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

    @GetMapping
    public ResponseEntity<List<Code>> loadCode() {
        return new ResponseEntity<List<Code>>(service.loadCode(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCode(@RequestBody Code code) {
        code.setAppid(SessionManager.getAppid());
        code.setCreatedBy(SessionManager.getAccount());
        code.setCreatedTime(TimeUtil.getTime());
        codeMapper.insertSelective(code);
        cacheManager.refreshSysCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> removeCode(@PathVariable String code) {
        codeMapper.deleteByPrimaryKey(code);
        cacheManager.refreshSysCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PostMapping("/item")
    public ResponseEntity<Object> addCodeItem(@RequestBody CodeItem item) {
        item.setAppid(SessionManager.getAppid());
        item.setCreatedBy(SessionManager.getAccount());
        item.setCreatedTime(TimeUtil.getTime());
        codeItemMapper.insertSelective(item);
        cacheManager.refreshSysCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PatchMapping("/item")
    public ResponseEntity<Object> editCodeItem(@RequestBody CodeItem item) {
        item.setUpdatedBy(SessionManager.getAccount());
        item.setUpdatedTime(TimeUtil.getTime());
        codeItemMapper.updateByPrimaryKeySelective(item);
        cacheManager.refreshSysCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Object> removeCodeItem(@PathVariable("id") int id) {
        try {
            codeItemMapper.deleteByPrimaryKey(id);
            cacheManager.refreshSysCode();
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

    @GetMapping("/appCode")
    public ResponseEntity<Map<String, List<Map<String, String>>>> loadAppCode() {
        return new ResponseEntity<Map<String, List<Map<String, String>>>>(
                cacheManager.loadAppCode().get(String.valueOf(SessionManager.getAppid())), HttpStatus.OK);
    }

    @GetMapping("/sysCode")
    public ResponseEntity<Map<String, List<CodeItem>>> loadSysCode() {
        return new ResponseEntity<Map<String, List<CodeItem>>>(
                cacheManager.loadSysCode().get(String.valueOf(SessionManager.getAppid())), HttpStatus.OK);
    }

    @GetMapping("/sysPathCode")
    public ResponseEntity<Map<String, Map<String, Object>>> loadSysPathCode() {
        return new ResponseEntity<Map<String, Map<String, Object>>>(
                cacheManager.loadSysPathCode().get(String.valueOf(SessionManager.getAppid())), HttpStatus.OK);
    }

    @GetMapping("/appCascadeCode")
    public ResponseEntity<Map<String, List<BaseCascaderCode<?>>>> loadAppCascadeCode() {
        return new ResponseEntity<Map<String, List<BaseCascaderCode<?>>>>(
                cacheManager.loadAppCascadeCode().get(String.valueOf(SessionManager.getAppid())), HttpStatus.OK);
    }

    @GetMapping("/appCascadePathCode")
    public ResponseEntity<Map<String, Map<String, Map<String, Object>>>> loadAppCascadePathCode() {
        return new ResponseEntity<Map<String, Map<String, Map<String, Object>>>>(
                cacheManager.loadAppCascadePathCode().get(String.valueOf(SessionManager.getAppid())), HttpStatus.OK);
    }

}
