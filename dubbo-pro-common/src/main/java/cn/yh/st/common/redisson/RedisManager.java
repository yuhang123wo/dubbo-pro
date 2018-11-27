package cn.yh.st.common.redisson;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.config.Config;

/**
 * 
 * @author yuhang
 * @Date 2018年11月27日
 * @desc
 */
public class RedisManager {
	private static final String RATOMIC_NAME = "genId_";

	private static Config config = new Config();
	private static Redisson redisson = null;

	public static void init(String key, String value) {
		try {
			if (key == null || "".equals(key)) {
				key = RATOMIC_NAME;
			}
			config.useSingleServer().setAddress("redis://127.0.0.1:6379");
			redisson = (Redisson) Redisson.create(config);
			// 清空自增的ID数字
			RAtomicLong atomicLong = redisson.getAtomicLong(key);
			long pValue = 1;
			if (value != null && !"".equals(value)) {
				pValue = Long.parseLong(value);
			}
			atomicLong.set(pValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Redisson getRedisson() {
		return redisson;
	}

	/** 获取redis中的原子ID */
	public static Long nextID() {
		RAtomicLong atomicLong = getRedisson().getAtomicLong(RATOMIC_NAME);
		// 原子性的获取下一个ID，递增1
		atomicLong.incrementAndGet();
		return atomicLong.get();
	}
}
