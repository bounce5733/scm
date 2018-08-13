package com.jyh.scm.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.entity.Condition;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T> {

	private static final Logger log = LoggerFactory.getLogger(AbstractService.class);
	
	@Autowired
	protected Mapper<T> mapper;

	private Class<T> modelClass; // 当前泛型真实类型的Class

	@SuppressWarnings("unchecked")
	public AbstractService() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		modelClass = (Class<T>) pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T model) {
		mapper.insertSelective(model);
	}

	@Override
	public void save(List<T> models) {
		mapper.insertList(models);
	}

	@Override
	public void deleteById(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteByIds(String ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public void update(T model) {
		mapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public T findById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public T findBy(String fieldName, Object value) throws TooManyResultsException {
		try {
			T model = modelClass.newInstance();
			Field field = modelClass.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(model, value);
			return mapper.selectOne(model);
		} catch (ReflectiveOperationException e) {
			log.info(e.getMessage());
			return null;
		}
	}

	@Override
	public List<T> findByIds(String ids) {
		return mapper.selectByIds(ids);
	}

	@Override
	public List<T> findByCondition(Condition condition) {
		return mapper.selectByCondition(condition);
	}

	@Override
	public List<T> findAll() {
		return mapper.selectAll();
	}
}
