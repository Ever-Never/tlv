package skymobi.tlv.decode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 解码器仓库
 * 
 * @author shaokai.yang
 * 
 */
public class TLVDecoderRepository {

	private static TLVDecoderRepository instance;
	private Map<Class<?>, TLVDecoder> repository;
	static {
		instance = new TLVDecoderRepository();
	}

	private TLVDecoderRepository() {
		repository = new ConcurrentHashMap<Class<?>, TLVDecoder>();
		repository.put(String.class, new StringTLVDecoder());
		repository.put(int.class, new IntTLVDecoder());
		repository.put(Integer.class, new IntTLVDecoder());
	}

	public static TLVDecoderRepository getInstance() {
		return instance;
	}

	public TLVDecoder getDecoder(Class<?> type) {
		return repository.get(type);
	}
}
