package com.inxedu.os.edu.entity.shopcart;

import com.inxedu.os.edu.entity.course.Course;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author www.inxedu.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Shopcart implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6757060331190184782L;
    private Long id;
    private Long goodsid;// 商品id
    private Long userid;
    private Long type;//1课程 2套餐（备用）
    private java.util.Date addTime;
    private Course course;
}
