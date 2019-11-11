package com.itbook.entity;

import java.io.Serializable;

/**
 * 用户
 */
public class User implements Serializable {

    private Integer id; //主键id
    private String loginName;//账号[手机号]
    private String password;//密码[0-9,英文大小写26个字母,下划线,不能数字开头]

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
