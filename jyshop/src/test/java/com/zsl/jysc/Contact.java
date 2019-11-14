package com.zsl.jysc;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//苹果手机数据
/* "addresses": [],
 "note": "",
 "phone_number": ["18682347994"],
 "social_profiles": [],
 "urls": [],
 "modification_date": "2019-06-18 16:41:20",
 "creation_date": "2018-06-06 23:03:42",
 "birthday": "",
 "emails": [],
 "organization_name": "",
 "name": "FDD-X004",
 "job_desc": ""*/
//安卓手机数据
/*[{"addresses":[],"emails":[],"givenName":"Ghh",
        "instant_message_addresses":{},
        "modification_date":"2019-11-13 04:21:40",
        "name":"Ghh",
        "phone_number":["13088865080"],
        "urls":[]}]*/

public class Contact implements Serializable {

    private String[] addresses;
    private String[] phone_number;
    private List<String> urls;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date modification_date;
    private List<String> emails;
    private String name;
    private String givenName;
    private String instant_message_addresses;

    public String[] getAddresses() {
        return addresses;
    }

    public void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }

    public String[] getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String[] phone_number) {
        this.phone_number = phone_number;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public Date getModification_date() {
        return modification_date;
    }

    public void setModification_date(Date modification_date) {
        this.modification_date = modification_date;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getInstant_message_addresses() {
        return instant_message_addresses;
    }

    public void setInstant_message_addresses(String instant_message_addresses) {
        this.instant_message_addresses = instant_message_addresses;
    }
}

