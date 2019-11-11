package com.inxedu.os.edu.controller.video;

import com.inxedu.os.common.constants.CommonConstants;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.edu.constants.enums.WebSiteProfileType;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 视频后台
 * @author www.inxedu.com
 */
@Controller
public class AdminVideoController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdminVideoController.class);

	@Autowired
	private WebsiteProfileService websiteProfileService;

	/**
	 * 跳转因酷云
	 */
	@RequestMapping("/admin/yun/inxedu")
	public String videoList(HttpServletRequest request) {
		String inxeduUserId="";//用户id
		String inxeduSecretKey="";//key
		String inxeduAccessKey="";//key
		String url = "";
		try {
			url= request.getParameter("url");
			Map<String,Object> map=(Map<String,Object>)websiteProfileService.getWebsiteProfileByType(WebSiteProfileType.inxeduCloud.toString()).get(WebSiteProfileType.inxeduCloud.toString());
			inxeduUserId = map.get("UserId")+"";
			inxeduSecretKey = map.get("SecretKey")+"";
			inxeduAccessKey = map.get("AccessKey")+"";
		} catch (Exception e) {
			logger.error("AdminVideoController.videoList", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:http://yun.inxedu.com/web/login/redirect?inxeduUserId="+inxeduUserId+"&inxeduSecretKey="+inxeduSecretKey+"&inxeduAccessKey="+inxeduAccessKey+"&url="+url+"&mydomain="+CommonConstants.MYDOMAIN;
	}

}
