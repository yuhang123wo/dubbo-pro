package cn.yh.st.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.yh.st.common.redisson.DistributedRedisLock;
import cn.yh.st.common.redisson.RedisManager;
import cn.yh.st.domain.MUser;
import cn.yh.st.service.MuserService;
import cn.yh.st.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:applicationContext.xml" })
public class JunitTest {

	// @Test
	public void test() {

		String key = "test123";
		RedisManager.init(key, "1001");
		DistributedRedisLock.acquire(key);

		Long result = RedisManager.nextID();

		DistributedRedisLock.release(key);

		System.out.println(result);
	}

//	@Autowired
//	private UserService userService;
	@Autowired
	private MuserService muserService;

	@Test
	public void testAutomiks() {

		System.out.println(1);
		MUser user = new MUser();
		user.setAddress("11");;
		user.setUsername("xxx");
		muserService.addMuser(user);
		System.out.println(2);
	}
}
