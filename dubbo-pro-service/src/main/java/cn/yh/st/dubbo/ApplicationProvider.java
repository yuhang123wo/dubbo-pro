package cn.yh.st.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationProvider {
	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:applicationContext.xml");
			context.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized (ApplicationProvider.class) {
			try {
				ApplicationProvider.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
