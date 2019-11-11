package com.inxedu.os.app.controller.shopcart;

import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.enums.ReqChanle;
import com.inxedu.os.edu.constants.order.OrderConstans;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.coupon.QueryCouponCode;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.shopcart.Shopcart;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.coupon.CouponCodeService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.shopcart.ShopcartService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.util.shopcart.ShopCartUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/webapp")
public class AppShopcartController extends BaseController{
	private static Logger logger=Logger.getLogger(AppShopcartController.class);
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private ShopcartService shopcartService;
	@Autowired
	private UserService userService;
	@Autowired(required = false)
	private CouponCodeService couponCodeService;
	@Autowired(required=false)
	private UserAccountService userAccountService;

	/**
	 * 添加到购物车
	 */
	@RequestMapping("/shopcart/add")
	@ResponseBody
	public Map<String, Object> addShopcart(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			String goodsId = request.getParameter("goodsid");// 商品id
			String type = "1";
			logger.info("++++++++++goodsId=" + goodsId + "++++++++++type=" + type);
			if (StringUtils.isNotEmpty(goodsId)) {
				String userId = request.getParameter("userId");
				if(userId==null||userId.trim().equals("")){
					json=this.setJson(false, "用户Id不能为空", null);
					return json;
				}
				if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
					json=this.setJson(false, "没有该用户", null);
					return json;
				}
				if (Integer.parseInt(userId)!=0) {
					// 登录用户添加到数据库
					Map<String,Object> dataMap=shopcartService.addShopcart(Long.valueOf(goodsId), Long.valueOf(type),Long.valueOf(userId));
					boolean isexsits=(Boolean) dataMap.get("isexsits");
					if(isexsits){
						json = this.setJson(false, "购物车已有此课程，请勿重复添加","");
					}else {
						json = this.setJson(true, null,"");
					}
				}
			}
		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("topLive()--error",e);
		}
		return json;
	}
	/**
	 * 进入购物车
	 */
	@RequestMapping("/queryShopCart")
	@ResponseBody
	public Map<String, Object> queryShopCart(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			Map<String,Object> result = new HashedMap();
			//用户登录查询用户优惠券
			if(SingletonLoginUtils.getLoginUserId(request)!=0) {
				//获取用户购物车总价格
				List<Shopcart> shopCartList;
				//总价格
				BigDecimal totalMoney = new BigDecimal(0);
				String userId = request.getParameter("userId");
				if (userId == null || userId.trim().equals("")) {
					json = this.setJson(false, "用户Id不能为空", null);
					return json;
				}
				if (ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))) {
					json = this.setJson(false, "没有该用户", null);
					return json;
				}
				shopCartList = shopcartService.getShopcartList(Long.valueOf(userId), 1l);
				if (ObjectUtils.isNotNull(shopCartList)) {
					for (Shopcart sc : shopCartList) {
						totalMoney = totalMoney.add(sc.getCourse().getCurrentPrice());
					}
				}
				if ("ON".equals(CacheConstans.COUPON_IS_OPEN) && ObjectUtils.isNotNull(couponCodeService)) {
					//查询我的优惠券 查询可以使用  并且达到优惠券最低限额的优惠券
					QueryCouponCode queryCouponCode = new QueryCouponCode();
					queryCouponCode.setUserId(Long.valueOf(userId));
					queryCouponCode.setLimitAmount(totalMoney);
					queryCouponCode.setStatus(1);
					queryCouponCode.setType(1);//查询课程优惠券（非会员优惠券）
					List<CouponCodeDTO> couponCodeList = couponCodeService.queryCouponCodeByUser(queryCouponCode);
					result.put("couponCodeList", couponCodeList);
				}
				if (ObjectUtils.isNotNull(userAccountService)) {
					result.put("userAccount", userAccountService.getUserAccountByUserId(Long.valueOf(userId)));
				}
				result.put("shopCartList",shopCartList);
			}
			json =this .setJson(true,"",result);
		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("topLive()--error",e);
		}
		return json;
	}
}
