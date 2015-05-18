package skymobi.tlv.encode;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AbstractTLVEncoder implements TLVEncoder {

	@Override
	public void encode(int tag, Object value, DataOutputStream out)
			throws IOException {
		if (value == null)
			return;

		// tag
		out.writeInt(tag);
		byte[] buf = valueToByteArray(value);
		int length = buf.length;
		// length
		out.writeInt(length);
		// value
		out.write(buf);
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
	protected byte[] valueToByteArray(Object value) throws IOException{
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		DataOutputStream out=new DataOutputStream(byteArrayOutputStream);
		writeValue(value, out);
		return byteArrayOutputStream.toByteArray();
	}
	/**
	 * 输出留中写入值
	 * @param value
	 * @param out
	 * @throws IOException
	 */
	protected abstract void writeValue(Object value,DataOutputStream out) throws IOException;

}
