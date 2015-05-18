package skymobi.tlv.encode;

import java.util.List;

/**
 * TLV编码接口
 * 
 * @author shaokai.yang
 * 
 */
public interface TLVEncoder {
	public List<byte[]> encode(int tag, Object value);
}
