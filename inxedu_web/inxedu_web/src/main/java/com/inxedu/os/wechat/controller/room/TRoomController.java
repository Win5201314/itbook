package com.inxedu.os.wechat.controller.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.UUIDUtil;
import com.inxedu.os.wechat.entity.school.TSchool;
import com.inxedu.os.wechat.entity.user.TUser;
import com.inxedu.os.wechat.service.school.TSchoolService;

import fr.opensagres.xdocreport.utils.StringUtils;

/**
 * app接口 Controller
 * @author lisheng
 */
@Controller
@RequestMapping("/wechat")
public class TRoomController extends BaseController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TRoomController.class);
	
	@Autowired
	private TSchoolService schoolService;
	
	/**
	 * 添加 空间
	 * 
	 */
	@RequestMapping("/room/add")
	@ResponseBody
	public Map<String, Object> roomAdd(HttpServletRequest request){
		Map<String, Object> json=new HashMap<String, Object>();
		try{
			String name=request.getParameter("name");
			String roomNo=request.getParameter("roomNo");
			String tagId=request.getParameter("tagId");	  //标签
			String schoolId=request.getParameter("schoolId");	  //学校
			
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
			mapParam.put("schoolId", schoolId);
			mapParam.put("name", name);
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
	 * 根据条件查询空间(教室)列表(直接从本系统数据库获取)
	 * 
	 */
	@RequestMapping("/getClassRoomList")
	@ResponseBody
	public List<Map<String,Object>> getClassRoomList(HttpServletRequest request,@RequestParam String buildId,@RequestParam String grade) {
		logger.info("进入设备控制页面......");
		List<Map<String,Object>> rListMap = new ArrayList<Map<String,Object>>();
		try {
			Map<String,Object> rMap0 = new HashMap<String,Object>();
			rMap0.put("id", "aaa");
			rMap0.put("roomName", "综合楼A101");
			rMap0.put("isUsed", 1);
			rMap0.put("usePerson", "张三");
			rListMap.add(rMap0);
			Map<String,Object> rMap1 = new HashMap<String,Object>();
			rMap1.put("id", "bbb");
			rMap1.put("roomName", "综合楼A102");
			rMap1.put("isUsed", 0);
			rMap1.put("usePerson", "");
			rListMap.add(rMap1);
			Map<String,Object> rMap2 = new HashMap<String,Object>();
			rMap2.put("id", "ccc");
			rMap2.put("roomName", "综合楼A103");
			rMap2.put("isUsed", 1);
			rMap1.put("usePerson", "lisheng");
			rListMap.add(rMap2);
           
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("异常",e);
        }
        return rListMap;
    }
	
	
	/**
	 * 获取当前教室 控制的这些设备的参数(直接从本系统数据库获取)
	 * 
	 */
	@RequestMapping("/getDeviceControlByCRoomId")
	@ResponseBody
	public List<Map<String,Object>> getDeviceControlByCRoomId(HttpServletRequest request,@RequestParam String roomId) {
		logger.info("进入设备控制页面......");
		List<Map<String,Object>> rListMap = new ArrayList<Map<String,Object>>();
		try {
			Map<String,Object> rMap0 = new HashMap<String,Object>();
			rMap0.put("id", "aaa");
			rMap0.put("ctrlName", "照明控制");
			rMap0.put("ctrlValue", 1);
			rListMap.add(rMap0);
			Map<String,Object> rMap1 = new HashMap<String,Object>();
			rMap1.put("id", "bbb");
			rMap1.put("ctrlName", "音量");
			rMap1.put("ctrlValue", 100);
			rListMap.add(rMap1);
			Map<String,Object> rMap2 = new HashMap<String,Object>();
			rMap2.put("id", "ccc");
			rMap2.put("ctrlName", "门禁开关");
			rMap2.put("ctrlValue", 0);
			rListMap.add(rMap2);
           
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("异常",e);
        }
        return rListMap;
    }
}