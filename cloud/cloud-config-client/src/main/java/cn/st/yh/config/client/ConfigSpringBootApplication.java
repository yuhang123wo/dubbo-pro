package cn.st.yh.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigSpringBootApplication.class, args);
	}

//	@Value("${foo}")
	String foo;
	
	@Value("${spring.rabbitmq.port}")
	
	String port;

	@RequestMapping(value = "/hi")
	public String hi() {
		System.out.println(port);
		return foo+":"+port;
	}

	/**
	 * 
	 * 
	 * spring.cloud.config.label 指明远程仓库的分支
	 * 
	 * spring.cloud.config.profile
	 * 
	 * dev开发环境配置文件 test测试环境 pro正式环境 spring.cloud.config.uri=
	 * http://localhost:8888/ 指明配置服务中心的网址。
	 */
}
