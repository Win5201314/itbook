package com.inxedu.os.edu.service.order;

import java.util.Map;

/**
 * @ClassName  TrxHessianService
 * @author www.inxedu.com
 */
public interface TrxHessianService {
    /**
     * 订单支付成功回调操作
     * @param sourceMap
     * @return
     */
    Map<String,String> noTrxTrxOrderComplete(Map<String, String> sourceMap);
}
