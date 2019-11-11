package com.inxedu.os.edu.service.card.impl.card;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.CardStatus;
import com.inxedu.os.edu.constants.enums.UserRegisterFrom;
import com.inxedu.os.edu.dao.card.CardCodeDao;
import com.inxedu.os.edu.dao.card.CardCourseDao;
import com.inxedu.os.edu.dao.card.CardDao;
import com.inxedu.os.edu.entity.card.*;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.card.CardService;
import com.inxedu.os.edu.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *Card管理接口
 * author www.inxedu.com
 */
@Service("cardService")
public class CardServiceImpl implements CardService {

	@Autowired
	private CardDao cardDao;
	@Autowired
	private CardCourseDao cardCourseDao;
	@Autowired
	private CardCodeDao cardCodeDao;
	@Autowired
	private UserService userService;

	/**
	 * 添加Card
	 * @param card 要添加的Card
	 * @return id
	 */
	public Long addCard(Card card) {
		return cardDao.addCard(card);
	}

	/**
	 * 根据id删除一个Card
	 * @param id 要删除的id
	 */
	public void deleteCardById(Long id) {
		cardDao.deleteCardById(id);
	}
	
	/**
	 * 修改Card
	 * @param card 要修改的Card
	 */
	public void updateCard(Card card) {
		cardDao.updateCard(card);
	}

	/**
	 * 根据id获取单个Card对象
	 * @param id 要查询的id
	 * @return Card
	 */
	public Card getCardById(Long id) {
		return cardDao.getCardById(id);
	}

	/**
	 * 根据条件获取Card列表
	 * @param card 查询条件
	 * @return List<Card>
	 */
	public List<Card> getCardList(Card card) {
		return cardDao.getCardList(card);
	}

	@Override
	public void saveCourseCardInfo(Card card, String courseIds) {
		// 主卡信息
		cardDao.addCard(card);
		// 副卡信息
		addcardCode(card.getId(), card.getNum());
		// 课程卡处理
		if (card.getType() == Card.COURSE_CARD) {
			// 中间表信息处理
			cardCourseDao.addCardCourse(getCardCourseList(card.getId(), courseIds));
		}
	}

	@Override
	public List<CourseCardDTO> getCardListByCondtion(QueryCardCode queryCardCode, PageEntity page) {
		return cardCodeDao.getCardListByCondtion(queryCardCode, page);
	}

	@Override
	public List<CourseCardDTO> getAllCardHistoryCondition(QueryCardCode queryCardCode) {
		return cardCodeDao.getAllCardHistoryCondition(queryCardCode);
	}

	@Override
	public List<MainCardDTO> getMianListByCondition(QueryMainCard queryMainCard, PageEntity page) {
		return cardCodeDao.getMainCardListCondition(queryMainCard, page);
	}

	/**
	 * 课程卡集合拼凑
	 * 
	 * @param cardId
	 * @param courseIds
	 * @return
	 */
	public List<CardCourse> getCardCourseList(long cardId, String courseIds) {
		List<CardCourse> CardCourseList = new ArrayList<CardCourse>();
		String[] courseId = courseIds.split(",");
		for (int i = 0; i < courseId.length; i++) {
			CardCourse cardCourse = new CardCourse();
			cardCourse.setCardId(cardId);
			cardCourse.setCourseId(Long.parseLong(courseId[i]));
			CardCourseList.add(cardCourse);
		}
		return CardCourseList;
	}

	/**
	 * 批量添加课程卡副卡信息
	 * */
	public void addcardCode(long cardId, long num) {
		int i;
		List<CardCode> cardList = new ArrayList<CardCode>();
		for (i = 1; i <= num; i++) {
			CardCode cardCode = new CardCode();
			cardCode.setCreateTime(new Date());
			cardCode.setCardId(cardId);
			cardCode.setStatus(CardStatus.INIT.toString());
            String ccode="1"+getFixString(Integer.valueOf(cardId+"").intValue(),3)+getFixString(i,3)+ StringUtils.getRandStr(4);
			cardCode.setCardCode(ccode);
			cardCode.setCardCodePassword(StringUtils.getRandStr(10));// 生成10位密码
			cardList.add(cardCode);
		}
		cardCodeDao.addCardCodeList(cardList);
	}

    /**
     * 长度补冲，前面加0
     *
     * @param num
     * @param len
     * @return String
     */
    static String getFixString(int num, int len) {
        String tp = "" + num;
        if (len == 0) {
            return tp;
        }
        if (tp.length() == len)
            return tp;
        if (tp.length() > len)
            return tp.substring(0, len);
        for (int i = 0; i <= len / 4 + 1; i++) {
            tp = "00000" + tp;
        }
        return tp.substring(tp.length() - len);
    }
	/**
	 * 添加学员卡
	 * @throws Exception
	 */
	public void addCourseUserCard(Card card, String courseIds) throws Exception {
		String[] courseArr=null;
		if(courseIds!=null && courseIds.trim().length()>0){
			courseArr = courseIds.split(",");
		}
		// 主卡信息
		cardDao.addCard(card);
		//生成编码
		int j;
		List<CardCode> cardList = new ArrayList<CardCode>();
		for (j = 1; j <= card.getNum(); j++) {
			CardCode cardCode = new CardCode();
			cardCode.setCreateTime(new Date());
			cardCode.setCardId(card.getId());
			cardCode.setStatus(CardStatus.INIT.toString());

			String loginAccount = generateLoginAccount(card.getLoginAccountPrefix(),j);
			cardCode.setCardCode(loginAccount);
			cardCode.setUserEmail(null);
			cardCode.setCardCodePassword(StringUtils.getCharAndNumr(6));// 生成6位密码
			cardList.add(cardCode);
		}
		cardCodeDao.addCardCodeList(cardList);
		//激活 学员卡
		for (int i =0; i < cardList.size(); i++) {
			CardCode cardCode= cardList.get(i);
			if (cardCode != null) {
				String loginAccount = cardCode.getCardCode();// 登录账号
				if (ObjectUtils.isNull(loginAccount) || loginAccount.trim().equals("")) {
					continue;
				}
				String pwd=cardCode.getCardCodePassword();//获得密码
				User users = new User();
				users.setLoginAccount(loginAccount);
				users.setShowName(loginAccount);
				users.setRegisterFrom(UserRegisterFrom.userCard.toString());
				users.setAge(0);
				users.setIsavalible(1);
				users.setSex(1);
				users.setUserName(loginAccount);
				if(StringUtils.isEmpty(pwd)){
					users.setPassword("111111");// 设置默认密码
				}else{
					users.setPassword(MD5.getMD5(pwd));
				}
				userService.createUser(users);
			}
		}
		//添加课程和学生卡的中间表
		if(courseArr!=null && courseArr.length>0){
			StringBuffer value = new StringBuffer();
			for(int i=0;i<courseArr.length;i++){
				if(i<courseArr.length-1){
					value.append("("+card.getId()+","+courseArr[i]+"),");
				}else{
					value.append("("+card.getId()+","+courseArr[i]+")");
				}
			}
			this.batchAddCardUserCourse(value.toString());
		}
	}

	private String generateLoginAccount(String accountPrefix,int index){
		String str = String.valueOf(index);
		int len = str.length();
		for(int i=0;i<6-len;i++){
			str="0"+str;
		}
		return accountPrefix+str;
	}

	@Override
	public void batchAddCardUserCourse(String value) {
		cardDao.batchAddCardUserCourse(value);
	}
}