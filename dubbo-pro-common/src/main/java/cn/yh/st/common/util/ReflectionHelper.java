package cn.yh.st.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionHelper {
	/**
	 * 获取泛型参数类型
	 * 
	 * @param clazz
	 *            类类型
	 * @param <T>
	 *            类类型
	 * @return 对象类型
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassGenricType(@SuppressWarnings("rawtypes") final Class clazz) {
		return getClassGenricType(clazz, 0);
	}

	/**
	 * 获取泛型参数类型
	 * 
	 * @param clazz
	 *            类类型
	 * @param index
	 *            参数索引
	 * @return 类类型
	 */
	@SuppressWarnings("rawtypes")
	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * 获取方法对象
	 * 
	 * @param clazz
	 *            类类型
	 * @param methodName
	 *            方法名
	 * @param parameterTypes
	 *            类型
	 * @return 方法
	 */
	public static Method getDeclaredMethod(Class<?> clazz, String methodName,
			Class<?>... parameterTypes) {
		Method method = null;

		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				return method;
			} catch (Exception e) {
			}
		}

		return null;
	}

	/**
	 * 循环向上转型, 获取对象的DeclaredField.
	 * 
	 * 如向上转型到Object仍无法找到, 返回null.
	 */
	protected static Field getDeclaredField(final Object object, final String fieldName) {
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {// NOSONAR
												// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 强行设置Field可访问.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers())
				|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

}
