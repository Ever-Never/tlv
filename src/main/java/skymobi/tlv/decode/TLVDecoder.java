package skymobi.tlv.decode;

import java.io.IOException;

/**
 * tlv 解析器
 * @author yangshaokai
 *
 */
public interface TLVDecoder {

	public Object decode(byte[] data) throws IOException ;
}
