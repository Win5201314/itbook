package com.inxedu.os.edu.constants.enums.order;

/**
 * @ClassName  TrxOrderStatus
 * @author www.inxedu.com
 */
public enum TrxOrderStatus {
    INIT,//未支付,初始化
    SUCCESS,//支付成功
    REFUND,//退款
    CANCEL//取消cancel
}
