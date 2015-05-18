package skymobi.tlv.encode;

import java.util.ArrayList;
import java.util.List;

import skymobi.tlv.util.ByteUtil;

public abstract class AbstractTLVEncoder implements TLVEncoder {

	@Override
	public List<byte[]> encode(int tag, Object value) {
		if (value == null)
			return null;
		List<byte[]> ls = new ArrayList<byte[]>();
		// tag
		ls.add(ByteUtil.intToByteArray(tag));
		byte[] buf = valueToByteArray(value);
		int length = buf.length;
		// length
		ls.add(ByteUtil.intToByteArray(length));
		// value
		ls.add(buf);
		return ls;
	}

	/**
	 * 将值转换成byte数组
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-7
	 * @param value
	 * @return
	 * @return byte[]
	 */
	protected abstract byte[] valueToByteArray(Object value);

}
