package com.inxedu.os.edu.controller.member;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.member.MemberRecordDTO;
import com.inxedu.os.edu.entity.member.MemberSale;
import com.inxedu.os.edu.entity.member.MemberSaleDTO;
import com.inxedu.os.edu.entity.member.MemberType;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.member.MemberRecordService;
import com.inxedu.os.edu.service.member.MemberSaleService;
import com.inxedu.os.edu.service.member.MemberTypeService;
import com.inxedu.os.edu.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * MemberController管理接口
 */
@Controller
public class MemberController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private MemberRecordService memberRecordService;
    @Autowired
    private MemberSaleService memberSaleService;

    /**
     * 会员
     */
    @RequestMapping("/uc/associator")
    public ModelAndView associator(HttpServletRequest request, @ModelAttribute("page") PageEntity page){
        ModelAndView model = new ModelAndView(getViewPath("/web/ucenter/user-associator"));
        try{
            page.setPageSize(10);
            User user = userService.queryUserById(SingletonLoginUtils.getLoginUserId(request));
			/*查询开通会员的记录*/
            List<MemberRecordDTO> memberRecordDTOs = memberRecordService.getMemberRecordByUser(new Long(user.getUserId()),page);
			/*计算开通会员的月数*/
            if(ObjectUtils.isNotNull(memberRecordDTOs)){
                for (MemberRecordDTO memberRecord : memberRecordDTOs) {
                    Calendar c1 = Calendar.getInstance();
                    Calendar c2 = Calendar.getInstance();
                    c1.setTime(memberRecord.getBeginDate());
                    c2.setTime(memberRecord.getEndDate());
                    int result = (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR)) * 12 + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
                    memberRecord.setMonth(Math.abs(result));
                }
            }

			/*查询所有会员类型当前时间的开通情况*/
            List<MemberRecordDTO> memberRecordDTOList = memberRecordService.userMemberInfo(new Long(SingletonLoginUtils.getLoginUserId(request)));
			/*会员类型*/
            List<MemberType> memberTypeList = memberTypeService.getMemberTypes();
			/*如果有会员*/
            if (ObjectUtils.isNotNull(memberRecordDTOList)){
				/*给拥有的会员类型set endTime*/
                for(int i=0;i<memberRecordDTOList.size();i++){
                    MemberRecordDTO memberRecordDTO = memberRecordDTOList.get(i);
                    memberTypeList.get(Integer.parseInt(memberRecordDTO.getMemberType()+"")-1).setEndTime(memberRecordDTO.getEndDate());
                }
            }

			/*每类会员一个月的商品信息*/
            List<MemberSale> memberSaleList = new ArrayList<>();
			/*根据会员类型获取一个月的商品信息*/
            for (int i=0;i<memberTypeList.size();i++){
                MemberSaleDTO memberSaleDTO = new MemberSaleDTO();
                memberSaleDTO.setType(memberTypeList.get(i).getId());
                memberSaleDTO.setDays(1);
                MemberSale memberSale = memberSaleService.getMemberSale(memberSaleDTO);
                memberSaleList.add(memberSale);
            }
            model.addObject("memberSaleList",memberSaleList);
            model.addObject("memberTypeList",memberTypeList);
            model.addObject("memberRecordDTOList",memberRecordDTOList);
            model.addObject("user", user);
            model.addObject("memberRecordDTOs",memberRecordDTOs);
        }catch (Exception e) {
            model.setViewName(this.setExceptionRequest(request, e));
            logger.error("associator()---error",e);
        }
        return model;
    }
}