package com.inxedu.os.edu.controller.order;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.enums.ReqChanle;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Trxorder管理接口
 * @author www.inxedu.com
 */
@Controller
public class TrxorderController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(TrxorderController.class);

	// 绑定变量名字和属性，参数封装进类
	@InitBinder("queryTrxorder")
	public void initBinderQueryTrxorder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryTrxorder.");
	}
	protected static final String ERROR = "/common/error";
	@Autowired
	private OrderService trxorderService;
	@Autowired(required=false)
	private MemberTypeService memberTypeService;
	/**
	 * 创建购物车订单
	 */
	@RequestMapping(value = "/order", params = "command=buy")
	@ResponseBody
	public Map<String, Object> createTrxorder(HttpServletRequest request,@ModelAttribute("trxorder") Order trxorder) throws Exception {
		Map<String, Object> json;
		try {
			/*如果是开通会员*/
			if ("MEMBER".equals(request.getParameter("orderType"))){
				MemberType memberType = memberTypeService.getMemberTypeById(Long.parseLong(request.getParameter("otherId")));
				/*如果会员已停用*/
				if (ObjectUtils.isNotNull(memberType)&&memberType.getStatus()==1){
					json = this.setJson(false,"该类会员已停用",null);
					return json;
				}
			}
			// 拼装参数
			Map<String, String> sourceMap = new HashMap<String, String>();
			sourceMap.put("type", request.getParameter("type"));// 下单类型
			sourceMap.put("couponcode", request.getParameter("couponcode"));// 优惠卷编码
			sourceMap.put("userid", SingletonLoginUtils.getLoginUserId(request)+"");
			sourceMap.put("reqchanle", ReqChanle.WEB.toString());
			sourceMap.put("payType", request.getParameter("payType"));// 支付类型
			sourceMap.put("reqIp", WebUtils.getIpAddr(request));// 用户ip
			sourceMap.put("orderType",request.getParameter("orderType"));//订单商品类型
			String otherId = StringUtils.isNotEmpty(request.getParameter("otherId"))?request.getParameter("otherId"):"";
			sourceMap.put("otherId",otherId);//相关id 会员销售类型id

			Map<String, Object> res = trxorderService.addTrxorder(sourceMap);
			if (res.get("msg") != null) {
				json = this.setJson(true, "success", res);
				return json;
			}
			json = this.setJson(true, "success", res);
		} catch (Exception e) {
			logger.error("createTrxorder error", e);
			json = this.setJson(false, "下单异常,请稍后再试！", null);
		}
		return json;
	}
}