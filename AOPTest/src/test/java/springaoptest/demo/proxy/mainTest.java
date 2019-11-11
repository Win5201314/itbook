package springaoptest.demo.proxy;

import java.lang.reflect.Proxy;

public class mainTest {

    public static void main(String[] args) {
        //静态代理
        /*Printeable proxy = new PrinterProxy();
        proxy.setName("5568");
        System.out.println(proxy.getName());
        proxy.print("df bhkfjhifjio");*/
        //动态代理
        Printeable proxy = (Printeable) Proxy.newProxyInstance(mainTest.class.getClassLoader(),
                new Class[]{Printeable.class},
                new JDKProxyPrinter(new Printer("656")));
        proxy.setName("djgidrjgodtfjig");
        System.out.println(proxy.getName());
        proxy.print("655+96585+9");
    }
}
