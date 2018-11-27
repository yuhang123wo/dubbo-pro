package cn.yh.st.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.yh.st.common.redisson.DistributedRedisLock;
import cn.yh.st.common.redisson.RedisManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:applicationContext.xml" })
public class JunitTest {

	@Test
	public void test() {
		
		String key = "test123";
		RedisManager.init(key,"1001");
		DistributedRedisLock.acquire(key);

		Long result = RedisManager.nextID();

		DistributedRedisLock.release(key);

		System.out.println(result);
	}
}
