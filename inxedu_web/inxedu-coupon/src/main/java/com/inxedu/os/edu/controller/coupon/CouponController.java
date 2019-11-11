package com.inxedu.os.edu.controller.coupon;

import com.inxedu.os.common.constants.CacheConstans;
import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.ObjectUtils;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.common.util.StringUtils;
import com.inxedu.os.edu.entity.coupon.Coupon;
import com.inxedu.os.edu.entity.coupon.CouponCode;
import com.inxedu.os.edu.entity.coupon.CouponCodeDTO;
import com.inxedu.os.edu.entity.coupon.QueryCouponCode;
import com.inxedu.os.edu.service.coupon.CouponCodeService;
import com.inxedu.os.edu.service.coupon.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Coupon 优惠券 管理接口
 * @author www.inxedu.com
 */
@Controller
public class CouponController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

    private String myCouPon = getViewPath("/web/coupon/uc_youhuiquan");//我的优惠券列表
    //访问受限
    private static String visitLimit = getViewPath("/web/pay/visit-limit");

    @Autowired(required = false)
    private CouponCodeService couponCodeService;
    @Autowired
    private CouponService couponService;
    // 绑定变量名参数
    @InitBinder("queryCouponCode")
    public void initBinderQueryCouponCode(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCouponCode.");
    }

    /**
     * 验证优惠券的限制范围
     */
    @RequestMapping("/coupon/check")
    @ResponseBody
    public Map<String, Object> checkCouponByCode(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            if("OFF".equals(CacheConstans.COUPON_IS_OPEN) || ObjectUtils.isNull(couponCodeService)){
                json = this.setJson(false, "您暂时未购买【优惠券】功能，无法使用", null);
                return json;
            }
            json = couponCodeService.checkCouponByCode(request);

        } catch (Exception e) {
            logger.error("CouponController.checkCouponByCode--验证优惠券出错", e);
            json = this.setJson(false, "参数错误！", null);
        }
        return json;
    }

    /**
     * 个人中心 我的优惠券
     */
    @RequestMapping("/uc/myCouPon")
    public String myCouPon(HttpServletRequest request, @ModelAttribute("queryCouponCode") QueryCouponCode queryCouponCode, @ModelAttribute("page") PageEntity page) {
        try {
            if("OFF".equals(CacheConstans.COUPON_IS_OPEN) || ObjectUtils.isNull(couponCodeService)){
                request.setAttribute("msg","您暂未购买【优惠券】功能，无法使用");
                return visitLimit;
            }
            // 设置分页参数
            page.setPageSize(6);
            //查询我的优惠券
            queryCouponCode.setUserId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));
            List<CouponCodeDTO> couponList = couponCodeService.queryCouponCodePageByUser(queryCouponCode, page);
            request.setAttribute("couponList", couponList);
            request.setAttribute("status", queryCouponCode.getStatus());
        } catch (Exception e) {
            logger.error("CouponController.myCouPon()---error", e);
            setExceptionRequest(request, e);
        }
        return myCouPon;
    }



    /**
     * 兑换优惠券
     */
    @RequestMapping("/coupon/couponActive")
    @ResponseBody
    public Map<String, Object> couponActive(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            /*获取输入的优惠券编码*/
            String couponcode = request.getParameter("couponCode");
            if(StringUtils.isEmpty(couponcode)){
                json = this.setJson(false,"请输入优惠券编码",null);
                return json;
            }
            CouponCodeDTO couponCodeDTO= couponCodeService.getCouponCodeByCode(couponcode);
            if(ObjectUtils.isNull(couponCodeDTO)){
                json = this.setJson(false,"请输入正确的优惠券编码",null);
                return json;
            }
            Coupon coupon = couponService.getCouponById(couponCodeDTO.getCouponId());
            if(coupon.getUseType()==1){
                json = this.setJson(false,"在有效期内可以被任意多人使用多次的优惠券，不支持兑换",null);
                return json;
            }
            /*如果优惠券 未使用、没过期、没有用户*/
            if (couponCodeDTO.getStatus()==1&&coupon.getEndTime().after(new Date())&&couponCodeDTO.getUserId()==0){
                /*把优惠券的用户设置为当前登陆用户*/
                int userId = SingletonLoginUtils.getLoginUserId(request);
                /*更新优惠券*/
                CouponCode couponCode = couponCodeService.getCouponCodeById(couponCodeDTO.getId());
                couponCode.setUserId(new Long(userId));
                couponCodeService.updateCouponCode(couponCode);
            }else if (!coupon.getEndTime().after(new Date())){
                json = this.setJson(false,"优惠券已过期",null);
                return json;
            }else if (couponCodeDTO.getStatus()!=1||couponCodeDTO.getUserId()!=0){
                json = this.setJson(false,"已经使用的优惠券",null);
                return json;
            }
            json = this.setJson(true,"",null);
        } catch (Exception e) {
            logger.error("CouponController.couponActive--兑换优惠券出错", e);
            json = this.setJson(false, "参数错误！", null);
        }
        return json;
    }
}