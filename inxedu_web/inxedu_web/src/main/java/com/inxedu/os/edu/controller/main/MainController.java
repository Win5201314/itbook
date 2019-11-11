package com.inxedu.os.edu.controller.main;

import com.inxedu.os.common.cache.CacheUtil;
import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.system.SysFunction;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.service.article.ArticleService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.statistics.StatisticsDayService;
import com.inxedu.os.edu.service.system.SysFunctionService;
import com.inxedu.os.edu.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author www.inxedu.com
 *
 */
@Controller
@RequestMapping("/admin/main")
public class MainController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	//private static String mainPage = getViewPath("/admin/main/main");
	private static String mainPage1 = getViewPath("/admin/main/main1");
	private static String mainIndexPage = getViewPath("/admin/main/index");
	@Autowired
	private SysFunctionService sysFunctionService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private StatisticsDayService statisticsDayService;
	@Autowired
	private OrderService orderService;
	/**
	 * 进入操作中心
	 * @param request
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping()
	public ModelAndView mainPage(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		try{
			SysUser sysuser = SingletonLoginUtils.getLoginSysUser(request);
			//得到缓存用户权限
			List<SysFunction> userFunctionList =(List<SysFunction>) CacheUtil.get(CacheConstans.USER_FUNCTION_PREFIX+sysuser.getUserId());
			//如果缓存中不存在则查询数据库中的用户权限
			if(ObjectUtils.isNull(userFunctionList)){
				userFunctionList = sysFunctionService.querySysUserFunction(sysuser.getUserId());
				CacheUtil.set(CacheConstans.USER_FUNCTION_PREFIX+sysuser.getUserId(), userFunctionList);
			}
			//处理权限
			List<SysFunction> functionList = handleUserFunction(userFunctionList);
			model.addObject("userFunctionList", functionList);
			model.setViewName(mainPage1);
			model.addObject("sysuser", sysuser);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("mainPage()--error",e);
		}
		return model;
	}

	/**
	 * 后台头部页面
	 */
	@RequestMapping("/header")
	public String mainHeader(HttpServletRequest request){
		try{
			SysUser sysuser = SingletonLoginUtils.getLoginSysUser(request);
			//得到缓存用户权限
			List<SysFunction> userFunctionList =(List<SysFunction>) CacheUtil.get(CacheConstans.USER_FUNCTION_PREFIX+sysuser.getUserId());
			//如果缓存中不存在则查询数据库中的用户权限
			if(ObjectUtils.isNull(userFunctionList)){
				userFunctionList = sysFunctionService.querySysUserFunction(sysuser.getUserId());
				CacheUtil.set(CacheConstans.USER_FUNCTION_PREFIX+sysuser.getUserId(), userFunctionList);
			}
			//处理权限
			List<SysFunction> functionList = handleUserFunction(userFunctionList);
			request.setAttribute("userFunctionList", functionList);

		}catch (Exception e) {
			logger.error("mainHeader()---error",e);
			return this.setExceptionRequest(request, e);
		}
		return getViewPath("/admin/main/header");
	}

	/**
	 * 后台头部页面
	 */
	@RequestMapping("/left")
	public String mainLeft(HttpServletRequest request,@RequestParam int parentId){
		try{
			SysUser sysuser = SingletonLoginUtils.getLoginSysUser(request);
			//得到缓存用户权限
			List<SysFunction> userFunctionList =(List<SysFunction>) CacheUtil.get(CacheConstans.USER_FUNCTION_PREFIX+sysuser.getUserId());
			//如果缓存中不存在则查询数据库中的用户权限
			if(ObjectUtils.isNull(userFunctionList)){
				userFunctionList = sysFunctionService.querySysUserFunction(sysuser.getUserId());
				CacheUtil.set(CacheConstans.USER_FUNCTION_PREFIX+sysuser.getUserId(), userFunctionList);
			}

			List<SysFunction> functionList = recursionFunction(userFunctionList,parentId);
			//处理权限
			//List<SysFunction> functionList = handleUserFunction(userFunctionList);
			request.setAttribute("userFunctionList", functionList);

		}catch (Exception e) {
			logger.error("mainLeft()---error",e);
			return this.setExceptionRequest(request, e);
		}
		return getViewPath("/admin/main/left");
	}
	//传入父id返回所有子类权限
	public List<SysFunction> initSysFunction(int parentId,List<SysFunction> userFunctionList){
		List<SysFunction> functionList = new ArrayList<>();
		if(ObjectUtils.isNull(userFunctionList)){
			return functionList;
		}

		for(SysFunction fun:userFunctionList){
			if(fun.getParentId()==parentId){
				fun.setChildList(initSysFunction(fun.getFunctionId(),userFunctionList));
				functionList.add(fun);
			}
		}
		return functionList;
	}

	/**
	 * 处理权限
	 * @param userFunctionList 用户权限列表
	 */
	private List<SysFunction> handleUserFunction(List<SysFunction> userFunctionList){
		List<SysFunction> functionList = new ArrayList<SysFunction>();
		//获取根级权限
		List<SysFunction> parentList = new ArrayList<SysFunction>();
		if(ObjectUtils.isNotNull(userFunctionList)){

			for(SysFunction sf : userFunctionList){
				if(sf.getParentId()==0){
					parentList.add(sf);
				}
			}
			//按sort排序
			Collections.sort(parentList, new Comparator<SysFunction>() {
				public int compare(SysFunction arg0, SysFunction arg1) {
					if(arg0.getSort()>arg1.getSort()){
						return -1;
					}
					if(arg0.getSort()<arg1.getSort()){
						return 1;
					}
					if(arg0.getSort()==arg1.getSort()){
						if(arg0.getFunctionId()>arg1.getFunctionId()){
							return 1;
						}
						if(arg0.getFunctionId()<arg1.getFunctionId()){
							return -1;
						}
					}
					return 0;
				}
			});
			//获取根级权限的子级权限
			for(SysFunction psf : parentList){
				psf.setChildList(recursionFunction(userFunctionList,psf.getFunctionId()));
			}
		}
		return parentList;
	}
	
	/**
	 * 递归得到每个权限的子级权限
	 * @param userFunctionList 用户权限列表
	 */
	private List<SysFunction> recursionFunction(List<SysFunction> userFunctionList,int parentId){
		List<SysFunction> childFunction = new ArrayList<SysFunction>();
		for(SysFunction usf : userFunctionList){
			if(parentId==usf.getParentId() && usf.getFunctionType()==1){
				usf.setChildList(recursionFunction(userFunctionList,usf.getFunctionId()));
				childFunction.add(usf);
			}
		}

		//按sort排序
		Collections.sort(childFunction, new Comparator<SysFunction>() {
			public int compare(SysFunction arg0, SysFunction arg1) {
				if(arg0.getSort()>arg1.getSort()){
					return -1;
				}
				if(arg0.getSort()<arg1.getSort()){
					return 1;
				}
				if(arg0.getSort()==arg1.getSort()){
					if(arg0.getFunctionId()>arg1.getFunctionId()){
						return 1;
					}
					if(arg0.getFunctionId()<arg1.getFunctionId()){
						return -1;
					}
				}
				return 0;
			}
		});
		return childFunction;
	}

	/**
	 * 后台操作中心初始化首页
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView mainIndex(HttpServletRequest request){
		ModelAndView model = new ModelAndView();
		try{
			// 今天登录人数数据获得
			int todayloginnum = statisticsDayService.getTodayLoginNum(new Date());
			model.addObject("todayloginnum", todayloginnum);
			model.setViewName(mainIndexPage);
			
			Map<String, Object> webCountMap=(Map<String, Object>) CacheUtil.get(CacheConstans.WEB_COUNT);
			
			if(webCountMap==null){
				webCountMap=new HashMap<String, Object>();
				//所有文章数量
				int articleCount = articleService.queryAllArticleCount();
				webCountMap.put("articleCount", articleCount);//所有文章数量
				//所有用户数量
				int userCount = userService.queryAllUserCount();
				webCountMap.put("userCount", userCount);//所有用户数量
				//所有课程数量
				int courseCount = courseService.queryAllCourseCount();
				webCountMap.put("courseCount", courseCount);//所有课程数量
				//所有订单数
				int orderCount=orderService.queryAllOrderCount();
				webCountMap.put("orderCount", orderCount);//所有订单数
				//已支付订单数
				int orderSuccessCount=orderService.queryOrderSuccessCount("SUCCESS");
				webCountMap.put("orderSuccessCount", orderSuccessCount);//所有已支付订单数
				//未支付订单数
				int orderInitCount=orderService.queryOrderSuccessCount("INIT");
				webCountMap.put("orderInitCount", orderInitCount);//所有未支付订单数
				CacheUtil.set(CacheConstans.WEB_COUNT, webCountMap,CacheConstans.WEB_COUNT_TIME);
				model.addObject("webCountMap",webCountMap);
			}else{
				model.addObject("webCountMap",webCountMap);
			}
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("mainIndex()---error",e);
		}
		return model;
	}
	
	/**
	 * 访问权限受限制跳转
	 * @return ModelAndView
	 */
	@RequestMapping("/notfunction")
	public ModelAndView notFunctionMsg(){
		ModelAndView model = new ModelAndView();
		model.addObject("message", "对不起，您没有操作权限！");
		model.setViewName("/common/notFunctonMsg");
		return model;
	}
	
}
