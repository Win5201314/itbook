package com.googleguicetest;

import com.google.inject.*;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
//ss://aes-256-cfb@47.252.78.174:16062
class BaseModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IPrinter.class).annotatedWith(Names.named("first")).to(FirstPrinter.class).in(Singleton.class);
        bind(IPrinter.class).annotatedWith(Names.named("second")).to(SecondPrinter.class).in(Scopes.SINGLETON);
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
        //Guice.createInjector(new BaseModule()).injectMembers(this);
        /*Injector injector = Guice.createInjector(new BaseModule());
        Test2 test2 = injector.getInstance(Test2.class);
        test2.function();*/
        /*List<String> list = new LinkedList<>();
        list.add("6+");
        list.add("669+");
        list.add("+6dgd");
        for (String s : list) {
            System.out.println(s);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/
        ArrayList<String> sendMsgFriend = null;
        String list = "13112345678;13698763743;13928370932";
        List<String> stringList = Arrays.asList(list.split(";"));
        sendMsgFriend = new ArrayList<>(stringList);
        for (String s : sendMsgFriend) {
            System.out.println("-------" + s);
        }

        ArrayList<String> list2 = new ArrayList<>();
        list2.addAll(sendMsgFriend);
        StringBuilder sb = new StringBuilder();
        list2.forEach(s -> {
            sb.append(s + ",");
        });
        System.out.println(sb);
        /*for (String s : list2) {
            sb.append(s + ",");
        }*/
        //https://oss-njp.oss-cn-hongkong.aliyuncs.com/picture/12710/15769221267114.jpg
    }
}
