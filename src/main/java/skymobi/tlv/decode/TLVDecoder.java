package skymobi.tlv.decode;

import java.io.IOException;

/**
 * tlv 解析器
 * @author yangshaokai
 *
 */
public interface TLVDecoder {

	/**
	 * 解析特定的值
	 * @param data 
	 * @return
	 * @throws IOException
	 */
	public Object decode(byte[] data) throws IOException ;
}
