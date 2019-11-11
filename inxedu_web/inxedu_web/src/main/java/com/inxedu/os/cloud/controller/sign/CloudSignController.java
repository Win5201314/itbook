package com.inxedu.os.cloud.controller.sign;

import com.inxedu.os.cloud.entity.sign.Sign;
import com.inxedu.os.cloud.service.sign.SignService;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.edu.service.website.WebsiteProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 云交互后台
 * @author ZJC
 * @Create Date 2018-12-25
 */
@Controller
public class CloudSignController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CloudSignController.class);

	@Autowired
	private WebsiteProfileService websiteProfileService;

	@Autowired
	private SignService signService;

	/**
	 * 根据直播id签到
	 * @param courseId 直播(课程)id
	 * @return
	 */
	@RequestMapping("/cloud/sign/signLive")
	@ResponseBody
	public Object signLive(HttpServletRequest request, HttpServletResponse response, @RequestParam("courseId")String courseId, @RequestParam("type")int type){
		Map<String, Object> json = null;
        try{
//			int userId = getLoginUserId(request);
//			String userName = getLoginUser(request).getUserName();
			Sign sign = new Sign();
			sign.setId(UUID.randomUUID().toString().replace("-",""));
			sign.setLiveId(courseId);
//			sign.setUserId(userId+"");
//			sign.setUserName(userName);
			sign.setCreateTime(new Date());
			sign.setType(type);
			sign.setSignName("签到");
			signService.addSign(sign);
			json=this.setJson(true, "签到成功！", "");
		}catch(Exception e){
			logger.error("signLive()----error", e);
			json=this.setJson(false, "系统繁忙,请稍后再试！", "");
		}
		return json;
	}

	/**
	 * 根据直播id显示签到详情
	 * @param courseId 直播(课程)id
	 * @return
	 */
	@RequestMapping("/cloud/sign/showSignLive")
	@ResponseBody
	public Object showSignLive(HttpServletRequest request, HttpServletResponse response, @RequestParam("courseId")int courseId){
		return null;
	}

	/**
	 * 根据直播id显示单个用户签到详情
	 * @param courseId 直播(课程)id
	 * @return
	 */
	@RequestMapping("/cloud/sign/showSignLiveUser")
	@ResponseBody
	public Object showSignLiveUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("courseId")int courseId){
		return null;
	}

	/**
	 * 个人签到详情
	 * @param courseId 直播(课程)id
	 * @return
	 */
	@RequestMapping("/cloud/sign/showSignUser")
	@ResponseBody
	public Object showSignUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("courseId")int courseId){
		return null;
	}
}
