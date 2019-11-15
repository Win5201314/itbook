package com.zsl.jysc.controller.backup;

import com.zsl.jysc.common.ServerResponse;
import com.zsl.jysc.common.annotation.VerifyAdminToken;
import com.zsl.jysc.entity.Provider;
import com.zsl.jysc.service.IProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 供应商模块的部分功能，批量处理等等就不写了
 */
@Api(value = "供应商模块")
@RestController
public class ProviderController {

    @Autowired
    private IProviderService providerService;

    @ApiOperation(value = "添加新供应商")
    @VerifyAdminToken
    @PostMapping("/addNewProvider")
    public ServerResponse<String> addNewProvider(@ApiParam(name = "provider", value = "供应商对象", required = true) Provider provider,
                                                 @ApiParam(name = "token", value = "口令", required = true) String token) {
        //营业执照号唯一，防止重复添加
        if (providerService.isExitBusinessNumber(provider.getBusinessNumber())) {
            if (providerService.addNewProvider(provider)) {
                return ServerResponse.createBySuccess("添加成功");
            } else {
                return ServerResponse.createByErrorMessage("添加失败，请重试");
            }
        } else {
            return ServerResponse.createByErrorMessage("之前添加过!");
        }
    }

    @ApiOperation(value = "查询供应商")
    @VerifyAdminToken
    @PostMapping("/selectProviderByBusinessNumber")
    public ServerResponse<Provider> selectProviderByBusinessNumber(@ApiParam(name = "token", value = "口令", required = true) String token,
                                                                   @ApiParam(name = "businessNumber", value = "营业执照号", required = true) String businessNumber) {
        Provider provider = providerService.selectProviderByBusinessNumber(businessNumber);
        if (provider != null) {
            return ServerResponse.createBySuccess(provider);
        } else {
            return ServerResponse.createByErrorMessage("没有符合的数据");
        }
    }

    @ApiOperation(value = "删除供应商")
    @VerifyAdminToken
    @PostMapping("/deleteProvider")
    public ServerResponse<String> deleteProvider(@ApiParam(name = "token", value = "口令", required = true) String token,
                                                 @ApiParam(name = "providerId", value = "供应商主键", required = true) Long providerId) {
        if (providerService.deleteProvider(providerId)) {
            return ServerResponse.createBySuccess("删除成功");
        } else {
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @ApiOperation(value = "更新供应商")
    @VerifyAdminToken
    @PostMapping("/updateProvider")
    public ServerResponse<String> updateProvider(@ApiParam(name = "token", value = "口令", required = true) String token,
                                                @ApiParam(name = "provider", value = "地址对象", required = true) Provider provider) {
        if (providerService.updateProvider(provider)) {
            return ServerResponse.createBySuccess("更新成功");
        } else {
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }
}
