package com.inxedu.os.edu.service.impl.account;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.constants.enums.account.AccountBizType;
import com.inxedu.os.edu.constants.enums.account.AccountHistoryType;
import com.inxedu.os.edu.constants.enums.account.AccountType;
import com.inxedu.os.edu.constants.enums.account.UserOptType;
import com.inxedu.os.edu.dao.account.UserAccountDao;
import com.inxedu.os.edu.dao.account.UserAccountOptRecordDao;
import com.inxedu.os.edu.dao.account.UserAccounthistoryDao;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.account.UserAccountDTO;
import com.inxedu.os.edu.entity.account.UserAccountOptRecord;
import com.inxedu.os.edu.entity.account.UserAccounthistory;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.exception.AccountException;
import com.inxedu.os.edu.exception.StaleObjectStateException;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserAccount管理接口 User: qinggang.liu Date: 2014-05-27
 */
@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {

	private Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);

	@Autowired
	private UserAccountDao userAccountDao;
	@Autowired
	private UserAccounthistoryDao userAccounthistoryDao;
	/*@Autowired
	private GuidGeneratorService guidGeneratorService;*/
	@Autowired
	private UserAccountOptRecordDao userAccountOptRecordDao;
	@Autowired
	private UserService userService;

	/**
	 * 添加UserAccount
	 * 
	 * @param userAccount
	 *            要添加的UserAccount
	 * @return id
	 */
	public Long addUserAccount(UserAccount userAccount) {
		return userAccountDao.addUserAccount(userAccount);
	}

	/**
	 * 根据id获取单个UserAccount对象
	 * 
	 * @param userId
	 *            要查询的id
	 * @return UserAccount
	 */
	public UserAccount getUserAccountByUserId(Long userId) throws AccountException {
		UserAccount account = userAccountDao.getUserAccountByUserId(userId);
		if (ObjectUtils.isNull(account)) {
			throw new AccountException(AccountException.ACCOUNT_NOT_FOUND);// 账户不存在
		}
		return account;
	}

	@Override
	public List<UserAccountDTO> getUserAccountListByCondition(PageEntity page, User user) throws StaleObjectStateException {
		return userAccountDao.getuserAccountListByCondition(user, page);
	}

	/**
	 * 根据条件获取UserAccount列表
	 * 
	 * @param userAccount
	 *            查询条件
	 * @return List<UserAccount>
	 */
	public List<UserAccount> getUserAccountList(UserAccount userAccount) {
		return userAccountDao.getUserAccountList(userAccount);
	}

	// 扣款,消费操作
	public UserAccount debit(UserAccount account, BigDecimal trxAmount, AccountHistoryType accountHistoryType, Long userId, Long trxOrderId, String requestId, Date trxDate, String description,
							 boolean isDisplay, AccountBizType accountBizType) throws AccountException, StaleObjectStateException {

		
		BigDecimal curBalance = account.getBalance();
		System.out.println("---------------------curBalance:"+curBalance);
		System.out.println("---------------------trxAmount:"+trxAmount);
		DecimalFormat df = new DecimalFormat("0.00");
		if (Double.parseDouble(df.format(curBalance.doubleValue())) < Double.parseDouble(df.format(trxAmount.doubleValue())))
			throw new AccountException(AccountException.ACCOUNT_NOT_ENOUGH);
		try {
			logger.info("debit befor:======balance:" + curBalance + "=====trxAmount:" + trxAmount + "============");
			account.debit(trxAmount);// 扣除操作,cash ,vm具体在此方法中
		} catch (IllegalArgumentException e) {
			throw new AccountException(e.getMessage());
		}

		// 更新账户dao
		account.setLastUpdateTime(new Date());
		userAccountDao.updateUserAccount(account);
		// 添加账户历史操作记录
		UserAccounthistory userAccounthistory = new UserAccounthistory();
		userAccounthistory.setUserId(userId);
		userAccounthistory.setTrxorderId(trxOrderId);// 订单id
		userAccounthistory.setOtherId(account.getId());// 相关账户ID
		userAccounthistory.setRequestId(requestId.split("#")[0]);
		userAccounthistory.setCreateTime(new Date());
		if (isDisplay) {// 是否显示账户历史记录:0显示;1不显示
			userAccounthistory.setIsDisplay(0);
		} else {
			userAccounthistory.setIsDisplay(1);
		}
		userAccounthistory.setBalance(account.getBalance());// 当前余额
		userAccounthistory.setCashAmount(account.getCashAmount());// cash余额
		userAccounthistory.setVmAmount(account.getVmAmount());// vm余额
		userAccounthistory.setBackAmount(account.getBackAmount());// 提现余额
		userAccounthistory.setTrxAmount(trxAmount);// 交易金额
		userAccounthistory.setDescription("消费金额" + trxAmount);// 账户历史内容描述
		userAccounthistory.setActHistoryType(accountHistoryType.toString());// 帐务历史类型.充值。消费等
		userAccounthistory.setBizType(accountBizType.toString());// 业务类型(课程订单、会员订单、图书订单，音频课程)
		userAccounthistory.setVersion(account.getVersion());// 乐观锁版本号
		userAccounthistoryDao.addUserAccounthistory(userAccounthistory);

		logger.info("debit SUCCESS:======balance:" + account.getBalance() + "=====trxAmount:" + trxAmount + "============");
		return account;
	}

	/**
	 * 账户入账,充值 入款
	 * 
	 * @param account
	 * @param trxAmount
	 *            交易金额
	 * @param accountType
	 *            账户操作类型，cash or vm
	 * @param accountHistoryType
	 *            账户历史记录类型
	 * @param userId
	 *            用户id
	 * @param trxOrderId
	 *            订单号,订单表主键id
	 * @param requestId
	 *            订单号,对系统的的
	 * @param out_trade_no
	 *            提交到支付宝的,(充值卡是用卡号)
	 * @param trxDate
	 *            时间
	 * @param description
	 *            描述
	 * @param isDisplay
	 *            在用户我的账户历史中是否显示 0显示 1不显示
	 * @param accountBizType
	 *            交易类型，课程、会员、图书等
	 * @throws AccountException
	 * @throws StaleObjectStateException
	 */

	public UserAccount credit(UserAccount account, BigDecimal trxAmount, AccountType accountType, AccountHistoryType accountHistoryType, Long userId, Long trxOrderId, String requestId,
							  String out_trade_no, Date trxDate, String description, boolean isDisplay, AccountBizType accountBizType) throws AccountException, StaleObjectStateException {
		BigDecimal curBalance = account.getBalance();
		try {
			logger.info("credit befor:======balance:" + curBalance + "=====trxAmount:" + trxAmount + "============");
			account.credit(trxAmount, accountType);//入款 根据入款金额 和 入款类型 计算
		} catch (IllegalArgumentException e) {
			throw new AccountException(e.getMessage());
		}
		// 每次充值都有out_trade_no，防止重复充值做查询判断 
		// 查询账户历史是否用out_trade_no，如果有了。抛出异常
		UserAccounthistory userAccounthistory = userAccounthistoryDao.getUserAccounthistoryByOutTtradeNo(out_trade_no);
		if (userAccounthistory != null) {
			throw new StaleObjectStateException(StaleObjectStateException.OPTIMISTIC_OUTTRADENO_EXIT);
		}
		// 更新账户dao
		account.setLastUpdateTime(new Date());
		userAccountDao.updateUserAccount(account);
		// 添加账户历史操作记录
		userAccounthistory = new UserAccounthistory();
		userAccounthistory.setUserId(userId);
		userAccounthistory.setTrxorderId(trxOrderId);// 订单id
		userAccounthistory.setOtherId(account.getId());// 相关账户ID
		userAccounthistory.setCreateTime(new Date());
		userAccounthistory.setRequestId(requestId);// 系统内订单编号
		userAccounthistory.setOutTradeNo(out_trade_no);// 对支付宝订单号
		if (isDisplay) {// 是否显示账户历史记录:0显示;1不显示
			userAccounthistory.setIsDisplay(0);
		} else {
			userAccounthistory.setIsDisplay(1);
		}
		userAccounthistory.setBalance(account.getBalance());// 当前余额
		userAccounthistory.setCashAmount(account.getCashAmount());// cash余额
		userAccounthistory.setVmAmount(account.getVmAmount());// vm余额
		userAccounthistory.setBackAmount(account.getBackAmount());// 提现余额
		userAccounthistory.setTrxAmount(trxAmount);// 交易金额
		DecimalFormat format = new DecimalFormat("0.00");
		userAccounthistory.setDescription(description + format.format(trxAmount));// 账户历史内容描述
		userAccounthistory.setActHistoryType(accountHistoryType.toString());// 帐务历史类型.充值。消费等
		userAccounthistory.setBizType(accountBizType.toString());// 业务类型(课程订单、会员订单、图书订单)
		userAccounthistory.setVersion(account.getVersion());// 乐观锁版本号
		userAccounthistoryDao.addUserAccounthistory(userAccounthistory);
		logger.info("credit SUCCESS:======balance:" + account.getBalance() + "=====trxAmount:" + trxAmount + "============");
		return account;
	}

	/**
	 * 更新账户状态
	 * 
	 * @param userId
	 * @param status
	 */
	public void updateUserAccountStatus(Long userId, String status) {
		userAccountDao.updateUserAccountStatus(userId, status);
	}

	/**
	 * 根据用户id获得详情
	 * 
	 * @param userId
	 * @return
	 */
	public UserAccountDTO getuserAccountInfo(Long userId) {
		return userAccountDao.getuserAccountInfo(userId);
	}

	/**
	 * 根据用户进行账户充值 /扣钱（后台操作）
	 * 
	 * @param userId
	 *            用户id
	 * @param balance
	 *            交易金额
	 * @param flag
	 *            充值 扣款标示
	 * @param sysUser
	 *            操作人
	 * @return
	 * @throws StaleObjectStateException
	 * @throws AccountException
	 */
	public boolean gainUserRechargeAmount(SysUser sysUser, Long userId, BigDecimal balance, String flag) throws AccountException, StaleObjectStateException {
		// 获得用户账户信息
		UserAccount userAccount = userAccountDao.getUserAccountByUserId(userId);
		if (ObjectUtils.isNotNull(userAccount)) {
			String out_no = "timeOrder"+System.currentTimeMillis();//系统充值没有订单，用时间戳代替不同的订单号
			if (flag.equals("credit")) {// 充值操作
				this.credit(userAccount, balance, AccountType.VM, AccountHistoryType.ADMINLOAD, userId, 0L, out_no, out_no, new Date(), "后台充值", false, AccountBizType.ADMIN);
				//添加账户操作记录
				this.addUserAccountOptRecord(userAccount.getUserId(),sysUser,userAccount.getId(), balance, AccountHistoryType.ADMINLOAD.toString(), out_no);
				return true;
			}
			if (flag.equals("debit")) {
				// 扣费操作
				this.debit(userAccount, balance, AccountHistoryType.ADMINREFUND, userId, 0L, out_no, new Date(), "后台扣款", false, AccountBizType.ADMIN);
				//添加账户操作记录
				this.addUserAccountOptRecord(userAccount.getUserId(),sysUser,userAccount.getId(), balance, AccountHistoryType.ADMINREFUND.toString(), out_no);
				return true;
			}
		}
		return false;
	}

	@Override
	public void updateUserAccount(UserAccount userAccount) throws Exception {
		userAccountDao.updateUserAccount(userAccount);
	}

	/**
	 * 添加账户操作记录
	 * 
	 * @param userId
	 *            学员Id
	 * @param sysUser
	 *            后台操作人
	 * @param accountId
	 *            账户Id
	 * @param balance
	 *            操作金额
	 * @param type
	 *            操作类型
	 * @param outNo
	 *            操作标示
	 */
	public void addUserAccountOptRecord(Long userId,SysUser sysUser, Long accountId, BigDecimal balance, String type, String outNo) {
		UserAccountOptRecord userAccountOptRecord = new UserAccountOptRecord();
		userAccountOptRecord.setUserId(userId);//学员Id
		userAccountOptRecord.setOptuser(Long.valueOf(sysUser.getUserId()));//操作人id
		userAccountOptRecord.setOptusername(sysUser.getLoginName());//操作人名字
		userAccountOptRecord.setAccountId(accountId);//账户Id
		userAccountOptRecord.setAmount(balance);//操作金额
		userAccountOptRecord.setType(type);// 操作类型
		userAccountOptRecord.setOutNo(outNo);//操作标示
		if(type.equals(UserOptType.ADMINLOAD.toString())){//描述
			userAccountOptRecord.setDescription("后台充值");
		}else{
			userAccountOptRecord.setDescription("后台扣款");
		}
		userAccountOptRecord.setCreateTime(new Date());
		userAccountOptRecordDao.addUserAccountOptRecord(userAccountOptRecord);
		//添加账户操作记录
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("optuser", "操作人id_"+sysUser.getUserId());
		map.put("accountId", "账户id_"+accountId);
		map.put("optType","操作_"+userAccountOptRecord.getDescription());
		//userService.addUserOptRecord(userId, type, sysUser.getUserId(), sysUser.getLoginName(), accountId, gson.toJson(map));
	}
}