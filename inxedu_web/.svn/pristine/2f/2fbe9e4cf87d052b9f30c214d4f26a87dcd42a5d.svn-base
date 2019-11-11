package com.inxedu.os.edu.service.impl.order;

import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.constants.enums.PayType;
import com.inxedu.os.edu.constants.enums.account.AccountBizType;
import com.inxedu.os.edu.constants.enums.account.AccountHistoryType;
import com.inxedu.os.edu.constants.enums.account.AccountType;
import com.inxedu.os.edu.constants.enums.order.TrxOrderStatus;
import com.inxedu.os.edu.constants.order.OrderConstans;
import com.inxedu.os.edu.entity.account.UserAccount;
import com.inxedu.os.edu.entity.order.Order;
import com.inxedu.os.edu.entity.order.TrxReqData;
import com.inxedu.os.edu.service.account.UserAccountService;
import com.inxedu.os.edu.service.order.OrderService;
import com.inxedu.os.edu.service.order.TrxHessianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TrxHessianServiceImple
 * @author www.inxedu.com
 */
@Service("trxHessianService")
public class TrxHessianServiceImple implements TrxHessianService {

    private Logger logger = LoggerFactory.getLogger(TrxHessianServiceImple.class);
    @Autowired
    private OrderService trxorderService;
    @Autowired(required=false)
    private UserAccountService userAccountService;

    /**
     * 订单支付成功回调操作, 保证事务的一致性！！重要
     * 
     * @param sourceMap
     * @return
     */
    public Map<String, String> noTrxTrxOrderComplete(Map<String, String> sourceMap) {
        Map<String, String> res = new HashMap<String, String>();
        try {
            logger.info("noTrxTrxOrderComplete param:"+sourceMap);
            Date date = new Date();
            String total_fee = sourceMap.get("total_fee");//交易金额,外界充值的金额，可能为0
            String requestId = sourceMap.get("requestId");//请求订单号，系统内的
            String userId= sourceMap.get("userId");//用户id
            String payType=sourceMap.get("payType");
            String out_trade_no=sourceMap.get("out_trade_no");//提交到支付宝的请求号
            //查询订单
            Order trxorder = trxorderService.getTrxorderByRequestId(requestId.split("#")[0]);
            TrxReqData trxReqData = new TrxReqData();
            trxReqData.setBankAmount(new BigDecimal(total_fee));
            trxReqData.setRequestId(requestId);
            trxReqData.setCreateTime(date);

            trxReqData.setPayType(Enum.valueOf(PayType.class, payType.trim()));

            trxReqData.setUserId(Long.valueOf(userId));
            trxReqData.setOut_trade_no(out_trade_no);
            trxReqData.setTrxorderId(Long.valueOf(trxorder.getOrderId()+""));
            // 先充值，事务1
            try{
                if (trxReqData.getBankAmount().doubleValue()>0){
                    if(/*"ON".equals(CacheConstans.ACCOUNT_IS_OPEN) &&*/ ObjectUtils.isNotNull(userAccountService) ){
                        UserAccount userAccount =  userAccountService.getUserAccountByUserId(Long.valueOf(trxorder.getUserId()));
                        userAccountService.credit(userAccount, trxReqData.getBankAmount(), AccountType.CASH, AccountHistoryType.CASHLOAD, Long.valueOf(trxorder.getUserId()),Long.valueOf(trxorder.getOrderId()), trxorder.getOrderNo(),
                                trxorder.getOutTradeNo(),  new Date(), "支付充值", true, AccountBizType.CASH);//充值订单
                    }
                    res.put(OrderConstans.BANKCODE, OrderConstans.SUCCESS);//充值成功给银行返回成功信息
                }


            }catch (Exception e){
                e.printStackTrace();
            }
            if (ObjectUtils.isNotNull(trxorder)) {
                if(TrxOrderStatus.SUCCESS.toString().equals( trxorder.getStates())){
                    res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
                    res.put(OrderConstans.RESMSG, "订单已经支付成功");
                    return res;
                }
                if(!TrxOrderStatus.INIT.toString().equals( trxorder.getStates())){
                    res.put(OrderConstans.RESCODE, OrderConstans.SUCCESS);
                    res.put(OrderConstans.RESMSG, "订单状态异常");
                    return res;
                }
                trxReqData.setAmount(trxorder.getSumMoney());
                trxReqData.setTrxorderId(Long.valueOf(trxorder.getOrderId()+""));
                //订单的正常操作，修改订单状态为成功+扣除账户余额
                try {
                    //事务2,账户去支付订单
                    Map<String, String> resOrder= trxorderService.updateCompleteOrder(trxReqData);
                    res.put(OrderConstans.RESCODE,resOrder.get(OrderConstans.RESCODE));
        			res.put("amount", resOrder.get("amount"));
        			res.put("balance", resOrder.get("balance"));
        			res.put("bankAmount", resOrder.get("bankAmount"));
        			res.put("requestId", resOrder.get("requestId"));
                    if(resOrder.get(OrderConstans.RESCODE).equals(OrderConstans.SUCCESS)){
                        res.put(OrderConstans.RESMSG, "订单支付成功！");
                    }else if (resOrder.get(OrderConstans.RESCODE).equals("balance")){//余额不足。
                        if(trxReqData.getBankAmount().doubleValue()>0){
                            res.put(OrderConstans.RESMSG, "订单支付失败，本次交易金额已经充值到您的账户中，请注意查看，请稍后再试");
                        }else{
                            res.put(OrderConstans.RESMSG, "订单支付失败，请稍后再试");
                        }
                    }else{//优惠券编码异常。
                    	if(trxReqData.getBankAmount().doubleValue()>0){
                            res.put(OrderConstans.RESMSG, "订单支付失败，"+res.get(OrderConstans.RESCODE)+"，本次交易金额已经充值到您的账户中，请注意查看");
                        }else{
                            res.put(OrderConstans.RESMSG, "订单支付失败，"+res.get(OrderConstans.RESCODE)+"！");
                        }
                    }
                } catch (Exception e) {
                    logger.error("updNoTrxTrxOrderComplete.trxorderService",e);
                }
            } else {
                res.put(OrderConstans.RESCODE, "ordernull");
                res.put(OrderConstans.RESMSG, "订单信息异常，请稍后再试"); 
                logger.info("updNoTrxTrxOrderComplete order is null");
            }

        } catch (Exception e) {
            res.put(OrderConstans.RESCODE, "error");
            res.put(OrderConstans.RESMSG, "操作异常，请稍后再试！");
            logger.error("updNoTrxTrxOrderComplete error", e);
        }
        return res;
    }
}
