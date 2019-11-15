package com.zsl.jysc.controller.backup;

import com.zsl.jysc.common.ServerResponse;
import com.zsl.jysc.common.annotation.VerifyAdminToken;
import com.zsl.jysc.entity.Product;
import com.zsl.jysc.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "商品模块")
@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 图片上传到阿里云
     */
    @ApiOperation(value = "添加商品，批量处理先不写")
    @VerifyAdminToken
    @PostMapping(value = "/addNewProduct")
    public ServerResponse<String> addNewProduct(@ApiParam(name = "token", value = "口令", required = true) String token,
                                                @ApiParam(name = "product", value = "商品对象", required = true) Product product) {
        if (productService.addNewProduct(product)) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    /**
     * 查询，删除，更新 暂时不写了，可以参照供应商写法，懒得写了
     */

}
