package skymobi.tlv.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 简单缓存
 * 
 * @author shaokai.yang
 * 
 */
public class SimpleCache<K, V> {

	private ConcurrentMap<K, V> map = new ConcurrentHashMap<K, V>();

	/**
	 * 获取缓存值，如果不存在调用自定义回调函数
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-5
	 * @param key
	 * @param ifAbsent
	 * @return
	 * @return V
	 */
	public V get(K key, Callable<V> ifAbsent) {
		V value = map.get(key);
		if (value == null) {
			try {
				value = ifAbsent.call();
				if (value != null) {
					map.putIfAbsent(key, value);
				}

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return value;
	}
}
