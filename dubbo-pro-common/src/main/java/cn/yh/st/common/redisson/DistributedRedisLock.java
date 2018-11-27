package cn.yh.st.common.redisson;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;

/**
 * 分布式锁简单实现
 * 
 * @author yuhang
 * @Date 2018年11月27日
 * @desc
 */
public class DistributedRedisLock {
	private static Redisson redisson = RedisManager.getRedisson();
	private static final String LOCK_TITLE = "redisLock_";

	public static boolean acquire(String lockName) {
		String key = LOCK_TITLE + lockName;
		RLock mylock = redisson.getLock(key);
		mylock.lock(2, TimeUnit.MINUTES); // lock提供带timeout参数，timeout结束强制解锁，防止死锁
		return true;
	}

	public static void release(String lockName) {
		String key = LOCK_TITLE + lockName;
		RLock mylock = redisson.getLock(key);
		mylock.unlock();
	}
}
