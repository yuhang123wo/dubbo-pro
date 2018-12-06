package cn.yh.st.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.DocWriteResponse.Result;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;

public class EsManagerImpl<T extends EsEntity> implements IEsManager<T> {

	/**
	 * TCP连接客户端
	 */
	private TransportClient client;

	public EsManagerImpl() {
		this.client = EsHandler.getClient();
	}

	/**
	 * 保存对象
	 * 
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public boolean save(String indexName, T t) throws JsonProcessingException {
		EsHandler.mapping(indexName, t);
		String className = t.getClass().getSimpleName().toLowerCase();
		IndexResponse response = client
				.prepareIndex(indexName, className, String.valueOf(t.getId()))
				.setSource(changeJsonToMap(t)).get(new TimeValue(1, TimeUnit.MINUTES));
		return response.getResult() == Result.CREATED;
	}

	@SuppressWarnings({ "unchecked" })
	private Map<String, Object> changeJsonToMap(T t) {
		String json = JSONObject.toJSONString(t);
		Map<String, Object> source = JSONObject.parseObject(json, Map.class);
		return source;
	}

	/**
	 * 更新
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public boolean update(String indexName, T t) {
		String className = t.getClass().getSimpleName().toLowerCase();
		UpdateResponse response = client
				.prepareUpdate(indexName, className, String.valueOf(t.getId()))
				.setDoc(changeJsonToMap(t)).get();
		return response.getResult() == Result.UPDATED;
	}

	/**
	 * 删除
	 * 
	 * @param unid
	 * @param beanClass
	 * @return
	 */
	@Override
	public boolean delete(String indexName, String unid, String type) {
		DeleteResponse response = client.prepareDelete(indexName, type, unid).get();
		return response.getResult() == Result.DELETED;
	}

	@Override
	public T get(String unid, String indexName, Class<T> beanClass) {
		GetResponse response = client.prepareGet(indexName,
				beanClass.getSimpleName().toLowerCase(), unid).get();
		String jsonStr = response.getSourceAsString();
		JSONObject json = JSONObject.parseObject(jsonStr);
		T bean = (T) JSONObject.toJavaObject(json, beanClass);
		return bean;
	}

	@Override
	public Page<T> getList(String indexName, Map<String, Object> map, Class<T> beanClass,
			int pageNo, int pageSize) {
		SearchRequestBuilder builder = client.prepareSearch(indexName)
				.setTypes(beanClass.getSimpleName().toLowerCase())
				.setSearchType(SearchType.DEFAULT);
		PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);
		PageHelper.startPage(pageNo, pageSize);
		SearchResponse response = builder.setFrom(pageNo * pageSize).setSize(pageSize)
				.setExplain(false).execute().actionGet(new TimeValue(1, TimeUnit.MINUTES));
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		List<T> list = new ArrayList<>();
		for (int i = 0; i < hits.getHits().length; i++) {
			String jsonStr = hits.getHits()[i].getSourceAsString();
			System.out.println(jsonStr);
			JSONObject json = JSONObject.parseObject(jsonStr);
			T bean = (T) JSONObject.toJavaObject(json, beanClass);
			list.add(bean);
		}
		return new PageImpl<>(list, pageRequest, total);
	}
}