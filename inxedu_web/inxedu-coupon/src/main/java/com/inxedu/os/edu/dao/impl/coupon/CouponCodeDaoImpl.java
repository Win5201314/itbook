package com.inxedu.os.edu.dao.impl.coupon;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.coupon.CouponCodeDao;
import com.inxedu.os.edu.entity.coupon.CouponCode;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.coupon.QueryCouponCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author www.inxedu.com
 */
 @Repository("couponCodeDao")
public class CouponCodeDaoImpl extends GenericDaoImpl implements CouponCodeDao {

	/**
     * 批量添加添加CouponCode
     * @return id
     */
    public void addCouponCode(StringBuffer val){
    	this.insert("CouponCodeMapper.createCouponCode", val.toString());
    }


    public void updateCouponCode(CouponCode couponCode) {
        this.update("CouponCodeMapper.updateCouponCode",couponCode);
    }

	/**
	 * 指派优惠券给用户
	 *
	 * @param couponCode
	 */
	public void updateCouponCodeUser(CouponCode couponCode) {
		this.update("CouponCodeMapper.updateCouponCodeUser",couponCode);
	}

    public CouponCode getCouponCodeById(Long id) {
        return this.selectOne("CouponCodeMapper.getCouponCodeById",id);
    }

    public List<CouponCode> getCouponCodeListByCouponId(CouponCode couponCode) {
        return this.selectList("CouponCodeMapper.getCouponCodeListByCouponId",couponCode);
    }
    /**
     * 优惠券id查找优惠券编码
     */
	public List<String> getStringCodeByCouponId(Long id){
		return this.selectList("CouponCodeMapper.getStringCodeListByCouponId",id);
	}
	
	/**
	 * 优惠券编码列表
	 * @param page
	 * @return
	 */
	public List<CouponCodeDTO> getCouponCodePage(QueryCouponCode queryCouponCode, PageEntity page) {
		return this.queryForListPage("CouponCodeMapper.getCouponCodePage", queryCouponCode, page);
	}
	/**
	 * id查询优惠券编码
	 * @return
	 */
	public CouponCodeDTO getCouponCodeDTO(Long id){
		return this.selectOne("CouponCodeMapper.getCouponCodeDTO",id);
	}
	/**
     * 根据优惠券编码获取单个CouponCode对象
     * @return CouponCode
     */
    public CouponCodeDTO getCouponCodeByCode(String code){
    	return this.selectOne("CouponCodeMapper.getCouponCodeDTOByCode",code);
    }

	/**
	 * 个人中心 我的优惠券
	 * @param queryCouponCode
	 * @param page
     * @return
     */
	public List<CouponCodeDTO> queryCouponCodePageByUser(QueryCouponCode queryCouponCode, PageEntity page) {
		return queryForListPage("CouponCodeMapper.queryCouponCodePageByUser",queryCouponCode,page);
	}

	/**
	 * 查询我未过期的 可用的优惠券
	 * @param queryCouponCode
	 * @return
     */
	public List<CouponCodeDTO> queryCouponCodeByUser(QueryCouponCode queryCouponCode) {
		return selectList("CouponCodeMapper.queryCouponCodeByUser",queryCouponCode);
	}


	/**
	 * 作废优惠券编码
	 */
	public void wasteCouponCode(String ids){
		this.update("CouponCodeMapper.wasteCouponCode", ids);
	}

	public void delCodeByCouponId(Long id) {
		this.delete("CouponCodeMapper.delCodeByCouponId",id);
	}

	/**
	 * 作废优惠券下的未使用优惠编码
	 * @param id
	 */
	public void wasteCodeByCouponId(Long id){
		this.update("CouponCodeMapper.wasteCodeByCouponId", id);
	}
	/**
	 * 过期的优惠编码改状态
	 */
	public void overdueCodeByTime(){
		this.update("CouponCodeMapper.overdueCodeByTime", 0);
	}

	@Override
	public List<CouponCodeDTO> timeOverCouponCodeMsg() {
		return this.selectList("CouponCodeMapper.timeOverCouponCodeMsg",null);
	}
}
