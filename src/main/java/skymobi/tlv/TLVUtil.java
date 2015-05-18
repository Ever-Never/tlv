package skymobi.tlv;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import skymobi.tlv.annotation.TLVAttribute;
import skymobi.tlv.decode.TLVDecoder;
import skymobi.tlv.decode.TLVDecoderRepository;
import skymobi.tlv.encode.TLVEncoder;
import skymobi.tlv.encode.TLVEncoderRepository;
import skymobi.tlv.util.ByteUtil;
import skymobi.tlv.util.TLVFieldUtil;

/**
 * TLV 工具类，实现对象的编解码
 * 
 * @author shaokai.yang
 * 
 */
public class TLVUtil {

	/**
	 * 编码
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-5
	 * @param object
	 * @return
	 * @return byte[]
	 */
	public static byte[] encode(final Object bean) {
		if (bean != null) {
			Class<?> objCls = bean.getClass();
			// 获取所有字段
			Field[] fields = TLVFieldUtil.getTLVFields(objCls);
			List<byte[]> dest = new ArrayList<byte[]>();
			int sum = 0;
			for (final Field field : fields) {
				field.setAccessible(true);
				TLVAttribute annotation = field
						.getAnnotation(TLVAttribute.class);
				int tag = annotation.tag();
				Class<?> fieldType = field.getType();
				TLVEncoder encoder = TLVEncoderRepository.getRepository()
						.getEncoder(fieldType);
				if (encoder != null) {
					Object fieldValue = null;
					try {
						fieldValue = field.get(bean);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					if (fieldValue == null) {
						continue;
					}

					List<byte[]> ev = encoder.encode(tag, fieldValue);
					if (ev != null && ev.size() > 0) {
						for (byte[] bv : ev) {
							dest.add(bv);
							sum += bv.length;
						}
					}

				} else {
					throw new RuntimeException(fieldType.getName()
							+ " tlv encoder not exits!");
				}
			}

			// 合并数组返回结果
			byte[] buffer = new byte[sum];
			int index = 0;
			for (byte[] data : dest) {
				System.arraycopy(data, 0, buffer, index, data.length);
				index += data.length;
			}
			return buffer;

		}
		return null;
	}

	/**
	 * 解码
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-5
	 * @param data
	 * @param type
	 * @return
	 * @return T
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T decode(byte[] data, Class<T> type)
			throws InstantiationException, IllegalAccessException {
		T instance = type.newInstance();
		Map<Integer, byte[]> tvMap = parseData(data);
		Map<Integer, Field> tfMap = parseType(type);
		if (tvMap != null && tfMap != null) {
			for (Entry<Integer, byte[]> entry : tvMap.entrySet()) {
				Integer tag = entry.getKey();
				byte[] value = entry.getValue();
				if (tfMap.containsKey(tag)) {
					Field field = tfMap.get(tag);
					TLVDecoder decoder = TLVDecoderRepository.getInstance()
							.getDecoder(field.getType());
					if (decoder != null) {
						field.set(instance, decoder.decode(value));
					} else {
						throw new RuntimeException(field.getType()
								+ " tlv decoder not exits!");
					}

				}
			}
		}
		return instance;
	}

	/**
	 * 将数据解析为 tag -- value 映射
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-6
	 * @param data
	 * @return
	 * @return Map<Integer,byte[]>
	 */
	private static Map<Integer, byte[]> parseData(byte[] data) {
		Map<Integer, byte[]> map = new HashMap<Integer, byte[]>();
		if (data.length >= 8) {
			for (int i = 0; i < data.length;) {
				byte[] tagByte = new byte[4];
				byte[] lengthByte = new byte[4];
				System.arraycopy(data, i, tagByte, 0, 4);
				i += 4;
				System.arraycopy(data, i, lengthByte, 0, 4);
				i += 4;
				int tag = ByteUtil.byteArrayToInt(tagByte, 0);
				int length = ByteUtil.byteArrayToInt(lengthByte, 0);
				byte[] value = new byte[length];
				System.arraycopy(data, i, value, 0, length);
				i += length;
				map.put(tag, value);
			}
		}
		return map;
	}

	/**
	 * 解析类中字段 为 tag -- field 映射
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-6
	 * @param type
	 * @return
	 * @return Map<Integer,Field>
	 */
	private static Map<Integer, Field> parseType(Class<?> type) {
		Map<Integer, Field> map = new HashMap<Integer, Field>();
		Field[] fields = TLVFieldUtil.getTLVFields(type);
		if (fields != null) {
			for (Field field : fields) {
				TLVAttribute attribute = field
						.getAnnotation(TLVAttribute.class);
				if (attribute != null) {
					int tag = attribute.tag();
					map.put(tag, field);
				}
			}
		}
		return map;
	}

}
