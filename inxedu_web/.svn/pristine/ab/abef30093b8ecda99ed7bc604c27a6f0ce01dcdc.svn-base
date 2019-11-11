package com.inxedu.os.edu.dao.user;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.user.QueryUser;
import com.inxedu.os.edu.entity.user.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author www.inxedu.com
 *
 */
public interface UserDao {
	/**
	 * 创建用户
	 * @param user
	 * @return 返回用户ID
	 */
	int createUser(User user);
	
	/**
	 * 通过用户ID查询用户
	 * @param userId 用户不D
	 * @return User
	 */
	User queryUserById(int userId);
	
	/**
	 * 检测手机是否存在 
	 * @param mobile 手机号
	 * @return 返回记录数
	 */
	int checkMobile(String mobile);
	
	/**
	 * 检测邮箱号是否存在 
	 * @param email 邮箱号
	 * @return 返回记录数
	 */
	int checkEmail(String email);

	/**
	 * 检测账户USER_NAME是否存在
	 * @param userName 账户号
	 * @return  返回记录数
	 */
	int checkUsername(String userName);
	
	/**
	 * 获取登录用户
	 * @param map 用户和密码 可以是邮箱号或手机号
	 * @return User
	 */
	User getLoginUser(Map<String, Object> map);
	
	/**
	 * 修改用户密码
	 * @param user
	 */
	void updateUserPwd(User user);
	
	/**
	 * 分页查询用户
	 * @param query 查询条件
	 * @param page 分页条件
	 * @return List<User>
	 */
	List<User> queryUserListPage(QueryUser query, PageEntity page);
	
	/**
	 * 冻结或解冻用户
	 * @param user
	 */
	void updateUserStates(User user);
	/**
	 * 修改用户头像
	 * @param user
	 */
	void updateImg(User user);
	/**
	 * 修改用户信息
	 * @param user
	 */
	void updateUser(User user);
	/**
	 * 修改用户全部信息
	 */
	void updateUserAll(User user);
	/**
	 * 修改个中心个性化图片URL
	 * @param user
	 */
	void updateUserBannerUrl(User user);
	
	/**
	 * 查询所有学员记录数
	 * @return 返回所有的记录数
	 */
	int queryAllUserCount();
	
	/**
	 * 通过手机号或邮箱号查询用户信息
	 * @param emailOrMobile 手机或邮箱号
	 * @return User
	 */
	User queryUserByEmailOrMobile(String emailOrMobile);
	
	/**
	 * 通过集合cusId 查询user 返回map
	 * @param cusIds
	 * @return
	 * @throws Exception
	 */
	List<User> queryUsersByIds(List<Long> cusIds) throws Exception;
	
	/**
	 * 通过标识更新未读数加一
	 */
	void updateUnReadMsgNumAddOne(String falg, Long cusId);

	/**
	 * 通过标识更新未读数清零
	 */
	void updateUnReadMsgNumReset(String falg, Long cusId);
	
	/**
	 * 更新用户的上传统计系统消息时间
	 */
	void updateCusForLST(Long cusId, Date date);
	
	/**
	 * 根据条件获取User列表  带课程名称
	 * @param user  用户
	 * @param page   分页参数
	 * @return
	 */
	List<User> getUserListPage(User user, PageEntity page);

	/**
	 * 根据条件获取User列表  带课程名称
	 * @param user  用户
	 * @param page   分页参数
	 * @return
	 */
	List<User> getUserListAndCourse(User user, PageEntity page);

	/**
	 * 验证登录账号前缀是否存在
	 * @param loginAccountPrefix 账号前缀
	 * @return
	 */
	int checkLoginAccount(String loginAccountPrefix);

	/**
	 * 查询用户
	 * @param query 查询条件
	 * @return List<User>
	 */
	List<User> queryUserList(QueryUser query);
}
