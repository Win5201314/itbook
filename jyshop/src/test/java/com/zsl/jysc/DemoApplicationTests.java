package com.zsl.jysc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {}

    public static void main(String[] args) {
        try {
            //设置表单数据 里面包含通讯录数据
            //String contact = "[{\"addresses\":[],\"emails\":[],\"givenName\":\"Ghh\",\"instant_message_addresses\":{},\"modification_date\":\"2019-11-13 04:21:40\",\"name\":\"Ghh\",\"phone_number\":[\"18797913400\"],\"urls\":[]}]";
            String pp = makeContactString(makeContact());
            System.out.println("---->" + pp);
            ////////////////////////////
            JSONArray jsonArray = JSON.parseArray(pp);
            JSONObject jb = jsonArray.getJSONObject(0);
            //Contact contact = JSON.parseObject(s, Contact.class);
            System.out.println("---->" + jb.getString("givenName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 生成通讯录数据字符串
     * @return 通讯录数据字符串
     */
    private static String makeContactString(List<Contact> contacts) {
        return JSON.toJSONString(contacts, SerializerFeature.WriteNullListAsEmpty);
    }

    private static List<Contact> makeContact() {
        List<Contact> contacts = null;
        try {
            contacts = new ArrayList<>();
            Contact c1 = new Contact();
            c1.setPhone_number(new String[]{"18797913400"});
            c1.setName("张三");
            c1.setGivenName("张三");
            c1.setModification_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-06-18 16:41:20"));
            c1 = makePublicFiled(c1);
            contacts.add(c1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private static Contact makePublicFiled(Contact c) {
        c.setAddresses(null);
        c.setUrls(null);
        c.setEmails(null);
        return c;
    }

}
