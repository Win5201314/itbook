package com.zsl.boss.demo.jackjsontest;

import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Test {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());//对象方式初始化Log对象

    @org.junit.Test
    public void test() throws Exception {
        JsonHelper jm = new JsonHelper();

        //1、对象<->Json
        //准备数据
        Company c = getCompany();
        //对象转Json
        String json =  jm.toJson(c);
        logger.info("1.1、对象转Json="+json);
        //Json转对象
        Company obj = jm.fromJson(json,Company.class);
        logger.info("1.2、Json转对象="+ obj.getDepartmentArrayList().get(0).getName());

        //2、Map<->Json
        //Json转Map
        Map map = jm.fromJson(json,Map.class);
        List<Map> tempList =  (List<Map>)map.get("departmentArrayList");
        logger.info("2.1、Json转Map="+tempList.get(0).get("name"));

        //Map转Json
        json =  jm.toJson(map);
        logger.info("2.2、Map转Json="+json);

        //3、List<Object> <->Json
        //准备数据
        List<Company> companyList = new ArrayList<Company>();
        companyList.add(getCompany());
        companyList.add(getCompany());
        companyList.add(getCompany());

        //List<Object>转Json
        json =  jm.toJson(companyList);
        logger.info("3.1、List<Object>转Json="+json);

        //Json转List<Object>
        List<Company> companyListDecode = jm.fromJson(json,new TypeReference<List<Company>>() {});
        logger.info("3.2、Json转List<Object>="+companyListDecode.get(0).getDepartmentArrayList().get(0).getName());

        //4、Map<String, Object><-> Json
        //准备数据
        Map<String, Company> map1 = new HashMap<String, Company>() ;
        map1.put("1",getCompany());
        map1.put("2",getCompany());
        map1.put("3",getCompany());

        //Map<String, Object>转Json
        json =  jm.toJson(map1);
        logger.info("4.1、Map<String, Object>转Json="+json);

        //Json转Map<String, Object>
        Map<String, Company> map1Decode = jm.fromJson(json,new TypeReference<Map<String, Company>>() {});
        logger.info("4.2、Json转Map<String, Object>="+map1Decode.get("1").getDepartmentArrayList().get(0).getName());

        //5、List<Map<String, Object>><->Json
        //准备数据
        List<Map<String, Company>> listMap =new ArrayList<Map<String, Company>>();
        Map<String, Company> map2 = new HashMap<String, Company>() ;
        map2.put("a",getCompany());
        map2.put("b",getCompany());
        map2.put("c",getCompany());
        listMap.add(map1);
        listMap.add(map2);

        //List<Map<String, Object>> 转Json
        json =  jm.toJson(listMap);
        logger.info("5.1、List<Map<String, Object>> 转Json="+json);

        //Json 转 List<Map<String, Object>>
        List<Map<String, Company>> listMapDecode = jm.fromJson(json,new TypeReference<List<Map<String, Company>>>() {});
        logger.info("5.2、List<Map<String, Object>> 转Json="+listMapDecode.get(0).get("1").getDepartmentArrayList().get(0).getName());
    }

    private  Company getCompany()
    {
        long time = new Date().getTime();
        String sTime =  String.valueOf(time);
        Company c = new Company();
        c.setEmployeesCount(10);
        c.setName("公司名"+sTime);
        c.setAddress("世界中心");
        c.setCreateDate(new Date());

        List list = new ArrayList();
        Department d1= new Department();
        d1.setEmployeesCount(5);
        d1.setName("部门1"+sTime);

        Department d2= new Department();
        d2.setEmployeesCount(5);
        d2.setName("部门2"+sTime);

        list.add(d1);
        list.add(d2);

        c.setDepartmentArrayList(list);

        return  c;
    }

    @org.junit.Test
    public void test2() throws Exception {
        JsonHelper jm = new JsonHelper();

        //1、对象<->Json
        //准备数据
        Company c = getCompany();
        //对象转Json
        String json =  jm.toJson(c);
        logger.info("1.1、对象转Json="+json);
    }
}
