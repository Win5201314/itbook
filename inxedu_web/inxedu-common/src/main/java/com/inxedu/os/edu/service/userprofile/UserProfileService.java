package com.inxedu.os.edu.service.userprofile;


import com.inxedu.os.edu.constants.enums.ProfileType;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.entity.userprofile.UserProfile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * UserProfile管理接口
 * @author www.inxedu.com
 */
public interface UserProfileService {

	/**
	 * 添加UserProfile
	 * 
	 * @param userProfile
	 *            要添加的UserProfile
	 * @return id
	 */
	Long addUserProfile(UserProfile userProfile);

	/**
	 * 根据id删除一个UserProfile
	 * 
	 * @param id
	 *            要删除的id
	 */
	void deleteUserProfileById(int id);

	/**
	 * 修改UserProfile
	 * 
	 * @param userProfile
	 *            要修改的UserProfile
	 */
	void updateUserProfile(UserProfile userProfile);

	/**
	 * 根据id获取单个UserProfile对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return List<UserProfile>
	 */
	List<UserProfile> getUserProfileByUserId(int userId);

	/**
	 * 根据条件获取UserProfile列表
	 * 
	 * @param userProfile
	 *            查询条件
	 * @return List<UserProfile>
	 */
	List<UserProfile> getUserProfileList(UserProfile userProfile);

	/**
	 * 第三方注册
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	User addOpenAppRegister(HttpServletRequest request, HttpServletResponse response,UserProfile userProfile) throws Exception;
	/**
	 * 根据id获取第三方信息
	 * @param id
	 * @return
	 */
	UserProfile queryUserProfileById(Long id);
	/**
	 * 查询该账号是否绑定过相同类型的第三方
	 * @param userProfile
	 * @return
	 */
	int queryUserProfileConflict(UserProfile userProfile);
	/**
	 * 如果没登陆过则执行绑定操作  如果登陆过执行登陆操作
	 */
	String queryDoUserThreeLogin(UserProfile userProfile, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)throws Exception;

	/**
	 * 下载图片
	 */
	String download(String urlString) throws Exception ;

}