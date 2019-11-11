package com.zsl.boss.demo.jackjsontest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Company {

    @JsonProperty("newName")
    private  String name;
    @JsonIgnore
    private  String address;
    private  int employeesCount;
    @JsonIgnore
    private List<Department> departmentArrayList;

    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }

    public List<Department> getDepartmentArrayList() {
        return departmentArrayList;
    }

    public void setDepartmentArrayList(List<Department> departmentArrayList) {
        this.departmentArrayList = departmentArrayList;
    }

}
