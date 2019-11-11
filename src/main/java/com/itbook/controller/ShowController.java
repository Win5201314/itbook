package com.itbook.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "展示各个页面的")
@Controller
public class ShowController {

    private static Logger logger = Logger.getLogger(ShowController.class);

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    /**
     * 展示主页
     * @return
     */
    @ApiOperation(value = "展示主页", notes = "展示主页面")
    @RequestMapping("/index")
    public String showIndex() {
        logger.debug("--------------------------------------------------");
        int i = 0;
        i++;
        System.out.println("------------------" + i);
        return "index";
    }

}
