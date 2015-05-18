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

		TLVEncoder encoder = null;
		// 初始化编码器

		encoder = new BooleanTLVEncoder();
		repository.put(Boolean.class, encoder);
		repository.put(boolean.class, encoder);

		encoder = new ByteTLVEncoder();
		repository.put(Byte.class, encoder);
		repository.put(byte.class, encoder);

		encoder = new CharTLVEncoder();
		repository.put(Character.class, encoder);
		repository.put(char.class, encoder);

		encoder = new DoubleTLVEncoder();
		repository.put(Double.class, encoder);
		repository.put(double.class, encoder);

		encoder = new FloatTLVEncoder();
		repository.put(Float.class, encoder);
		repository.put(float.class, encoder);

		encoder = new IntTLVEncoder();
		repository.put(Integer.class, encoder);
		repository.put(int.class, encoder);

		encoder = new LongTLVEncoder();
		repository.put(Long.class, encoder);
		repository.put(long.class, encoder);

		encoder = new ShortTLVEncoder();
		repository.put(Short.class, encoder);
		repository.put(short.class, encoder);

		encoder = new StringTLVEncoder();
		repository.put(String.class, encoder);

	}

	public static TLVEncoderRepository getRepository() {
		return instance;
	}

	public TLVEncoder getEncoder(Class<?> type) {
		return repository.get(type);
	}
}
