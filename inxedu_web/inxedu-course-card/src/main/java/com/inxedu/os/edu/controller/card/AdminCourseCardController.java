package com.inxedu.os.edu.controller.card;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.sysLog.SystemLog;
import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.FileExportImportUtil;
import com.inxedu.os.common.util.MD5;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.constants.enums.CardStatus;
import com.inxedu.os.edu.entity.card.*;
import com.inxedu.os.edu.entity.system.SysUser;
import com.inxedu.os.edu.entity.user.User;
import com.inxedu.os.edu.service.card.CardCodeService;
import com.inxedu.os.edu.service.card.CardService;
import com.inxedu.os.edu.service.user.UserService;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *课程卡信息管理
 * author www.inxedu.com
 */
@Controller
@RequestMapping("/admin")
public class AdminCourseCardController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(AdminCourseCardController.class);

    @Autowired
    private CardService cardService;
    @Autowired
    private CardCodeService cardCodeService;
    @Autowired
    private UserService userService;

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("card")
    public void initBinderUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("card.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryCardCode")
    public void initBinderCardCode(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCardCode.");
    }

    // 绑定变量名字和属性，参数封装进类
    @InitBinder("queryMainCard")
    public void initBinderMainCard(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryMainCard.");
    }

    /**
     * 页面跳转 添加页面
     *
     * @return
     */
    @RequestMapping("/card/tocreatecard")
    public ModelAndView toCreateCard(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getViewPath("/admin/card/card_create"));// 创建课程卡页面
        modelAndView.addObject("addType", request.getParameter("addtype"));
        return modelAndView;
    }

    /**
     * 创建课程卡
     * @return
     */
    @RequestMapping("/card/createcard")
    @SystemLog(type="add",operation="创建课程卡")
    public String createCourseCard(HttpServletRequest request,@ModelAttribute("card") Card card) {
        try {
            String courseIds = request.getParameter("courseIds");
            SysUser user = SingletonLoginUtils.getLoginSysUser(request);
            card.setCreateTime(new Date());
            card.setCreateUser(user.getUserName().toString());
            card.setStatus(1);
            // 创建课程卡操作
            cardService.saveCourseCardInfo(card, courseIds);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createCourseCard" + e);
        }
        return "redirect:/admin/card/mainlist?queryMainCard.type="+card.getType(); // 重定向
    }
    /**
     * 创建充值卡
     * @return
     */
    @RequestMapping("/card/createRechargecard")
    @SystemLog(type="add",operation="创建充值卡")
    public String createRechargecard(HttpServletRequest request,@ModelAttribute("card") Card card) {
        try {
            SysUser user = SingletonLoginUtils.getLoginSysUser(request);
            card.setCreateTime(new Date());
            card.setCreateUser(user.getUserName().toString());
            card.setStatus(2);
            cardService.addCard(card);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createRechargecard" + e);
        }
        return "redirect:/admin/card/mainlist?queryMainCard.type=2"; // 重定向
    }
    /**
     * 课程卡列表
     *
     * @return
     */
    @RequestMapping("/card/cardlist")
    public ModelAndView courseCardList(HttpServletRequest reqeust,
                                       @ModelAttribute("page") PageEntity page,
                                       @ModelAttribute QueryCardCode queryCardCode) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getViewPath("/admin/card/admin_card_list"));// 课程卡列表
        try {
            // 设置分页 ，默认每页10
            List<CourseCardDTO> cardCodeList = cardService.getCardListByCondtion(queryCardCode, page);
            modelAndView.addObject("cardCodeList", cardCodeList);
            modelAndView.addObject("page", page);

            Card card=cardService.getCardById(queryCardCode.getCardId());
            modelAndView.addObject("card", card);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("CardMainAction.saveCourseCardInfo", e);

        }
        return modelAndView;
    }

    /**
     * 通过主卡作废详卡表信息
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/card/closemaincard/{id}")
    public Map<String, Object> closeMainCard(HttpServletRequest request,
                                             @PathVariable("id") Long id) {
        Map<String, Object> json = null;
        try {
            cardCodeService.closeMainCard(id);
            json = this.setJson(true, "true", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 课程卡主卡信息列表
     *
     * @param request
     * @param page
     * @param queryMainCard
     * @return
     */
    @RequestMapping("/card/mainlist")
    public ModelAndView courseCardMainList(HttpServletRequest request,
                                           @ModelAttribute("page") PageEntity page,
                                           @ModelAttribute QueryMainCard queryMainCard) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getViewPath("/admin/card/main_card_list"));
        try {

            List<MainCardDTO> mainCardList = cardService.getMianListByCondition(queryMainCard, page);
            for (MainCardDTO mainCardDTO:mainCardList){
                int usedCardCode=0;//已经使用的课程卡编码数量
                QueryCardCode queryCardCode = new QueryCardCode();
                queryCardCode.setCardId(mainCardDTO.getId());
                /*查询课程卡编码集合*/
                List<CourseCardDTO> cardCodeList = cardService.getAllCardHistoryCondition(queryCardCode);

                for (CourseCardDTO courseCardDTO:cardCodeList){
                    if ("USED".equals(courseCardDTO.getStatus())){
                        usedCardCode += 1;
                    }
                }
                mainCardDTO.setUsedCardCode(usedCardCode);
            }

            modelAndView.addObject("mainCardList", mainCardList);
            modelAndView.addObject("queryMainCard", queryMainCard);
            modelAndView.addObject("pagew", page);
        } catch (Exception e) {
            logger.error("courseCardMainList", e);
        }
        return modelAndView;
    }

    /**
     * 课程卡状态修改
     *
     * @param request
     * @return
     */
    @RequestMapping("/card/closecard")
    @ResponseBody
    @SystemLog(type="update",operation="课程卡状态修改（关闭）")
    public Map<String, Object> updateCardCode(HttpServletRequest request) {
        Map<String, Object> json = null;
        try {
            Long cardCodeId = new Long(request.getParameter("id"));
            String status = request.getParameter("status");

            CardCode cardCode = cardCodeService.getCardCodeById(cardCodeId);
            if ("CLOSE".equals(status)){
                cardCode.setStatus(CardStatus.CLOSE.toString());
            }else if ("INIT".equals(status)){
                cardCode.setStatus(CardStatus.INIT.toString());
            }
            cardCodeService.updateCardCode(cardCode);

            //如果是学员卡 冻结用户
            Card card=cardService.getCardById(cardCode.getCardId());
            if(card.getType()==3){
                User user = userService.getLoginUser(cardCode.getCardCode(), MD5.getMD5(cardCode.getCardCodePassword()));
                if ("CLOSE".equals(status)){
                    user.setIsavalible(2);
                }else if ("INIT".equals(status)){
                    user.setIsavalible(1);
                }
                userService.updateUserStates(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 课程卡信息导出
     *
     * @param request
     * @param response
     * @param queryCardCode
     */
    @RequestMapping("/card/exportCard")
    public void exportCard(HttpServletRequest request, HttpServletResponse response,
                           @ModelAttribute QueryCardCode queryCardCode) {
        try {
            // 指定文件生成路径
            String dir = request.getSession().getServletContext().getRealPath("/")+"/excelfile/order";
            // 文件名
            String expName = "充值卡_" + DateUtils.getStringDateShort();
            // 表头信息
            String[] headName = {"ID", "名称", "类型", "金额", "有效开始时间", "有效结束时间", "卡号", "密码", "状态", "订单号", "创建时间", "创建人"};
            // 拆分为一万条数据每Excel，防止内存使用太大
            PageEntity page = new PageEntity();
            page.setPageSize(10000);
            cardService.getCardListByCondtion(queryCardCode, page);
            int num = page.getTotalPageSize();// 总页数
            List<File> srcfile = new ArrayList<File>();// 生成的excel的文件的list
            for (int i = 1; i <= num; i++) {// 循环生成num个xls文件
                page.setCurrentPage(i);
                List<CourseCardDTO> cardCodeList = cardService.getCardListByCondtion(queryCardCode, page);
                List<List<String>> list = cardJoint(cardCodeList);
                File file = FileExportImportUtil.createExcel(headName, list, expName + "_" + i, dir);
                srcfile.add(file);
            }
            FileExportImportUtil.createRar(response, dir, srcfile, expName);// 生成的多excel的压缩包
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 卡编码excel格式拼接
     *
     * @param cardCodeList
     * @return
     */
    public List<List<String>> cardJoint(List<CourseCardDTO> cardCodeList) {
        List<List<String>> list = new ArrayList<List<String>>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < cardCodeList.size(); i++) {
            List<String> small = new ArrayList<String>();
            small.add(cardCodeList.get(i).getId() + "");
            small.add(cardCodeList.get(i).getName());
            if (cardCodeList.get(i).getType() == 1) {
                small.add("课程卡");
            } else if (cardCodeList.get(i).getType() == 2) {
                small.add("充值卡");
            } else if (cardCodeList.get(i).getType() == 3) {
                small.add("学员卡");
            }

            small.add(cardCodeList.get(i).getMoney() + "");
            if (cardCodeList.get(i).getType() == 1 || cardCodeList.get(i).getType() == 2) {
                small.add(format.format(cardCodeList.get(i).getBeginTime()));
                small.add(format.format(cardCodeList.get(i).getEndTime()));
            } else {
                small.add("~");
                small.add("~");
            }
            small.add(cardCodeList.get(i).getCardCode());
            small.add(cardCodeList.get(i).getCardCodePassword());
            if (cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.INIT.toString())) {
                small.add("未使用");
            }
            if (cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.USED.toString())) {
                small.add("已使用");
            }
            if (cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.OVERDUE.toString())) {
                small.add("已过期");
            }
            if (cardCodeList.get(i).getStatus().equalsIgnoreCase(CardStatus.CLOSE.toString())) {
                small.add("已关闭");
            }
            small.add(cardCodeList.get(i).getRequestId());
            small.add(format.format(cardCodeList.get(i).getCreateTime()));
            small.add(cardCodeList.get(i).getCreateUser());

            list.add(small);
        }
        return list;
    }
    /**
     * 页面学员卡添加
     */
    @RequestMapping("/card/tocreatUserecard")
    public String toCreateUserCard(HttpServletRequest request) {
        return getViewPath("/admin/card/card_user_create");// 创建学员卡
    }
    /**
     * 创建充值卡
     */
    @RequestMapping("/card/toCreateRechargecard")
    public String toCreateRechargecard(HttpServletRequest request) {
        return getViewPath("/admin/card/rechargecard_create");// 创建充值卡
    }
    /**
     * 创建学员卡
     *
     * @return
     */
    @RequestMapping("/card/createUsercard")
    @ResponseBody
    @SystemLog(type="add",operation="创建学员卡")
    public Map<String, Object> createCourseUserCard(HttpServletRequest request,
                                                    @ModelAttribute("card") Card card) {
        Map<String, Object> json = null;
        try {
            if (card.getLoginAccountPrefix() == null || card.getLoginAccountPrefix().trim().length() <= 0) {
                json = setJson(false, "请填写学员卡登录账号前缀", null);
                return json;
            }
            boolean isok = userService.checkLoginAccount(card.getLoginAccountPrefix().trim());
            if (isok == false) {
                json = this.setJson(false, "学员卡登录账号前缀已经存在", null);
                return json;
            }
            String courseIds = request.getParameter("courseIds");
            SysUser sysUser = SingletonLoginUtils.getLoginSysUser(request);
            card.setCreateTime(new Date());
            card.setCreateUser(sysUser.getUserName().toString());
            // 创建学员卡操作
            cardService.addCourseUserCard(card, courseIds);
            json = this.setJson(true, "添加成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("createCourseUserCard" + e);
            json = this.setJson(false, "系统出错，操作失败", null);
        }
        return json;
    }
}
