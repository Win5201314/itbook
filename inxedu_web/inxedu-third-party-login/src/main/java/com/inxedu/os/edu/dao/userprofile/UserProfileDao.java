package com.inxedu.os.edu.dao.userprofile;


import com.inxedu.os.edu.entity.userprofile.UserProfile;

import java.util.List;

/**
 * UserProfile管理接口
 * @author www.inxedu.com
 */
public interface UserProfileDao {

	/**
	 * 添加UserProfile
	 * 
	 * @param userProfile
	 *            要添加的UserProfile
	 * @return id
	 */
	public Long addUserProfile(UserProfile userProfile);

	/**
	 * 根据id删除一个UserProfile
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteUserProfileById(int id);

	/**
	 * 修改UserProfile
	 * 
	 * @param userProfile
	 *            要修改的UserProfile
	 */
	public void updateUserProfile(UserProfile userProfile);

	/**
	 * 根据id获取单个UserProfile对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return UserProfile
	 */
	public List<UserProfile> getUserProfileByUserId(int userid);

	/**
	 * 根据条件获取UserProfile列表
	 * 
	 * @param userProfile
	 *            查询条件
	 * @return List<UserProfile>
	 */
	public List<UserProfile> getUserProfileList(UserProfile userProfile);

	/**
	 * 根据id获取第三方信息
	 * @param id
	 * @return
     */
	public UserProfile queryUserProfileById(Long id);

	/**
	 * 查询该账号是否绑定过相同类型的第三方
	 * @param userProfile
	 * @return
     */
	public int queryUserProfileConflict(UserProfile userProfile);
}