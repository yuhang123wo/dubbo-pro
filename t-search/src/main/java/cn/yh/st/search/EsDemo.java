package cn.yh.st.search;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.cli.UserException;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.alibaba.fastjson.JSONObject;

public class EsDemo {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// Settings settings = Settings.builder().put("cluster.name",
		// "elasticsearch").build();
		// TransportClient client = new PreBuiltTransportClient(settings)
		// .addTransportAddress(new
		// TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

		// SearchRequestBuilder srb1 = client.prepareSearch()
		// .setQuery(QueryBuilders.queryStringQuery("elasticsearch")).setSize(1);
		// SearchRequestBuilder srb2 = client.prepareSearch()
		// .setQuery(QueryBuilders.matchQuery("user", "kimchy")).setSize(1);
		//
		// MultiSearchResponse sr =
		// client.prepareMultiSearch().add(srb1).add(srb2).get();
		//
		// // You will get all individual responses from
		// // MultiSearchResponse#getResponses()
		// long nbHits = 0;
		// for (MultiSearchResponse.Item item : sr.getResponses()) {
		// SearchResponse response = item.getResponse();
		// System.out.println(response);
		// nbHits += response.getHits().getTotalHits();
		// }

		// SearchResponse sr = client.prepareSearch()
		// .setQuery(QueryBuilders.matchAllQuery())
		// .addAggregation(
		// AggregationBuilders.terms("agg1").field("field")
		// )
		// .addAggregation(
		// AggregationBuilders.dateHistogram("agg2")
		// .field("birth")
		// .dateHistogramInterval(DateHistogramInterval.YEAR)
		// )
		// .get();
		//
		// // Get your facet results
		// Terms agg1 = sr.getAggregations().get("agg1");
		// Histogram agg2 = sr.getAggregations().get("agg2");

		// SearchResponse sr =
		// client.prepareSearch("twitter").setTerminateAfter(1000).get();
		//
		// if (sr.isTerminatedEarly()) {
		// System.out.println(sr);
		// }

		// EsDemo u = new EsDemo();
		//
		// String type = u.getClass().getSimpleName().toLowerCase();
		// System.out.println(type);

		IEsManager<UserEsVo> iter = new EsManagerImpl<UserEsVo>();
		// UserEsVo es = new UserEsVo(3, "mingtang", "湖北s", 1, new Date());
		// System.out.println(iter.save("users", es));
		// UserEsVo ess = new UserEsVo(3, "mingtang", "湖北ss", 1, new Date());
		// System.out.println(iter.update("users", ess));
		// // System.out.println(iter.delete("users", "2",
		// // UserEsVo.class.getSimpleName().toLowerCase()));
		// System.out.println(iter.get("2", "users", UserEsVo.class));

		// QueryInfo q = new QueryInfo();
		// Map<String, String> requestMap = new HashMap<String, String>();
		// requestMap.put("yuhang_n", "haha");
		// requestMap.put("yuhang_m", "yuh");
		// q.setRequestMap(requestMap);
		// String queryStr = requestMap.get("yuhang");
		//
		// System.out.println(queryStr);

		System.out.println(iter.getList("users", null, UserEsVo.class, 1, 1).getContent().get(0));
		//
		//
		// JSONObject queryObj = JSONObject.parseObject(queryStr);
		// JSONObject likeObj = queryObj.getJSONObject("");

	}

	public static XContentBuilder createJson4() throws Exception {
		// 创建json对象, 其中一个创建json的方式
		XContentBuilder source = XContentFactory.jsonBuilder().startObject().field("user", "yuh")
				.field("postDate", new Date()).field("message", "trying to out ElasticSearch")
				.endObject();
		return source;
	}
}
