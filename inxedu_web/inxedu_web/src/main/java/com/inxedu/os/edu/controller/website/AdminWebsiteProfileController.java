package com.inxedu.os.edu.controller.website;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonParser;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.QCloud.QCloudSMSSignUtil;
import com.inxedu.os.common.util.QCloud.QCloudSMSTemplateUtil;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.qcloudsmstemplate.QcloudSmsTemplate;
import com.inxedu.os.edu.entity.website.WebsiteProfile;
import com.inxedu.os.edu.service.letter.MsgSystemService;
import com.inxedu.os.edu.service.qcloudsmstemplate.QcloudSmsTemplateService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网站配置管理
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteProfileController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteProfileController.class);
	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private QcloudSmsTemplateService qcloudSmsTemplateService;
	@Autowired
	private MsgSystemService msgSystemService;
	private static final String getWebSiteList = getViewPath("/admin/website/profile/website_profile_list");// 网站配置管理页面
	private static final String toupdateWebSite = getViewPath("/admin/website/profile/website_profile_update");// 更新网站配置管理页面
	private static final String getWebSiteOnline = getViewPath("/admin/website/profile/website_profile_online");// 在线咨询

	private static final String ICONAME="favicon.ico";//定义ico文件常量
	/**
	 * 查询网站配置 根据Type
	 */
	@RequestMapping("/websiteProfile/find/{type}")
	public String getWebSiteList(HttpServletRequest request, Model model, @PathVariable("type") String type) {
		String returnUrl = "";
		Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sms.toString());
		Map<String,Object> smsMap=(Map<String,Object>)websitemap.get("sms");
		QCloudSMSSignUtil qCloudSMSSignUtil = new QCloudSMSSignUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
		QCloudSMSTemplateUtil qCloudSMSTemplateUtil = new QCloudSMSTemplateUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
		try {
			if (WebSiteProfileType.ico.toString().equals(type)) {// 类型是ico文件
				return getWebSiteList;
			}
			QcloudSmsTemplate qcloudSmsTemplate = new QcloudSmsTemplate();
			List<QcloudSmsTemplate>qcloudSmsTemplates  = qcloudSmsTemplateService.queryQcloudSmsTemplateList(qcloudSmsTemplate);
			String flag = request.getParameter("flag");
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(type);
            /*视频管理被整合到了一个页面、当搜索因酷云视频配置把cc和直播的配置信息也放到map中*/
            if ("inxeduVideo".equals(type)){
                Map<String, Object> cc = websiteProfileService.getWebsiteProfileByType("cc");
                map.put("cc",cc.get("cc"));
                Map<String, Object> genseeLive = websiteProfileService.getWebsiteProfileByType("genseeLive");
                map.put("genseeLive",genseeLive.get("genseeLive"));
                Map<String, Object> inxeduCloud = websiteProfileService.getWebsiteProfileByType("inxeduCloud");
                map.put("inxeduCloud",inxeduCloud.get("inxeduCloud"));
            }
            /*搜索短信配置时把邮箱配置和消息配置的内容放入map*/
            if ("sms".equals(type)){
                Map<String, Object> emailConfigure = websiteProfileService.getWebsiteProfileByType("emailConfigure");
                map.put("emailConfigure",emailConfigure.get("emailConfigure"));
                Map<String, Object> message = websiteProfileService.getWebsiteProfileByType("message");
                map.put("message",message.get("message"));
            }
			if (StringUtils.isNotEmpty(flag)) {
				returnUrl = toupdateWebSite;// 跳转到更新页面
			} else {
				returnUrl = getWebSiteList;// 列表页
			}
			if (StringUtils.isNotEmpty(smsMap.get("sdkappid").toString())&&StringUtils.isNotEmpty(smsMap.get("strAppkey").toString())) {
 				/*查询审查状态*/
				qcloudSmsTemplateService.checkStatus(qcloudSmsTemplates);
			}
			for (QcloudSmsTemplate qcloudSmsTemplate1 :qcloudSmsTemplates){
				String text = qcloudSmsTemplate1.getText();

				qcloudSmsTemplate1.setText(text);
			}
			model.addAttribute("qcloudSmsTemplates", qcloudSmsTemplates);
			model.addAttribute("webSiteMap", map);
			model.addAttribute("type", type);
		} catch (Exception e) {
			logger.error("getWebSiteList", e);
		}
		return returnUrl;
	}
	/**
	 * 更新管理根据类型
	 */
	@RequestMapping("/websiteProfile/update")
	public String updateWebSiteByType(HttpServletRequest request, Model model, @RequestParam("type") String  type, @PathVariable("icoFile") MultipartFile icoFile) {
		try {
			if (ObjectUtils.isNotNull(type) && StringUtils.isNotEmpty(type)) {

				JsonParser jsonParser = new JsonParser();
				Map<String, Object> map = new HashMap<String, Object>();
				if (type.equals(WebSiteProfileType.web.toString())) {
					map.put("email", request.getParameter("email"));// 公司邮箱
					map.put("phone", request.getParameter("phone"));// 24小时电话
					map.put("workTime", request.getParameter("workTime"));// 工作时间
					map.put("remarks", request.getParameter("remarks"));// 备注
					map.put("copyright", request.getParameter("copyright"));// 备案
					map.put("author", request.getParameter("author"));// 作者
					map.put("keywords", request.getParameter("keywords"));// 关键词
					map.put("description", request.getParameter("description"));// 描述
					map.put("title", request.getParameter("title"));//
					map.put("company", request.getParameter("company"));// 网校名称
					map.put("icoFile", request.getParameter("icoFile"));// ico文件
					map.put("logo", request.getParameter("logo"));// 网校logo
					map.put("censusCodeString", request.getParameter("censusCodeString"));// 代码统计
					map.put("onlineUrl", request.getParameter("onlineUrl"));// 咨询链接
					map.put("onlineImageUrl", request.getParameter("onlineImageUrl"));// 二维码
					if (!icoFile.isEmpty()) {
						// 获得项目的真实路径
						String path = request.getSession().getServletContext().getRealPath("/");
						File file = new File(path + "/" + ICONAME);
						if (!file.exists()) {
							file.mkdirs();
						}
						try {
							icoFile.transferTo(file);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				// 类型为alipay
				if (type.equals(WebSiteProfileType.alipay.toString())) {
					map.put("alipaypartnerID", request.getParameter("alipaypartnerID").trim());// 合作者身份ID
					map.put("alipaykey", request.getParameter("alipaykey").trim());// 交易安全校验码
					map.put("sellerEmail", request.getParameter("sellerEmail").trim());// 商家邮箱
					map.put("status", request.getParameter("status").trim());// 支付宝选择（企业和个人）
				}

				// 类型为logo
				if (type.equals(WebSiteProfileType.logo.toString())) {// 类型为logo
					map.put("url", request.getParameter("url"));// clientID
				}
				// 注册开关
				if (type.equals(WebSiteProfileType.registerController.toString())) {//
					map.put("emailRegister", request.getParameter("emailRegister"));//
					map.put("phoneRegister", request.getParameter("phoneRegister"));
					map.put("emailProving", request.getParameter("emailProving"));
					map.put("phoneProving", request.getParameter("phoneProving"));
					map.put("ipLimit",request.getParameter("ipLimit"));
					map.put("emailLimit",request.getParameter("emailLimit"));
					map.put("mobileLimit",request.getParameter("mobileLimit"));
					map.put("emailRecovery",request.getParameter("emailRecovery"));
					map.put("mobileRecovery",request.getParameter("mobileRecovery"));
				}
				// 统计代码
				if (type.equals(WebSiteProfileType.censusCode.toString())) {
					map.put("censusCodeString", request.getParameter("censusCodeString"));// thirdloginstatus第三方登录是否开启
				}
				// 因酷云视频
				if (type.equals(WebSiteProfileType.inxeduVideo.toString())) {
					map.put("UserId", request.getParameter("UserId").trim());
					map.put("SecretKey", request.getParameter("SecretKey").trim());
					map.put("AccessKey", request.getParameter("AccessKey").trim());
				}
				// 因酷云视频
				if (type.equals(WebSiteProfileType.inxeduCloud.toString())) {
					map.put("UserId", request.getParameter("cloudUserId").trim());
					map.put("SecretKey", request.getParameter("SecretKey").trim());
					map.put("AccessKey", request.getParameter("AccessKey").trim());
				}
				// 百家云视频
				if (type.equals(WebSiteProfileType.baijiayun.toString())) {
					map.put("PartnerId", request.getParameter("PartnerId").trim());
					map.put("PartnerKey", request.getParameter("PartnerKey").trim());
					map.put("SecretKey", request.getParameter("SecretKey").trim());
				}
				// CC视频
				if (type.equals(WebSiteProfileType.cc.toString())) {
					map.put("ccappID", request.getParameter("ccappID").trim());
					map.put("ccappKEY", request.getParameter("ccappKEY").trim());
				}
				// 网站开关配置
				if (type.equals(WebSiteProfileType.WebSwitch.toString())) {
					map.put("limitLogin", request.getParameter("limitLogin").trim());
					map.put("imPath", request.getParameter("imPath").trim());
				}
				// 购买服务开关
				if (type.equals(WebSiteProfileType.serviceSwitch.toString())) {
					map.put("verifyWeiXin", request.getParameter("verifyWeiXin"));
					map.put("verifySINA", request.getParameter("verifySINA"));
					map.put("verifyQQ", request.getParameter("verifyQQ"));
					map.put("coupon", request.getParameter("coupon"));
					map.put("alipay", request.getParameter("alipay"));
					map.put("yibaopay", request.getParameter("yibaopay"));
					map.put("weixinpay", request.getParameter("weixinpay"));
					map.put("cardServer", request.getParameter("cardServer"));
					map.put("live", request.getParameter("live"));
					map.put("exam", request.getParameter("exam"));
					map.put("indexLive", request.getParameter("indexLive"));
					map.put("PackageSwitch", request.getParameter("PackageSwitch"));
					map.put("thirdLoginBinding", request.getParameter("thirdLoginBinding"));
					map.put("invite", request.getParameter("invite"));
					map.put("account", request.getParameter("account"));
					map.put("member", request.getParameter("member"));
					map.put("recharge", request.getParameter("recharge"));

				}
				// 邮件配置
				if (type.equals(WebSiteProfileType.emailConfigure.toString())) {
					map.put("SMTP", request.getParameter("SMTP").trim());
					map.put("username", request.getParameter("username").trim());
					map.put("password", request.getParameter("password").trim());
				}
				// 邮件配置
				if (type.equals(WebSiteProfileType.sms.toString())) {
					map.put("smstype", request.getParameter("smstype").trim());
					map.put("strAppkey", request.getParameter("strAppkey").trim());
					map.put("sdkappid", request.getParameter("sdkappid").trim());
				}
				// 类型为genseeLive
				if (type.equals(WebSiteProfileType.genseeLive.toString())) {
					map.put("siteDomain", request.getParameter("siteDomain").trim());
					map.put("loginName", request.getParameter("loginName").trim());
					map.put("password", request.getParameter("password").trim());
				}

				// 消息发送限制
				if (type.equals(WebSiteProfileType.message.toString())) {
					//订单消息开关
					Map<String, Object> config=new HashMap<>();
					//学员注册消息开关
					config=new HashMap<>();
					String registerType=StringUtils.arrayToString(request.getParameterValues("register.sendType"),",");
					String registerModel=request.getParameter("register.modelContent");
					config.put("sendType",registerType);
					config.put("modelContent",registerModel);
					String registerSign=request.getParameter("register.sign");
					String registerTemplate=request.getParameter("register.template");
					config.put("sign",registerSign);
					config.put("template",registerTemplate);

					map.put("register", config);

					//第三方第一次登陆发送信息
					config=new HashMap<>();
					String outsideRegisterType=StringUtils.arrayToString(request.getParameterValues("outsideRegister.sendType"),",");
					String outsideRegisterModel=request.getParameter("outsideRegister.modelContent");
					String outsideRegisterSign=request.getParameter("outsideRegister.sign");
					String outsideRegisterTemplate=request.getParameter("outsideRegister.template");

					config.put("sign",outsideRegisterSign);
					config.put("template",outsideRegisterTemplate);
					config.put("sendType",outsideRegisterType);
					config.put("modelContent",outsideRegisterModel);
					map.put("outsideRegister", config);

					//后台开通订单
					config=new HashMap<>();
					String auditingOrderType=StringUtils.arrayToString(request.getParameterValues("auditingOrder.sendType"),",");
					String auditingOrderModel=request.getParameter("auditingOrder.modelContent");
					config.put("sendType",auditingOrderType);
					String auditingOrderSign=request.getParameter("auditingOrder.sign");
					String auditingOrderTemplate=request.getParameter("auditingOrder.template");
					config.put("sign",auditingOrderSign);
					config.put("template",auditingOrderTemplate);
					config.put("modelContent",auditingOrderModel);
					map.put("auditingOrder", config);

					//后台取消订单
					config=new HashMap<>();
					String cancleOrderType=StringUtils.arrayToString(request.getParameterValues("cancleOrder.sendType"),",");
					String cancleOrderModel=request.getParameter("cancleOrder.modelContent");
					config.put("sendType",cancleOrderType);
					String cancleOrderSign=request.getParameter("cancleOrder.sign");
					String cancleOrderTemplate=request.getParameter("cancleOrder.template");
					config.put("sign",cancleOrderSign);
					config.put("template",cancleOrderTemplate);
					config.put("modelContent",cancleOrderModel);
					map.put("cancleOrder", config);

					//后台恢复订单
					config=new HashMap<>();
					String recoveryOrderType=StringUtils.arrayToString(request.getParameterValues("recoveryOrder.sendType"),",");
					String recoveryOrderModel=request.getParameter("recoveryOrder.modelContent");
					config.put("sendType",recoveryOrderType);
					String recoveryOrderSign=request.getParameter("recoveryOrder.sign");
					String recoveryOrderTemplate=request.getParameter("recoveryOrder.template");
					config.put("sign",recoveryOrderSign);
					config.put("template",recoveryOrderTemplate);
					config.put("modelContent",recoveryOrderModel);
					map.put("recoveryOrder", config);

					//后台订单退款
					config=new HashMap<>();
					String refundOrderType=StringUtils.arrayToString(request.getParameterValues("refundOrder.sendType"),",");
					String refundOrderModel=request.getParameter("refundOrder.modelContent");
					config.put("sendType",refundOrderType);
					String refundOrderSign=request.getParameter("refundOrder.sign");
					String refundOrderTemplate=request.getParameter("refundOrder.template");
					config.put("sign",refundOrderSign);
					config.put("template",refundOrderTemplate);
					config.put("modelContent",refundOrderModel);
					map.put("refundOrder", config);

					//后台订单详情延期
					config=new HashMap<>();
					String delayOrderType=StringUtils.arrayToString(request.getParameterValues("delayOrder.sendType"),",");
					String delayOrderModel=request.getParameter("delayOrder.modelContent");
					config.put("sendType",delayOrderType);
					String delayOrderSign=request.getParameter("delayOrder.sign");
					String delayOrderTemplate=request.getParameter("delayOrder.template");
					config.put("sign",delayOrderSign);
					config.put("template",delayOrderTemplate);
					config.put("modelContent",delayOrderModel);
					map.put("delayOrder", config);

					//后台订单详情课程关闭
					config=new HashMap<>();
					String closeCourseOrderType=StringUtils.arrayToString(request.getParameterValues("closeCourseOrder.sendType"),",");
					String closeCourseOrderModel=request.getParameter("closeCourseOrder.modelContent");
					config.put("sendType",closeCourseOrderType);
					String closeCourseOrderSign=request.getParameter("closeCourseOrder.sign");
					String closeCourseOrderTemplate=request.getParameter("closeCourseOrder.template");
					config.put("sign",closeCourseOrderSign);
					config.put("template",closeCourseOrderTemplate);
					config.put("modelContent",closeCourseOrderModel);
					map.put("closeCourseOrder", config);

					//后台赠送优惠券
					config=new HashMap<>();
					String giveCouponCodeType=StringUtils.arrayToString(request.getParameterValues("giveCouponCode.sendType"),",");
					String giveCouponCodeModel=request.getParameter("giveCouponCode.modelContent");
					config.put("sendType",giveCouponCodeType);
					String giveCouponCodeSign=request.getParameter("giveCouponCode.sign");
					String giveCouponCodeTemplate=request.getParameter("giveCouponCode.template");
					config.put("sign",giveCouponCodeSign);
					config.put("template",giveCouponCodeTemplate);
					config.put("modelContent",giveCouponCodeModel);
					map.put("giveCouponCode", config);

					//后台问答采纳最佳答案
					config=new HashMap<>();
					String adminQuestionAcceptType=StringUtils.arrayToString(request.getParameterValues("adminQuestionAccept.sendType"),",");
					String adminQuestionAcceptModel=request.getParameter("adminQuestionAccept.modelContent");
					config.put("sendType",adminQuestionAcceptType);
					String adminQuestionAcceptSign=request.getParameter("adminQuestionAccept.sign");
					String adminQuestionAcceptTemplate=request.getParameter("adminQuestionAccept.template");
					config.put("sign",adminQuestionAcceptSign);
					config.put("template",adminQuestionAcceptTemplate);
					config.put("modelContent",adminQuestionAcceptModel);
					map.put("adminQuestionAccept", config);

					//前台问答 采纳最佳答案
					config=new HashMap<>();
					String frontQuestionAcceptType=StringUtils.arrayToString(request.getParameterValues("frontQuestionAccept.sendType"),",");
					String frontQuestionAcceptModel=request.getParameter("frontQuestionAccept.modelContent");
					config.put("sendType",frontQuestionAcceptType);
					String frontQuestionAcceptSign=request.getParameter("frontQuestionAccept.sign");
					String frontQuestionAcceptTemplate=request.getParameter("frontQuestionAccept.template");
					config.put("sign",frontQuestionAcceptSign);
					config.put("template",frontQuestionAcceptTemplate);
					config.put("modelContent",frontQuestionAcceptModel);
					map.put("frontQuestionAccept", config);

					//订单支付成功
					config=new HashMap<>();
					String paySuccessType=StringUtils.arrayToString(request.getParameterValues("paySuccess.sendType"),",");
					String paySuccessModel=request.getParameter("paySuccess.modelContent");
					config.put("sendType",paySuccessType);
					String paySuccessSign=request.getParameter("paySuccess.sign");
					String paySuccessTemplate=request.getParameter("paySuccess.template");
					config.put("sign",paySuccessSign);
					config.put("template",paySuccessTemplate);
					config.put("modelContent",paySuccessModel);
					map.put("paySuccess", config);

					//订单支付成功
					config=new HashMap<>();
					String giveCourseType=StringUtils.arrayToString(request.getParameterValues("giveCourse.sendType"),",");
					String giveCourseModel=request.getParameter("giveCourse.modelContent");
					config.put("sendType",giveCourseType);
					String giveCourseSign=request.getParameter("giveCourse.sign");
					String giveCourseTemplate=request.getParameter("giveCourse.template");
					config.put("sign",giveCourseSign);
					config.put("template",giveCourseTemplate);
					config.put("modelContent",giveCourseModel);
					map.put("giveCourse", config);
					//注册信息模板
					config=new HashMap<>();
					String registerMsgType=StringUtils.arrayToString(request.getParameterValues("registerMsg.sendType"),",");
					String registerMsgModel=request.getParameter("registerMsg.modelContent");
					config.put("sendType",registerMsgType);
					String registerMsgSign=request.getParameter("registerMsg.sign");
					String registerMsgTemplate=request.getParameter("registerMsg.template");
					config.put("sign",registerMsgSign);
					config.put("template",registerMsgTemplate);
					config.put("modelContent",registerMsgModel);
					map.put("registerMsg", config);
					//注册信息模板
					config=new HashMap<>();
					String drawMoneyMsgType=StringUtils.arrayToString(request.getParameterValues("drawMoney.sendType"),",");
					String drawMoneyModel=request.getParameter("drawMoney.modelContent");
					config.put("sendType",drawMoneyMsgType);
					String drawMoneySign=request.getParameter("drawMoney.sign");
					String drawMoneyTemplate=request.getParameter("drawMoney.template");
					config.put("sign",drawMoneySign);
					config.put("template",drawMoneyTemplate);
					config.put("modelContent",drawMoneyModel);
					map.put("drawMoney", config);
					//密码找回信息模板
					config=new HashMap<>();
					String recoverySmsType=StringUtils.arrayToString(request.getParameterValues("recoverySms.sendType"),",");
					String recoverySmsModel=request.getParameter("recoverySms.modelContent");
					config.put("sendType",recoverySmsType);
					String recoverySmsSign=request.getParameter("recoverySms.sign");
					String recoverySmsTemplate=request.getParameter("recoverySms.template");
					config.put("sign",recoverySmsSign);
					config.put("template",recoverySmsTemplate);
					config.put("modelContent",recoverySmsModel);
					map.put("recoverySms", config);
                    //课程过期模板
                    config=new HashMap<>();
                    String timeOverMsgType=StringUtils.arrayToString(request.getParameterValues("timeOverMsg.sendType"),",");
                    String timeOverMsgModel=request.getParameter("timeOverMsg.modelContent");
                    config.put("sendType",timeOverMsgType);
                    String timeOverMsgSign=request.getParameter("timeOverMsg.sign");
                    String timeOverMsgTemplate=request.getParameter("timeOverMsg.template");
                    config.put("sign",timeOverMsgSign);
                    config.put("template",timeOverMsgTemplate);
                    config.put("modelContent",timeOverMsgModel);
                    map.put("timeOverMsg", config);
					//优惠券过期模板
					config=new HashMap<>();
					String timeOverCouponCodeMsgType=StringUtils.arrayToString(request.getParameterValues("timeOverCouponCodeMsg.sendType"),",");
					String timeOverCouponCodeMsgModel=request.getParameter("timeOverCouponCodeMsg.modelContent");
					config.put("sendType",timeOverCouponCodeMsgType);
					String timeOverCouponCodeMsgSign=request.getParameter("timeOverCouponCodeMsg.sign");
					String timeOverCouponCodeMsgTemplate=request.getParameter("timeOverCouponCodeMsg.template");
					config.put("sign",timeOverCouponCodeMsgSign);
					config.put("template",timeOverCouponCodeMsgTemplate);
					config.put("modelContent",timeOverCouponCodeMsgModel);
					map.put("timeOverCouponCodeMsg", config);
				}

				// 类型为invite
				if (type.equals(WebSiteProfileType.invite.toString())) {
					map.put("cashback", request.getParameter("cashback").trim());
					map.put("percent", request.getParameter("percent").trim());
					map.put("withdrawCash", request.getParameter("withdrawCash").trim());
				}
				// 将map转化json串
				JSONObject jsonObject = FastJsonUtil.obj2JsonObject(map);
				if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
					WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
					websiteProfile.setType(type);
					websiteProfile.setDesciption(jsonObject.toString());
					websiteProfileService.updateWebsiteProfile(websiteProfile);
				}
				/*当type为cc或直播时将其改为因酷云视频配置 加参数flag直接跳转到修改页面（现3种视频相关配置管理都在因酷云视频配置页面）*/
				/*if ("inxeduVideo".equals(type)||"cc".equals(type)||"genseeLive".equals(type)||"inxeduCloud".equals(type)){
				    type = "inxeduVideo?flag=flag";
                }
                *//*短信配置 邮件配置 消息管理 的type都为sms */
                if ("sms".equals(type)||"emailConfigure".equals(type)||"message".equals(type)){
                    type = "sms?flag=flag";
                }
                /*如果是修改消息后直接跳转修改消息页面 不到列表页面*/
                if ("message".equals(type)){
					type="sms?flag=flag";
				}
			}
		} catch (Exception e) {
			logger.error("AdminWebsiteProfileController.updateWebSiteByType", e);
		}
		return "redirect:/admin/websiteProfile/find/" + type;
	}

	/**
	 * 上传ico文件
	 */
	@RequestMapping("/websiteProfile/uploadIco")
	public String uploadIcoFile(HttpServletRequest request, @RequestParam("icoFile") MultipartFile icoFile) {
		try {
			if (!icoFile.isEmpty()) {
				// 获得项目的真实路径
				String path = request.getSession().getServletContext().getRealPath("/");
				File file = new File(path + "/" + ICONAME);
				if (!file.exists()) {
					file.mkdirs();
				}
				try {
					icoFile.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.error("uploadIcoFile", e);
		}
		return "redirect:/admin/websiteProfile/find/ico";
	}

	/**
	 * 查询在线咨询
	 */
	@RequestMapping("/websiteProfile/online")
	public String getWebsiteOnline(HttpServletRequest request, Model model) {
		try {
			// 查询在线咨询详情
			Map<String, Object> map = websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.online.toString());
			model.addAttribute("websiteonlinemap", map);
		} catch (Exception e) {
			logger.error("getWebsiteOnline", e);
			return setExceptionRequest(request, e);
		}
		return getWebSiteOnline;
	}

	/**
	 * 更新WebsiteOnline
	 */
	@RequestMapping("/websiteProfile/online/update")
	public String updateWebsiteOnline(HttpServletRequest request) {
		try {
			JsonParser jsonParser = new JsonParser();
			Map<String, String> map = new HashMap<String, String>();
			map.put("onlineUrl", request.getParameter("onlineUrl"));// 链接
			map.put("onlineImageUrl", request.getParameter("onlineImageUrl"));// 图片链接
			map.put("onlineKeyWord", request.getParameter("onlineKeyWord"));// 开关
			// 将map转化json串
			JSONObject jsonObject = FastJsonUtil.obj2JsonObject(map);
			if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
				WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
				websiteProfile.setType(WebSiteProfileType.online.toString());
				websiteProfile.setDesciption(jsonObject.toString());
				websiteProfileService.updateWebsiteProfile(websiteProfile);
			}
		} catch (Exception e) {
			logger.error("updateWebsiteOnline", e);
		}
		return "redirect:/admin/websiteProfile/online";
	}
	/**
	 * ajax 查询网站配置 根据Type
	 */
	@RequestMapping("/ajax/getWebsiteProfile/{type}")
	@ResponseBody
	public Object getWebSiteListAjax(HttpServletRequest request, Model model, @PathVariable("type") String type) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			Map<String, Object> map = (Map<String, Object>)websiteProfileService.getWebsiteProfileByType(type).get(type);
			json = this.setJson(true, "", map);
		} catch (Exception e) {
			logger.error("getWebSiteList", e);
			json = this.setJson(false, "", "");
		}
		return json;
	}

	/**
	 * ajax 更新管理根据类型
	 */
	@RequestMapping("/ajax/updateWebsiteProfile/{type}")
	@ResponseBody
	public Object updWebSiteByTypeAjax(HttpServletRequest request, Model model, @PathVariable("type") String type) {
		Map<String,Object> json = new HashMap<String,Object>();
		try {
			if (ObjectUtils.isNotNull(type) && StringUtils.isNotEmpty(type)) {
				JsonParser jsonParser = new JsonParser();
				Map<String, String> map = new HashMap<String, String>();
				if (type.equals(WebSiteProfileType.web.toString())) {
					map.put("email", request.getParameter("email"));// 公司邮箱
					map.put("phone", request.getParameter("phone"));// 24小时电话
					map.put("workTime", request.getParameter("workTime"));// 工作时间
					map.put("copyright", request.getParameter("copyright"));// 备案
					map.put("author", request.getParameter("author"));// 作者
					map.put("keywords", request.getParameter("keywords"));// 关键词
					map.put("description", request.getParameter("description"));// 描述
					map.put("title", request.getParameter("title"));//
					map.put("company", request.getParameter("company"));// 网校名称
				}
				// 类型为alipay
				if (type.equals(WebSiteProfileType.alipay.toString())) {
					map.put("alipaypartnerID", request.getParameter("alipaypartnerID").trim());// 合作者身份ID
					map.put("alipaykey", request.getParameter("alipaykey").trim());// 交易安全校验码
					map.put("sellerEmail", request.getParameter("sellerEmail").trim());// 商家邮箱
					map.put("status", request.getParameter("status"));// 支付宝选择（企业和个人）
				}

				// 类型为logo
				if (type.equals(WebSiteProfileType.logo.toString())) {// 类型为logo
					map.put("url", request.getParameter("url"));// clientID
				}
				// 统计代码
				if (type.equals(WebSiteProfileType.censusCode.toString())) {
					map.put("censusCodeString", request.getParameter("censusCodeString"));// thirdloginstatus第三方登录是否开启
				}
				//注册管理
				if (type.equals(WebSiteProfileType.registerController.toString())){
					map.put("emailRegister", request.getParameter("emailRegister"));
					map.put("phoneRegister", request.getParameter("phoneRegister"));
					map.put("emailProving", request.getParameter("emailProving"));
					map.put("phoneProving", request.getParameter("phoneProving"));
					map.put("ipLimit",request.getParameter("ipLimit"));
					map.put("emailLimit",request.getParameter("emailLimit"));
					map.put("mobileLimit",request.getParameter("mobileLimit"));
					map.put("emailRecovery",request.getParameter("emailRecovery"));
					map.put("mobileRecovery",request.getParameter("mobileRecovery"));
				}
				// CC视频
				if (type.equals(WebSiteProfileType.cc.toString())) {
					map.put("ccappID", request.getParameter("ccappID").trim());
					map.put("ccappKEY", request.getParameter("ccappKEY").trim());
				}
				// 购买服务开关
				if (type.equals(WebSiteProfileType.serviceSwitch.toString())) {
					map.put("verifyWeiXin", request.getParameter("verifyWeiXin"));
					map.put("verifySINA", request.getParameter("verifySINA"));
					map.put("verifyQQ", request.getParameter("verifyQQ"));
					map.put("coupon", request.getParameter("coupon"));
					map.put("alipay", request.getParameter("alipay"));
					map.put("yibaopay", request.getParameter("yibaopay"));
					map.put("weixinpay", request.getParameter("weixinpay"));
					map.put("thirdLoginBinding", request.getParameter("thirdLoginBinding"));
				}
				// qq第三方登录配置
				if (type.equals("qqLogin")) {
					map.put("app_ID", request.getParameter("app_ID").trim());
					map.put("app_KEY", request.getParameter("app_KEY").trim());
					//map.put("redirect_URI", request.getParameter("redirect_URI").trim());
					map.put("scope", request.getParameter("scope").trim());
					map.put("authorizeURL", request.getParameter("authorizeURL").trim());
					map.put("accessTokenURL", request.getParameter("accessTokenURL").trim());
					map.put("getUserInfoURL", request.getParameter("getUserInfoURL").trim());
					map.put("getOpenIDURL", request.getParameter("getOpenIDURL").trim());
				}
				// 新浪微博第三方登录配置
				if (type.equals("sinaLogin")) {
					map.put("client_ID", request.getParameter("client_ID").trim());
					map.put("client_SERCRET", request.getParameter("client_SERCRET").trim());
					//map.put("redirect_URI", request.getParameter("redirect_URI").trim());
					map.put("baseURL", request.getParameter("baseURL").trim());
					map.put("accessTokenURL", request.getParameter("accessTokenURL").trim());
					map.put("authorizeURL", request.getParameter("authorizeURL").trim());
					map.put("rmURL", request.getParameter("rmURL").trim());
				}
				// 微信第三方登录配置
				if (type.equals("weixinLogin")) {
					map.put("appid", request.getParameter("appid").trim());
					//map.put("redirect_uri", request.getParameter("redirect_uri").trim());
					map.put("response_type", request.getParameter("response_type").trim());
					map.put("scope", request.getParameter("scope").trim());
					map.put("secret", request.getParameter("secret").trim());
					map.put("href", request.getParameter("href").trim());
					map.put("forceWeChatLogin",request.getParameter("forceWeChatLogin").trim());
					map.put("weixinLogin_appId",request.getParameter("weixinLogin_appId").trim());
					map.put("weixinLogin_wxAppSecret", request.getParameter("weixinLogin_wxAppSecret").trim());
				}
				// 易宝 yee 配置
				if (type.equals("yee")) {
					map.put("p1_MerId", request.getParameter("p1_MerId").trim());
					map.put("keyValue", request.getParameter("keyValue").trim());
				}
				// 微信支付配置
				if (type.equals("weixin")) {
					map.put("wxMchId", request.getParameter("wxMchId").trim());
					map.put("wxAppID", request.getParameter("wxAppID").trim());
					map.put("wxToken", request.getParameter("wxToken").trim());
					map.put("mobilePayKey", request.getParameter("mobilePayKey").trim());
					map.put("wxPayKey", request.getParameter("wxPayKey").trim());
					map.put("encodingAESKey", request.getParameter("encodingAESKey").trim());
					map.put("wxAppSecret", request.getParameter("wxAppSecret").trim());
					map.put("mobileAppId", request.getParameter("mobileAppId").trim());
					map.put("mobileMchId", request.getParameter("mobileMchId").trim());
				}
				// 类型为分销
				if (type.equals(WebSiteProfileType.invite.toString())) {
					map.put("cashback", request.getParameter("cashback"));
					map.put("coursePercent", request.getParameter("coursePercent"));
					map.put("packagePercent", request.getParameter("packagePercent"));
					map.put("livePercent", request.getParameter("livePercent"));
					map.put("memberPercent", request.getParameter("memberPercent"));
					map.put("withdrawCash", request.getParameter("withdrawCash"));
				}
				// 提现开关
				if (type.equals(WebSiteProfileType.withdrawals.toString())) {
					map.put("withdrawalsSwitch", request.getParameter("withdrawalsSwitch"));
				}
				// 将map转化json串
				JSONObject jsonObject = FastJsonUtil.obj2JsonObject(map);
				if (ObjectUtils.isNotNull(jsonObject) && StringUtils.isNotEmpty(jsonObject.toString())) {// 如果不为空进行更新
					WebsiteProfile websiteProfile = new WebsiteProfile();// 创建websiteProfile
					websiteProfile.setType(type);
					websiteProfile.setDesciption(jsonObject.toString());
					websiteProfileService.updateWebsiteProfile(websiteProfile);
				}
			}
			json = this.setJson(true, "", "");
		} catch (Exception e) {
			logger.error("AdminWebsiteProfileController.updateWebSiteByType", e);
			json = this.setJson(false, "", "");
		}
		return json;
	}
	

}
