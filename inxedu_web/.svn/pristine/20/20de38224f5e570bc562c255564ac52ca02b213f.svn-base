package com.inxedu.os.edu.controller.website;


import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.common.util.WebUtils;
import com.inxedu.os.edu.entity.website.WebsiteNavigate;
import com.inxedu.os.edu.service.website.WebsiteNavigateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 导航管理
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminWebsiteNavigateController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminWebsiteNavigateController.class);
 	@Autowired
    private WebsiteNavigateService websiteNavigateService;
	private static final String updateWebsiteNavigate = getViewPath("/admin/website/navigate/websiteNavigate_update");//修改 导航配置
	private static final String addWebsiteNavigate = getViewPath("/admin/website/navigate/websiteNavigate_add");//跳转添加导航
	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("websiteNavigate")
	public void initBinderWebsiteNavigate(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("websiteNavigate.");
	}
 	/**
	 * 查询导航配置
	 */
	@RequestMapping("/website/navigates")
	public ModelAndView showWebsiteNavigates(HttpServletRequest request,WebsiteNavigate websiteNavigate,String type){
		ModelAndView modelAndView = new ModelAndView(getViewPath("/admin/website/navigate/websiteNavigate_list"));
		//友情链接
		if("FRIENDLINK".equals(type)){
			modelAndView.setViewName(getViewPath("/admin/website/navigate/websiteNavigate_list_friendlink"));
		}
		/*手机脚步链接*/
		if("FOOTER".equals(type)){
			modelAndView.setViewName(getViewPath("/admin/website/navigate/websiteNavigate_list_footer"));
		}
		try{
			websiteNavigate.setType(type);
			List<WebsiteNavigate> websiteNavigates=websiteNavigateService.getWebsiteNavigate(websiteNavigate);
			modelAndView.addObject("websiteNavigates",websiteNavigates);
			request.getSession().setAttribute("websiteListUri", WebUtils.getServletRequestUriParms(request));
			modelAndView.addObject("type",type);
		}catch(Exception e){
			logger.error("AdminWebsiteNavigateController.showWebsiteNavigates--导航列表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 跳转添加导航
	 */
	@RequestMapping("/website/doAddNavigates")
	public ModelAndView doAddWebsiteNavigate(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(addWebsiteNavigate);
		return modelAndView;
	}
	/**
	 * 添加导航配置
	 */
	@RequestMapping("/website/addNavigate")
	@SystemLog(type="add",operation="添加导航配置")
	@ResponseBody
	public Map<String,Object>  addWebsiteNavigate(WebsiteNavigate websiteNavigate,HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>(4);
		try{
			if(websiteNavigate!=null && websiteNavigate.getName()!=null && websiteNavigate.getName().trim().length()>0){
				/*给新建赋排序值 最大排序值+1*/
				List<WebsiteNavigate> websiteNavigateList = websiteNavigateService.getWebsiteNavigate(new WebsiteNavigate());
				int newOrderNum = websiteNavigateList.size()+1;
				websiteNavigate.setOrderNum(newOrderNum);
				/*如果新建是友情链接则在新页面打开*/
				if ("FRIENDLINK".equals(websiteNavigate.getType())){
					websiteNavigate.setNewPage(0);
				}
				websiteNavigateService.addWebsiteNavigate(websiteNavigate);
			}
			json = this.setJson(true,"添加成功!",websiteNavigate);
		}catch(Exception e){
			logger.error("AdminWebsiteNavigateController.addWebsiteNavigate--添加导航出错", e);
			json = setJson(false, "更新导航出错", null);
		}
		return json;
	}
	
	/**
	 * 跳转更新导航配置
	 */
	@RequestMapping("/website/doUpdateNavigate/{id}")
	public ModelAndView doUpdateWebsiteNavigate(@PathVariable Integer id,HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(updateWebsiteNavigate);
		try{
			if(id!=null){
				WebsiteNavigate websiteNavigate=websiteNavigateService.getWebsiteNavigateById(id);
				modelAndView.addObject("websiteNavigate",websiteNavigate);
			}
		}catch(Exception e){
			logger.error("AdminWebsiteNavigateController.doUpdateWebsiteNavigates--跳转更新导航出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 更新导航配置
	 */
	@RequestMapping("/website/updateNavigate")
	@SystemLog(type="update",operation="更新导航配置")
    @ResponseBody

    public Map<String,Object>  updatewebsiteNavigate(WebsiteNavigate websiteNavigate,HttpServletRequest request){
        Map<String, Object> json = new HashMap<String, Object>(4);

        try{
			if(websiteNavigate!=null && websiteNavigate.getName()!=null && websiteNavigate.getName().trim().length()>0){
				websiteNavigateService.updateWebsiteNavigate(websiteNavigate);
			}
            json = this.setJson(true,"更新成功！",websiteNavigate);
        }catch(Exception e){
            logger.error("AdminWebsiteNavigateController.updatewebsiteNavigate--更新导航出错", e);
            json = setJson(false, "更新导航出错", null);
		}
		return json;
	}
	/**
	 * 更新或新建导航
	 */
	@RequestMapping("/website/updateNav")
	public ModelAndView updateNav(HttpServletRequest request,@ModelAttribute("websiteNavigate") WebsiteNavigate websiteNavigate){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin/website/navigates?type="+websiteNavigate.getType());

		try{
			if (websiteNavigate.getId()!=0){
				websiteNavigateService.updateWebsiteNavigate(websiteNavigate);
			}else {
				/*给新建赋排序值 最大排序值+1*/
				List<WebsiteNavigate> websiteNavigateList = websiteNavigateService.getWebsiteNavigate(new WebsiteNavigate());
				int newOrderNum = websiteNavigateService.getMaxOrdernumNav().getOrderNum()+1;
				websiteNavigate.setOrderNum(newOrderNum);
				websiteNavigateService.addWebsiteNavigate(websiteNavigate);
			}
		}catch(Exception e){
			logger.error("AdminWebsiteNavigateController.doUpdateWebsiteNavigates--跳转更新导航出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 冻结或解冻导航
	 */
	@RequestMapping("/website/freezeNavigate")
	@ResponseBody
	@SystemLog(type="update",operation="冻结或解冻导航")
	public Map<String,Object> freezeWebsiteNavigate(WebsiteNavigate websiteNavigate,HttpServletRequest request){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			websiteNavigateService.freezeWebsiteNavigate(websiteNavigate);
			json = this.setJson(true, "true", null);
		}catch(Exception e){
			logger.error("AdminWebsiteNavigateController.freezeWebsiteNavigate--更新导航出错", e);
			json = this.setJson(false, "false", null);
		}
		return json;
	}
	/**
	 * 删除导航
	 */
	@RequestMapping("/website/delNavigate/{id}")
	@ResponseBody
	@SystemLog(type="del",operation="删除导航")
	public Map<String,Object> delWebsiteNavigate(HttpServletRequest request,@PathVariable Integer id){
		Map<String,Object> json = new HashMap<String,Object>();
		try{
			websiteNavigateService.delWebsiteNavigate(id);

			json = this.setJson(true, "true", null);
		}catch(Exception e){
			logger.error("AdminWebsiteNavigateController.delWebsiteNavigate--删除导航出错", e);
			json = this.setJson(false, "false", null);
		}
		return json;
	}
	/**
	 * 上移导航
	 */
	@RequestMapping("/website/moveNavUp")
	@ResponseBody
	@SystemLog(type="moveNavUp",operation="上移导航")
	public Map<String,Object> moveNavUp(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>(4);
		try{
			int navId = Integer.parseInt(request.getParameter("navId"));
			int orderNum = Integer.parseInt(request.getParameter("orderNum"));
			List<WebsiteNavigate> websiteNavigateList = websiteNavigateService.getWebsiteNavigate(new WebsiteNavigate());
			for (int i=0;i<websiteNavigateList.size();i++){
				if (websiteNavigateList.get(i).getId()==navId){
					/*调换两个导航的排序值*/
					websiteNavigateList.get(i-1).setOrderNum(orderNum);
					websiteNavigateList.get(i).setOrderNum(orderNum-1);
					websiteNavigateService.updateWebsiteNavigate(websiteNavigateList.get(i));
					websiteNavigateService.updateWebsiteNavigate(websiteNavigateList.get(i-1));
					orderNum = websiteNavigateList.get(i).getOrderNum();
				}
			}
			json = setJson(true, orderNum+"", null);
		}catch(Exception e){
			logger.error("AdminWebsiteNavigateController.moveNavUp--上移出错", e);
			json = setJson(false, "false", null);
		}
		return json;
	}
	/**
	 * 下移导航
	 */
	@RequestMapping("/website/moveNavDown")
	@ResponseBody
	@SystemLog(type="moveNavDown",operation="下移导航")
	public Map<String,Object> moveNavDown(HttpServletRequest request){
		Map<String, Object> json = new HashMap<String, Object>(4);
		try{
			int navId = Integer.parseInt(request.getParameter("navId"));
			int orderNum = Integer.parseInt(request.getParameter("orderNum"));
			List<WebsiteNavigate> websiteNavigateList = websiteNavigateService.getWebsiteNavigate(new WebsiteNavigate());
			for (int i=0;i<websiteNavigateList.size();i++){
				if (websiteNavigateList.get(i).getId()==navId){
					/*调换两个导航的排序值*/
					websiteNavigateList.get(i+1).setOrderNum(orderNum);
					websiteNavigateList.get(i).setOrderNum(orderNum+1);
					websiteNavigateService.updateWebsiteNavigate(websiteNavigateList.get(i));
					websiteNavigateService.updateWebsiteNavigate(websiteNavigateList.get(i+1));
					orderNum = websiteNavigateList.get(i).getOrderNum();
				}
			}
			json = setJson(true,orderNum+"", null);
		}catch(Exception e){
			logger.error("AdminWebsiteNavigateController.moveNavDown--下移出错", e);
			json = setJson(false, "false", null);
		}
		return json;
	}
}