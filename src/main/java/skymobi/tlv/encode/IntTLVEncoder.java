package skymobi.tlv.encode;

import skymobi.tlv.util.ByteUtil;

public class IntTLVEncoder extends AbstractTLVEncoder {

	@Override
	protected byte[] valueToByteArray(Object value) {
		Integer integer = (Integer) value;
		return ByteUtil.intToByteArray(integer.intValue());
	}

}
