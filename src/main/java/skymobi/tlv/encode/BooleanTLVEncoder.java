package skymobi.tlv.encode;


public class BooleanTLVEncoder extends AbstractTLVEncoder {

	@Override
	protected byte[] valueToByteArray(Object value) {
		return new byte[] { (byte) ((Boolean) value ? 1 : 0) };
	}
}
