package cn.yh.st.feign;

import org.springframework.stereotype.Component;

/**
 * Feign中使用断路器
 * 
 * @author yuhang
 * @Date 2018年12月12日
 * @desc
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
	@Override
	public String sayHiFromClientOne(String name) {
		return "sorry " + name;
	}
}
