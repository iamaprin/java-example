package io.vilya.example.dispatcher;

/**
 * @author zhukuanxin
 * @time 2017年4月8日 上午7:45:22
 */
public abstract class Assert {
	
	public static void notNull(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void notNull(Object obj) {
		notNull(obj, "");
	}
	
	
}
