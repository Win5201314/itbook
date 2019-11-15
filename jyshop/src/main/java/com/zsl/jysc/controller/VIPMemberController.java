package com.zsl.jysc.controller;

import com.zsl.jysc.common.ServerResponse;
import com.zsl.jysc.entity.VIPMember;
import com.zsl.jysc.exception.VerifyTokenException;
import com.zsl.jysc.service.IUserService;
import com.zsl.jysc.service.IVIPMemberService;
import com.zsl.jysc.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(value = "会员模块")
@RestController
public class VIPMemberController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IVIPMemberService memberService;

    @ApiOperation(value = "添加新会员")
    @PostMapping(value = "/addNewVIPMember")
    public ServerResponse<String> addNewVIPMember(@ApiParam(name = "member", value = "会员对象", required = true) VIPMember member,
                                                  @ApiParam(name = "token", value = "口令", required = true) String token) throws VerifyTokenException {
        Map<String, Object> map = JWTUtil.verifyToken(token);
        if (map == null) throw new VerifyTokenException("令牌验证失败");
        Long userId = (Long) map.get("userId");
        String openid = (String) map.get("openid");
        if (userService.isExistOpenidAndUserId(openid, userId)) {
            member.setUserId(userId);
            if (memberService.addNewVIPMember(member)) {
                return ServerResponse.createBySuccess("添加成功");
            } else {
                return ServerResponse.createByErrorMessage("添加失败，请重试");
            }
        } else {
            throw new VerifyTokenException("令牌验证失败");
        }
    }
    /**
     * 后台管理会员模块暂时不写
     */
}
