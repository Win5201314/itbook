package com.inxedu.os.edu.service.impl.member;


import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.FastJsonUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.constants.enums.PayType;
import com.inxedu.os.edu.constants.enums.order.TrxOrderStatus;
import com.inxedu.os.edu.dao.member.MemberOrderDao;
import com.inxedu.os.edu.dao.member.MemberOrderOptRecordDao;
import com.inxedu.os.edu.entity.member.*;
import com.inxedu.os.edu.exception.StaleObjectStateException;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.member.MemberOrderService;
import com.inxedu.os.edu.service.member.MemberRecordService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MemberOrder管理接口
 * User: qinggang.liu
 * Date: 2014-09-26
 */
@Service("memberOrderService")
public class MemberOrderServiceImpl implements MemberOrderService {
	private static Logger logger = LoggerFactory.getLogger(MemberOrderServiceImpl.class);
 	@Autowired
    private MemberOrderDao memberOrderDao;
 	@Autowired
    private MemberSaleService memberSaleService;
 	@Autowired
    private MemberRecordService memberRecordService;

	@Autowired
	private MsgReceiveService msgReceiveService;
	@Autowired
	private MemberOrderOptRecordDao memberOrderOptRecordDao;
 	
	/** 
	 * 添加MemberOrder
	 * @param
	 * @return id
	 */
	public Map<String, Object> addMemberOrder(Map<String, String> sourceMap) throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();

		// 检查参数
		MemberOrderReqData memberOrderReqData = checkMemberOrderParam(sourceMap);
		if (memberOrderReqData == null) {// 参数验证失败
			result.put("msg", "param");
			return result;
		}
		if (ObjectUtils.isNull(memberOrderReqData.getMemberId())) {
			result.put("msg", "param");
			return result;
		}
		//开通的会员类型
		MemberSale memberType= memberSaleService.getMemberSaleById(memberOrderReqData.getMemberId());
		// 拼装订单数据
		MemberOrder memberOrder = new MemberOrder();
		// 下单时间
		Date date = new Date();
		memberOrder.setCreateTime(date);// 下单时间
		memberOrder.setUserId(memberOrderReqData.getUserId());
		memberOrder.setRequestId(this.getMemberOrderNum(memberOrderReqData.getUserId()));// 交易请求号
		memberOrder.setTrxStatus(TrxOrderStatus.INIT.toString());// 交易装态
		memberOrder.setPayType(memberOrderReqData.getPayType().toString());// 支付类型
		memberOrder.setReqChannel(memberOrderReqData.getReqChannel());// 请求渠道
		memberOrder.setReqIp(memberOrderReqData.getReqIp());
		memberOrder.setDescription(FastJsonUtil.obj2JsonString(sourceMap));
		memberOrder.setMemberId(memberType.getId());//会员商品id
		memberOrder.setMemberDays(memberType.getDays());//开通天数
		memberOrder.setMemberType(memberType.getType());//会员类型
		// 原始金额
		BigDecimal orderAmount = memberType.getPrice();
		memberOrder.setOrderAmount(orderAmount); // 原始金额
		memberOrder.setCouponCodeId(0L);

		BigDecimal couponAmount = new BigDecimal(0);
		memberOrder.setCouponAmount(couponAmount);//优惠金额
		// 实际需要支付的金额,四舍五去取2位
		BigDecimal amount = orderAmount.subtract(couponAmount).setScale(2, RoundingMode.HALF_UP);
		if (amount.doubleValue() <= 0) {
			result.put("msg", "amount");
			return result;
		}
		memberOrder.setAmount(amount);// 实际支付金额
		// 添加订单
		memberOrderDao.addMemberOrder(memberOrder);
		result.put("memOrder", memberOrder);
		return result;
	}
 	/**
	 * 订单检查请求参数
	 * 
	 * @param sourceMap
	 * @return
	 */
	public MemberOrderReqData checkMemberOrderParam(Map<String, String> sourceMap) {
		MemberOrderReqData reqData = new MemberOrderReqData();
		String couponcode = sourceMap.get("couponcode"); // 优惠卷编码
		if (StringUtils.isNotEmpty(couponcode)) {
			reqData.setCouponCode(couponcode);
		}

		String userid = sourceMap.get("userid");// 用户id
		if (StringUtils.isNotEmpty(userid) && Long.valueOf(userid).longValue() > 0) {
			reqData.setUserId(Long.valueOf(userid));
		} else {
			return null;
		}

		String reqchanle = sourceMap.get("reqchanle");// 用户id
		if (StringUtils.isNotEmpty(reqchanle)) {
			reqData.setReqChannel(reqchanle);
		} else {
			return null;
		}
		String memberid = sourceMap.get("memberId");// 用户id
		if (StringUtils.isNotEmpty(memberid)) {
			reqData.setMemberId(Long.valueOf(memberid));
		} else {
			return null;
		}
		String payType = sourceMap.get("payType");// 支付类型
		if (StringUtils.isNotEmpty(payType)) {

			if(PayType.class != null && !"".equals(payType)) {
				try {
					reqData.setPayType(Enum.valueOf(PayType.class, payType.trim()));
				} catch (IllegalArgumentException var3) {
					var3.printStackTrace();
				}
			}
			else {
				reqData.setPayType(null);
			}
		} else {
			return null;
		}

		String reqIp = sourceMap.get("reqIp");// 用户reqIp
		if (StringUtils.isNotEmpty(reqIp)) {
			reqData.setReqIp(reqIp);
		} else {
			reqData.setReqIp("");
		}
		return reqData;
	}
	/**
	 * 生成订单号 当前用户id+毫秒数
	 * 
	 * @return
	 */
	public String getMemberOrderNum(Long userId) {
		return userId + DateUtils.toString(new Date(), "yyyyMMddHHmmssSSS");
	}
    /**
     * 添加MemberOrder
     * @param memberOrder 要添加的MemberOrder
     * @return id
     */
    public Long addMemberOrder(MemberOrder memberOrder){
    	return memberOrderDao.addMemberOrder(memberOrder);
    }

    /**
     * 根据id删除一个MemberOrder
     * @param id 要删除的id
     */
    public void deleteMemberOrderById(Long id){
    	 memberOrderDao.deleteMemberOrderById(id);
    }

    /**
     * 修改MemberOrder
     * @param memberOrder 要修改的MemberOrder
     */
    public void updateMemberOrder(MemberOrder memberOrder){
     	memberOrderDao.updateMemberOrder(memberOrder);
    }

    /**
     * 根据id获取单个MemberOrder对象
     * @param id 要查询的id
     * @return MemberOrder
     */
    public MemberOrder getMemberOrderById(Long id){
    	return memberOrderDao.getMemberOrderById( id);
    }
    /**
     * 根据id获取单个MemberOrderDTO对象
     * @param id 要查询的id
     * @return MemberOrderDTO
     */
    public MemberOrderDTO getMemberOrderDTOById(Long id){
    	return memberOrderDao.getMemberOrderDTOById( id);
    }
    /**
     * 根据requestId获取单个MemberOrder对象
     * @param
     * @return
     */
    public MemberOrder getMemberOrderByRequestId(String requestId){
    	return memberOrderDao.getMemberOrderByRequestId(requestId);
    }
    
    /**
     * 根据条件获取MemberOrder列表
     * @param memberOrder 查询条件
     * @return List<MemberOrder>
     */
    public List<MemberOrder> getMemberOrderList(MemberOrder memberOrder){
    	return memberOrderDao.getMemberOrderList(memberOrder);
    }

    /**
     * 会员订单列表
     * @param queryMemberOrder
     * @param page
     * @return
     */
	public List<MemberOrderDTO> getMemberOrderPage(QueryMemberOrder queryMemberOrder, PageEntity page){
		return memberOrderDao.getMemberOrderPage(queryMemberOrder,page);
	}
	/**
	 * 订单回调,余额支付订单操作 事物开启
	 * 
	 * @param
	 * @return
	 * @throws StaleObjectStateException
	 */
	public Map<String, String> updateCompleteMemberOrder(MemberOrderReqData memberReqData) throws Exception {
		logger.info("updateCompleteOrder memberReqData:" + memberReqData);
		Map<String, String> res = new HashMap<String, String>();

		// 计算此次订单使用的cash金额和vm金额
		BigDecimal amount = memberReqData.getAmount();// 订单所需支付的金额
		BigDecimal useCashAmoun = new BigDecimal(0);
		BigDecimal useVmAmount = new BigDecimal(0);


		MemberOrder memberOrder = getMemberOrderByRequestId(memberReqData.getRequestId());
		memberOrder.setCashAmount(useCashAmoun);
		memberOrder.setVmAmount(useVmAmount);
		memberOrder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());
		memberOrder.setPayTime(memberReqData.getCreateTime());
		memberOrder.setPayType(memberReqData.getPayType().toString());
		memberOrder.setOutTradeNo(memberReqData.getOut_trade_no());

		return null;

	}


	/**
	 * 免费赠送订单操作
	 * @param sourceMap 需要的参数
	 * @throws
	 */
	@Override
	public Map<String, Object> addFreeMemberOrder(Map<String, String> sourceMap) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		// 检查参数
		/*MemberOrderReqData memberOrderReqData = checkFreeMemberOrderParam(sourceMap);
		if (memberOrderReqData == null) {// 参数验证失败
			result.put("msg", "param");
			return result;
		}
		if (ObjectUtils.isNull(memberOrderReqData.getUserId())) {
			result.put("msg", "param");
			return result;
		}
		if (ObjectUtils.isNull(memberOrderReqData.getMemberId())) {
			result.put("msg", "param");
			return result;
		}
		//开通的会员类型
		MemberSale memberType= memberSaleService.getMemberSaleById(memberOrderReqData.getMemberId());
		// 拼装订单数据
		MemberOrder memberOrder = new MemberOrder();
		// 下单时间
		Date date = new Date();
		memberOrder.setUserId(memberOrderReqData.getUserId()); // 开通用户
		memberOrder.setMemberId(memberType.getId());//会员商品id
		memberOrder.setCreateTime(date);// 下单时间
		memberOrder.setPayTime(date); // 支付成功时间
		memberOrder.setOrderAmount(memberType.getPrice()); // 订单原始金额
		memberOrder.setCouponAmount(memberType.getPrice());//优惠金额
		memberOrder.setAmount(new BigDecimal(0));// 实际支付金额
		memberOrder.setCashAmount(new BigDecimal(0)); // 实际支付现金金额
		memberOrder.setVmAmount(new BigDecimal(0)); // 实际支付虚拟币金额
		memberOrder.setCouponCodeId(0l); // 优惠券id
		memberOrder.setRequestId(this.getMemberOrderNum(memberOrderReqData.getUserId()));// 交易请求号
		memberOrder.setTrxStatus(TrxOrderStatus.SUCCESS.toString());// 交易装态
		memberOrder.setPayType("FREE");// 支付类型
		memberOrder.setReqChannel("WEB");// 请求渠道
		memberOrder.setMemberDays(memberType.getDays());//开通天数
		memberOrder.setMemberType(memberType.getType());//会员类型
		memberOrder.setDescription(new Gson().toJson(sourceMap));
		memberOrder.setReqIp(""); // ip
		// 添加订单
		memberOrderDao.addMemberOrder(memberOrder);
		//开通会员
		memberRecordService.addMemberRecord(memberOrder);
		// 添加系统用户操作记录
		MemberOrderOptRecord memberOrderOptRecord = new MemberOrderOptRecord();
		memberOrderOptRecord.setUserId(memberOrderReqData.getUserId()); // 用户id
		memberOrderOptRecord.setOptuser(0L); // 系统用户id
		memberOrderOptRecord.setType("GIVE"); // 赠送类型  GIVE：中文意思赠送
		memberOrderOptRecord.setDescription("赠送操作"); // 描述
		memberOrderOptRecord.setCreateTime(new Date()); // 赠送时间
		memberOrderOptRecord.setOptusername("系统管理员"); // 操作者名称
		memberOrderOptRecordDao.addMemberOrderOptRecord(memberOrderOptRecord);
		result.put("memOrder", memberOrder);*/
		return result;
	}

	/**
	 * 赠送会员订单检查请求参数
	 *
	 * @param sourceMap
	 * @return
	 */
	public MemberOrderReqData checkFreeMemberOrderParam(Map<String, String> sourceMap) {
		MemberOrderReqData reqData = new MemberOrderReqData();

		String userid = sourceMap.get("userId");// 用户id
		if (StringUtils.isNotEmpty(userid) && Long.valueOf(userid).longValue() > 0) {
			reqData.setUserId(Long.valueOf(userid));
		} else {
			return null;
		}

		String memberid = sourceMap.get("memberId");// 用户id
		if (StringUtils.isNotEmpty(memberid)) {
			reqData.setMemberId(Long.valueOf(memberid));
		} else {
			return null;
		}
		return reqData;
	}
	
}