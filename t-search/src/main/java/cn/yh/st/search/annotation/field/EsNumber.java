package cn.yh.st.search.annotation.field;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//注释起作用的位置，此处表示它只能给类、接口、枚举注
@Target(ElementType.FIELD)
// 保留的环境
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EsNumber {

	/**
	 * 数字类型
	 * 
	 * @return NumberEnum
	 */
	NumberEnum type();

	/**
	 * 描述
	 * 
	 * @return String
	 */
	String desc() default "";

}
