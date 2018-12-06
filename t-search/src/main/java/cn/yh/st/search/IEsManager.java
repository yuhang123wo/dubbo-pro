package cn.yh.st.search;

import java.util.Map;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;

public interface IEsManager<T extends EsEntity> {
	/**
	 * 保存对象
	 * 
	 * @param obj
	 * @return
	 */
	boolean save(String indexName, T t) throws JsonProcessingException;

	/**
	 * 更新
	 * 
	 * @param obj
	 * @return
	 */
	boolean update(String indexName, T t);

	/**
	 * 删除
	 * 
	 * @param unid
	 * @param beanClass
	 * @return
	 */
	boolean delete(String indexName, String unid, String type);

	/**
	 * 获取单个对象
	 * 
	 * @param unid
	 * @param beanClass
	 * @param <T>
	 * @return
	 */
	T get(String unid, String indexName, Class<T> beanClass);

	/**
	 * 获取分页列表
	 * 
	 * @param queryInfo
	 * @param beanClass
	 * @param <T>
	 * @return
	 */
	Page<T> getList(String indexName, Map<String, Object> map, Class<T> beanClass, int pageNo,
			int pageSize);
}
