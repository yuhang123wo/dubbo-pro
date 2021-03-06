package cn.yh.st.search.annotation;

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
public @interface EsAnnotation {
	/**
	 * mapping构建类型
	 * 
	 * @return MappingType
	 */
	MappingType mappingType() default MappingType.NOT_ANALYZER;

	/**
	 * 查询
	 * 
	 * @return SearchType
	 */
	QueryType searchType() default QueryType.TIMEQUERY;
}
