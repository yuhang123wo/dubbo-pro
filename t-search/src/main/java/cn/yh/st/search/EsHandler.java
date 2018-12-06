package cn.yh.st.search;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class EsHandler {
	/**
	 * 需要分词字段
	 */
	private static List<String> ANALYZED_FIELD = new ArrayList<String>() {
		private static final long serialVersionUID = -6602792201760384903L;

		{
			add("userName");
			add("orderNo");
		};
	};
	/**
	 * 集群名称cluster.name
	 */
	public static final String ES_CLUSTER_NAME = "elasticsearch";
	/**
	 * 主机IP
	 */
	private static final String ES_HOST = "localhost";
	/**
	 * 端口号
	 */
	private static final int ES_TCP_PORT = 9300;

	// private static final Map<String, String> MAPPINGS = new HashMap<>();

	static Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
	// 创建ES客户端
	private static TransportClient client;
	static {
		try {
			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName(ES_HOST),
							ES_TCP_PORT));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得ES客户端
	 */
	public static TransportClient getClient() {
		return client;
	}

	/**
	 * 关闭客户端
	 *
	 * @param client
	 */
	public static void close(TransportClient client) {
		if (null != client) {
			client.close();
		}
	}

	/**
	 * Mapping处理
	 * 
	 * @param indexName
	 *            索引
	 * @param typeObj
	 *            类型
	 */
	public static void mapping(String indexName, Object typeObj) {
		String type = typeObj.getClass().getSimpleName().toLowerCase();
		synchronized (EsHandler.class) {
			createIndex(indexName);
			// 获取所有的Mapping
			IndexMetaData indexMeta = client.admin().cluster().prepareState().execute().actionGet()
					.getState().getMetaData().getIndices().get(indexName);
			ImmutableOpenMap<String, MappingMetaData> mappings = indexMeta.getMappings();
			if (null == indexMeta || null == mappings.get(type)) {
				createMapping(indexName, typeObj);
			}
		}
	}

	/**
	 * 创建Mapping
	 *
	 * @param obj
	 */
	public static void createMapping(String indexName, Object typeObj) {
		String type = typeObj.getClass().getSimpleName().toLowerCase();
		PutMappingRequest mapping = Requests.putMappingRequest(indexName).type(type)
				.source(setMapping(typeObj));
		EsHandler.getClient().admin().indices().putMapping(mapping).actionGet();
	}

	/**
	 * 设置对象的ElasticSearch的Mapping
	 *
	 * @param obj
	 * @return
	 */
	public static XContentBuilder setMapping(Object obj) {
		List<Field> fieldList = getFields(obj);
		XContentBuilder mapping = null;
		try {
			mapping = XContentFactory.jsonBuilder().startObject().startObject("properties");
			for (Field field : fieldList) {
				// 修饰符是static的字段不处理
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				String name = field.getName();
				// if (ANALYZED_FIELD.contains(name)) {
				// mapping.startObject(name)
				// .field("type",
				// getElasticSearchMappingType(field.getType().getSimpleName()
				// .toLowerCase())).field("analyzer", "ik_max_word")
				// .field("search_analyzer", "ik_max_word").endObject();
				// } else {
				mapping.startObject(name)
						.field("type",
								getElasticSearchMappingType(field.getType().getSimpleName()
										.toLowerCase())).endObject();
				// }
			}
			mapping.endObject().endObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapping;
	}

	/**
	 * 获取对象所有自定义的属性
	 *
	 * @param obj
	 * @return
	 */
	private static List<Field> getFields(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		List<Field> fieldList = new ArrayList<>();
		fieldList.addAll(Arrays.asList(fields));
		Class<?> objClass = obj.getClass().getSuperclass();
		if (null != objClass) {
			fieldList.addAll(Arrays.asList(objClass.getDeclaredFields()));
		}
		return fieldList;
	}

	/**
	 * java类型与ElasticSearch类型映射
	 *
	 * @param varType
	 * @return
	 */
	private static String getElasticSearchMappingType(String varType) {
		String es;
		switch (varType) {
		case "date":
			es = "date";
			break;
		case "double":
			es = "double";
			break;
		case "long":
			es = "long";
			break;
		case "int":
			es = "integer";
			break;
		case "boolean":
			es = "boolean";
			break;
		default:
			es = "text";
			break;
		}
		return es;
	}

	/**
	 * 判断ElasticSearch中的索引是否存在
	 * 
	 * @param indexName
	 * @return boolean
	 */
	private static boolean indexExists(String indexName) {
		IndicesAdminClient adminClient = client.admin().indices();
		IndicesExistsRequest request = new IndicesExistsRequest(indexName);
		IndicesExistsResponse response = adminClient.exists(request).actionGet();
		if (response.isExists()) {
			return true;
		}
		return false;
	}

	/**
	 * 创建索引
	 * 
	 * @param indexName
	 *            void
	 */
	private static void createIndex(String indexName) {
		if (!indexExists(indexName)) {
			CreateIndexRequest request = new CreateIndexRequest(indexName);
			client.admin().indices().create(request);
		}
	}
}
