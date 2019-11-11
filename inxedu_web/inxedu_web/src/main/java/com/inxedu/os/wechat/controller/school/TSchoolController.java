package com.inxedu.os.wechat.controller.school;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.UUIDUtil;
import com.inxedu.os.wechat.entity.school.TSchool;
import com.inxedu.os.wechat.service.school.TSchoolService;

@Controller
@RequestMapping("/wechat")
public class TSchoolController extends BaseController{
	private static Logger logger=Logger.getLogger(TSchoolController.class);
	
	@Autowired
	private TSchoolService schoolService;
	
	/**
	 * 添加学校
	 * 
	 */
	@RequestMapping("/school/add")
	@ResponseBody
	public Map<String, Object> schoolAdd(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			String name=request.getParameter("name");
			String pNo=request.getParameter("pNo");   //省
			String cNo=request.getParameter("cNo");	  //市
			String dNo=request.getParameter("dNo");	  //区
			String id = UUIDUtil.getUUID();
			
			Map<String,Object> mapParam = new HashMap<String,Object>();
			mapParam.put("pNo", pNo);
			mapParam.put("cNo", cNo);
			mapParam.put("dNo", dNo);
			mapParam.put("name", name);
			TSchool school = schoolService.getSchoolByMap(mapParam);
			
			if(school == null){
				TSchool schoolE = new TSchool();
				schoolE.setId(id);
				schoolE.setName(name);
				schoolE.setPNo(pNo);
				schoolE.setCNo(cNo);
				schoolE.setDNo(dNo);
				// 创建学校
				schoolService.addSchool(schoolE);
				
				json.put("state", 1);   //创建状态   1 成功   0 失败
				json.put("msg", "创建成功！");
				json.put("schoolId", id);
			}else{
				json.put("state", 0);   //创建状态   1 成功   0 失败
				json.put("msg", "该学校已经被创建！");
			}
			
		}catch(Exception e){
			json.put("state", 0);   //创建状态   1 成功   0 失败
			json.put("msg", "创建失败！");
			logger.error("addSchool()--error",e);
		}
		return json;
	}

}
