package com.inxedu.os.wechat.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.UUIDUtil;
import com.inxedu.os.edu.constants.enums.Constants;
import com.inxedu.os.edu.util.httputil.HttpRequest;
import com.inxedu.os.wechat.entity.school.TSchool;
import com.inxedu.os.wechat.entity.user.TUser;
import com.inxedu.os.wechat.service.school.TSchoolService;
import com.inxedu.os.wechat.service.user.TUserService;

import fr.opensagres.xdocreport.utils.StringUtils;

@Controller
@RequestMapping("/wechat")
public class TUserController extends BaseController{
	private static Logger logger=Logger.getLogger(TUserController.class);
	
	@Autowired
	private TUserService userService;
	@Autowired
	private TSchoolService schoolService;
	
	/**
	 * 添加用户
	 * 
	 */
	@RequestMapping("/user/add")
	@ResponseBody
	public Map<String, Object> userAdd(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			String name=request.getParameter("name");
			String sex=request.getParameter("sex");   //性别
			String phone=request.getParameter("phone");	  //电话
			String password=request.getParameter("password");	  //电话
			String qq=request.getParameter("qq");	  //qq
			String weixin=request.getParameter("weixin");	  //微信
			String email=request.getParameter("email");	  //邮箱
			String schoolId=request.getParameter("schoolId");	  //学校id
			String tagId=request.getParameter("tagId");	  //标签
			
			if(StringUtils.isEmpty(schoolId)){
				json.put("state", 0);   //创建状态   1 成功   0 失败
				json.put("msg", "学校参数不能为空！");
				return json;
			}
			
			TSchool school = schoolService.querySchoolById(schoolId);
			if(school == null){
				json.put("state", 0);   //创建状态   1 成功   0 失败
				json.put("msg", "学校不存在！");
				return json;
			}
			
			Map<String,Object> mapParam = new HashMap<String,Object>();
			mapParam.put("phone", phone);
			TUser user = userService.getUserByMap(mapParam);
			if(user == null){
				String id = UUIDUtil.getUUID();
				TUser userNew = new TUser();
				userNew.setId(id);
				userNew.setName(name);
				userNew.setSex(Integer.parseInt(sex));
				userNew.setPhone(phone);
				userNew.setPassword(MD5.getMD5(password));
				userNew.setQq(qq);
				userNew.setWeixin(weixin);
				userNew.setEmail(email);
				userNew.setSchoolId(schoolId);
				userNew.setTagId(tagId);
				// 创建用户
				userService.addUser(userNew);
				
				json.put("state", 1);   //创建状态   1 成功   0 失败
				json.put("msg", "创建成功！");
				json.put("userId", id);
			}else{
				json.put("state", 0);   //创建状态   1 成功   0 失败
				json.put("msg", "该用户已经注册过！");
			}
			
		}catch(Exception e){
			json.put("state", 0);   //创建状态   1 成功   0 失败
			json.put("msg", "创建失败！");
			logger.error("addUser()--error",e);
		}
		return json;
	}
	
	/**
	 * 向微信服务器 使用登录凭证 code 获取 session_key 和 openid
	 * 
	 */
	@RequestMapping("/appLogin")
	@ResponseBody
	public Map<String,Object> appLogin(HttpServletRequest request,@RequestParam String phone,@RequestParam String password,
										@RequestParam String code) {
		logger.info("微信小程序登陆开始。。。");
		
		Map<String,Object> rMap = new HashMap<String,Object>();
		try {
			if(!StringUtils.isNotEmpty(phone)){
				rMap.put("status", 0);   //失败
				rMap.put("msg", "请输入手机号");
				return rMap;
			}
			if(!StringUtils.isNotEmpty(password)){
				rMap.put("status", 0);   //失败
				rMap.put("msg", "请输入登录密码");
				return rMap;
			}
			if(!StringUtils.isNotEmpty(code)){
				rMap.put("status", 0);   //失败
				rMap.put("msg", "code 不能为空");
		        return rMap;
			}
			
			//判断系统是否有该用户(直接根据本系统判断)
			Map<String,Object> mapParam = new HashMap<String,Object>();
			mapParam.put("phone", phone);
			TUser user = userService.getUserByMap(mapParam);
			if(user != null){
				String params = "appid=" + Constants.APPID + "&secret=" + Constants.APP_SECRECT + "&js_code=" + code + "&grant_type="
					       + Constants.AUTHORIZATION_CODE;
					String resultStr = HttpRequest.sendGet(Constants.GET_SESSION_URL, params);
					
		            // 解析相应内容（转换成json对象）
		            JSONObject resultJson = new JSONObject(resultStr);
		            //成功
		            if(resultJson.has("session_key")){
		            	 // 获取会话密钥（session_key）
		                String session_key = resultJson.get("session_key").toString();
		                // 用户的唯一标识（openid）
		                String openid = (String) resultJson.get("openid").toString();
		                
		                rMap.put("status", 1);   //成功
		    			rMap.put("msg", "登陆成功！");
		                rMap.put( "session_key",session_key);
		                rMap.put( "openId",openid );
		                
		                //将openid更新入user表
		                Map<String,Object> updateMap = new HashMap<String,Object>();
		                updateMap.put("id", user.getId());
		                updateMap.put("openid", openid);
		                userService.updateUserByMap(updateMap);
		            	
		            }else{
		            	String errmsg = "";
		            	 // 返回码
		                String errcode = (String) resultJson.get("errcode").toString();
		                if("-1".equals(errcode)){
		                	errmsg = "系统繁忙,请稍后再试！";
		                }else if("40029".equals(errcode)){
		                	errmsg = "code 无效！";
		                }else if("45011".equals(errcode)){
		                	errmsg = "操作频率过高，请稍后再试！";
		                }else{
		                	errmsg = (String) resultJson.get("errmsg").toString();
		                }
		            	rMap.put("status", 0);   //失败
		    			rMap.put("msg", errmsg);
		            	
		            }
				
			}else{
				rMap.put("status", 0);   //失败
				rMap.put("msg", "系统没有该用户");
		        return rMap;
			}
			
			//添加登录日志  t_edu_login_log
			
           
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("异常",e);
            rMap.put("status", 0);   //失败
			rMap.put("msg", "登陆异常！");
        }
        return rMap;
    }

}
