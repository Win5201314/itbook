package com.inxedu.os.edu.controller.dataclean;


import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.edu.entity.dataclean.DataClean;
import com.inxedu.os.edu.service.dataclean.DataCleanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 数据清理
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminDataCleanController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminDataCleanController.class);

	@Autowired
	private DataCleanService dataCleanService;
	/**
	 * 到 添加数据清理 页面
	 */
	@RequestMapping("/dataclean/toadd")
	public String toAddDataClean(HttpServletRequest request) {
		return getViewPath("/admin/dataclean/add_data_clean");
	}
	/**
	 * 添加数据清理
	 */
	@SystemLog(type="add",operation="添加数据清理")
	@RequestMapping("/dataclean/add")
	public String addDataClean(HttpServletRequest request,DataClean dataClean) {
		try {
			dataCleanService.addDataClean(dataClean);
		} catch (Exception e) {
			logger.error("toAddDataClean", e);
			return this.setExceptionRequest(request, e);
		}
		return "redirect:/admin/dataclean/list";
	}
	/**
	 * 数据清理 list
	 */
	@RequestMapping("/dataclean/list")
	public String queryDataClean(HttpServletRequest request,DataClean dataClean, @ModelAttribute("page") PageEntity page) {
		try {
			List<DataClean> dataCleanList = dataCleanService.getDataCleanByPage(dataClean,page);
			request.setAttribute("dataClean",dataClean);
			request.setAttribute("dataCleanList",dataCleanList);
			request.setAttribute("page",page);
		} catch (Exception e) {
			logger.error("toAddDataClean", e);
			return this.setExceptionRequest(request, e);
		}
		return getViewPath("/admin/dataclean/data_clean_list");
	}

	/**
	 * 到修改 数据清理 页面
	 */
	@RequestMapping("/dataclean/toupdate")
	public String toupdate(HttpServletRequest request, @RequestParam int id) {
		try {
			DataClean dataClean = dataCleanService.queryDataCleanById(id);
			request.setAttribute("dataClean",dataClean);
		} catch (Exception e) {
			logger.error("toupdate", e);
			return this.setExceptionRequest(request, e);
		}
		return getViewPath("/admin/dataclean/update_data_clean");
	}
	/**
	 * 修改数据清理
	 */
	@RequestMapping("/dataclean/update")
	@SystemLog(type="update",operation="修改数据清理")
	public String update(HttpServletRequest request, DataClean dataClean) {
		try {
			dataCleanService.updateDataClean(dataClean);
		} catch (Exception e) {
			logger.error("toupdate", e);
			return this.setExceptionRequest(request, e);
		}
		return "redirect:/admin/dataclean/list";
	}

	/**
	 * 删除数据清理
	 */
	@RequestMapping("/dataclean/del")
	@ResponseBody
	@SystemLog(type="del",operation="批量通过id批量清理数据")
	public Object del(HttpServletRequest request, @RequestParam int id) {
		Map<String, Object> json = null;
		try {
			dataCleanService.delDataClean(id);
			json=this.setJson(true, "", "");
		} catch (Exception e) {
			logger.error("toAddDataClean", e);
			json=this.setJson(false, "", "");
		}
		return json;
	}
	/**
	 * 执行sql
	 */
	@RequestMapping("/dataclean/dosql")
	@ResponseBody
	@SystemLog(type="del",operation="通过sql批量清理数据")
	public Object dosql(HttpServletRequest request, @RequestParam int id) {
		Map<String, Object> json = null;
		try {
			dataCleanService.queryDoSql(id);
			json=this.setJson(true, "", "");
		} catch (Exception e) {
			logger.error("dosql", e);
			json=this.setJson(false, "", "");
		}
		return json;
	}

	/**
	 * 批量执行 sql
	 */
	@RequestMapping("/dataclean/multiDosql")
	@ResponseBody
	@SystemLog(type="del",operation="批量通过sql批量清理数据")
	public Object multiDosql(HttpServletRequest request, @RequestParam String ids) {
		Map<String, Object> json = null;
		try {
			String idArr[] = ids.split(",");
			for(String idStr:idArr){
				dataCleanService.queryDoSql(Integer.parseInt(idStr));
			}
			json=this.setJson(true, "", "");
		} catch (Exception e) {
			logger.error("dosql", e);
			json=this.setJson(false, "", "");
		}
		return json;
	}
}