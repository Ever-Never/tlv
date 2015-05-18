package skymobi.tlv.encode;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 字符串编码器
 * 
 * @author shaokai.yang
 * 
 */
public class StringTLVEncoder extends AbstractTLVEncoder {

	@Override
	protected void writeValue(Object value, DataOutputStream out)
			throws IOException {
		String strVal = (String) value;
		out.write(strVal.getBytes("utf-8"));
	}

}
