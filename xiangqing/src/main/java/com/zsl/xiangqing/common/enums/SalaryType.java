package com.zsl.xiangqing.common.enums;

/**
 * 薪水区间类型（月薪制）
 */
public enum SalaryType {

    A("4000以下"), B("4000-5000"), C("5000-6000"), D("6000-7000"),
    E("7000-8000"), F("8000-9000"), G("9000-10000"), H("10000-11000"),
    I("11000-12000"), J("12000-13000"), K("13000-14000"), L("14000-15000"),
    M("15000-16000"), N("16000-17000"), O("17000-18000"), P("18000-19000"),
    Q("19000-20000"), R("20000以上");

    private final String salary;
    SalaryType(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }
}
