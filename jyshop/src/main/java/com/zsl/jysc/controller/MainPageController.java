package com.zsl.jysc.controller;

import com.zsl.jysc.common.ServerResponse;
import com.zsl.jysc.common.annotation.VerifyToken;
import com.zsl.jysc.entity.Product;
import com.zsl.jysc.service.IMainPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "主页模块")
@RestController
public class MainPageController {

    @Autowired
    private IMainPageService mainPageService;

    //顶部搜索
    @VerifyToken
    @ApiOperation(value = "获取轮播图,数据库里面就存放一条数据即可，三张图片地址用英文逗号隔开")
    @PostMapping(value = "/bannerImages")
    public ServerResponse<String> bannerImages(@ApiParam(name = "token", value = "口令", required = true) String token) {
        String images = mainPageService.selectBannerImages();
        if (StringUtils.isNotBlank(images)) {
            return ServerResponse.createBySuccess(images);
        }
        return ServerResponse.createByError();
    }

    @VerifyToken
    @ApiOperation(value = "精品首发 + 剁手灵感")
    @PostMapping(value = "/secondImages")
    public ServerResponse<List<Product>> secondImages(@ApiParam(name = "token", value = "口令", required = true) String token) {
        List<Product> products = mainPageService.selectProductByStatus();
        if (products != null && products.size() > 0) {
            return ServerResponse.createBySuccess(products);
        }
        return ServerResponse.createByError();
    }

    @VerifyToken
    @ApiOperation(value = "LINE FRIENDS请求")
    @PostMapping(value = "/lineFriends")
    public ServerResponse<List<Product>> lineFriends(@ApiParam(name = "token", value = "口令", required = true) String token) {
        //商品数量比较少，一次全部取出
        List<Product> products = mainPageService.selectProductByStatusEq2();
        if (products != null && products.size() > 0) {
            return ServerResponse.createBySuccess(products);
        }
        return ServerResponse.createByError();
    }
}
