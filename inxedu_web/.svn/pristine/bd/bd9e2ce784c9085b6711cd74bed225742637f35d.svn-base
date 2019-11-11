package com.inxedu.os.app.controller.websiteProfile;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.QCloud.QCloudSMSSignUtil;
import com.inxedu.os.common.util.QCloud.QCloudSMSTemplateUtil;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.entity.qcloudsmstemplate.QcloudSmsTemplate;
import com.inxedu.os.edu.service.qcloudsmstemplate.QcloudSmsTemplateService;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/webapp")
public class AppWebsiteProfile extends BaseController{
	private static Logger logger=Logger.getLogger(AppWebsiteProfile.class);

	@Autowired
	private WebsiteProfileService websiteProfileService;
	@Autowired
	private QcloudSmsTemplateService qcloudSmsTemplateService;
	/**
	 * 网站配置
	 */
	@RequestMapping("/websiteProfile")
	@ResponseBody
	public Map<String,Object> websiteProfile(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			Map<String,Object> result = new HashedMap();
			String type = request.getParameter("type");
			String returnUrl = "";
			Map<String,Object> websitemap=websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.sms.toString());
			Map<String,Object> smsMap=(Map<String,Object>)websitemap.get("sms");
			QCloudSMSSignUtil qCloudSMSSignUtil = new QCloudSMSSignUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());
			QCloudSMSTemplateUtil qCloudSMSTemplateUtil = new QCloudSMSTemplateUtil(smsMap.get("sdkappid").toString(),smsMap.get("strAppkey").toString());

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
				if (StringUtils.isNotEmpty(smsMap.get("sdkappid").toString())&&StringUtils.isNotEmpty(smsMap.get("strAppkey").toString())) {
 				/*查询审查状态*/
					qcloudSmsTemplateService.checkStatus(qcloudSmsTemplates);
				}
				for (QcloudSmsTemplate qcloudSmsTemplate1 :qcloudSmsTemplates){
					String text = qcloudSmsTemplate1.getText();

					qcloudSmsTemplate1.setText(text);
				}
				result.put("qcloudSmsTemplates", qcloudSmsTemplates);
				result.put("webSiteMap", map);
				result.put("type", type);
				json = this.setJson(true, "",result);

		}catch (Exception e) {
			json=this.setJson(false, "异常", null);
			logger.error("websiteProfile()--error",e);
		}
		return json;
	}

}
