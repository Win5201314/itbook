package com.inxedu.os.edu.service.impl.account;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.dao.account.UserAccounthistoryDao;
import com.inxedu.os.edu.entity.account.QueryUserAccounthistory;
import com.inxedu.os.edu.entity.account.UserAccounthistory;
import com.inxedu.os.edu.service.account.UserAccounthistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * UserAccounthistory管理接口
 * User: qinggang.liu
 * Date: 2014-05-27
 */
@Service("userAccounthistoryService")
public class UserAccounthistoryServiceImpl implements UserAccounthistoryService {

 	@Autowired
    private UserAccounthistoryDao userAccounthistoryDao;
    
    /**
     * 添加UserAccounthistory
     * @param userAccounthistory 要添加的UserAccounthistory
     * @return id
     */
    public Long addUserAccounthistory(UserAccounthistory userAccounthistory){
    	DecimalFormat format = new DecimalFormat("0.00");
    	BigDecimal amount = new BigDecimal(0);
    	BigDecimal cashAmount = userAccounthistory.getCashAmount();
    	if(cashAmount!=null){
    		amount = new BigDecimal(format.format(cashAmount.floatValue()));
    		userAccounthistory.setCashAmount(amount);
    	}
    	BigDecimal balance = userAccounthistory.getBalance();
    	if(balance!=null){
    		amount = new BigDecimal(format.format(balance.floatValue()));
    		userAccounthistory.setBalance(amount);
    	}
    	BigDecimal trxAmount = userAccounthistory.getTrxAmount();
    	if(trxAmount!=null){
    		amount = new BigDecimal(format.format(trxAmount.floatValue()));
    		userAccounthistory.setTrxAmount(amount);
    	}
    	BigDecimal VmAmount = userAccounthistory.getVmAmount();
    	if(VmAmount!=null){
    		amount = new BigDecimal(format.format(VmAmount.floatValue()));
    		userAccounthistory.setVmAmount(amount);
    	}
    	BigDecimal backAmount = userAccounthistory.getBackAmount();
    	if(backAmount!=null){
    		amount = new BigDecimal(format.format(backAmount.floatValue()));
    		userAccounthistory.setBackAmount(amount);
    	}
    	return userAccounthistoryDao.addUserAccounthistory(userAccounthistory);
    }

    /**
     * 根据id获取单个UserAccounthistory对象
     * @param id 要查询的id
     * @return UserAccounthistory
     */
    public UserAccounthistory getUserAccounthistoryById(Long id){
    	return userAccounthistoryDao.getUserAccounthistoryById( id);
    }

    /**
     * 根据条件获取UserAccounthistory列表
     * @param userAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    public List<UserAccounthistory> getUserAccounthistoryList(UserAccounthistory userAccounthistory){
    	return userAccounthistoryDao.getUserAccounthistoryList(userAccounthistory);
    }
    /**
     * 账户历史 不隐藏后台操作 后台查看
     * 根据条件获取UserAccounthistory分页列表
     * @param queryUserAccounthistory, 查询条件
     * @return List<UserAccounthistory>
     */
    @Override
    public List<UserAccounthistory> getUserAccountHistroyListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
    	return userAccounthistoryDao.getUserAccounthistoryListByCondition(queryUserAccounthistory, page);
    }
    /**
     * 账户历史 隐藏后台操作 前台个人中心查看
     * 根据条件获取UserAccounthistory分页列表
     * @param queryUserAccounthistory 查询条件
     * @return List<UserAccounthistory>
     */
    @Override
    public List<UserAccounthistory> getWebUserAccountHistroyListByCondition(QueryUserAccounthistory queryUserAccounthistory, PageEntity page) {
    	return userAccounthistoryDao.getWebUserAccounthistoryListByCondition(queryUserAccounthistory, page);
    }

	@Override
	public void updateUserAccountHistroy(UserAccounthistory userAccounthistory) {
		userAccounthistoryDao.updateUserAccountHistroy(userAccounthistory);
	}
}
