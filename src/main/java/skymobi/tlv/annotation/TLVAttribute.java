package skymobi.tlv.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 属性注解
 * 
 * @author shaokai.yang
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TLVAttribute {

	/**
	 * Tag 值
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-5
	 * @return
	 * @return int
	 */
	public int tag();

	/**
	 * 字段描述
	 * 
	 * @author shaokai.yang
	 * @date 2015-5-5
	 * @return
	 * @return String
	 */
	public String description() default "";
}
