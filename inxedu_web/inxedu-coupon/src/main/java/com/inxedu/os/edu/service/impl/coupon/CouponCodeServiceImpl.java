package com.inxedu.os.edu.service.impl.coupon;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.enums.MsgType;
import com.inxedu.os.edu.dao.coupon.CouponCodeDao;
import com.inxedu.os.edu.entity.coupon.CouponCode;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.coupon.QueryCouponCode;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.service.coupon.CouponCodeService;
import com.inxedu.os.edu.service.coupon.CouponService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.shopcart.ShopcartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * CouponCode 优惠券编码 管理接口
 * @author www.inxedu.com
 */
@Service("couponCodeService")
public class CouponCodeServiceImpl implements CouponCodeService {

 	@Autowired(required = false)
    private CouponCodeDao couponCodeDao;
	@Autowired
	private ShopcartService shopcartService;
	@Autowired
	private OrderService trxorderService;
	@Autowired(required = false)
	private CouponService couponService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private MsgReceiveService msgReceiveService;
	Logger logger = LoggerFactory.getLogger(CouponCodeServiceImpl.class);

	/**
     * 添加CouponCode
     * @param val 要添加的CouponCode
     * @return id
     */
	public void addCouponCode(StringBuffer val) {
 		couponCodeDao.addCouponCode(val);//生成优惠券编码
	}


    /**
     * 修改CouponCode
     * @param couponCode 要修改的CouponCode
     */
    public void updateCouponCode(CouponCode couponCode){
     	couponCodeDao.updateCouponCode(couponCode);
    }

	/**
	 * 指派优惠券给用户
	 *
	 * @param couponCode
	 */
	public void updateCouponCodeUser(CouponCode couponCode) {
		couponCodeDao.updateCouponCodeUser(couponCode);
	}

    /**
     * 根据id获取单个CouponCode对象
     * @param id 要查询的id
     * @return CouponCode
     */
    public CouponCode getCouponCodeById(Long id){
    	return couponCodeDao.getCouponCodeById(id);
    }
    /**
     * 根据优惠券编码获取单个CouponCode对象
     * @param code 要查询的code
     * @return CouponCode
     */
    public CouponCodeDTO getCouponCodeByCode(String code){
    	return couponCodeDao.getCouponCodeByCode(code);
    }
	/**
	 * 验证优惠券的限制范围
	 */
	public Map<String, Object> checkCouponByCode(HttpServletRequest request){
		Map<String, Object> json = null;
		String couponCode = request.getParameter("couponCode");// 优惠券编码
		String requestId = request.getParameter("requestId");// 订单编号
		Long userId = Long.valueOf(SingletonLoginUtils.getLoginUserId(request));// 用户id
		// 查询优惠券编码
		CouponCodeDTO couponCodeDTO = getCouponCodeByCode(couponCode);
		if(ObjectUtils.isNull(couponCodeDTO)){
			json = WebUtils.setJson(false, "优惠券编码不存在", null);
			return json;
		}
		List<Course> courses = null;
		if (requestId != null && requestId != "") {// 订单课程集合
			courses = trxorderService.getTrxCourseByRequestId(requestId);// 订单课程集合
		} else {// 查询购物车的课程集合
			courses = shopcartService.getShopcartCourseList(userId);
			if (courses == null || courses.size() == 0) {// 如果购物车为空
				json = WebUtils.setJson(false, "购物车不能为空", null);
				return json;
			}
		}
		// 验证优惠券编码
		Map<String, Object> map = checkCode(couponCodeDTO, courses, userId);
		/**coupon_frozen_temp**/
		//判断优惠券是否冻结（已下单）
		Order order =trxorderService.getTrxorderByRequestId(requestId);
		//重新支付验证
		if (ObjectUtils.isNotNull(order)&&order.getCouponCodeId().intValue()!=couponCodeDTO.getId().intValue()&&couponCodeDTO.getStatus()==6){
			json = WebUtils.setJson(false, "此优惠券已在其他订单中使用！", map);
			return json;
		}else if(ObjectUtils.isNull(order) && couponCodeDTO.getStatus()==6){//未下订单
			json = WebUtils.setJson(false, "此优惠券已在其他订单中使用！", map);
			return json;
		}
		/**coupon_frozen_temp**/
		if (map.get("msg") == "true") {
			map.put("couponCodeDTO", couponCodeDTO);
			json = WebUtils.setJson(true, "true", map);
		} else {
			json = WebUtils.setJson(false, map.get("msg").toString(), null);
		}
		return json;
	}
	/**
	 * 根据条件获取CouponCode列表
	 * @param couponCode
	 * @return
     */
    public List<CouponCode> getCouponCodeListByCouponId(CouponCode couponCode){
    	return couponCodeDao.getCouponCodeListByCouponId(couponCode);
    }
    /**
     * 优惠券id查找优惠券编码
     */
	public List<String> getStringCodeByCouponId(Long id) {
		return couponCodeDao.getStringCodeByCouponId(id);
	}
	/**
	 * 优惠券编码列表
	 * @param queryCouponCode
	 * @param page
	 * @return
	 */
	public List<CouponCodeDTO> getCouponCodePage(QueryCouponCode queryCouponCode, PageEntity page){
		return couponCodeDao.getCouponCodePage(queryCouponCode,page);
	}
	/**
	 * id查询优惠券编码
	 * @param id
	 * @return
	 */
	public CouponCodeDTO getCouponCodeDTO(Long id){
		return couponCodeDao.getCouponCodeDTO(id);
	}
	/**
	 * 作废优惠券编码
	 * @param ids
	 */
	public void wasteCouponCode(String ids){
		couponCodeDao.wasteCouponCode(ids);
	}
	/**
	 * 作废优惠券下的未使用优惠编码
	 * @param id
	 */
	public void wasteCodeByCouponId(Long id){
		couponCodeDao.wasteCodeByCouponId(id);
	}

	@Override
	public void delCodeByCouponId(Long id) {
		couponCodeDao.delCodeByCouponId(id);
	}

	/**
	 * 过期的优惠编码改状态
	 */
	public void overdueCodeByTime(){
		try {
			couponCodeDao.overdueCodeByTime();
			 /*查询即将过去的优惠券信息*/
			List<CouponCodeDTO> couponCodeDTOs = couponCodeDao.timeOverCouponCodeMsg();
			for (CouponCodeDTO couponCodeDTO:couponCodeDTOs){
				String title = "优惠券过期提示！";
				msgReceiveService.sendMessage(Integer.parseInt(couponCodeDTO.getUserId().toString()), title, MsgType.timeOverCouponCodeMsg.toString(), true,couponCodeDTO.getCouponCode() );
			}

		}catch (Exception e){
			logger.error("MsgSystemServiceImpl---timeOverMsg is error", e);
		}
	}
	/**
	 * 优惠编码使用限制
	 * @param couponCodeDTO
	 * @param courses
	 * @return
	 */
	public Map<String,Object> checkCode(CouponCodeDTO couponCodeDTO,List<Course> courses,Long userId){
		String resultMsg="";
		Map<String,Object> map=new HashMap<String, Object>();
        if (couponCodeDTO == null) {
        	resultMsg="优惠券不存在";
        } else if (couponCodeDTO.getStatus() == 2) {
        	resultMsg="优惠券已使用";
        } else if (couponCodeDTO.getStatus() == 3) {
        	resultMsg="优惠券已过期";
        } else if (couponCodeDTO.getStatus() == 4) {
        	resultMsg="优惠券已作废";
        }else if(couponCodeDTO.getStartTime().after(new Date())){
			resultMsg="优惠券不在使用期限内";
		}else if(ObjectUtils.isNotNull(userId)&&ObjectUtils.isNotNull(couponCodeDTO.getUserId())&&userId.longValue()!=couponCodeDTO.getUserId().longValue()){
        	resultMsg="优惠券已被占用";
        } else{
        	Date startDate = couponCodeDTO.getStartTime();
            Date endDate = couponCodeDTO.getEndTime();
            Date date=new Date();
        	if (startDate.getTime() >= date.getTime() || endDate.getTime() <= date.getTime()) {
        		resultMsg="优惠券不在使用期限内";
        	}
        }
        if(resultMsg!=""){
        	map.put("msg", resultMsg);
        	return map;
        }
		map=courseOrderCode(couponCodeDTO,courses);
        return map;
	}

	/**
	 * 个人中心 我的优惠券
	 * @param queryCouponCode
	 * @param page
     * @return
     */
	public List<CouponCodeDTO> queryCouponCodePageByUser(QueryCouponCode queryCouponCode, PageEntity page) {
		List<CouponCodeDTO> couponList = couponCodeDao.queryCouponCodePageByUser(queryCouponCode,page);
		if (couponList != null && couponList.size() > 0) {
			//处理优惠券是否过期
			Date nowDate = DateUtils.strToDate(DateUtils.dateToStr(new Date(), "yyyy-MM-dd"));
			for (CouponCodeDTO couponCodeDTO : couponList) {
				Date endDate = DateUtils.strToDate(DateUtils.dateToStr(couponCodeDTO.getEndTime(), "yyyy-MM-dd"));
				if (couponCodeDTO.getStatus() == 1) {
					//优惠券状态
					if (endDate.compareTo(nowDate) < 0) {
						couponCodeDTO.setStatus(3);
					} else if (endDate.compareTo(dateAddDay(nowDate, 3)) < 0) {
						couponCodeDTO.setStatus(5);
					}
				}
			}
		}
		return couponList;
	}
	//计算几天后的时间
	private static Date dateAddDay(Date date, int count) {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.add(Calendar.DATE, count);   // int amount   代表天数
		return cal.getTime();
	}
	/**
	 * 查询我未过期的 可用的优惠券
	 * @param queryCouponCode
	 * @return
     */
	public List<CouponCodeDTO> queryCouponCodeByUser(QueryCouponCode queryCouponCode) {
		return couponCodeDao.queryCouponCodeByUser(queryCouponCode);
	}

	/**
 	 * 课程订单优惠券编码验证
 	 * @return
 	 */
 	public Map<String,Object> courseOrderCode(CouponCodeDTO couponCodeDTO, List<Course> courses){
 		Map<String,Object> map=new HashMap<String, Object>();
		String resultMsg="";
 		double tempPrice=0.00;//临时价格
		String courseIds="";//购物车课程id字符串
        //所有课程都能用
		for(Course course:courses){//购物车课程id字符串
			courseIds+=course.getCourseId()+",";
		}
		//课程限制集合
		List<Course> limitCourses=couponService.getCouponLimitCourseById(couponCodeDTO.getCouponId());

		//优惠券项目限制,含有该项目下的课程且含有课程总价格大于优惠券限制价格
		if(couponCodeDTO.getSubjectId()>0){
			courseIds=courseIds.substring(0, courseIds.length()-1);
			//购物车中满足项目限制的课程集合
			List<Course> subjectCourses=courseService.getCouponSubjectCourse(couponCodeDTO.getSubjectId(),courseIds);
			String limitCourseIds="";//记录优惠的课程id字符串，方便以后查看
			for(Course course:subjectCourses){
				limitCourseIds+=course.getCourseId()+",";
				tempPrice+=course.getCurrentPrice().doubleValue();
			}
			if(tempPrice==0||tempPrice<couponCodeDTO.getLimitAmount().doubleValue()){
				resultMsg="优惠券不在使用范围内";//无该项目限制的课程或课程价格不满足优惠券限制
			}else{//返回能优惠的课程金额,具体的打折、减额在页面展示
				map.put("msg", "true");//通过验证标志
				map.put("tempPrice", tempPrice);
				map.put("limitCourseIds", limitCourseIds);
				return map;
			}
		}
		//优惠券课程限制,必须含有优惠券限制的所有课程
		else if(limitCourses!=null&&limitCourses.size()>0){
			String limitCourseIds="";//记录优惠的课程id字符串，方便以后查看
			boolean flag=false;
			for(Course course:limitCourses){//是否包含全部限制课程
				limitCourseIds+=course.getCourseId()+",";
				if(courseIds.indexOf(course.getCourseId()+",")!=-1){
					flag=true;
				}
				tempPrice+=course.getCurrentPrice().doubleValue();//限制课程总价格
			}
			if(flag!=true||tempPrice==0||tempPrice<couponCodeDTO.getLimitAmount().doubleValue()){
				resultMsg="优惠券不在使用范围内";//购物车没有优惠限制的全部课程或课程价格不满足优惠券限制
			}else{//返回能优惠的课程金额,具体的打折、减额在页面展示
				map.put("msg", "true");//通过验证标志
				map.put("tempPrice", tempPrice);
				map.put("limitCourseIds", limitCourseIds);
				return map;
			}
		}
		//所有课程都能用
		else{
			for(Course course:courses){
				tempPrice+=course.getCurrentPrice().doubleValue();
			}
			if(tempPrice==0||tempPrice<couponCodeDTO.getLimitAmount().doubleValue()){
				resultMsg="优惠券不在使用范围内";//购物车课程价格不满足优惠券限制
			}else{//返回能优惠的课程金额,具体的打折、减额在页面展示
				map.put("msg", "true");//通过验证标志
				map.put("tempPrice", tempPrice);

				return map;
			}
		}
		map.put("msg",resultMsg);//返回错误信息
		return map;
 	}
}
