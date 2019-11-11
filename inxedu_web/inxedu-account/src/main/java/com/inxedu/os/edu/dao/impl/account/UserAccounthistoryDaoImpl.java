package com.inxedu.os.edu.dao.impl.account;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.account.UserAccounthistoryDao;
import com.inxedu.os.edu.entity.account.QueryUserAccounthistory;
import com.inxedu.os.edu.entity.account.UserAccounthistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * UserAccounthistory
 * User: qinggang.liu voo@163.com
 * Date: 2014-05-27
 */
 @Repository("userAccounthistoryDao")
public class UserAccounthistoryDaoImpl extends GenericDaoImpl implements UserAccounthistoryDao {

    public Long addUserAccounthistory(UserAccounthistory userAccounthistory) {
        return this.insert("UserAccounthistoryMapper.createUserAccounthistory",userAccounthistory);
    }

    public UserAccounthistory getUserAccounthistoryById(Long id) {
        return this.selectOne("UserAccounthistoryMapper.getUserAccounthistoryById",id);
    }

    public List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory) {
        return this.selectList("UserAccounthistoryMapper.getUserAccounthistoryList",userAccounthistory);
    }
  
    public List<UserAccounthistory> getUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
    	return this.queryForListPage("UserAccounthistoryMapper.getUserAccounthistoryListByCondition", queryUserAccounthistory, page);
    }

	public UserAccounthistory getUserAccounthistoryByOutTtradeNo( String outTradeNo) {
		
		return this.selectOne("UserAccounthistoryMapper.getUserAccounthistoryByOutTtradeNo",outTradeNo);
	}
	public List<UserAccounthistory> getWebUserAccounthistoryListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
		
		return this.queryForListPage("UserAccounthistoryMapper.getWebUserAccounthistoryListByCondition", queryUserAccounthistory, page);
	}

    @Override
    public void updateUserAccountHistroy(UserAccounthistory userAccounthistory) {
        this.update("UserAccounthistoryMapper.updateUserAccounthistory",userAccounthistory);
    }
}
