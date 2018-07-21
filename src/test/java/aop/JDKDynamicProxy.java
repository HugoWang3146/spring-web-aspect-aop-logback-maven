package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxy implements InvocationHandler {

	private Object target;
	
	public JDKDynamicProxy(Object target) {
		this.target=target;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
        Object result = method.invoke(target, args);
        after();
        return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            this
        );
    }
	
	private void before() {
		System.out.println("before");
	}

	private void after() {
		System.out.println("after");
	}

}