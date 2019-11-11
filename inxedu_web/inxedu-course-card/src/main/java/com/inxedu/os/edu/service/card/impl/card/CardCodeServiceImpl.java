package com.inxedu.os.edu.service.card.impl.card;

import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.constants.enums.CardStatus;
import com.inxedu.os.edu.constants.enums.PayType;
import com.inxedu.os.edu.constants.enums.ReqChanle;
import com.inxedu.os.edu.constants.enums.account.AccountBizType;
import com.inxedu.os.edu.constants.enums.account.AccountHistoryType;
import com.inxedu.os.edu.dao.card.CardCodeDao;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.account.UserAccounthistory;
import com.inxedu.os.edu.entity.card.*;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.account.UserAccounthistoryService;
import com.inxedu.os.edu.service.card.CardCodeService;
import com.inxedu.os.edu.service.card.CardCourseService;
import com.inxedu.os.edu.service.card.CardService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *CardCode管理接口
 * author www.inxedu.com
 */
@Service("cardCodeService")
public class CardCodeServiceImpl implements CardCodeService {
	private Logger logger = LoggerFactory.getLogger(CardCodeServiceImpl.class);

	@Autowired
	private CardCodeDao cardCodeDao;

	@Autowired(required=false)
	private UserAccounthistoryService userAccounthistoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private CardCourseService cardCourseService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CardService cardService;
	@Autowired(required=false)
	private UserAccountService userAccountService;
	/**
	 * 添加CardCode
	 * 
	 * @param cardCode
	 *            要添加的CardCode
	 * @return id
	 */
	public Long addCardCode(CardCode cardCode) {
		return cardCodeDao.addCardCode(cardCode);
	}

	/**
	 * 根据id删除一个CardCode
	 * 
	 * @param id
	 *            要删除的id
	 */
	public void deleteCardCodeById(Long id) {
		cardCodeDao.deleteCardCodeById(id);
	}

	/**
	 * 修改CardCode
	 * 
	 * @param cardCode
	 *            要修改的CardCode
	 */
	public void updateCardCode(CardCode cardCode) {
		cardCodeDao.updateCardCode(cardCode);
	}

	/**
	 * 根据id获取单个CardCode对象
	 * 
	 * @param id
	 *            要查询的id
	 * @return CardCode
	 */
	public CardCode getCardCodeById(Long id) {
		return cardCodeDao.getCardCodeById(id);
	}

	/**
	 * 根据条件获取CardCode列表
	 * 
	 * @param cardCode
	 *            查询条件
	 * @return List<CardCode>
	 */
	public List<CardCode> getCardCodeList(CardCode cardCode) {
		return cardCodeDao.getCardCodeList(cardCode);
	}

	@Override
	public List<CardCode> getHistoryByQueryCardCode(QueryCardCode queryCardCode) {
		return cardCodeDao.getHistoryByQueryCardCode(queryCardCode);
	}

	/**
	 * 激活课程卡 充值卡实现层
	 * 
	 * @param cardCode
	 */
	public String activationCard(CardCode cardCode, Long userId,int type) throws Exception {
		String returnMsg = "";
		List<CardCode> cardCodes=getCardCodeList(cardCode);
		if(cardCodes==null||cardCodes.size()==0){
			returnMsg = "passwordError";
		} else {
			CardCode _cardCode = cardCodes.get(0);
			if (_cardCode.getType()!=type) {
				returnMsg = "typeError";//类型不匹配
			} else if (!_cardCode.getCardCodePassword().equals(cardCode.getCardCodePassword())) {
				returnMsg = "passwordError";//卡号或密码错误
			} else if (_cardCode.getStatus().equals("USED")) {
				returnMsg = "alreadyUse";//已经使用
			} else if(_cardCode.getStatus().equals(CardStatus.CLOSE.toString())){
				returnMsg = "close";//已关闭
			} else if(_cardCode.getStatus().equals(CardStatus.OVERDUE.toString())){
				returnMsg ="overDue";//已过期
			} else{//不在有效期内
				Card card= cardService.getCardById(_cardCode.getCardId());
				Date startDate = card.getBeginTime();
		        Date endDate = card.getEndTime();
		        Date date=new Date();
		    	if (startDate.getTime() > date.getTime() || endDate.getTime() < date.getTime()) {
		    		returnMsg="dateError";
		    	}
			}
			if(returnMsg.equals("")){
				User user = userService.queryUserById(Integer.parseInt(userId+""));
					//课程卡
				if (type==1){
					addCourseCard(_cardCode, user);
				}
					/*充值卡*/
				if (type==2){
					addRechargeCard(_cardCode,user);
				}
			}
		}
		return returnMsg;
	}
	/**
	 * 定时器操作
	 * 课程卡过期状态操作
	 */
	public void updateCardStatus() {
		try {
			String cardIds = "";
			//获得 所有未使用的
			List<CourseCardDTO> cardCodeList = cardCodeDao.getCardCodeStatus();
			for (CourseCardDTO courseCardDTO : cardCodeList) {
				if (courseCardDTO.getEndTime().before(new Date())) {
					cardIds += courseCardDTO.getId() + ",";
				}
			}
			//传递数据修改
			if(cardIds.length()>1){
				cardIds = cardIds.substring(0, cardIds.length()-1);
				cardCodeDao.updateCardCodeStatus(cardIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 通过主卡作废详卡表信息
	 * 
	 * */
	@Override
	public void closeMainCard(long cardId) {
		CardCode  cardCode = new CardCode();
		cardCode.setCardId(cardId);
		cardCode.setStatus(CardStatus.INIT.toString());
		List<CardCode> cardList = cardCodeDao.getCardCodeList(cardCode);
		cardCodeDao.closeCardCodeStatus(cardList);

		//如果是学员卡 冻结用户
		Card card=cardService.getCardById(cardId);
		if(card.getType()==3){
			for(CardCode cardCode1:cardList){
				User user = userService.getLoginUser(cardCode1.getCardCode(), MD5.getMD5(cardCode1.getCardCodePassword()));
				user.setIsavalible(2);
				userService.updateUserStates(user);
			}
		}
	}
	/**
	 * 执行课程卡操作
	 * 
	 * @param cardCode
	 * @throws Exception
	 */
	public void addCourseCard(CardCode cardCode, User user) throws Exception {
	
		CardCourse cardCourse = new CardCourse();
		cardCourse.setCardId(cardCode.getCardId());
		String courseIds = "";
		//查找未过期的 课程， 存在的课程
		List<CardCourse> cardCourseList = cardCourseService.getCardCourseList(cardCourse);
		if(ObjectUtils.isNotNull(cardCourseList)){
			for (CardCourse _cardCourse : cardCourseList) {
				courseIds += _cardCourse.getCourseId() + ",";
			}
			// 参数拼装
			Map<String, String> sourceMap = new HashMap<String, String>();
			sourceMap.put("courseIds", courseIds);
			sourceMap.put("type", "1");
			sourceMap.put("couponcode", cardCode.getCardCode());
			sourceMap.put("userid", user.getUserId() + "");
			sourceMap.put("payType", PayType.CARD.toString());
			sourceMap.put("reqchanle", ReqChanle.WEB.toString());
			sourceMap.put("reqIp", "");// 用户访问id
			// 订单

			String [] array =orderService.addCourseCardOrder(sourceMap).split(",");
			Long orderId =new Long (array[0]);
			// 课程卡信息改变
			cardCode.setUseTime(new Date());
			cardCode.setUserEmail(user.getEmail());
			cardCode.setStatus(CardStatus.USED.toString());
			cardCode.setUserId(Long.valueOf(user.getUserId()));
			cardCode.setTrxorderId(orderId);
			cardCode.setRequestId(array[1]);
			cardCodeDao.updateCardCode(cardCode);
		}
	}
	/**
	 * 执行充值卡操作
	 *
	 * @param cardCode
	 * @throws Exception
	 */
	public void addRechargeCard(CardCode cardCode, User user) throws Exception {
		/*获取充值卡*/
		Card card = cardService.getCardById(cardCode.getCardId());
		/*更新账户信息*/
		UserAccount userAccount = userAccountService.getUserAccountByUserId(new Long(user.getUserId()));
			/*账户余额加上充值卡金额*/
		userAccount.setBalance(userAccount.getBalance().add(card.getMoney()));
			/*充值余额加上充值卡金额*/
		userAccount.setVmAmount(userAccount.getVmAmount().add(card.getMoney()));
			/*最后操作时间*/
		userAccount.setLastUpdateTime(new Date());
		userAccountService.updateUserAccount(userAccount);
		/*充值记录*/
		UserAccounthistory userAccounthistory = new UserAccounthistory();
		userAccounthistory.setUserId(new Long(user.getUserId()));
		userAccounthistory.setTrxorderId(null);// 订单id
			/*充值卡code id*/
		userAccounthistory.setOtherId(cardCode.getId());
		userAccounthistory.setCreateTime(new Date());
		userAccounthistory.setBalance(userAccount.getBalance());// 当前余额
		userAccounthistory.setCashAmount(userAccount.getCashAmount());// cash余额
		userAccounthistory.setVmAmount(userAccount.getVmAmount());// vm余额
		userAccounthistory.setBackAmount(userAccount.getBackAmount());// 提现余额
		userAccounthistory.setTrxAmount(card.getMoney());// 交易金额
		userAccounthistory.setDescription("充值金额" + card.getMoney());// 账户历史内容描述
		userAccounthistory.setActHistoryType(AccountHistoryType.VMLOAD+"");// 帐务类型.充值
		userAccounthistory.setBizType(AccountBizType.CARDLOAD+"");// 业务类型充值卡
		userAccounthistory.setVersion(userAccount.getVersion());// 乐观锁版本号
		userAccounthistoryService.addUserAccounthistory(userAccounthistory);
		logger.info("credit SUCCESS:======balance:" + userAccount.getBalance() + "=====trxAmount:" + card.getMoney() + "============");
		// 充值卡信息改变
		cardCode.setUseTime(new Date());
		cardCode.setUserEmail(user.getEmail());
		cardCode.setStatus(CardStatus.USED.toString());
		cardCode.setUserId(Long.valueOf(user.getUserId()));
		cardCode.setTrxorderId(null);
		cardCode.setRequestId(null);
		cardCodeDao.updateCardCode(cardCode);
	}
	/**
     * 根据卡编码  修改   未使用  为  已使用  状态
     */
    public void updateCardStatusByCode(String cardCode){
    	cardCodeDao.updateCardStatusByCode(cardCode);
    }

	@Override
	public CardCode queryCardCodeByCode(String cardCode) {
		return cardCodeDao.queryCardCodeByCode(cardCode);
	}

	@Override
	public void createLoginUserCouserOrder(String loginAccount,long userId) throws Exception {
		//卡编码
		CardCode cardCode = this.queryCardCodeByCode(loginAccount);
		//学员卡未使用 才可以 绑定课程
		if(cardCode!=null && (CardStatus.INIT.toString()).equals(cardCode.getStatus())){
			//学员卡的课程id（课程存在 且未过期）
			List<Long> courseList = this.queryCardCodeCourseIdsByCodeId(cardCode.getCardId());
			if(courseList!=null && courseList.size()>0){
				String courseIds ="";
				for(int i=0;i<courseList.size();i++){
					if(i<courseList.size()-1){
						courseIds+=courseList.get(i)+",";
					}else{
						courseIds+=courseList.get(i);
					}
				}
				String result = this.addOrderMsg(userId,courseIds);
				String[] orderArr = result.split(",");
				cardCode.setTrxorderId(Long.parseLong(orderArr[0]));
				cardCode.setRequestId(orderArr[1]);
			}
			cardCode.setUserId(userId);
			cardCode.setStatus(CardStatus.USED.toString());
			cardCode.setUseTime(new Date());
			//修改状态
			this.updateCardCode(cardCode);
		}
	}

	@Override
	public List<Long> queryCardCodeCourseIdsByCodeId(long codeId) {
		return cardCodeDao.queryCardCodeCourseIdsByCodeId(codeId);
	}

	 //创建学员卡订单
 	public String addOrderMsg(Long userId, String courseIds) throws Exception {
 		//订单数据
 		Map<String, String> sourceMap = new HashMap<String, String>();
 		sourceMap.put("type", "1");//类型
 		sourceMap.put("couponcode", "");// 优惠卷
 		sourceMap.put("userid",String.valueOf(userId));
 		sourceMap.put("reqchanle", ReqChanle.WEB.toString());
 		sourceMap.put("reqIp", "");
 		sourceMap.put("courseIds", courseIds);
 		sourceMap.put("payType", PayType.USERCARD.toString());
 		String reuslt = orderService.addUserCardOrder(sourceMap);
 		return reuslt;
 	}

	@Override
	public void updCardStatus() {
		cardCodeDao.updateCardStatus();
	}
}