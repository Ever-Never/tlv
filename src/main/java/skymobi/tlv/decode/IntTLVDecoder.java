package skymobi.tlv.decode;

import skymobi.tlv.util.ByteUtil;

public class IntTLVDecoder implements TLVDecoder {

	@Override
	public Object decode(byte[] data) {
		return ByteUtil.byteArrayToInt(data, 0);
	}

}
