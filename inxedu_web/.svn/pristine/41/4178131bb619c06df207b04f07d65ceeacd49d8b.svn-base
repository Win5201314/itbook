package com.inxedu.os.edu.controller.system;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.system.SysLog;
import com.inxedu.os.edu.service.system.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminSysLogController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(AdminSysLogController.class);

	@Autowired
	private SysLogService sysLogService;

	@InitBinder({"sysLog"})
	public void initBinderSysLog(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("sysLog.");
	}
	
	/**
	 * 操作日志列表
	 */
	@RequestMapping("/syslog/page")
	public String syslogPage(HttpServletRequest request, @ModelAttribute("sysLog") SysLog sysLog, @ModelAttribute("page") PageEntity page){
		try{
			List<SysLog> sysLogList = sysLogService.querySysLogPage(sysLog, page);
			request.setAttribute("sysLog", sysLog);
			request.setAttribute("page", page);
			request.setAttribute("sysLogList", sysLogList);
		}catch (Exception e) {
			logger.error("syslogPage()--error",e);
			return this.setExceptionRequest(request, e);
		}
		return getViewPath("/admin/syslog/sys-log-list");
	}
	/**
	 * 操作日志列表
	 */
	@RequestMapping("/syslog/query")
	public String querySyslog(HttpServletRequest request, @RequestParam("id") int id){
		try{
			SysLog sysLog = new SysLog();
			sysLog.setId(id);
			sysLog = sysLogService.querySysLogById(sysLog);
			request.setAttribute("sysLog", sysLog);
		}catch (Exception e) {
			logger.error("querySyslog()--error",e);
			return this.setExceptionRequest(request, e);
		}
		return getViewPath("/admin/syslog/sys_log_info");
	}



}
