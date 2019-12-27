package com.zsl.jysc.controller;

import com.zsl.jysc.common.ServerResponse;
import com.zsl.jysc.common.annotation.VerifyToken;
import com.zsl.jysc.common.controller.BaseController;
import com.zsl.jysc.common.page.TableDataInfo;
import com.zsl.jysc.entity.ShopCar;
import com.zsl.jysc.exception.VerifyTokenException;
import com.zsl.jysc.service.IShopCarService;
import com.zsl.jysc.service.IUserService;
import com.zsl.jysc.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value = "购物车模块")
@RestController
@CrossOrigin
public class ShopCarController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IShopCarService shopCarService;

    @ApiOperation(value = "添加新购物车")
    @PostMapping("/addNewShopCar")
    public ServerResponse<String> addNewShopCar(@ApiParam(name = "shopCar", value = "购物车", required = true) ShopCar shopCar,
                                                @ApiParam(name = "token", value = "口令", required = true) String token) throws VerifyTokenException {
        Map<String, Object> map = JWTUtil.verifyToken(token);
        if (map == null) throw new VerifyTokenException("令牌验证失败");
        Long userId = (Long) map.get("userId");
        String openid = (String) map.get("openid");
        if (userService.isExistOpenidAndUserId(openid, userId)) {
            shopCar.setUserId(userId);
            if (shopCarService.addShopCar(shopCar)) {
                return ServerResponse.createBySuccess("添加成功");
            } else {
                return ServerResponse.createByErrorMessage("添加失败，请重试");
            }
        } else {
            throw new VerifyTokenException("令牌验证失败");
        }
    }

    @ApiOperation(value = "查询购物车")
    @PostMapping("/selectShopCar")
    public ServerResponse<TableDataInfo> selectShopCar(@ApiParam(name = "token", value = "口令", required = true) String token,
                                                @ApiParam(name = "pageNum", value = "当前记录起始索引,前端要自己记住再传递过来", required = true) Integer pageNum,
                                                @ApiParam(name = "pageSize", value = "每页显示记录数,默认10条", required = false, defaultValue = "10") Integer pageSize,
                                                @ApiParam(name = "isAsc", value = "降序", required = false, defaultValue = "desc") String isAsc,
                                                @ApiParam(name = "orderByColumn", value = "根据主键", required = false, defaultValue = "id") String orderByColumn) throws VerifyTokenException {
        Map<String, Object> map = JWTUtil.verifyToken(token);
        if (map == null) throw new VerifyTokenException("令牌验证失败");
        Long userId = (Long) map.get("userId");
        String openid = (String) map.get("openid");
        if (userService.isExistOpenidAndUserId(openid, userId)) {
            //分页查询 根据userId查询
            //设置分页信息
            startPage();
            //查询数据
            List<ShopCar> list = shopCarService.selectShopCarDesc();
            //包装查询的数据
            TableDataInfo tableDataInfo = getDataTable(list);
            return ServerResponse.createBySuccess(tableDataInfo);
        } else {
            throw new VerifyTokenException("令牌验证失败");
        }
    }

    @ApiOperation(value = "删除购物车")
    @PostMapping("/deleteShopCar")
    @VerifyToken
    public ServerResponse<String> deleteShopCar(@ApiParam(name = "token", value = "口令", required = true) String token,
                                                @ApiParam(name = "shopCarId", value = "购物车主键", required = true) Long shopCarId) {
        if (shopCarService.deleteShopCarById(shopCarId)) {
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByError();
        }
    }

    /**
     * 一般更新的是商品的数量
     */
    @ApiOperation(value = "更新购物车")
    @PostMapping("/updateShopCar")
    @VerifyToken
    public ServerResponse<String> updateShopCar(@ApiParam(name = "token", value = "口令", required = true) String token,
                                                @ApiParam(name = "shopCar", value = "购物车", required = true) ShopCar shopCar) {
        if (shopCarService.updateShopCar(shopCar)) {
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByError();
        }
    }
}
