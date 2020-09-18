package proxy.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    private Object targetObject;

    public Object getInstance(Object targetObject){
        this.targetObject = targetObject;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理方法执行之前");
        Object invoke = methodProxy.invoke(targetObject, objects);
        System.out.println("代理方法执行之后");
        return invoke;
    }

    public static void main(String[] args) {
        Person peter = new Person("peter");

        Person instance = (Person) new CglibProxy().getInstance(peter);

        instance.SayName();
    }
}
