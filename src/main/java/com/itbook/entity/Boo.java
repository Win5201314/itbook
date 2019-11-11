package com.itbook.entity;

public class Boo {

    private String name;
    public int age;

    public String getName() {
        return "559";
    }

    public int agePlus() {
        return age + 1;
    }

    private String nameW() {
        return name + "12";
    }

    public Boo() {}
    private Boo(String name) {
        this.name = name;
    }
}
