package com.inxedu.os.app.controller.order;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.constants.enums.PayType;
import com.inxedu.os.edu.constants.enums.ReqChanle;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.teacher.QueryTeacher;
import com.inxedu.os.edu.entity.teacher.Teacher;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.livecourse.LiveCourseService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.order.TrxHessianService;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.teacher.TeacherService;
import com.inxedu.os.edu.service.user.UserService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/webapp")
public class AppOrderController extends BaseController{
	private static Logger logger=Logger.getLogger(AppOrderController.class);
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private OrderService trxorderService;
	@Autowired
	private UserService userService;
	@Autowired(required=false)
	private MemberTypeService memberTypeService;
	@Autowired
	private TrxHessianService trxHessianService;
	/**
	 * 下订单
	 * count
	 */
	@RequestMapping("/addOrder")
	@ResponseBody
	public Map<String, Object> addOrder(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			String userId=request.getParameter("userId");
			if(userId==null||userId.equals("")){
				json = this.setJson(false, "用户Id不能为空", null);
				return json;
			}
			if(ObjectUtils.isNull(userService.queryUserById(Integer.parseInt(userId)))){
				json=this.setJson(false, "没有该用户", null);
				return json;
			}
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
			sourceMap.put("type","1");// 下单类型
			sourceMap.put("couponcode", request.getParameter("couponcode"));// 优惠卷编码
			sourceMap.put("userid", userId+"");
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

		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("topLive()--error",e);
		}
		return json;
	}
	/**
	 * 支付成功回调
	 * count
	 */
	@RequestMapping("/payCallBack")
	@ResponseBody
	public Map<String, Object> payCallBack(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			Map<String, String> sourceMap = new HashedMap();
			sourceMap.put("total_fee", request.getParameter("total_fee"));//支付金额
			sourceMap.put("out_trade_no",  request.getParameter("out_trade_no"));//支付方对应单号
			sourceMap.put("requestId", request.getParameter("requestId"));//本地订单号
			sourceMap.put("userId", request.getParameter("userId"));//用户id
			sourceMap.put("payType", request.getParameter("payType"));//支付方式
			Map<String, String> res = trxHessianService.noTrxTrxOrderComplete(sourceMap);

			json = this.setJson(true, "", res);

		}catch(Exception e){
			json=this.setJson(false, "异常", null);
			logger.error("topLive()--error",e);
		}
		return json;
	}
}
