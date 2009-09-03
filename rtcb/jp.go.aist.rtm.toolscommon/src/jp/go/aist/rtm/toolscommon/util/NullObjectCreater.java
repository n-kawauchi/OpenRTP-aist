package jp.go.aist.rtm.toolscommon.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * NullObjectを作成するクラス
 */
public class NullObjectCreater {
	/**
	 * NullObjectを作成する
	 * 
	 * @param clazz
	 *            作成するNullObjectの型
	 * @return NullObject
	 */
	public static Object createNullObject(Class[] clazz) {
		Object result = Proxy.newProxyInstance(NullObjectCreater.class
				.getClassLoader(), clazz, new InvocationHandler() {
			public Object invoke(Object obj, Method method, Object[] arts)
					throws Throwable {
				Object result = null;
				Class<?> returnType = method.getReturnType();
				if (returnType != null) {
					try {
						result = returnType.newInstance();
					} catch (Exception e) {
						if (returnType.isInterface()) {
							result = createNullObject(new Class[] { returnType });
						} else {
							result = createNullObject(returnType
									.getInterfaces());
						}
					}
				}

				return result;
			}
		});
		return result;
	}
}
