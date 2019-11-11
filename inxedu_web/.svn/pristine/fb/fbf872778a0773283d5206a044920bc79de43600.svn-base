package com.inxedu.os.edu.controller.shopcart;

import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.order.OrderConstans;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.coupon.QueryCouponCode;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.member.MemberSale;
import com.inxedu.os.edu.entity.member.MemberSaleDTO;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.shopcart.Shopcart;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.coupon.CouponCodeService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.shopcart.ShopcartService;
import com.inxedu.os.edu.util.shopcart.ShopCartUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shopcart管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Controller
public class ShopcartController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ShopcartController.class);
	// 购物车
	private static final String shopCart = getViewPath("/web/shopcart/shopCart");
    /*开通会员支付*/
    private static final String shopMemberCart = getViewPath("/web/shopcart/shopMemberCart");
	// 购物车
	private static final String showshopCartinfo = getViewPath("/web/shopcart/showCourseShopcartinfo");
	// 头部购物车
	private static final String ajaxShopCartListRight = getViewPath("/web/shopcart/ajaxShopCartListRight");
	@Autowired
	private ShopcartService shopcartService;
	@Autowired
	private CourseService courseService;
	@Autowired(required = false)
	private CouponCodeService couponCodeService;
    @Autowired(required=false)
    private MemberSaleService memberSaleService;
    @Autowired(required=false)
    private MemberTypeService memberTypeService;
    @Autowired(required=false)
    private UserAccountService userAccountService;
	/**
	 * 添加商品并跳转到购物车
	 */
	@RequestMapping(value = "/shopcart", params = "command=addShopitem")
	public String addShopcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String goodsId = request.getParameter("goodsid");// 商品id
			String type = request.getParameter("type");// 类型1课程2套餐
			logger.info("++++++++++goodsId=" + goodsId + "++++++++++type=" + type);
			if (StringUtils.isNotEmpty(goodsId) && StringUtils.isNotEmpty(type)) {
				int userId=SingletonLoginUtils.getLoginUserId(request);
				if (userId!=0) {
					// 登录用户添加到数据库
					shopcartService.addShopcart(Long.valueOf(goodsId), Long.valueOf(type),Long.valueOf(userId));
				} else {
					// 从Cookie获取购物车信息
					String shopJson = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
					shopJson = shopcartService.addTempShopcart(Long.valueOf(goodsId), Long.valueOf(type), shopJson);
					WebUtils.setCookie(response, OrderConstans.SHOP_CART, shopJson, 1);
				}
			}
		} catch (Exception e) {
			logger.error("addShopcart error", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/shopcart?command=queryShopCart";
	}
	/**
	 * 查询购物车（临时购物车,用户购物车）
	 */
	@RequestMapping(value = "/shopcart", params = "command=queryShopCart")
	public String queryShopCart(HttpServletRequest request, HttpServletResponse response) {
		try {
			//用户登录查询用户优惠券
			if(SingletonLoginUtils.getLoginUserId(request)!=0){
				//获取用户购物车总价格
				List<Shopcart> shopCartList;
				//总价格
				BigDecimal totalMoney=new BigDecimal(0);
				int userId=SingletonLoginUtils.getLoginUserId(request);
				if (userId!=0) {
					shopCartList = shopcartService.getShopcartList(Long.valueOf(userId), 1l);
				} else {
					String json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
					shopCartList = shopcartService.getTempShopcartList(json);
				}
				if(ObjectUtils.isNotNull(shopCartList)){
					for(Shopcart sc:shopCartList){
						totalMoney=totalMoney.add(sc.getCourse().getCurrentPrice());
					}
				}
				if("ON".equals(CacheConstans.COUPON_IS_OPEN) && ObjectUtils.isNotNull(couponCodeService)){
					//查询我的优惠券 查询可以使用  并且达到优惠券最低限额的优惠券
					QueryCouponCode queryCouponCode=new QueryCouponCode();
					queryCouponCode.setUserId(Long.valueOf(userId));
					queryCouponCode.setLimitAmount(totalMoney);
					queryCouponCode.setStatus(1);
					queryCouponCode.setType(1);//查询课程优惠券（非会员优惠券）
					List<CouponCodeDTO> couponCodeList=couponCodeService.queryCouponCodeByUser(queryCouponCode);
					request.setAttribute("couponCodeList",couponCodeList);
				}
				if (ObjectUtils.isNotNull(userAccountService)){
					request.setAttribute("userAccount",userAccountService.getUserAccountByUserId(Long.valueOf(userId)));
				}
			}
		}catch (Exception e){
			logger.error("ShopcartController.queryShopCart()--error",e);
			setExceptionRequest(request,e);
		}
		return shopCart;
	}
	/**
	 * 查询购物车（临时购物车,用户购物车）
	 */
	@RequestMapping(value = "/shopcart/ajax/queryShopCartinfo")
	public String queryShopCartinfo(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Long type) {
		try {
			List<Shopcart> shopCartList = null;
			int userId=SingletonLoginUtils.getLoginUserId(request);
			if (userId!=0) {
				shopCartList = shopcartService.getShopcartList(Long.valueOf(userId), type);
			} else {
				String json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
				shopCartList = shopcartService.getTempShopcartList(json);
			}
			for (Shopcart shopcart:shopCartList){
				/*如果订单里是套餐*/
				if ("PACKAGE".equals(shopcart.getCourse().getSellType())){
					/*取套餐下的子课*/
					List<Long> ids = new ArrayList<Long>();
					ids.add(Long.valueOf(shopcart.getCourse().getCourseId()));
					List<CourseDto> courseDtoList=courseService.getCourseListPackage(ids);
					/*给该订单的课程set 子课集合*/
					shopcart.getCourse().setCourseList(courseDtoList);
				}
			}
				request.setAttribute("shopcartList", shopCartList);
		} catch (Exception e) {
			logger.error("queryShopCart error", e);
		}
		return showshopCartinfo;
	}

	/**
	 * 查询购物车列表
	 */
	@RequestMapping(value = "/shopcart/ajax/headerShopCartinfo")
	public String ajaxShopCartListRight(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Long type) {
		try {
			List<Shopcart> shopCartList = null;
			int userId=SingletonLoginUtils.getLoginUserId(request);
			if (userId>0) {
				shopCartList = shopcartService.getShopcartList(Long.valueOf(userId), type);
			} else {
				String json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
				shopCartList = shopcartService.getTempShopcartList(json);
			}
			request.setAttribute("shopcartList", shopCartList);
		} catch (Exception e) {
			logger.error("queryShopCart error", e);
		}
		return ajaxShopCartListRight;
	}

	/**
	 * 删除购物车的一个Item ,Ajax 完成实时的 操作
	 */
	@RequestMapping(value = "/shopcart/ajax/deleteShopitem")
	@ResponseBody
	public Map<String, Object> deleteShopItem(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String goodsId = request.getParameter("goodsid");// 商品id
		String type = request.getParameter("type");// 类型1课程2套餐
		try {
			int userId=SingletonLoginUtils.getLoginUserId(request);
			if (userId!=0) {
				shopcartService.deleteShopcartById(id, Long.valueOf(userId));
			} else {
				String json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
				json = shopcartService.deleteTempShopcart(Long.valueOf(goodsId), Long.valueOf(type), json);
				if (json == null) {
					json = "";
				}
				WebUtils.setCookie(response, OrderConstans.SHOP_CART, json, 1);
			}
			map.put("message", "success");
		} catch (Exception e) {
			logger.error("deleteShopItem error", e);
			map.put("message", "false");
			return map;
		}
		return map;
	}

	/**
	 * 根据type清空购物车
	 */
	@RequestMapping(value = "/shopcart/clearShopitem/{type}")
	@ResponseBody
	public Map<String, Object> clearShopitem(@PathVariable Long type, HttpServletRequest request) throws Exception {
		Map<String, Object> json = null;
		try {
			int userId=SingletonLoginUtils.getLoginUserId(request);
			if (userId!=0) {
				shopcartService.deleteShopcartByType(type, Long.valueOf(userId));
				json = this.setJson(true, "", null);
			}
		} catch (Exception e) {
			logger.error("deleteShopItem error", e);
			json = this.setJson(false, "", null);
		}
		return json;
	}

	/**
	 * 查询购物车（临时购物车,用户购物车）
	 */
	@RequestMapping(value = "/shopcart/ajax/shopcartnums")
	@ResponseBody
	public Object queryShopCartnums(HttpServletRequest request,@RequestParam(required = true) Long type) {
		Map<String, Object> json = null;
		try {
			List<Shopcart> shopCartList = null;
			int userId=SingletonLoginUtils.getLoginUserId(request);
			if (userId!=0) {
				shopCartList = shopcartService.getShopcartList(Long.valueOf(userId), type);
			} else {
				String _json = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
				shopCartList = shopcartService.getTempShopcartList(_json);
			}
			Map<String,Object> data = new HashMap<String, Object>();
			data.put("shopCartList", shopCartList);
			int shopCartNum =0;
			if(shopCartList!=null){
				shopCartNum+=shopCartList.size();
			}
			data.put("shopCartNum", shopCartNum);
			json = this.setJson(true, null, data);
		} catch (Exception e) {
			json = this.setJson(true, null, null);
		}
		return json;
	}
	/**
	 * ajax添加商品到购物车
	 */
	@RequestMapping(value = "/shopcart/ajax/add")
	@ResponseBody
	public Object addShopcartAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> json = null;
		try {
			String goodsId = request.getParameter("goodsid");// 商品id
			String type = request.getParameter("type");// 类型1课程2套餐
			logger.info("++++++++++goodsId=" + goodsId + "++++++++++type=" + type);
			if (StringUtils.isNotEmpty(goodsId) && StringUtils.isNotEmpty(type)) {
				int userId=SingletonLoginUtils.getLoginUserId(request);
				if (userId!=0) {
					// 登录用户添加到数据库
					Map<String,Object> dataMap=shopcartService.addShopcart(Long.valueOf(goodsId), Long.valueOf(type),Long.valueOf(userId));
					boolean isexsits=(Boolean) dataMap.get("isexsits");
					if(isexsits){
						json = this.setJson(false, "购物车已有此课程，请勿重复添加","");
					}else {
						json = this.setJson(true, null,"");
					}
				} else {
					// 从Cookie获取购物车信息
					String shopJson = WebUtils.getCookie(request, OrderConstans.SHOP_CART);
					int itemIndex =-2;
					if (StringUtils.isNotEmpty(shopJson)){
						itemIndex = ShopCartUtil.queryIndex(Long.valueOf(goodsId), shopJson, Long.valueOf(type));
					}
					if (itemIndex == -2) { // -2表示查找不到
						shopJson = shopcartService.addTempShopcart(Long.valueOf(goodsId), Long.valueOf(type), shopJson);
						WebUtils.setCookie(response, OrderConstans.SHOP_CART, shopJson, 1);
						json = this.setJson(true, null,"");
					}else {
						json = this.setJson(false, "购物车已有此课程，请勿重复添加","");
					}
				}
			}
		} catch (Exception e) {
			logger.error("addShopcartAjax error", e);
			json = this.setJson(false, "系统异常", "");
		}
		return json;
	}
    /**
     * 支付开通会员金额
     */
    @RequestMapping("/payMember/{memberSaleId}")
    public String payMember(HttpServletRequest request,@PathVariable long memberSaleId) {
        try {
			/*会员类型*/
			List<MemberType> memberTypeList = memberTypeService.getMemberTypes();
			/*每类会员一个月的商品信息*/
			List<MemberSale> memberSaleList = new ArrayList<>();
			/*根据会员类型获取一个月的商品信息*/
			for (int i=0;i<memberTypeList.size();i++){
				MemberSaleDTO memberSaleDTO = new MemberSaleDTO();
				memberSaleDTO.setType(memberTypeList.get(i).getId());
				memberSaleDTO.setDays(1);
				MemberSale memberSale = memberSaleService.getMemberSale(memberSaleDTO);
				memberSaleList.add(memberSale);
			}
        	String memberTypeId = request.getParameter("memberTypeId");

            request.setAttribute("memberTypeList",memberTypeList);
			request.setAttribute("memberSaleList",memberSaleList);
			request.setAttribute("memberSaleId",memberSaleId);
			request.setAttribute("memberTypeId",memberTypeId);
			request.setAttribute("userAccount",userAccountService.getUserAccountByUserId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request))));
        }catch (Exception e){
            logger.error("ShopcartController.payMember()--error",e);
            setExceptionRequest(request,e);
        }
        return shopMemberCart;
    }
}
