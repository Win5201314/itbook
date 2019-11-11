package com.inxedu.os.edu.service.coupon;


import com.inxedu.os.edu.entity.coupon.CouponCode;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.coupon.QueryCouponCode;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.common.entity.PageEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * CouponCode管理接口
 * @author www.inxedu.com
 */
public interface CouponCodeService {

    /**
     * 添加Coupon
     * @return id
     */
	void addCouponCode(StringBuffer val);

    /**
     * 修改CouponCode
     * @param couponCode 要修改的CouponCode
     */
	void updateCouponCode(CouponCode couponCode);

	/**
	 * 指派优惠券给用户
	 * @param couponCode
	 */
	void updateCouponCodeUser(CouponCode couponCode);

    /**
     * 根据id获取单个CouponCode对象
     * @return CouponCode
     */
	CouponCode getCouponCodeById(Long id);
    /**
     * 根据优惠券编码获取单个CouponCode对象
     * @return CouponCode
     */
	CouponCodeDTO getCouponCodeByCode(String code);
	/**
	 * 验证优惠券的限制范围
	 */
	Map<String, Object> checkCouponByCode(HttpServletRequest request);

    /**
     * 根据条件获取CouponCode列表
     * @param couponCode 查询条件
     * @return List<CouponCode>
     */
	List<CouponCode> getCouponCodeListByCouponId(CouponCode couponCode);
	/**
	 * 优惠券编码列表
	 * @param page
	 * @return
	 */
	List<CouponCodeDTO> getCouponCodePage(QueryCouponCode queryCouponCode, PageEntity page);
	/**
	 * id查询优惠券编码
	 * @return
	 */
	CouponCodeDTO getCouponCodeDTO(Long id);
	/**
	 * 作废优惠券编码
	 */
	void wasteCouponCode(String ids);
	/**
	 * 作废优惠券下的未使用优惠编码
	 * @param id
	 */
	void wasteCodeByCouponId(Long id);

	/**
	 * 删除优惠券下的优惠编码
	 * @param id
	 */
	void delCodeByCouponId(Long id);
	/**
	 * 优惠编码使用限制
	 * @param couponCodeDTO
	 * @return
	 */
	Map<String,Object> checkCode(CouponCodeDTO couponCodeDTO,List<Course> courses, Long loginUserId);
	/**
	 * 个人中心我的优惠券
	 * @param queryCouponCode
	 * @param page
	 * @return
	 */
	List<CouponCodeDTO> queryCouponCodePageByUser(QueryCouponCode queryCouponCode, PageEntity page);

	/**
	 * 过期的优惠编码改状态
	 */
	void overdueCodeByTime();

	/**
	 * 查询我未过期的 可用的优惠券
	 */
	List<CouponCodeDTO> queryCouponCodeByUser(QueryCouponCode queryCouponCode);

}