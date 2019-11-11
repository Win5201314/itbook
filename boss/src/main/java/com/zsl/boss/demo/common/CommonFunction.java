package com.zsl.boss.demo.common;

public class CommonFunction {

    /**
     * 是否等于一，用语插入，更新，删除操作的，是否操作成功
     * @param number 操作后的返回值
     * @return 操作成功返回true,反之则为false
     */
    public static boolean isEqualOne(int number) {
        return (number == 1);
    }
}
