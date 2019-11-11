package com.inxedu.os.edu.controller.card;

import com.inxedu.os.common.controller.BaseController;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.util.SingletonLoginUtils;
import com.inxedu.os.edu.entity.card.CardCode;
import com.inxedu.os.edu.entity.card.CourseCardDTO;
import com.inxedu.os.edu.entity.card.QueryCardCode;
import com.inxedu.os.edu.service.card.CardCodeService;
import com.inxedu.os.edu.service.card.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
/**
 *
 * author www.inxedu.com
 */
@Controller
public class CardController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    private CardService cardService;
    @Autowired
    private CardCodeService cardCodeSerivce;

    // 绑定属性 封装参数
    @InitBinder("queryCardCode")
    public void initQueryCardCode(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("queryCardCode.");
    }

    // 绑定属性 封装参数
    @InitBinder("cardCode")
    public void initCardCode(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("cardCode.");
    }

    /**
     * 虚拟卡信息激活
     *
     * @param request
     * @param cardCode
     * @return
     */
    @RequestMapping("/course/activationcard/{type}")
    @ResponseBody
    public Map<String, Object> activationCard(HttpServletRequest request,
                                              @ModelAttribute("cardCode") CardCode cardCode,
                                              @PathVariable int type) {
        String msg = "";
        Map<String, Object> json = null;
        try {
            msg = cardCodeSerivce.activationCard(cardCode, Long.valueOf(SingletonLoginUtils.getLoginUserId(request)), type);
            json = this.setJson(true, "", msg);
        } catch (Exception e) {
            json = this.setJson(false, "参数错误", null);
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 个人中心我的课程卡
     */
    @RequestMapping("/uc/courseCard")
    public ModelAndView cardList(HttpServletRequest request,@ModelAttribute("page") PageEntity page) {
        ModelAndView modelAndView = new ModelAndView();
        QueryCardCode queryCardCode = new QueryCardCode();
        modelAndView.setViewName(getViewPath("/web/card/uc_courseCard")); // 课程卡信息
        try {
            queryCardCode.setUserId(Long.valueOf(SingletonLoginUtils.getLoginUserId(request)));
            queryCardCode.setType(1);
            List<CourseCardDTO> userCardCodeList = cardService.getCardListByCondtion(queryCardCode, page);
            modelAndView.addObject("userCardCodeList", userCardCodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView(this.setExceptionRequest(request, e));
        }
        return modelAndView;
    }

}