package skymobi.tlv.encode;

import java.util.ArrayList;
import java.util.List;

import skymobi.tlv.util.ByteUtil;

/**
 * 字符串编码器
 * 
 * @author shaokai.yang
 * 
 */
public class StringTLVEncoder implements TLVEncoder {

	@Override
	public List<byte[]> encode(int tag, Object value) {

		if (value != null) {
			List<byte[]> ls = new ArrayList<byte[]>();
			// tag
			ls.add(ByteUtil.intToByteArray(tag));
			String str = (String) value;
			byte[] strBuf = ByteUtil.stringToByteArray(str);
			int length = strBuf.length;
			// length
			ls.add(ByteUtil.intToByteArray(length));
			// value
			ls.add(strBuf);
			return ls;
		}
		return null;
	}

}
