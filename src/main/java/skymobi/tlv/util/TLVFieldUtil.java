package skymobi.tlv.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import skymobi.tlv.annotation.TLVAttribute;

public class TLVFieldUtil {

	private static SimpleCache<Class<?>, Field[]> tlvFieldsCache = new SimpleCache<Class<?>, Field[]>();

	/**
	 * 返回TLV注解的字段
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-5
	 * @param tlvClass
	 * @return
	 * @return Field[]
	 */
	public static Field[] getTLVFields(final Class<?> tlvClass) {
		return tlvFieldsCache.get(tlvClass, new Callable<Field[]>() {

			@Override
			public Field[] call() throws Exception {

				return getAnnotationFieldsOf(tlvClass, TLVAttribute.class);
			}
		});
	}

	private static Field[] getAnnotationFieldsOf(Class<?> cls,
			Class<? extends Annotation> annotationClass) {

		List<Field> fields = new ArrayList<Field>();

		Class<?> itr = cls;
		while ((itr != null) && !itr.equals(Object.class)) {
			for (Field field : itr.getDeclaredFields()) {
				if (field.getAnnotation(annotationClass) != null) {
					fields.add(field);
				}
			}
			itr = itr.getSuperclass();
		}

		return fields.toArray(new Field[0]);
	}
}
