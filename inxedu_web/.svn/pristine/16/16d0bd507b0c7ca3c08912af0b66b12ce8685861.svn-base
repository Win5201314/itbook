package com.inxedu.os.edu.dao.impl.account;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.account.UserAccountDao;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.account.UserAccountDTO;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.exception.StaleObjectStateException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * UserAccount
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("userAccountDao")
public class UserAccountDaoImpl extends GenericDaoImpl implements UserAccountDao {

    public Long addUserAccount(UserAccount userAccount) {
        return this.insert("UserAccountMapper.createUserAccount",userAccount);
    }

    public void updateUserAccount(UserAccount userAccount) throws StaleObjectStateException {
       Long c= this.update("UserAccountMapper.updateUserAccount",userAccount);
       if(c.longValue()==0){
           throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_LOCK_ERROR);
       }
    }

    public UserAccount getUserAccountByUserId(Long userId) {
        return this.selectOne("UserAccountMapper.getUserAccountByUserId",userId);
    }

    public List<UserAccount> getUserAccountList(UserAccount userAccount) {
        return this.selectList("UserAccountMapper.getUserAccountList",userAccount);
    }
    @Override
    public List<UserAccountDTO> getuserAccountListByCondition(User user, PageEntity pageEntity) {
    	// TODO Auto-generated method stub
    	return this.queryForListPage("UserAccountMapper.getuserAccountListByCondition",user,pageEntity);
    }
    /**
	 * 更新账户状态
	 * 
	 * @param userId
	 * @param status
	 */
    public void updateUserAccountStatus(Long userId, String status) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("userId", userId);
    	map.put("status", status);
    	this.update("UserAccountMapper.updateUserAccountStatus", map);
    }
    /**
	 * 根据用户id获得详情
	 * 
	 * @param userId
	 * @return
	 */
    public UserAccountDTO getuserAccountInfo(Long userId) {
    	return this.selectOne("UserAccountMapper.getuserAccountInfo", userId);
    }
}
