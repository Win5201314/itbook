package com.inxedu.os.edu.controller.member;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.edu.entity.member.*;
import com.inxedu.os.edu.entity.order.TrxorderDetail;
import com.inxedu.os.edu.service.member.MemberRecordService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.order.TrxorderDetailService;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * MemberSale管理接口 User: qinggang.liu Date: 2014-09-26
 */
@Controller
@RequestMapping("/admin")
public class AdminMemberSaleController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdminMemberSaleController.class);
	@Autowired
	private MemberTypeService memberTypeService;
	@Autowired
	private MemberSaleService memberSaleService;

	private static final String getMemberSales = getViewPath("/admin/member/member_sale_list");
	private static final String addMemberSale = getViewPath("/admin/member/member_sale_add");
	private static final String updateMemberSale = getViewPath("/admin/member/member_sale_update");

	private static final String getMemberRecords = getViewPath("/admin/member/member_record_list");
	private static final String getMemberRecord = getViewPath("/admin/member/member_order_opt_info");// 返回到开通详情页
	
	@Autowired
	private MemberRecordService memberRecordService;
	@Autowired
	private TrxorderDetailService trxorderDetailService;
	// 创建群 绑定变量名字和属性，把参数封装到类
	@InitBinder("memberSale")
	public void initBindermemberSale(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("memberSale.");
	}

	@InitBinder("memberSaleDTO")
	public void initBinderMemberSaleDTO(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("memberSaleDTO.");
	}
    @InitBinder("memberRecordDTO")
    public void initBinderMemberRecordDTO(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("memberRecordDTO.");
    }
	@InitBinder("queryMemberSale")
	public void initBinderQueryMemberSale(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("queryMemberSale.");
	}

	/**
	 * 会员商品列表
	 *
	 * @param request
	 * @param queryMemberSale
	 * @param page
	 * @return
	 */
	@RequestMapping("/membersale/list")
	public ModelAndView getMemberSales(HttpServletRequest request, QueryMemberSale queryMemberSale,
			@ModelAttribute("page") PageEntity page) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getMemberSales);
		try {
			List<MemberSaleDTO> memberSaleList = memberSaleService.getMemberSalePage(queryMemberSale, page);
			modelAndView.addObject("memberSaleList", memberSaleList);
			List<MemberType> memberTypes = memberTypeService.getMemberTypes();
			modelAndView.addObject("memberTypes", memberTypes);
			modelAndView.addObject("page", page);
		} catch (Exception e) {
			logger.error("AdminMemberSaleController.getMemberSales--会员商品列表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 跳转更新会员商品
	 *
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/membersale/doupdate/{id}")
	public ModelAndView doUpdateMemberSale(HttpServletRequest request, @PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(updateMemberSale);
		try {
			List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
			MemberSale memberSale = memberSaleService.getMemberSaleById(id);
			modelAndView.addObject("memberSale", memberSale);
			modelAndView.addObject("memberTypes", memberTypes);
		} catch (Exception e) {
			logger.error("AdminMemberSaleController.doUpdateMemberSale--跳转更新会员商品出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 更新会员商品
	 *
	 * @param request
	 * @param memberSale
	 * @return
	 */
	@RequestMapping("/membersale/update")
	public String updateMemberSale(HttpServletRequest request, MemberSale memberSale) {
		try {
			memberSaleService.updateMemberSale(memberSale);
		} catch (Exception e) {
			logger.error("AdminMemberSaleController.updateMemberSale--更新会员商品出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/membersale/list.json";
	}

	/**
	 * 跳转添加会员商品
	 *
	 * @return
	 */
	@RequestMapping("/membersale/doadd")
	public ModelAndView doAddMemberSale(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(addMemberSale);
		try {
			List<MemberType> memberTypes = memberTypeService.getWebMemberTypes();
			modelAndView.addObject("memberTypes", memberTypes);
		} catch (Exception e) {
			logger.error("AdminMemberSaleController.doAddMemberSale--跳转添加会员商品出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}

	/**
	 * 添加会员商品
	 *
	 * @return
	 */
	@RequestMapping("/membersale/add")
	public String addMemberSale(HttpServletRequest request, MemberSale memberSale) {
		try {
			memberSaleService.addMemberSale(memberSale);
		} catch (Exception e) {
			logger.error("AdminMemberSaleController.addMemberSale--添加会员商品出错", e);
			return setExceptionRequest(request, e);
		}
		return "redirect:/admin/membersale/list.json";
	}

	/**
	 * 删除会员商品
	 *
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping("/membersale/del")
	@ResponseBody
	public Map<String, Object> delMemberSale(HttpServletRequest request, @RequestParam("ids") String ids) {
		Map<String, Object> json = null;
		try {
			memberSaleService.delMemberSale(ids);
			json = this.setJson(true, "操作成功！", null);
        } catch (Exception e) {
			logger.error("AdminMemberSaleController.addMemberSale--删除会员商品出错", e);
			json = this.setJson(false, "系统繁忙,请稍后再试！", null);
		}
		return json;
	}

	/**
	 * 会员记录列表
	 * @param memberRecordDTO
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping("/memberrecord/memberrecords")
	public ModelAndView getMemberRecords(@ModelAttribute("memberRecordDTO")MemberRecordDTO memberRecordDTO, @ModelAttribute("page") PageEntity page,
										 HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getMemberRecords);
		try {
			List<MemberRecordDTO> memberRecordDTOs = memberRecordService.getMemberRecordPage(memberRecordDTO, page);
			modelAndView.addObject("memberRecordDTOs", memberRecordDTOs);
			modelAndView.addObject("page", page);
			List<MemberType> memberTypes = memberTypeService.getMemberTypes();
			modelAndView.addObject("memberTypes", memberTypes);
		} catch (Exception e) {
			logger.error("AdminMemberSaleController.getMemberRecords--会员记录列表出错", e);
			return new ModelAndView(setExceptionRequest(request, e));
		}
		return modelAndView;
	}
	/**
	 * 关闭会员
	 */
	@RequestMapping("/memberrecord/close")
	@ResponseBody
	public Map<String,Object> close(HttpServletRequest request) {
		Map<String,Object> json = new HashedMap();
		try {
			/*订单id*/
			String trxorderId = request.getParameter("trxorderId");
			/*订单状态*/
			String authStatus =  request.getParameter("authStatus");
			TrxorderDetail trxorderDetail = trxorderDetailService.getTrxorderDetailById(Long.parseLong(trxorderId));
			/*会员销售类型*/
			MemberSale memberSale = memberSaleService.getMemberSaleById(trxorderDetail.getCourseId());
			MemberRecordDTO memberRecordDTO = new MemberRecordDTO();
			memberRecordDTO.setUserId(trxorderDetail.getUserId());
			memberRecordDTO.setMemberType(memberSale.getType());
			/*查询该类型的最大结束时间的流水记录*/
			MemberRecordDTO memberRecordDTO1 = memberRecordService.getUserMember(memberRecordDTO);

			if("CLOSED".equals(authStatus)&&trxorderDetail.getId().intValue()!=memberRecordDTO1.getId().intValue()){
				json = this.setJson(false,"只能关闭最大到期时间的充值记录!",null);
				return json;
			}

			trxorderDetail.setAuthStatus(authStatus);
			trxorderDetailService.updateTrxorderDetail(trxorderDetail);
			json = this.setJson(true,"",null);
		} catch (Exception e) {
			logger.error("AdminMemberSaleController.close--更改会员类型状态出错", e);
			json = this.setJson(false, "error", null);
		}
		return json;
	}
	/**
	 *会员延期
	 */
	@RequestMapping("/memberrecord/updatemrecord")
	@ResponseBody
	public Map<String,Object> updatemrecord(HttpServletRequest request) {
		Map<String,Object> json = new HashedMap();
		try {
			/*流水id*/
			String trxorderId = request.getParameter("trxorderId");
			/*到期时间*/
			String authTime = request.getParameter("authTime");
			TrxorderDetail trxorderDetail = trxorderDetailService.getTrxorderDetailById(Long.parseLong(trxorderId));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date authDate = simpleDateFormat.parse(authTime);
			trxorderDetail.setAuthTime(authDate);
			trxorderDetailService.updateTrxorderDetail(trxorderDetail);
			json = this.setJson(true,"",null);
		} catch (Exception e) {
			logger.error("AdminMemberSaleController.close--更改会员类型状态出错", e);
			json = this.setJson(false, "error", null);
		}
		return json;
	}
	/**
     * 获得会员开通记录详情
     *
     * @param request
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/member/mrecordinfo/{id}")
    public String getMemberRecord(HttpServletRequest request, Model model, @PathVariable Long id) {
        try {
            // 查询开通记录详情
            MemberRecordDTO memberRecordDTO = memberRecordService.getMemberRecordInfo(id);
            model.addAttribute("memberRecordDTO", memberRecordDTO);
        } catch (Exception e) {
            logger.error("getMemberRecord", e);
            return setExceptionRequest(request, e);
        }
        return getMemberRecord;
    }

}