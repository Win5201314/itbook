package com.inxedu.os.edu.dao.coupon;

import com.inxedu.os.edu.entity.coupon.CouponCode;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.coupon.QueryCouponCode;
import com.inxedu.os.common.entity.PageEntity;

import java.util.List;

/**
 * CouponCode管理接口
 * @author www.inxedu.com
 */
public interface CouponCodeDao {

    /**
     * 批量添加添加CouponCode
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
     * @param it 要查询的id
     * @return CouponCode
     */
	CouponCode getCouponCodeById(Long it);

    /**
     * 根据条件获取CouponCode列表
     * @param couponCode 查询条件
     * @return List<CouponCode>
     */
	List<CouponCode> getCouponCodeListByCouponId(CouponCode couponCode);
    /**
     * 优惠券id查找优惠券编码
     */
	List<String> getStringCodeByCouponId(Long id);
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
	 * 删除优惠券编码
	 */
	void delCodeByCouponId(Long id);
	/**
	 * 作废优惠券下的未使用优惠编码
	 * @param id
	 */
	void wasteCodeByCouponId(Long id);
	/**
	 * 过期的优惠编码改状态
	 */
	void overdueCodeByTime();

	/**
     * 根据优惠券编码获取单个CouponCode对象
     * @return CouponCode
     */
	CouponCodeDTO getCouponCodeByCode(String code);

	/**
	 * 个人中心我的优惠券
	 * @param queryCouponCode
	 * @param page
     * @return
     */
	List<CouponCodeDTO> queryCouponCodePageByUser(QueryCouponCode queryCouponCode, PageEntity page);

	/**
	 * 查询我未过期的 可用的优惠券
	 * @param queryCouponCode
	 * @return
     */
	List<CouponCodeDTO> queryCouponCodeByUser(QueryCouponCode queryCouponCode);
	/*查询即将过期的优惠券*/
	List<CouponCodeDTO> timeOverCouponCodeMsg();

}