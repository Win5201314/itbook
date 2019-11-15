package com.zsl.jysc.controller.backup;

import com.zsl.jysc.service.IProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "商品模块")
@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

}
