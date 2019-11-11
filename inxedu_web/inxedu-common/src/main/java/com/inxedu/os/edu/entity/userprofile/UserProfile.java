package com.inxedu.os.edu.entity.userprofile;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserProfile implements Serializable{
    private Long id;
    private String name;
    private String value;
    private String valueTwo;
    private String profiletype;
    private Long userid;
    private java.util.Date profiledate;
    private String avatar;//第三方头像
}
