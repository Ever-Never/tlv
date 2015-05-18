package skymobi.tlv.decode;

import java.io.UnsupportedEncodingException;


public class StringTLVDecoder implements TLVDecoder {

	@Override
	public Object decode(byte[] data) throws UnsupportedEncodingException {
		return new String(data, "utf-8");
	}

}
