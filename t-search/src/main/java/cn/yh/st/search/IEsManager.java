package cn.yh.st.search;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IEsManager<T extends EsEntity> {
	/**
	 * 保存对象
	 * 
	 * @param obj
	 * @return
	 */
	boolean save(T t) throws JsonProcessingException;

	/**
	 * 更新
	 * 
	 * @param obj
	 * @return
	 */
	boolean update(T t);

	/**
	 * 删除
	 * 
	 * @param unid
	 * @param beanClass
	 * @return
	 */
	boolean delete(String unid);

	/**
	 * 获取单个对象
	 * 
	 * @param unid
	 * @param beanClass
	 * @param <T>
	 * @return
	 */
	T get(String unid);

	/**
	 * 获取分页列表
	 * 
	 * @param queryInfo
	 * @param beanClass
	 * @param <T>
	 * @return
	 */
	Page<T> getList(QueryInfo queryInfo,int pageNo, int pageSize) throws IllegalArgumentException, IllegalAccessException;
}
