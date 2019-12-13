package com.googleguicetest;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class Sample {

    @Inject
    private PrinterHello printerHello;

    public void function() {
        printerHello.printHello();
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector();
        Sample sample = injector.getInstance(Sample.class);
        sample.function();
    }
}

@Singleton
class PrinterHello {

    public void printHello() {
        System.out.println("hello");
    }
}