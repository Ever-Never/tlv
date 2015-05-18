package skymobi.tlv.encode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 编码器仓库
 * 
 * @author shaokai.yang
 * 
 */
public class TLVEncoderRepository {

	private static TLVEncoderRepository instance;

	private Map<Class<?>, TLVEncoder> repository;

	static {
		instance = new TLVEncoderRepository();
	}

	private TLVEncoderRepository() {
		repository = new ConcurrentHashMap<Class<?>, TLVEncoder>();
		// 初始化编码器
		repository.put(String.class, new StringTLVEncoder());
		repository.put(Integer.class, new IntTLVEncoder());
		repository.put(int.class, new IntTLVEncoder());
	}

	public static TLVEncoderRepository getRepository() {
		return instance;
	}

	public TLVEncoder getEncoder(Class<?> type) {
		return repository.get(type);
	}
}
