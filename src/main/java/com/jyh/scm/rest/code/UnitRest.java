package com.jyh.scm.rest.code;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyh.scm.base.CacheManager;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.constant.AppConst;
import com.jyh.scm.dao.code.UnitMapper;
import com.jyh.scm.entity.code.Unit;
import com.jyh.scm.service.code.UnitService;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * 计量单位
 * 
 * @author jiangyonghua
 * @date 2018年9月17日 下午7:56:53
 */
@RestController
@RequestMapping(path = "code/units")
public class UnitRest {

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private UnitService unitService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping
    public ResponseEntity<List<Unit>> loadUnit() {
        Condition c = new Condition(Unit.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
        List<Unit> records = unitMapper.selectByCondition(c);
        records.sort(new Comparator<Unit>() {
            @Override
            public int compare(Unit o1, Unit o2) {
                return o1.getSort() > o2.getSort() ? 1 : o1.getSort() < o2.getSort() ? -1 : 0;
            }
        });
        return new ResponseEntity<List<Unit>>(records, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addUnit(@RequestBody Unit unit) {
        unit.setCreatedBy(SessionManager.getAccount());
        unit.setCreatedTime(TimeUtil.getTime());
        unit.setAppid(SessionManager.getAppid());
        unitMapper.insertSelective(unit);
        cacheManager.refreshAppCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeUnit(@PathVariable("id") Integer id) {
        unitMapper.deleteByPrimaryKey(id);
        cacheManager.refreshAppCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/moveTop/{id}")
    public ResponseEntity<Object> moveTopUnit(@PathVariable("id") Integer id) {
        unitService.moveTop(id);
        cacheManager.refreshAppCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
