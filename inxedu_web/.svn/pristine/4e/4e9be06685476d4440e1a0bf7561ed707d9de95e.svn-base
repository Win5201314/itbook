package com.inxedu.os.edu.controller.mobile;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.edu.controller.user.AdminUserController;
import com.inxedu.os.edu.service.email.UserEmailMsgService;
import com.inxedu.os.edu.service.mobile.UserMobileMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author www.inxedu.com
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminQcloudSMSController extends BaseController{
	
	private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	@InitBinder("userMobileMsg")
	public void initBinderMobile(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userMobileMsg.");
	}
	/**
	 * 添加腾讯云短信
	 */
	@RequestMapping("/qcloudsms/add_sign")
	public String querySendMobileMsgList( HttpServletRequest request) {
		try {
		} catch (Exception e) {
			logger.error("querySendMobileMsgList", e);
		}
		return getViewPath("/admin/qcloudSMS/to_add_sign.jsp");
	}

	
}
