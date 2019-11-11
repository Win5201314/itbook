package com.inxedu.os.edu.dao.impl.coupon;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.coupon.CouponDao;
import com.inxedu.os.edu.entity.coupon.Coupon;
import com.inxedu.os.edu.entity.coupon.CouponLimit;
import com.inxedu.os.edu.entity.coupon.QueryCoupon;
import com.inxedu.os.edu.entity.course.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Coupon
 * @author www.inxedu.com
 */
 @Repository("couponDao")
public class CouponDaoImpl extends GenericDaoImpl implements CouponDao {

    public Long addCoupon(Coupon coupon) {
        return this.insert("CouponMapper.createCoupon",coupon);
    }

    public void delCouponById(Long id){
        this.delete("CouponMapper.deleteCouponById",id);
    }
	public void updateCoupon(Coupon coupon) {
		this.update("CouponMapper.updateCoupon",coupon);
	}
    /**
     * 修改Coupon使用数量
     */
    public void updateCouponUserNum(Long id){
        this.update("CouponMapper.updateCouponUserNum",id);
    }
    public Coupon getCouponById(Long id) {
        return this.selectOne("CouponMapper.getCouponById",id);
    }

	public List<Coupon> getCouponPage(QueryCoupon queryCoupon, PageEntity page) {
		return this.queryForListPage("CouponMapper.getCouponPage", queryCoupon, page);
	}
	public void addCouponLimitCourse(List<CouponLimit> couponLimits) {
		this.insert("CouponMapper.addCouponLimitCourse",couponLimits);

	}
	
	/**
	 * 查看优惠券
	 * @param id
	 * @return
	 */
	public Coupon getCouponDTOById(Long id) {
		return this.selectOne("CouponMapper.getCouponDTOById",id);
	}

	/**
	 * 优惠券id查找限制课程
	 * @param id
	 * @return
	 */
	public List<Course> getCouponLimitCourseById(Long id){
		return this.selectList("CouponMapper.getCouponCourse",id);
	}
}
