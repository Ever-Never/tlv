package skymobi.tlv.decode;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public abstract class AbstractTLVDecoder implements TLVDecoder {

	@Override
	public Object decode(byte[] data) throws IOException {
		ByteArrayInputStream byteInputStream = new ByteArrayInputStream(data);
		DataInputStream dataInputStream = new DataInputStream(byteInputStream);
		return readValue(dataInputStream);
	}

	protected abstract Object readValue(DataInputStream dataInputStream) throws IOException ;

}
