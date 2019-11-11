package springaoptest.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxyPrinter implements InvocationHandler {

    private Printer printer;

    public JDKProxyPrinter(Printer printer) {
        this.printer = printer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("------------------------开始动态代理");
        Object result = null;
        //通过反射调用被代理对象
        result = method.invoke(printer, args);
        return result;
    }
}
