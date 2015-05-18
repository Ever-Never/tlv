package skymobi.tlv.decode;

import java.io.DataInputStream;
import java.io.IOException;

public class BooleanTLVDecoder extends AbstractTLVDecoder {

	@Override
	protected Object readValue(DataInputStream dataInputStream)
			throws IOException {
		return dataInputStream.readBoolean();
	}

}
