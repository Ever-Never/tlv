package skymobi.tlv.util;

import java.io.UnsupportedEncodingException;

public class ByteUtil {

	private static final String DEFAULT_CHARSET = "UTF-8";

	/**
	 * 将整型转换成byte数组
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-6
	 * @param integer
	 * @return
	 * @return byte[]
	 */
	public static byte[] intToByteArray(final int integer) {
		int byteNum = (40 - Integer.numberOfLeadingZeros(integer < 0 ? ~integer
				: integer)) / 8;
		byte[] byteArray = new byte[4];

		for (int n = 0; n < byteNum; n++)
			byteArray[3 - n] = (byte) (integer >>> (n * 8));

		return byteArray;
	}

	/**
	 * byte数组转换成整型
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-6
	 * @param b
	 * @param offset
	 * @return
	 * @return int
	 */
	public static int byteArrayToInt(byte[] b, int offset) {
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int shift = (4 - 1 - i) * 8;
			value += (b[i + offset] & 0x000000FF) << shift;
		}
		return value;
	}

	/**
	 * 将字符串转换成byte 数组
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-6
	 * @param string
	 * @return
	 * @return byte[]
	 */
	public static byte[] stringToByteArray(String string) {
		try {
			if (string != null)
				return string.getBytes(DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * byte数组转换成字符串
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-6
	 * @param b
	 * @return
	 * @return String
	 */
	public static String byteArrayToString(byte[] b) {
		try {
			if (b != null)
				return new String(b, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		byte[] buf = ByteUtil.intToByteArray(250000);
		System.out.println(buf.length);
		System.out.println(ByteUtil.byteArrayToInt(buf, 0));
	}
}
