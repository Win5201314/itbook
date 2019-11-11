package com.inxedu.os.edu.controller.coupon;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.common.util.*;
import com.inxedu.os.edu.constants.enums.MsgRemindStatus;
import com.inxedu.os.edu.entity.coupon.*;
import com.inxedu.os.edu.entity.course.CourseDto;
import com.inxedu.os.edu.entity.course.QueryCourse;
import com.inxedu.os.edu.entity.subject.QuerySubject;
import com.inxedu.os.edu.entity.subject.Subject;
import com.inxedu.os.edu.entity.user.QueryUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.coupon.CouponCodeService;
import com.inxedu.os.edu.service.coupon.CouponService;
import com.inxedu.os.edu.service.course.CourseService;
import com.inxedu.os.edu.service.letter.MsgReceiveService;
import com.inxedu.os.edu.service.subject.SubjectService;
import com.inxedu.os.edu.service.user.UserService;
import org.apache.commons.httpclient.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Coupon 优惠券 后台管理接口
 * @author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminCouponController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdminCouponController.class);
    @Autowired(required = false)
    private CouponService couponService;
    @Autowired
    private SubjectService subjectService;
    @Autowired(required = false)
    private CouponCodeService couponCodeService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private MsgReceiveService msgReceiveService;

    private static final String addCoupon = getViewPath("/admin/coupon/Coupon_add");//优惠券添加
    private static final String couponDetail = getViewPath("/admin/coupon/Coupon_detail");//查看优惠券
    private static final String couponCodeDetail = getViewPath("/admin/coupon/CouponCode_detail");//查看优惠券编码
    private static final String getUserCoupons = getViewPath("/admin/coupon/couponCode_user_list");//赠送用户优惠券列表
    private static final String giveCouponBatch = getViewPath("/admin/coupon/give_coupon_batch");//批量赠送
    private static final String showCouponCourseList = getViewPath("/admin/coupon/course_coupon_list");// 课程列表(优惠券限制课程)
    private static final String toSelectCouponUserList = getViewPath("/admin/coupon/select_coupon_user_list");// 优惠券批量赠送 查询学员列表

    // 创建群 绑定变量名字和属性，把参数封装到类
    @InitBinder("coupon")
    public void initBinderCoupon(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("coupon.");
    }

    @InitBinder("queryCoupon")
    public void initBinderQueryCoupon(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCoupon.");
    }

    @InitBinder("couponCode")
    public void initBinderCouponCode(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("couponCode.");
    }

    @InitBinder("queryCouponCode")
    public void initBinderQueryCouponCode(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCouponCode.");
    }
    @InitBinder("queryCourse")
    public void initBinderQueryCourse(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCourse.");
    }
    @InitBinder("user")
    public void initBinderUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    /**
     * 添加优惠券
     *
     * @param coupon
     * @param request
     * @return
     */
    @RequestMapping("/coupon/add")
    @SystemLog(type="add",operation="添加优惠券")
    public String addCoupon(Coupon coupon, HttpServletRequest request) {
        try {
            if (coupon != null) {
                coupon.setCreateTime(new Date());
                coupon.setOptuserName(SingletonLoginUtils.getLoginSysUser(request).getUserName());
                if (coupon.getUseType() == 1) {// 适用范围为所有人只生成一张优惠券编码
                    coupon.setTotalNum(1L);
                }
                couponService.addCoupon(coupon);// 添加优惠券
                String courseIds = request.getParameter("limitCourseId");
                if (courseIds != null && courseIds.trim() != "") {// 添加课程限制
                    List<CouponLimit> couponLimits = new ArrayList<CouponLimit>();
                    String[] courseArry = courseIds.replace(",", " ").trim().split(" ");
                    for (int i = 0; i < courseArry.length; i++) {
                        CouponLimit couponLimit = new CouponLimit();
                        couponLimit.setCouponId(coupon.getId());
                        couponLimit.setCourseId(Long.parseLong(courseArry[i]));
                        couponLimits.add(couponLimit);
                    }
                    if (couponLimits.size() > 0) {
                        couponService.addCouponLimitCourse(couponLimits);// 添加优惠券课程限制
                    }
                }
                // 生成数量
                Long totalNum = coupon.getTotalNum();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String createTime = sdf.format(new Date());
                StringBuffer val = new StringBuffer();
                /*拼优惠券编码*/
                for (int j = 0; j < totalNum; j++) {
                    SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    String code = sd.format(new Date());
                    code = handleStrValue(code + j);
                    code = code.substring(0, 20);
                    // 拼数据
                    val.append("(" + coupon.getId() + ",");
                    val.append("1,");
                    val.append(0 + ",");
                    val.append("'',");
                    val.append(0 + ",");
                    val.append("'" + code + "',");
                    val.append("'" + createTime + "',");
                    val.append("'" + MsgRemindStatus.INIT.toString() + "')");
                    if (j != totalNum - 1) {
                        val.append(",");
                    }
                }
                couponCodeService.addCouponCode(val);// 生成优惠券编码
            }
        } catch (Exception e) {
            logger.error("AdminCouponController.addCoupon--添加优惠券出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/coupon/page";
    }

    /**
     * 跳转添加优惠券
     *
     * @param request
     * @return
     */
    @RequestMapping("/coupon/doadd")
    public ModelAndView doAddCoupon(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(addCoupon);
        try {
            // 查询专业
            QuerySubject querySubject = new QuerySubject();
            List<Subject> subjectList = subjectService.getSubjectList(querySubject);
            request.setAttribute("subjectList", FastJsonUtil.obj2JsonString(subjectList));
        } catch (Exception e) {
            logger.error("AdminCouponController.doAddCoupon--跳转添加优惠券出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 查看优惠券
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/coupon/detail/{id}")
    public ModelAndView couponDetail(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(couponDetail);
        try {
            Coupon coupon = couponService.getCouponDTOById(id);
            modelAndView.addObject("couponDTO", coupon);
        } catch (Exception e) {
            logger.error("AdminCouponController.couponDetail--查看优惠券出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 优惠券分页列表
     */
    @RequestMapping("/coupon/page")
    public ModelAndView getCouponPage(QueryCoupon queryCoupon, @ModelAttribute("page") PageEntity page, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getViewPath("/admin/coupon/Coupon_list"));
        try {
            List<Coupon> couponDTOList = couponService.getCouponPage(queryCoupon, page);
            modelAndView.addObject("couponDTOList", couponDTOList);
            modelAndView.addObject("page", page);
            modelAndView.addObject("userCount", userService.queryAllUserCount());
        } catch (Exception e) {
            logger.error("AdminCouponController.getCouponPage--优惠券分页列表出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 生成优惠券编码
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/coupon/createcode/{id}")
    @SystemLog(type="add",operation="生成优惠券编码")
    public String createCoding(@PathVariable Long id, HttpServletRequest request) {
        try {
            // 优惠卷ID
            Coupon coupon = couponService.getCouponById(id);
            // 生成数量
            Long totalNum = coupon.getTotalNum();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = sdf.format(new Date());
            StringBuffer val = new StringBuffer();
            for (int j = 0; j < totalNum; j++) {
                SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String code = sd.format(new Date());
                code = handleStrValue(code + j);
                code = code.substring(0, 20);
                // 并数据
                val.append("(" + coupon.getId() + ",");
                val.append("1,");
                val.append(0 + ",");
                val.append("'',");
                val.append(0 + ",");
                val.append("'" + code + "',");
                val.append("'" + createTime + "',");
                val.append("'" + MsgRemindStatus.INIT.toString() + "')");
                if (j != totalNum - 1) {
                    val.append(",");
                }
            }
            couponCodeService.addCouponCode(val);// 生成优惠券编码

        } catch (Exception e) {
            logger.error("AdminCouponController.createCoding--生成优惠券编码出错", e);
            return setExceptionRequest(request, e);
        }
        return "redirect:/admin/coupon/detail/" + id + "#intro";
    }

    //生成优惠券编码
    private static String[] sts = new String[]{"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private static Random ran = new Random();

    private static String handleStrValue(String value) {
        StringBuffer buf = new StringBuffer(value);
        for (int i = 0; i < 4; i++) {
            String key = sts[ran.nextInt(26)];
            int offset = ran.nextInt(value.length() - 1);
            buf.insert(offset, key);
        }
        return buf.toString();
    }

    /**
     * 导出优惠券编码
     *
     * @param queryCouponCode
     * @param request
     * @param response
     */
    @RequestMapping("/coupon/exportcode")
    public void exportCouponCode(QueryCouponCode queryCouponCode, HttpServletRequest request, HttpServletResponse response) {
        try {
            // 指定文件生成路径
            String dir = request.getSession().getServletContext().getRealPath("/")+"/excelfile/couponCode";
            // 文件名
            String expName = "优惠券_" + DateUtils.getStringDateShort();
            // 表头信息
            String[] headName = {"优惠券名称", "优惠券编码", "次数限制", "使用开始时间","使用结束时间","状态"};
            // 拆分为一万条数据每Excel，防止内存使用太大
            PageEntity page = new PageEntity();
            page.setPageSize(10000);
            couponCodeService.getCouponCodePage(queryCouponCode, page);
            int num = page.getTotalPageSize();// 总页数
            List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
            for (int i = 1; i <= num; i++) {// 循环生成num个xls文件
                page.setCurrentPage(i);
                List<CouponCodeDTO> couponCodes = couponCodeService.getCouponCodePage(queryCouponCode, page);
                List<List<String>> list = couponCodeJoint(couponCodes);
                File file = FileExportImportUtil.createExcel(headName, list, expName + "_" + i, dir);
                srcfile.add(file);
            }
            FileExportImportUtil.createRar(response, dir, srcfile, expName);// 生成的多excel的压缩包
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 优惠券excel格式拼接
     *
     * @param couponCodes
     * @return
     */
    public List<List<String>>   couponCodeJoint(List<CouponCodeDTO> couponCodes) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < couponCodes.size(); i++) {
            CouponCodeDTO couponCodeDTO = couponCodes.get(i);
            List<String> small = new ArrayList<String>();
            //small.add(couponCodeDTO.getId().toString());
            small.add(couponCodeDTO.getTitle());
            small.add(couponCodeDTO.getCouponCode());
            if (couponCodeDTO.getUseType() == 1) {
                small.add("无限");
            }
            if (couponCodeDTO.getUseType() == 2) {
                small.add("正常");
            }
            small.add(DateUtil.formatDate(couponCodeDTO.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
            small.add(DateUtil.formatDate(couponCodeDTO.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
            if (couponCodeDTO.getStatus() == 1) {
                small.add("未使用");
            }
            if (couponCodeDTO.getStatus() == 2) {
                small.add("已使用");
            }
            if (couponCodeDTO.getStatus() == 3) {
                small.add("过期");
            }
            if (couponCodeDTO.getStatus() == 4) {
                small.add("作废");
            }
            list.add(small);
        }
        return list;
    }

    /**
     * 查看优惠编码
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/couponcode/detail/{id}")
    public ModelAndView couponCodeDetail(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(couponCodeDetail);
        try {
            CouponCode couponCode = couponCodeService.getCouponCodeById(id);
            Coupon coupon = couponService.getCouponDTOById(couponCode.getCouponId());
            modelAndView.addObject("couponCode", couponCode);
            modelAndView.addObject("couponDTO", coupon);
        } catch (Exception e) {
            logger.error("AdminCouponController.couponCodeDetail--查看优惠券编码出错", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 优惠券编码分页列表
     *
     * @param queryCouponCode
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/couponcode/page")
    public ModelAndView getCouponCodePage(QueryCouponCode queryCouponCode, @ModelAttribute("page") PageEntity page, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getViewPath("/admin/coupon/CouponCode_list"));//优惠券编码列表
        try {
            page.setPageSize(10);
            List<CouponCodeDTO> couponCodeDTOList = couponCodeService.getCouponCodePage(queryCouponCode, page);
            modelAndView.addObject("couponCodeDTOList", couponCodeDTOList);
            modelAndView.addObject("page", page);
        } catch (Exception e) {
            logger.error("AdminCouponController.getCouponPage--优惠券编码分页列表", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 优惠券编码分页列表 指派优惠券
     *
     * @param queryCouponCode
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/usercouponcode/page")
    public ModelAndView getUserCouponCodePage(QueryCouponCode queryCouponCode, @ModelAttribute("page") PageEntity page, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getUserCoupons);
        try {
            queryCouponCode.setEndTime(new Date());
            List<CouponCodeDTO> couponCodeDTOList = couponCodeService.getCouponCodePage(queryCouponCode, page);
            modelAndView.addObject("couponCodeDTOList", couponCodeDTOList);
        } catch (Exception e) {
            logger.error("AdminCouponController.getUserCouponCodePage--error", e);
            return new ModelAndView(setExceptionRequest(request, e));
        }
        return modelAndView;
    }

    /**
     * 优惠券指派用户
     *
     * @param request
     * @param couponCode
     * @return
     */
    @RequestMapping("/ajax/giveUserCouponCode")
    @ResponseBody
    @SystemLog(type="update",operation="优惠券指派用户")
    public Map<String, Object> giveUserCouponCode(HttpServletRequest request, @ModelAttribute("couponCode") CouponCode couponCode) {
        Map<String, Object> json = null;
        try {
            if (ObjectUtils.isNotNull(couponCode.getUserId())) {
                CouponCode couponCode1 = couponCodeService.getCouponCodeById(couponCode.getId());
                if (ObjectUtils.isNotNull(couponCode1.getUserId())) {
                    json = this.setJson(true, "优惠券已被指派！", null);
                    return json;
                }
                couponCodeService.updateCouponCodeUser(couponCode);
                json = this.setJson(true, "操作成功！", null);
            } else {
                json = this.setJson(false, "参数错误", null);
            }
        } catch (Exception e) {
            logger.error("AdminCouponController.giveUserCouponCode--error", e);
            json = this.setJson(false, "系统繁忙,请稍后再试", null);
        }
        return json;
    }

    /**
     * 作废优惠券下的未使用优惠编码
     *
     * @return
     */
    @RequestMapping("/coupon/wastecoupon/{id}")
    @ResponseBody
    @SystemLog(type="update",operation="作废优惠券下的未使用优惠编码")
    public Map<String, Object> wasteCodeByCouponId(@PathVariable Long id, HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            couponCodeService.wasteCodeByCouponId(id);

            Coupon coupon=couponService.getCouponById(id);
            json = this.setJson(true, "true", null);
        } catch (Exception e) {
            logger.error("AdminCouponController.wasteCouponCodel--作废优惠券下的未使用优惠编码", e);
            json = this.setJson(false, "error", null);
        }
        return json;
    }

    /**
     * 作废优惠编码
     *
     * @return
     */
    @RequestMapping("/couponcode/waste")
    @ResponseBody
    @SystemLog(type="update",operation="作废优惠编码")
    public Map<String, Object> wasteCouponCode(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            String ids = request.getParameter("ids");
            String status = request.getParameter("status");
            CouponCode couponCode = couponCodeService.getCouponCodeById(Long.parseLong(ids));
            couponCode.setStatus(Integer.parseInt(status));
            couponCodeService.updateCouponCode(couponCode);
            json = this.setJson(true, "true", null);
        } catch (Exception e) {
            logger.error("AdminCouponController.wasteCouponCodel--作废优惠券编码出错", e);
            json = this.setJson(false, "error", null);
        }
        return json;
    }

    /**
     * 批量赠送优惠券
     *
     * @param request
     * @param id      优惠券id
     * @return
     */
    @RequestMapping("/coupon/giveCouponBatch/{id}")
    public String giveCouponBatch(HttpServletRequest request, @PathVariable("id") Long id) {
        try {
            Coupon coupon = couponService.getCouponById(id);
            request.setAttribute("coupon", coupon);

            //查询未指派的优惠券
            CouponCode couponCode = new CouponCode();
            couponCode.setCouponId(id);
            couponCode.setStatus(1);
            couponCode.setUserId(-1l);
            List<CouponCode> courseCodeList = couponCodeService.getCouponCodeListByCouponId(couponCode);
            request.setAttribute("courseCodeList", courseCodeList);
            request.setAttribute("isOk", coupon.getEndTime().after(new Date()));

            //查询已经发出的优惠券
            couponCode = new CouponCode();
            couponCode.setCouponId(id);
            //couponCode.setStatus(1);
            couponCode.setUserId(-2l);
            courseCodeList = couponCodeService.getCouponCodeListByCouponId(couponCode);
            request.setAttribute("givedCouponCodeList", courseCodeList);
        } catch (Exception e) {
            logger.error("AdminCouponController.giveCouponBatch--error", e);
            return setExceptionRequest(request, e);
        }
        return giveCouponBatch;
    }

    /**
     * 批量赠送优惠券编码
     */
    @RequestMapping("/coupon/ajax/giveCouponCode")
    @ResponseBody
    @SystemLog(type="update",operation="批量赠送优惠券")
    public Map<String, Object> giveCouponCode(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("type") int type, @RequestParam("couponId") Long couponId) {
        Map<String, Object> json = null;
        try {
            //查询未指派的优惠券
            CouponCode couponCode = new CouponCode();
            couponCode.setCouponId(couponId);
            couponCode.setStatus(1);
            couponCode.setUserId(-1l);
            List<CouponCode> courseCodeList = couponCodeService.getCouponCodeListByCouponId(couponCode);
            if(type==1){
                QueryUser queryUser=new QueryUser();
                queryUser.setNameEmailMobile(username);
                List<User> userList=userService.queryUserList(queryUser);
                if(ObjectUtils.isNotNull(userList)){

                    if (ObjectUtils.isNotNull(courseCodeList)) {
                            Long userId = Long.valueOf(userList.get(0).getUserId());
                            couponCode.setUserId(userId);
                            couponCode.setId(courseCodeList.get(0).getId());
                            couponCodeService.updateCouponCodeUser(couponCode);

                            //发送站内信
                            msgReceiveService.sendMessage(userId.intValue(),"赠送优惠券提示" ,"giveCouponCode", true, courseCodeList.get(0).getCouponCode());
                    }else {
                        json = this.setJson(false, "赠送失败，没有未赠送优惠券！", null);
                        return json;
                    }

                }else{
                    json = this.setJson(false, "赠送失败，账户不存在！", null);
                    return json;
                }
            }else if(type==2){
                QueryUser queryUser=new QueryUser();
                List<User> userList=userService.queryUserList(queryUser);
                if(ObjectUtils.isNotNull(userList)){

                    if (ObjectUtils.isNotNull(courseCodeList)) {
                        if(courseCodeList.size()>=userList.size()){
                            for (int i = 0; i < userList.size(); i++) {
                                Long userId = Long.parseLong(userList.get(i).getUserId()+"");
                                couponCode.setUserId(userId);
                                couponCode.setId(courseCodeList.get(i).getId());
                                couponCodeService.updateCouponCodeUser(couponCode);

                                //发送站内信
                                msgReceiveService.sendMessage(userId.intValue(),"赠送优惠券提示" ,"giveCouponCode", true, courseCodeList.get(i).getCouponCode());
                            }
                        }else{
                            json = this.setJson(false, "赠送失败，未赠送优惠券不足以赠送给全部学员！", null);
                            return json;
                        }

                    }else {
                        json = this.setJson(false, "赠送失败，没有未赠送优惠券！", null);
                        return json;
                    }
                }else{
                    json = this.setJson(false, "赠送失败，用户不存在！", null);
                    return json;
                }
            }


            json = this.setJson(true, "赠送成功！", null);
        } catch (Exception e) {
            logger.error("AdminCouponController.giveCouponCode---error", e);
            json = this.setJson(false, "系统繁忙,请稍后再试！", null);
        }
        return json;
    }



    /**
     * 课程列表(优惠券限制课程用)
     *
     * @return
     */
    @RequestMapping("/cou/couponCourseList")
    public String showCourseListByCoupon(HttpServletRequest request, @ModelAttribute("page") PageEntity page, @ModelAttribute("queryCourse") QueryCourse queryCourse) {
        try {
            // 只查询上架的
            //queryCourse.setIsavaliable(1);
            // 搜索课程列表
            List<CourseDto> courseList = courseService.queryCourseListPage(queryCourse, page);
            request.setAttribute("courseList", courseList);
            request.setAttribute("page", page);
            request.setAttribute("course", queryCourse);
            /*List<WebsiteCourse> websiteCourses = websiteCourseService.queryWebsiteCourseList();
            request.setAttribute("websiteCourses", websiteCourses);*/
        } catch (Exception e) {
            logger.error("AdminCouponController.showCourseListByRecommend", e);
            return setExceptionRequest(request, e);
        }
        return showCouponCourseList;
    }

    /**
     * 批量赠送优惠券选择学员
     * @param request
     * @param user
     * @param page
     * @return
     */
    @RequestMapping("/user/selectCouponUserList")
    public String selectCouponUserList(HttpServletRequest request, @ModelAttribute("user") User user, @ModelAttribute("page") PageEntity page) {
        try {
            // 查询学员列表
            List<User> list = userService.getUserListAndCourse(user, page);
            request.setAttribute("list",list);
        } catch (Exception e) {
            logger.error("AdminCouponController.selectCouponUserList--error", e);
        }
        return toSelectCouponUserList;
    }

    /**
     * 根据id串查询学员列表
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping("/user/queryByIds")
    @ResponseBody
    public Object queryUserByIds(HttpServletRequest request, @ModelAttribute("ids") String ids) {
        Map<String, Object> json = null;
        try {
            String idArr[] = ids.split(",");
            List<Long> idList = new ArrayList<>();
            for (int i=0;i<idArr.length;i++){
                idList.add(Long.valueOf(idArr[i]));
            }
            List<User> userList=userService.queryUserInIds(idList);
            json = this.setJson(true, "查询成功", userList);
        } catch (Exception e) {
            logger.error("AdminUserController.queryUserByIds---error", e);
            json = this.setJson(false, "系统繁忙,请稍后再试", null);
        }
        return json;
    }

    /**
     * 根据id删除优惠券
     * @param request
     * @return
     */
    @RequestMapping("/ajax/deleteCouponById/{id}")
    @ResponseBody
    @SystemLog(type="del",operation="删除优惠券")
    public Object deleteCouponById(HttpServletRequest request, @ModelAttribute("id") Long id) {
        Map<String, Object> json = null;
        try {
            couponService.deleteCouponById(id);//同时 删除所有的 优惠券编码
            json = this.setJson(true, "删除成功", "");
        } catch (Exception e) {
            logger.error("AdminUserController.deleteCouponById---error", e);
            json = this.setJson(false, "系统繁忙,请稍后再试", null);
        }
        return json;
    }
}