package skymobi.tlv.encode;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * TLV编码接口
 * 
 * @author shaokai.yang
 * 
 */
public interface TLVEncoder {
	public void encode(int tag, Object value,DataOutputStream out) throws IOException;
}
