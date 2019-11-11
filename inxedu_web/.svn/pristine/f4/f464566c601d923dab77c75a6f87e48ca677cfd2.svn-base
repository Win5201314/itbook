package com.inxedu.os.edu.service.coupon;


import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.coupon.Coupon;
import com.inxedu.os.edu.entity.coupon.CouponLimit;
import com.inxedu.os.edu.entity.coupon.QueryCoupon;
import com.inxedu.os.edu.entity.course.Course;

import java.util.List;

/**
 * Coupon管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
public interface CouponService {

    /**
     * 添加Coupon
     * @param coupon 要添加的Coupon
     * @return id
     */
    Long addCoupon(Coupon coupon);

    /**
     * 根据id删除一个Coupon
     * @param id 要删除的id
     */
    void deleteCouponById(Long id);
    /**
     * 修改Coupon
     * @param coupon 要修改的Coupon
     */
    void updateCoupon(Coupon coupon);
    /**
     * 修改Coupon使用数量
     */
    void updateCouponUserNum(Long id);

    /**
     * 根据id获取单个Coupon对象
     * @param id 要查询的id
     * @return Coupon
     */
    Coupon getCouponById(Long id);

    /**
     * 优惠券分页列表
     * @param queryCoupon
     * @param page
     * @return
     */
    List<Coupon> getCouponPage(QueryCoupon queryCoupon, PageEntity page);
    /**
     * 添加优惠券课程限制
     * @param couponLimits
     */
    void addCouponLimitCourse(List<CouponLimit> couponLimits);
	/**
	 * 查看优惠券
	 * @param id
	 * @return
	 */
    Coupon getCouponDTOById(Long id);
    /**
     * 优惠券id查找限制课程
     * @param id
     * @return
     */
    List<Course> getCouponLimitCourseById(Long id);
}