package com.googleguicetest;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * 参考博客：https://www.jianshu.com/p/9ac108d14608
 */
@ImplementedBy(FirstPrinter.class)
interface IPrinter {
    void printHello();
}

@Singleton
class FirstPrinter implements IPrinter {

    @Override
    public void printHello() {
        System.out.println("First");
    }
}

@Singleton
class SecondPrinter implements IPrinter {

    @Override
    public void printHello() {
        System.out.println("second");
    }
}

class BaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IPrinter.class).annotatedWith(Names.named("first")).to(FirstPrinter.class);
        bind(IPrinter.class).annotatedWith(Names.named("second")).to(SecondPrinter.class);
    }
}

@Singleton
public class Test2 {

    @Inject
    @Named("first")
    private IPrinter printer;

    @Inject
    @Named("second")
    private IPrinter printer2;

    public void function() {
        printer.printHello();
        printer2.printHello();
    }

    public static void main(String[] args) {
        /*Injector injector = Guice.createInjector();
        Test2 test2 = injector.getInstance(Test2.class);
        test2.function();*/
        Injector injector = Guice.createInjector(new BaseModule());
        Test2 test2 = injector.getInstance(Test2.class);
        test2.function();
    }
}
