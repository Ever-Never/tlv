package skymobi.tlv.decode;

import skymobi.tlv.util.ByteUtil;

public class StringTLVDecoder implements TLVDecoder {

	@Override
	public Object decode(byte[] data) {
		return ByteUtil.byteArrayToString(data);
	}

}
