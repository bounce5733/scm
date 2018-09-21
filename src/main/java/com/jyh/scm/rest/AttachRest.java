package com.jyh.scm.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jyh.scm.base.CacheManager;
import com.jyh.scm.util.CodeUtil;

@RestController
@RequestMapping(path = "attachs")
public class AttachRest {

    private static final Logger log = LoggerFactory.getLogger(AttachRest.class);

    @RequestMapping(value = "/companyAvatar", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> companyAvatar(@RequestParam(value = "file") MultipartFile file) {
        try {
            CacheManager.cacheBase64Img(CodeUtil.base64Encode(file.getBytes()));
        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
