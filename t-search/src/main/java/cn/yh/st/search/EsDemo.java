package cn.yh.st.search;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import cn.yh.st.common.util.ReflectionHelper;
import cn.yh.st.search.user.EsDemoVoService;
import cn.yh.st.search.user.EsDemoVoServiceImpl;
import cn.yh.st.search.vo.EsDemoVo;
import cn.yh.st.search.vo.NestVo;

public class EsDemo {

	public static void main(String[] args) throws Exception {
		EsDemoVoService service = new EsDemoVoServiceImpl();
		EsDemoVo t = new EsDemoVo();
		t.setAddress("北京北京");
		t.setAmount(new BigDecimal("15.22"));
		t.setCreateTime(new Date());
		t.setId(3);
		t.setOrderNo("2837928357982357");
		t.setPrice(5.12);
		t.setSex(1);
		t.setUserId(23456);
		t.setUserName("中华人民共和国");

		List<NestVo> list = new ArrayList<NestVo>();
		NestVo vo = new NestVo();
		vo.setVoAddress("nested yuhang.hahah");
		vo.setVoId(2);
		vo.setVoName("voName");
		vo.setVoTime(new Date());
		list.add(vo);
		t.setVo(list);

		// service.save(t);
		EsDemoVo tt = new EsDemoVo();
		tt.setUserName("中国");
		tt.setId(3);
		
		
		QueryInfo q = new QueryInfo();
		List<QueryAttribute> ll = new ArrayList<QueryAttribute>();
		ll.add(new QueryAttribute("userName","人民"));
		q.setLike(ll);
		
//		System.out.println(service.getList(q, 1, 100).getContent());
		
//       EsHandler.delIndex("yuhang3");
	}

	public void test() {
		Class x = ReflectionHelper.getClassGenricType(this.getClass().getDeclaringClass());
		System.out.println(x.getSimpleName());
	}

	public static XContentBuilder createJson4() throws Exception {
		// 创建json对象, 其中一个创建json的方式
		XContentBuilder source = XContentFactory.jsonBuilder().startObject().field("user", "yuh")
				.field("postDate", new Date()).field("message", "trying to out ElasticSearch")
				.endObject();
		return source;
	}
}
