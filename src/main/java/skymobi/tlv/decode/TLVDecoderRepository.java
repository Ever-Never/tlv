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
		
		TLVDecoder decoder=null;
		
		decoder=new BooleanTLVDecoder();
		repository.put(boolean.class, decoder);
		repository.put(Boolean.class, decoder);
		
		decoder=new ByteTLVDecoder();
		repository.put(byte.class, decoder);
		repository.put(Byte.class, decoder);
		
		decoder=new CharTLVDecoder();
		repository.put(char.class, decoder);
		repository.put(Character.class, decoder);
		
		decoder=new DoubleTLVDecoder();
		repository.put(double.class, decoder);
		repository.put(Double.class, decoder);
		
		decoder=new FloatTLVDecoder();
		repository.put(float.class, decoder);
		repository.put(Float.class, decoder);
		
		decoder=new IntTLVDecoder();
		repository.put(int.class, decoder);
		repository.put(Integer.class, decoder);
		
		decoder=new LongTLVDecoder();
		repository.put(long.class, decoder);
		repository.put(Long.class, decoder);
		
		decoder=new ShortTLVDecoder();
		repository.put(short.class, decoder);
		repository.put(Short.class, decoder);
		
		repository.put(String.class, new StringTLVDecoder());
		
	}

	public static TLVDecoderRepository getInstance() {
		return instance;
	}

	public TLVDecoder getDecoder(Class<?> type) {
		return repository.get(type);
	}
}
