package com.inxedu.os.edu.controller.orderdetail;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.FileExportImportUtil;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.edu.entity.order.QueryTrxorderDetail;
import com.inxedu.os.edu.service.order.TrxorderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author www.inxedu.com
 *
 */
@Controller
@RequestMapping("/admin/orderDetial")
public class AdminOrderDetailController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(AdminOrderDetailController.class);

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
	 * 显示订单课程列表
	 */
	@RequestMapping("/list")
	public ModelAndView showList(HttpServletRequest request, @ModelAttribute("queryTrxorderDetail") QueryTrxorderDetail queryTrxorderDetail, @ModelAttribute("page") PageEntity page){
		ModelAndView model = new ModelAndView();
		try{
			page.setPageSize(10);
			model.setViewName(getViewPath("/admin/orderdetail/trxorderDetail-list"));
			List<QueryTrxorderDetail> trxorderDetailList = trxorderDetailService.queryTrxorderDetailByOrder(queryTrxorderDetail,page);
			model.addObject("trxorderDetailList", trxorderDetailList);
			model.addObject("page", page);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("showList()--error",e);
		}
		return model;
	}
	@RequestMapping("/trxorderDetailInfo/{trxorderDetailId}")
	public ModelAndView showTrxorderDetailInfo(HttpServletRequest request, @ModelAttribute("trxorderDetailId") long trxorderDetailId) {
		ModelAndView model = new ModelAndView();
		try{
			model.setViewName(getViewPath("/admin/orderdetail/trxorderDetail_info"));
			QueryTrxorderDetail queryTrxorderDetail = new QueryTrxorderDetail();
			queryTrxorderDetail = trxorderDetailService.queryQueryTrxorderDetailById(trxorderDetailId);
			model.addObject("queryTrxorderDetail",queryTrxorderDetail);
		}catch (Exception e) {
			model.setViewName(this.setExceptionRequest(request, e));
			logger.error("showTrxorderDetailInfo()--error",e);
		}
		return model;
	}
	@RequestMapping("/trxorderExcel")
	public void trxorderExcel(HttpServletRequest request,HttpServletResponse response, @ModelAttribute("queryTrxorderDetail") QueryTrxorderDetail queryTrxorderDetail, @ModelAttribute("page") PageEntity page) {
		try {
			//指定文件生成路径
			String dir = request.getSession().getServletContext().getRealPath("/")+"/excelfile/trxorder";
			//文件名
			String expName = "订单课程列表_" + DateUtils.getStringDateShort();
			//表头信息
			String[] headName = { "订单号","学员邮箱","学员手机","课程名","金额","创建时间","状态","支付时间"};
			page = new PageEntity();
			//拆分为一万条数据每Excel，防止内存使用太大
			page.setPageSize(10000);
			trxorderDetailService.queryTrxorderDetailByOrder(queryTrxorderDetail,page);
			int num = page.getTotalPageSize();//总页数
			List<File> srcFile = new ArrayList<File>();
			for (int i=1;i<=num;i++){
				page.setCurrentPage(i);
				List<QueryTrxorderDetail> queryTrxorderDetailList = trxorderDetailService.queryTrxorderDetailByOrder(queryTrxorderDetail,page);
				List<List<String>> info = dataJoint(queryTrxorderDetailList);
				File file = FileExportImportUtil.createExcel(headName, info, expName+"_"+i,dir);
				srcFile.add(file);
			}
			FileExportImportUtil.createRar(response, dir, srcFile, expName);//生成的多excel的压缩包

		}catch (Exception e) {
			logger.error("trxorderExcel()--error",e);
		}
	}
	//流水信息excel格式拼接
	public List<List<String>> dataJoint(List<QueryTrxorderDetail> queryTrxorderDetailList) {
		List<List<String>> list = new ArrayList<>();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < queryTrxorderDetailList.size(); i++) {
			List<String> info = new ArrayList<>();
			info.add(queryTrxorderDetailList.get(i).getOrderNo());
			info.add(queryTrxorderDetailList.get(i).getEmail());
			info.add(queryTrxorderDetailList.get(i).getMobile());
			info.add(queryTrxorderDetailList.get(i).getCourseName());
			info.add(queryTrxorderDetailList.get(i).getCurrentPrice().toString());
			info.add(format.format(queryTrxorderDetailList.get(i).getCreateTime()));
			if ("INIT".equals(queryTrxorderDetailList.get(i).getTrxStatus())) {
				info.add("未支付");
			} else if ("SUCCESS".equals(queryTrxorderDetailList.get(i).getTrxStatus())) {
				info.add("已支付");
			} else if ("REFUND".equals(queryTrxorderDetailList.get(i).getTrxStatus())) {
				info.add("退款");
			} else if ("CANCEL".equals(queryTrxorderDetailList.get(i).getTrxStatus())) {
				info.add("已取消");
			} else if ("CLOSED".equals(queryTrxorderDetailList.get(i).getTrxStatus())) {
				info.add("已关闭");
			}
			if (ObjectUtils.isNotNull(queryTrxorderDetailList.get(i).getPayTime())) {
				info.add(format.format(queryTrxorderDetailList.get(i).getPayTime()));
			} else {
				info.add("--");
			}
			list.add(info);
		}
		return list;
	}

}
