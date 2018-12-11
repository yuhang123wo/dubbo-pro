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
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import cn.yh.st.common.util.ReflectionHelper;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class EsManagerImpl<T extends EsEntity> implements IEsManager<T> {

	protected Class<T> clazz;
	private TransportClient client;

	public EsManagerImpl() {
		clazz = ReflectionHelper.getClassGenricType(getClass());
		this.client = EsHandler.getClient();
	}

	/**
	 * 所引名
	 * 
	 * @return String
	 */
	public abstract String getIndexName();

	/**
	 * 保存对象
	 * 
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	@Override
	public boolean save(T t) throws JsonProcessingException {
		EsHandler.mapping(getIndexName(), t);
		String className = t.getClass().getSimpleName().toLowerCase();
		IndexResponse response = client
				.prepareIndex(getIndexName(), className, String.valueOf(t.getId()))
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
	public boolean update(T t) {
		String className = t.getClass().getSimpleName().toLowerCase();
		UpdateResponse response = client
				.prepareUpdate(getIndexName(), className, String.valueOf(t.getId()))
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
	public boolean delete(String unid) {
		DeleteResponse response = client.prepareDelete(getIndexName(),
				clazz.getSimpleName().toLowerCase(), unid).get();
		return response.getResult() == Result.DELETED;
	}

	@Override
	public T get(String unid) {
		GetResponse response = client.prepareGet(getIndexName(),
				clazz.getSimpleName().toLowerCase(), unid).get();
		String jsonStr = response.getSourceAsString();
		JSONObject json = JSONObject.parseObject(jsonStr);
		T bean = (T) JSONObject.toJavaObject(json, clazz);
		return bean;
	}

	@Override
	public Page<T> getList(QueryInfo queryInfo, int pageNo, int pageSize)
			throws IllegalArgumentException, IllegalAccessException {
		// 分页
		PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);
		// builder构建
		SearchRequestBuilder builder = client.prepareSearch(getIndexName())
				.setTypes(clazz.getSimpleName().toLowerCase()).setSearchType(SearchType.DEFAULT);
		BoolQueryBuilder boolQueryBuilder = createBoolQueryBuilder(queryInfo);
		// 分页查询
		SearchResponse response = builder.setQuery(boolQueryBuilder)
				.setFrom(pageRequest.getPageNumber() * pageSize).setSize(pageSize)
				.setExplain(false).execute().actionGet(new TimeValue(1, TimeUnit.MINUTES));
		SearchHits hits = response.getHits();
		long total = hits.getTotalHits();
		List<T> list = new ArrayList<>();
		// 对象转化
		for (int i = 0; i < hits.getHits().length; i++) {
			String jsonStr = hits.getHits()[i].getSourceAsString();
			JSONObject json = JSONObject.parseObject(jsonStr);
			T bean = (T) JSONObject.toJavaObject(json, clazz);
			list.add(bean);
		}
		return new PageImpl<>(list, pageRequest, total);
	}

	/**
	 * 
	 * @param queryInfo
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 *             BoolQueryBuilder
	 */
	public BoolQueryBuilder createBoolQueryBuilder(QueryInfo queryInfo)
			throws IllegalArgumentException, IllegalAccessException {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		List<QueryAttribute> listEquals = queryInfo.getEquals();
		for (int i = 0; i < listEquals.size(); i++) {
			QueryAttribute qab = listEquals.get(i);
			boolQueryBuilder.must(QueryBuilders.matchQuery(qab.getKey(), qab.getValue()));
		}
		List<QueryAttribute> listLike = queryInfo.getLike();
		for (int i = 0; i < listLike.size(); i++) {
			QueryAttribute qab = listLike.get(i);
			boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(qab.getKey(), qab.getValue()));
		}
		List<QueryAttribute> listGe = queryInfo.getGe();
		for (int i = 0; i < listGe.size(); i++) {
			QueryAttribute qab = listGe.get(i);
			RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(qab.getKey()).gte(
					qab.getValue());
			boolQueryBuilder.must(rangeQuery);
		}
		List<QueryAttribute> listLe = queryInfo.getLe();
		for (int i = 0; i < listLe.size(); i++) {
			QueryAttribute qab = listLe.get(i);
			RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery(qab.getKey()).lte(
					qab.getValue());
			boolQueryBuilder.must(rangeQuery);
		}
		return boolQueryBuilder;
	}

}