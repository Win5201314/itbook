package com.googleguicetest;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class FacebookFriendsRecord implements Serializable {

    //当前账号
    private String name;
    //好友人数
    private int number;
    //手机uid
    private String uid;

    private AtomicInteger s;

    public FacebookFriendsRecord() {
    }

    public FacebookFriendsRecord(String name, int number, String uid) {
        this.name = name;
        this.number = number;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public static void main(String[] args) {
        List<FacebookFriendsRecord> list = new ArrayList<>();
        list.add(new FacebookFriendsRecord("+13480901446", 56, "256893"));
        list.add(new FacebookFriendsRecord("+13480901448", 59, "256893"));
        list.add(new FacebookFriendsRecord("+13480901449", 55, "256893"));
        String json = JSON.toJSONString(list);
        System.out.println(json);
        String s = "[{\"name\":\"+13480901446\",\"number\":56,\"uid\":\"256893\"},{\"name\":\"+13480901448\",\"number\":59,\"uid\":\"256893\"},{\"name\":\"+13480901449\",\"number\":55,\"uid\":\"256893\"}]";
        List<FacebookFriendsRecord> list2 = new ArrayList<>();
        list2 = JSON.parseArray(s, FacebookFriendsRecord.class);
        for (FacebookFriendsRecord fb : list2) {
            System.out.println(fb.getName() + ":" + fb.getNumber());
        }
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        int n = 0;
        try {
            n++;
        } finally {
            lock.unlock();
        }
    }
}
