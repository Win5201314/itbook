package com.inxedu.os.edu.controller.orderdetail;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.edu.entity.order.TrxorderDetail;
import com.inxedu.os.edu.service.order.TrxorderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author www.inxedu.com
 *
 */
@Controller
@RequestMapping("front/trxorderDetail")
public class UcOrderDetailController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(UcOrderDetailController.class);

	@Autowired
	private TrxorderDetailService trxorderDetailService;
	@InitBinder({"order"})
	public void initBinderorder(WebDataBinder binder){
		binder.setFieldDefaultPrefix("order.");
	}
	@InitBinder({"queryOrder"})
	public void initBinderQueryOrder(WebDataBinder binder){
		binder.setFieldDefaultPrefix("queryOrder.");
	}
	@InitBinder({"queryTrxorderDetail"})
	public void initBinderTrxorderDetail(WebDataBinder binder){
		binder.setFieldDefaultPrefix("queryTrxorderDetail.");
	}
	/**
	 * 前台删除课程 （隐藏订单流水）
	 */
	@RequestMapping("/delTrxorder")
	@ResponseBody
	public Map<String, Object> delTrxorder( HttpServletRequest request) {
		Map<String, Object> json = null;
		try {
			Long detailId = Long.parseLong(request.getParameter("detailId"));
			TrxorderDetail trxorderDetail = new TrxorderDetail();
			trxorderDetail = trxorderDetailService.getTrxorderDetailById(detailId);
			trxorderDetail.setDelStatus("DELETE");
			trxorderDetailService.updateTrxorderDetail(trxorderDetail);
			json = this.setJson(true, "success", null);
		} catch (Exception e) {
			logger.error("UcOrderDetailController.delTrxorder", e);
			json = this.setJson(false, "error", null);
		}
		return json;
	}

}
